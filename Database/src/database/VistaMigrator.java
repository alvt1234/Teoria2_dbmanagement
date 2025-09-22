package database;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.*;

public class VistaMigrator {

    private static final Logger logger = Logger.getLogger(VistaMigrator.class.getName());

    public VistaMigrator(Connection conexionMariaDB, Connection conexionPostgreSQL) throws SQLException {
        migrarVistas(conexionMariaDB, conexionPostgreSQL);
    }

    // Recorre las vistas de MariaDB y las crea en Postgres
    private void migrarVistas(Connection mariaDB, Connection postgres) throws SQLException {
        DatabaseMetaData meta = mariaDB.getMetaData();
        ResultSet vistas = meta.getTables(null, null, "%", new String[]{"VIEW"});

        Map<String, VistaInfo> vistasInfo = new HashMap<>();
        List<String> nombresVistas = new ArrayList<>();

        while (vistas.next()) {
            String nombreVista = vistas.getString("TABLE_NAME");
            nombresVistas.add(nombreVista);

            String ddlOriginal = obtenerDefinicionVista(mariaDB, nombreVista);
            if (ddlOriginal != null && !ddlOriginal.trim().isEmpty()) {
                String ddlConvertido = convertirSintaxisVista(ddlOriginal, nombreVista);
                Set<String> dependencias = extraerDependencias(ddlConvertido);

                vistasInfo.put(nombreVista, new VistaInfo(ddlConvertido, dependencias));

                if (!dependencias.isEmpty()) {
                    System.out.println("  Vista '" + nombreVista + "' depende de: " + dependencias);
                } else {
                    System.out.println("  Vista '" + nombreVista + "' no tiene dependencias");
                }
            }
        }
        vistas.close();

        System.out.println("Total de vistas encontradas: " + vistasInfo.size());

        List<String> ordenEjecucion = ordenarPorDependencias(vistasInfo);
        System.out.println("Orden de ejecución: " + ordenEjecucion);

        int contadorVistas = 0;
        for (String nombreVista : ordenEjecucion) {
            VistaInfo info = vistasInfo.get(nombreVista);
            try {
                ejecutarEnPostgreSQL(postgres, info.ddl, nombreVista);
                contadorVistas++;
            } catch (SQLException e) {
                System.err.println("Error con vista '" + nombreVista + "': " + e.getMessage());
                logger.log(Level.WARNING, "Error en vista " + nombreVista, e);
            }
        }

        System.out.println("Total de vistas migradas: " + contadorVistas);
    }

    // Info de cada vista
    private static class VistaInfo {
        String ddl;
        Set<String> dependencias;

        VistaInfo(String ddl, Set<String> dependencias) {
            this.ddl = ddl;
            this.dependencias = dependencias;
        }
    }

    // Busca dependencias en el SQL de la vista
    private Set<String> extraerDependencias(String ddl) {
        Set<String> dependencias = new HashSet<>();
        String ddlLower = ddl.toLowerCase();

        String patronFrom = "\\bfrom\\s+([a-zA-Z_][a-zA-Z0-9_]*)";
        String patronJoin = "\\bjoin\\s+([a-zA-Z_][a-zA-Z0-9_]*)";

        java.util.regex.Pattern patternFrom = java.util.regex.Pattern.compile(patronFrom);
        java.util.regex.Pattern patternJoin = java.util.regex.Pattern.compile(patronJoin);

        java.util.regex.Matcher matcherFrom = patternFrom.matcher(ddlLower);
        java.util.regex.Matcher matcherJoin = patternJoin.matcher(ddlLower);

        while (matcherFrom.find()) {
            String tabla = matcherFrom.group(1);
            if (tabla != null && !tabla.isEmpty()) dependencias.add(tabla);
        }
        while (matcherJoin.find()) {
            String tabla = matcherJoin.group(1);
            if (tabla != null && !tabla.isEmpty()) dependencias.add(tabla);
        }

        // Revisa subconsultas dentro de paréntesis
        int inicioParentesis = 0;
        int finParentesis = 0;
        while ((inicioParentesis = ddlLower.indexOf('(', finParentesis)) != -1) {
            finParentesis = ddlLower.indexOf(')', inicioParentesis);
            if (finParentesis != -1) {
                String subconsulta = ddlLower.substring(inicioParentesis + 1, finParentesis);
                dependencias.addAll(extraerDependencias(subconsulta));
            } else {
                break;
            }
        }
        return dependencias;
    }

    // Ordena las vistas según dependencias
    private List<String> ordenarPorDependencias(Map<String, VistaInfo> vistasInfo) {
        List<String> ordenado = new ArrayList<>();
        Set<String> visitado = new HashSet<>();
        Set<String> visitando = new HashSet<>();

        for (String nombreVista : vistasInfo.keySet()) {
            if (!visitado.contains(nombreVista)) {
                ordenarPorDependenciasDFS(nombreVista, vistasInfo, visitado, visitando, ordenado);
            }
        }
        Collections.reverse(ordenado);
        return ordenado;
    }

    private void ordenarPorDependenciasDFS(String vista, Map<String, VistaInfo> vistasInfo,
                                          Set<String> visitado, Set<String> visitando, List<String> ordenado) {
        if (visitando.contains(vista)) {
            throw new RuntimeException("Ciclo de dependencias detectado en vista: " + vista);
        }
        if (visitado.contains(vista)) return;

        visitando.add(vista);
        VistaInfo info = vistasInfo.get(vista);
        if (info != null) {
            for (String dependencia : info.dependencias) {
                if (vistasInfo.containsKey(dependencia)) {
                    ordenarPorDependenciasDFS(dependencia, vistasInfo, visitado, visitando, ordenado);
                }
            }
        }
        visitando.remove(vista);
        visitado.add(vista);
        ordenado.add(vista);
    }

    // Obtiene el DDL de una vista en MariaDB
    private String obtenerDefinicionVista(Connection conexion, String nombreVista) throws SQLException {
        String query = "SHOW CREATE VIEW " + nombreVista;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (SQLException e) {
            throw new SQLException("No se pudo obtener la definición de la vista: " + nombreVista, e);
        }
        return null;
    }

    // Ajusta la sintaxis de la vista para Postgres
    private String convertirSintaxisVista(String ddlVista, String nombreVista) {
        if (ddlVista == null || ddlVista.trim().isEmpty()) {
            throw new IllegalArgumentException("Definición de vista vacía");
        }

        String ddl = ddlVista.trim();
        ddl = ddl.replace("`", "");
        ddl = ddl.replaceAll("(?i)DEFINER\\s*=\\s*[^\\s]+\\s+", "");
        ddl = ddl.replaceAll("(?i)SQL SECURITY\\s+(DEFINER|INVOKER)\\s+", "");
        ddl = ddl.replaceAll("(?i)ALGORITHM\\s*=\\s*(UNDEFINED|MERGE|TEMPTABLE)\\s+", "");

        ddl = ddl.replaceAll("(?i)\\bTINYINT\\s*\\(\\s*1\\s*\\)", "BOOLEAN");
        ddl = ddl.replaceAll("(?i)\\bDATETIME\\b", "TIMESTAMP");
        ddl = ddl.replaceAll("(?i)\\bTINYINT\\s*\\(\\s*\\d+\\s*\\)", "SMALLINT");
        ddl = ddl.replaceAll("(?i)\\bMEDIUMINT\\s*\\(\\s*\\d+\\s*\\)", "INTEGER");
        ddl = ddl.replaceAll("(?i)\\bBIGINT\\s*\\(\\s*\\d+\\s*\\)", "BIGINT");

        ddl = ddl.replaceAll("/\\*!\\d+\\s+[^\\*]+\\*/", "");
        ddl = ddl.replaceAll("\\s+", " ");
        if (!ddl.endsWith(";")) ddl += ";";

        return ddl;
    }

    // Ejecuta el SQL de creación en Postgres
    private void ejecutarEnPostgreSQL(Connection conexion, String sentenciaSQL, String nombreVista) throws SQLException {
        try (Statement stmt = conexion.createStatement()) {
            try {
                stmt.executeUpdate("DROP VIEW IF EXISTS " + nombreVista + " CASCADE");
            } catch (SQLException e) {
                if (!e.getMessage().contains("does not exist")) throw e;
            }
            stmt.executeUpdate(sentenciaSQL);
            System.out.println("Vista '" + nombreVista + "' migrada correctamente");
        } catch (SQLException e) {
            throw new SQLException("Error ejecutando vista '" + nombreVista + "': " + sentenciaSQL, e);
        }
    }
}

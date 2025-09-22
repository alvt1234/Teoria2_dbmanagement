package database;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public final class Conexiones {

    private static final Path RUTA = Paths.get(
            //esto lo que hace es que guarda las conexiones en un archivo para luego poder cargarlas
            System.getProperty("user.home"), ".dbtool", "conexiones.properties");

    public static Map<String, usuario> cargar() {
        Map<String, usuario> map = new LinkedHashMap<>();
        Properties p = new Properties();
        try {
            if (Files.exists(RUTA)) {
                try (InputStream in = Files.newInputStream(RUTA)) {
                    p.load(in);
                }
                int n = Integer.parseInt(p.getProperty("count", "0"));
                for (int i = 0; i < n; i++) {
                    usuario us = new usuario();
                    us.nombre = p.getProperty("p." + i + ".nombre", "perfil-" + i);
                    us.host = p.getProperty("p." + i + ".host", "localhost");
                    us.port = Integer.parseInt(p.getProperty("p." + i + ".puerto", "3306"));
                    us.database = p.getProperty("p." + i + ".database", "");
                    us.username = p.getProperty("p." + i + ".user", "");
                    us.guardarPassword = Boolean.parseBoolean(p.getProperty("p." + i + ".savepw", "false"));
                    us.password = us.guardarPassword ? p.getProperty("p." + i + ".password", "") : "";
                    map.put(us.nombre, us);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public static void guardar(Collection<usuario> perfiles) {
        try {
            Files.createDirectories(RUTA.getParent());
            Properties p = new Properties();
            p.setProperty("count", String.valueOf(perfiles.size()));
            int i = 0;
            for (usuario pc : perfiles) {
                p.setProperty("p." + i + ".nombre", pc.nombre);
                p.setProperty("p." + i + ".host", pc.host);
                p.setProperty("p." + i + ".puerto", String.valueOf(pc.port));
                p.setProperty("p." + i + ".database", pc.database);
                p.setProperty("p." + i + ".user", pc.username);
                p.setProperty("p." + i + ".savepw", String.valueOf(pc.guardarPassword));
                if (pc.guardarPassword) {
                    p.setProperty("p." + i + ".password", pc.password == null ? "" : pc.password);
                }
                i++;
            }
            try (OutputStream out = Files.newOutputStream(RUTA)) {
                p.store(out, "Perfiles de conexion");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

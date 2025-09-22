
package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class barratareas {

    public barratareas() {
    }
    
    
    public void inicializarbarratareas(JPanel panel){
          JMenu archivo = new JMenu("Archivo");
        JMenu editar = new JMenu("Editar");
        JMenu navegar = new JMenu("Navegar");
        JMenu buscar = new JMenu("Buscar");
        JMenu editorSQL = new JMenu("Editor SQL");
        JMenu baseDatos = new JMenu("Base de Datos");
        JMenu ventana = new JMenu("Ventana");
        JMenu ayuda = new JMenu("Ayuda");
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.darkGray);
        menuBar.add(archivo);
        menuBar.add(editar);
        menuBar.add(navegar);
        menuBar.add(buscar);
        menuBar.add(editorSQL);
        menuBar.add(baseDatos);
        menuBar.add(ventana);
        menuBar.add(ayuda);
        panel.add(menuBar, BorderLayout.NORTH);
        archivo.setForeground(Color.WHITE);
        editar.setForeground(Color.WHITE);
        navegar.setForeground(Color.WHITE);
        buscar.setForeground(Color.WHITE);
        editorSQL.setForeground(Color.WHITE);
        baseDatos.setForeground(Color.WHITE);
        ventana.setForeground(Color.WHITE);
        ayuda.setForeground(Color.WHITE);
    }
    
        public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        java.sql.ResultSetMetaData metaData = rs.getMetaData();

        // Nombres de columnas
        java.util.Vector<String> columnNames = new java.util.Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnLabel(i));
        }

        // Datos
        java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();
        while (rs.next()) {
            java.util.Vector<Object> vector = new java.util.Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                vector.add(rs.getObject(i));
            }
            data.add(vector);
        }

        return new javax.swing.table.DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
}

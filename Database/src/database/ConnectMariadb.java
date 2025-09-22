package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMariadb {

    public Connection conectar(String host, int puerto, String database, String user, String password) throws SQLException {
        String url = String.format("jdbc:mariadb://%s:%d/%s", host, puerto, database);
        return DriverManager.getConnection(url, user, password);
    }

    public boolean probarConexion(String host, int puerto, String database, String user, String password) {
        try (Connection conn = conectar(host, puerto, database, user, password)) {
            return true;
        } catch (SQLException e) {
            System.err.println("Error conectando: " + e.getMessage());
            return false;
        }
    }
    public static Connection getConnection(String host, String puerto, String db, String user, String pass) throws SQLException {
    String url = "jdbc:mariadb://" + host + ":" + puerto + "/" + db;
    return DriverManager.getConnection(url, user, pass);
    
    
}

}

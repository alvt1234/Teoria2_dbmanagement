package database;

import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.Connection;

public class usuario {

    public String nombre;
    public String host;
    public int port;
    public String database;
    public String username;
    public String password;
    public boolean guardarPassword;

    public usuario() {
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String displayKey() {
        return nombre + "@" + host + ":" + port + " (" + username + ")";
    }

}

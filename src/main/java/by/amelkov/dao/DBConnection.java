package by.amelkov.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    String host = "jdbc:postgresql://localhost:5432/notes";
    String username = "postgres";
    String password = "admin";
    String driver = "org.postgresql.Driver";

    private Connection connection;

    public void createConnection() throws IOException, ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(host, username, password);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}

package co.edu.cue.escolarvote.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresConnection {
    private static String url = "jdbc:postgresql://localhost:5432/school_vote?currentSchema=school_vote";
    private static String user = "postgres";
    private static String password = "123";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}


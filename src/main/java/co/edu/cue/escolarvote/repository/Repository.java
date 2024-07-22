package co.edu.cue.escolarvote.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> list();
    T byId(Long id);
    void save(T t);
    void delete(Long id);
    void update(Long id,T t);
    static Connection getConnection() throws SQLException {
        return PostgresConnection.getInstance();
    }
}

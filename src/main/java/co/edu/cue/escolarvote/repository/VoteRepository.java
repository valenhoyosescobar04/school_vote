package co.edu.cue.escolarvote.repository;

import co.edu.cue.escolarvote.domain.entities.Vote;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VoteRepository {
    static Connection getConnection() throws SQLException {
        return PostgresConnection.getInstance();
    }

    List<Vote> list();
    void save(Vote t);
    void deleteAll();

    void deleteAllFromCandidate(Long candidate_id);
}

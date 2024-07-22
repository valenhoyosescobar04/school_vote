package co.edu.cue.escolarvote.repository.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepository implements Repository<Candidate> {

    private Candidate createCandidate(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setGrade(resultSet.getString("grade"));
        candidate.setProfileImage(resultSet.getBytes("profile_image"));
        candidate.setStudyingDay(StudyingDay.valueOf(resultSet.getString("studying_day")));
        return candidate;
    }

    @Override
    public List<Candidate> list() {
        List<Candidate> candidates = new ArrayList<>();
        try (Statement statement= Repository.getConnection().createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM candidate")
        ){
            while (resultSet.next()) {
                Candidate candidate = createCandidate(resultSet);
                candidates.add(candidate);
            }
        }catch (SQLException e) {
            return candidates;
        }
        return candidates;
    }

    @Override
    public Candidate byId(Long id) {
        Candidate candidate = null;
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("SELECT * FROM candidate where id=?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                candidate = createCandidate(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return candidate;
    }

    @Override
    public void save(Candidate candidate) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("INSERT INTO candidate(name,grade,profile_image,studying_day) VALUES (?,?,?,?)")){
            preparedStatement.setString(1,candidate.getName());
            preparedStatement.setString(2,candidate.getGrade());
            preparedStatement.setBytes(3,candidate.getProfileImage());
            preparedStatement.setString(4,candidate.getStudyingDay().toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("DELETE FROM candidate WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Candidate candidate) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("UPDATE candidate SET name=? ,grade=?,profile_image=?, studying_day=?  where id=?")){
            preparedStatement.setString(1,candidate.getName());
            preparedStatement.setString(2,candidate.getGrade());
            preparedStatement.setBytes(3,candidate.getProfileImage());
            preparedStatement.setString(4,candidate.getStudyingDay().toString());
            preparedStatement.setLong(5,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

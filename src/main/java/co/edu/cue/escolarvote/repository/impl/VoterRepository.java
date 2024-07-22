package co.edu.cue.escolarvote.repository.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.repository.PostgresConnection;
import co.edu.cue.escolarvote.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoterRepository implements Repository<Voter> {

    private Voter createVoter(ResultSet resultSet) throws SQLException{
        Voter voter = new Voter();
        voter.setId(resultSet.getLong("id"));
        voter.setNid(resultSet.getString("nid"));
        voter.setStudyingDay(StudyingDay.valueOf(resultSet.getString("studying_day")));
        return voter;
    }

    @Override
    public List<Voter> list() {
        List<Voter> voters = new ArrayList<>();
        try (Statement statement= Repository.getConnection().createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM voter")
        ){
            while (resultSet.next()) {
                Voter voter = createVoter(resultSet);
                voters.add(voter);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return voters;
    }

    @Override
    public Voter byId(Long id) {
        Voter voter = null;
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("SELECT * FROM voter where id=?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                voter = createVoter(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return voter;
    }

    @Override
    public void save(Voter voter) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("INSERT INTO voter(nid,studying_day) VALUES (?,?)")){
            preparedStatement.setString(1,voter.getNid());
            preparedStatement.setString(2,voter.getStudyingDay().toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("DELETE FROM voter WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Voter voter) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("UPDATE voter SET nid=? ,studying_day=? where id=?")){
            preparedStatement.setString(1,voter.getNid());
            preparedStatement.setString(2,voter.getStudyingDay().toString());
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Voter getVoterByNID(String nid) {
        Voter voter = null;
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("SELECT * FROM voter where nid=?")){
            preparedStatement.setString(1,nid);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                voter = createVoter(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return voter;
    }
}

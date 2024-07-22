package co.edu.cue.escolarvote.repository.impl;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Vote;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.repository.Repository;
import co.edu.cue.escolarvote.repository.VoteRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoteRepositoryImpl implements VoteRepository {

    private Vote createVote(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("candidate_id"));
        candidate.setName(resultSet.getString("candidate_name"));
        candidate.setGrade(resultSet.getString("candidate_grade"));
        candidate.setProfileImage(resultSet.getBytes("candidate_profile_image"));
        Voter voter = new Voter();
        voter.setId(resultSet.getLong("voter_id"));
        voter.setNid(resultSet.getString("voter_nid"));
        voter.setStudyingDay(StudyingDay.valueOf(resultSet.getString("voter_studying_day")));
        Vote vote = new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        return vote;
    }

    @Override
    public List<Vote> list() {
        List<Vote> votes = new ArrayList<>();
        try (Statement statement=Repository.getConnection().createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT " +
                     "    c.id AS candidate_id, " +
                     "    c.name AS candidate_name, " +
                     "    c.grade AS candidate_grade, " +
                     "    c.profile_image AS candidate_profile_image, " +
                     "    v.id AS voter_id, " +
                     "    v.nid AS voter_nid, " +
                     "    v.studying_day AS voter_studying_day " +
                     "FROM " +
                     "    vote AS vt " +
                     "JOIN " +
                     "    candidate AS c ON vt.candidate_id = c.id " +
                     "JOIN " +
                     "    voter AS v ON vt.voter_id = v.id")
        ){
            while (resultSet.next()) {
                Vote vote = createVote(resultSet);
                votes.add(vote);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }


    @Override
    public void save(Vote vote) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("INSERT INTO vote(candidate_id,voter_id) VALUES (?,?)")){
            preparedStatement.setLong(1,vote.getCandidate().getId());
            preparedStatement.setLong(2,vote.getVoter().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("DELETE FROM vote")){
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllFromCandidate(Long candidate_id) {
        try (PreparedStatement preparedStatement= Repository.getConnection().prepareStatement("DELETE FROM vote where candidate_id=?")){
            preparedStatement.setLong(1,candidate_id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

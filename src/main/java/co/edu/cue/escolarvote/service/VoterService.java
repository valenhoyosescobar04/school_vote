package co.edu.cue.escolarvote.service;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;

import java.util.List;
import java.util.Optional;

public interface VoterService {
    void createVoter(String nid, StudyingDay studyingDay);

    List<Voter> getVoter();

    Optional<Voter> getVoterByNID(String nid);

    void deleteVoters();
}

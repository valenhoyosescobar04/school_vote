package co.edu.cue.escolarvote.service;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Vote;
import co.edu.cue.escolarvote.domain.entities.Voter;

import java.util.List;

public interface VoteService {
    void createVote(Voter vote, Candidate candidate);
    void deleteVotes();

    void deleteVotesOfCandidate(Long candidate_id);

    List<Vote> filterByCandidate(Long candidate_id);

    List<Vote> listVotes();
}

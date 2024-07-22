package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Vote;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.service.VoteService;

import java.util.List;

public record VoteServiceImpl(School school) implements VoteService {

    @Override
    public void createVote(Voter voter, Candidate candidate) {
        school.voteRepository.save(new Vote(candidate, voter));
    }

    @Override
    public void deleteVotes() {
        school.voteRepository.deleteAll();
    }

    @Override
    public void deleteVotesOfCandidate(Long candidate_id){
        school.voteRepository.deleteAllFromCandidate(candidate_id);
    }

    @Override
    public List<Vote> filterByCandidate(Long candidate_id){
        return school.voteRepository.list().stream().filter(x->x.getCandidate().getId().equals(candidate_id)).toList();
    }

    @Override
    public List<Vote> listVotes() {
        return school.voteRepository.list();
    }
}

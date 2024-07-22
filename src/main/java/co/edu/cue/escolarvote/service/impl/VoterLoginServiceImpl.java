package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.controller.ModelFactoryController;
import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Vote;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.service.LoginService;

import java.util.List;
import java.util.Optional;

public record VoterLoginServiceImpl(School school) implements LoginService<Voter> {


    @Override
    public Boolean login(Voter voter) {
        String voterNid = voter.getNid();
        Optional<Voter> bdVoterOpt = school.voterService.getVoterByNID(voterNid);
        if (bdVoterOpt.isEmpty()) {
            school.voterService.createVoter(voterNid,voter.getStudyingDay());
            return true;
        }
        Voter bdVoter = bdVoterOpt.get();
        boolean hasVoted = school.voteService.listVotes().stream()
                .anyMatch(x -> x.getVoter().getNid().equals(voterNid));
        return bdVoter.getStudyingDay().equals(voter.getStudyingDay()) && !hasVoted;
    }
}

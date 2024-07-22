package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.service.VoterService;

import java.util.List;
import java.util.Optional;

public record VoterServiceImpl(School school) implements VoterService {

    @Override
    public void createVoter(String nid, StudyingDay studyingDay) {
        school.voterRepository.save(new Voter(nid, studyingDay));
    }

    @Override
    public List<Voter> getVoter() {
        return school.voterRepository.list();
    }

    @Override
    public Optional<Voter> getVoterByNID(String nid) {
        return school.voterRepository.list().stream().filter(x -> x.getNid().equals(nid)).findFirst();
    }

    @Override
    public void deleteVoters() {
        school.voterRepository.list().forEach(x -> school.voterRepository.delete(x.getId()));
    }
}

package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.service.CandidateService;

import java.util.List;

public record CandidateServiceImpl(School school) implements CandidateService {

    @Override
    public void createCandidate(String name, String grade, byte[] profile_image, StudyingDay studyingDay) {
        school.candidateRepository.save(new Candidate(name, profile_image, grade, studyingDay));
    }

    @Override
    public List<Candidate> getCandidates() {
        return school.candidateRepository.list();
    }

    @Override
    public void deleteCandidate(Long id) {
        school.candidateRepository.delete(id);
        school.voteService.deleteVotesOfCandidate(id);
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return school.candidateRepository.byId(id);
    }

    @Override
    public void updateCandidate(Long id, Candidate candidate) {
        school.candidateRepository.update(id, candidate);
    }

    @Override
    public List<Candidate> filterByStudyingDay(StudyingDay studyingDay) {
        return school.candidateRepository.list().stream().filter(x -> x.getStudyingDay().equals(studyingDay)).toList();
    }
}

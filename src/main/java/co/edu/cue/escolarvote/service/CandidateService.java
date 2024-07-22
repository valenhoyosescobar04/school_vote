package co.edu.cue.escolarvote.service;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;

import java.util.List;

public interface CandidateService {
    void createCandidate(String name, String grade, byte[] profile_image, StudyingDay studyingDay);

    List<Candidate> getCandidates();

    Candidate getCandidateById(Long id);

    void updateCandidate(Long id,Candidate candidate);

    void deleteCandidate(Long id);

    List<Candidate> filterByStudyingDay(StudyingDay studyingDay);
}

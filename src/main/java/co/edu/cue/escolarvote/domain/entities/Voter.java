package co.edu.cue.escolarvote.domain.entities;

import co.edu.cue.escolarvote.domain.enums.StudyingDay;

public class Voter {
    private Long id;
    private String nid;
    private StudyingDay studyingDay;

    public Voter(String nid, StudyingDay studyingDay) {
        this.nid = nid;
        this.studyingDay = studyingDay;
    }

    public Voter() {

    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public StudyingDay getStudyingDay() {
        return studyingDay;
    }

    public void setStudyingDay(StudyingDay studyingDay) {
        this.studyingDay = studyingDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

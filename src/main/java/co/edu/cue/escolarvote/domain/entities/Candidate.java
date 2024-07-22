package co.edu.cue.escolarvote.domain.entities;

import co.edu.cue.escolarvote.domain.enums.StudyingDay;

import java.awt.*;

public class Candidate {
    private Long id;
    private String name;
    private byte[] profileImage;
    private String grade;
    private StudyingDay studyingDay;

    public Candidate(Long id, String name, byte[] profileImage, String grade, StudyingDay studyingDay) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.grade = grade;
        this.studyingDay = studyingDay;
    }

    public Candidate(String name, byte[] profileImage, String grade, StudyingDay studyingDay) {
        this.name = name;
        this.profileImage = profileImage;
        this.grade = grade;
        this.studyingDay = studyingDay;
    }

    public Candidate() {

    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudyingDay getStudyingDay() {
        return studyingDay;
    }

    public void setStudyingDay(StudyingDay studyingDay) {
        this.studyingDay = studyingDay;
    }
}

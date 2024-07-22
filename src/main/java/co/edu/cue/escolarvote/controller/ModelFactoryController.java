package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.service.ModelFactoryService;
import co.edu.cue.escolarvote.service.impl.School;

import java.time.LocalDateTime;

public class ModelFactoryController implements ModelFactoryService {

    private School school;
    public Voter voter= null;
    public Candidate candidate = null;
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public ModelFactoryController() {
        school = new School();
    }


}

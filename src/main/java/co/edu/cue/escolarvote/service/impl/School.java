package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.repository.Repository;
import co.edu.cue.escolarvote.repository.VoteRepository;
import co.edu.cue.escolarvote.repository.impl.AdministratorRepository;
import co.edu.cue.escolarvote.repository.impl.CandidateRepository;
import co.edu.cue.escolarvote.repository.impl.VoteRepositoryImpl;
import co.edu.cue.escolarvote.repository.impl.VoterRepository;
import co.edu.cue.escolarvote.service.*;

public class School {
    public final Repository<Administrator> administratorRepository;
    public final Repository<Candidate> candidateRepository;
    public final Repository<Voter> voterRepository;
    public final VoteRepository voteRepository;
    public final AdministratorService administratorService;
    public final CandidateService candidateService;
    public final VoterService voterService;
    public final VoteService voteService;
    public final LoginService<Administrator> adminLoginService;
    public final LoginService<Voter> voterLoginService;

    public School() {
        this.administratorRepository = new AdministratorRepository();
        this.candidateRepository = new CandidateRepository();
        this.voterRepository = new VoterRepository();
        this.voteRepository = new VoteRepositoryImpl();
        this.administratorService=new AdministratorServiceImpl(this);
        this.candidateService=new CandidateServiceImpl(this);
        this.voteService=new VoteServiceImpl(this);
        this.voterService=new VoterServiceImpl(this);
        this.adminLoginService=new AdministratorLoginServiceImpl(this);
        this.voterLoginService=new VoterLoginServiceImpl(this);
    }

}

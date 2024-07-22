package co.edu.cue.escolarvote.domain.entities;

public class Vote {
    private Candidate candidate;
    private Voter voter;

    public Vote(Candidate candidate, Voter voter) {
        this.candidate = candidate;
        this.voter = voter;
    }

    public Vote() {

    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}

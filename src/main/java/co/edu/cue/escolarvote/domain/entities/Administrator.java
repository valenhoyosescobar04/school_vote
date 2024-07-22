package co.edu.cue.escolarvote.domain.entities;

public class Administrator {
    private Long id;
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Administrator(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Administrator() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

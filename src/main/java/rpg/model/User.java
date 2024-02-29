package rpg.model;

import java.time.LocalDateTime;

public class User extends AbstractBaseEntity {
    private String email;
    private String password;
    private boolean alreadyVoted;
    private LocalDateTime dateTimeOfVote;
    private Role role;

    public User() {
        super(null);
        this.alreadyVoted = false;
        this.dateTimeOfVote = null;
    }

    public User(String email, String password, Role role) {
        super(null);
        this.email = email;
        this.password = password;
        this.role = role;
        this.alreadyVoted = false;
        this.dateTimeOfVote = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAlreadyVoted() {
        return alreadyVoted;
    }

    public LocalDateTime getDateTimeOfVote() {
        return dateTimeOfVote;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}

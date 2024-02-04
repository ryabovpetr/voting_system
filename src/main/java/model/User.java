package model;

import java.time.LocalDateTime;

public class User {
    private final String email;
    private final String password;
    private final boolean alreadyVoted;
    private final LocalDateTime dateTimeOfVote;
    private final Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.alreadyVoted = false;
        this.dateTimeOfVote = null;
    }
}

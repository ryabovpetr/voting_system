package rpg.model;

import java.time.LocalDateTime;

public class User extends AbstractBaseEntity {
    private String email;
    private String password;
    private boolean alreadyVoted;
    private LocalDateTime dateTimeOfVote;
    private final Role role;

    public User(String email, String password, Role role) {
        super(null);
        this.email = email;
        this.password = password;
        this.role = role;
        this.alreadyVoted = false;
        this.dateTimeOfVote = null;
    }
}

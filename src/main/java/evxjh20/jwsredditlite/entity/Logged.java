package evxjh20.jwsredditlite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Logged {
    @Id
    @Column(name = "id", nullable = false)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Logged(String token, User user) {
        this.token = token;
        this.user = user;
    }
}

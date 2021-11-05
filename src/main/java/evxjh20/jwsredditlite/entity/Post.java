package evxjh20.jwsredditlite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer senderId;

    @Setter
    @Column(nullable = false)
    private String sender;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @ElementCollection
    private List<Integer> upVoters = new ArrayList<Integer>();

    @Setter
    @ElementCollection
    private List<Integer> downVoters= new ArrayList<Integer>();

    public Post(Integer senderId, String sender, String title, String content) {
        this.senderId = senderId;
        this.sender = sender;
        this.title = title;
        this.content = content;
    }
}

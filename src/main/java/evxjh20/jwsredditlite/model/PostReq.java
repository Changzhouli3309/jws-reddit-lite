package evxjh20.jwsredditlite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostReq {
    private int senderId;
    private String sender;
    private String title;
    private String content;
    private List<Integer> upVoters;
    private List<Integer> downVoters;
}

package evxjh20.jwsredditlite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRes {
    private int id;
    private String username;
    private String token;
}

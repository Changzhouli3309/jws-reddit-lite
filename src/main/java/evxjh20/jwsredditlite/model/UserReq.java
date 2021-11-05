package evxjh20.jwsredditlite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReq {
    private String username;
    private String password;
}

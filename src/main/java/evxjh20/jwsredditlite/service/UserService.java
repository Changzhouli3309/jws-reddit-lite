package evxjh20.jwsredditlite.service;

import evxjh20.jwsredditlite.entity.User;
import evxjh20.jwsredditlite.model.UserRes;

public interface UserService {
    User register(String username, String password);

    UserRes login(String username, String password);

    boolean logout(String token);

    boolean isValidate(String token);
}

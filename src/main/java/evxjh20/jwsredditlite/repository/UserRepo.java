package evxjh20.jwsredditlite.repository;

import evxjh20.jwsredditlite.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}

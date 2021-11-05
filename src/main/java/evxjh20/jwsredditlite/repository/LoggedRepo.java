package evxjh20.jwsredditlite.repository;

import evxjh20.jwsredditlite.entity.Logged;
import evxjh20.jwsredditlite.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoggedRepo extends CrudRepository<Logged, String> {

    Optional<Logged> findByUser(User user);
}

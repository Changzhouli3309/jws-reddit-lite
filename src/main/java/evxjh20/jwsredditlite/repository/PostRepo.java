package evxjh20.jwsredditlite.repository;

import evxjh20.jwsredditlite.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Integer> {
}

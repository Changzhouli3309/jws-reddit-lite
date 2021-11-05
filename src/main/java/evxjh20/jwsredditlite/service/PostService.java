package evxjh20.jwsredditlite.service;

import evxjh20.jwsredditlite.entity.Post;
import evxjh20.jwsredditlite.model.PostReq;

import java.util.List;

public interface PostService {
    Post createPost(int senderId, String sender, String title, String content);

    List<Post> getAll();

    Post updatePost(int id, PostReq updatePost);

    Boolean deletePost(int id);
}

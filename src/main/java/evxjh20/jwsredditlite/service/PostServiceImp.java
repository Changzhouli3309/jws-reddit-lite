package evxjh20.jwsredditlite.service;

import evxjh20.jwsredditlite.entity.Post;
import evxjh20.jwsredditlite.model.PostReq;
import evxjh20.jwsredditlite.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post createPost(int senderId, String sender, String title, String content) {
        return postRepo.save(new Post(senderId, sender, title, content));
    }

    @Override
    public List<Post> getAll() {
        return (List<Post>) postRepo.findAll();
    }

    @Override
    public Post updatePost(int id, PostReq updatePost) {
        Optional<Post> optional = postRepo.findById(id);

        if (optional.isPresent()) {
            Post post = optional.get();

            post.setTitle(updatePost.getTitle());
            post.setContent(updatePost.getContent());
            post.setUpVoters(updatePost.getUpVoters());
            post.setDownVoters(updatePost.getDownVoters());

            return postRepo.save(post);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deletePost(int id) {
        Optional<Post> optional = postRepo.findById(id);

        if (optional.isPresent()) {
            postRepo.delete(optional.get());
            return true;
        } else {
            return false;
        }
    }
}

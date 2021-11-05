package evxjh20.jwsredditlite.controller;

import evxjh20.jwsredditlite.entity.Post;
import evxjh20.jwsredditlite.model.PostReq;
import evxjh20.jwsredditlite.service.PostService;
import evxjh20.jwsredditlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("token") String token, @RequestBody PostReq postReq, HttpServletResponse response) {
        if (!userService.isValidate(token)) {
            response.setStatus(401);
            return null;
        }

        if (postReq.getSender().equals("") || postReq.getTitle().equals("") || postReq.getContent().equals("")) {
            response.setStatus(400);
            return null;
        }

        Post post = postService.createPost(postReq.getSenderId(), postReq.getSender(), postReq.getTitle(), postReq.getContent());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost(@RequestHeader("token") String token, HttpServletResponse response) {
        if (!userService.isValidate(token)) {
            response.setStatus(401);
            return null;
        }

        List<Post> posts = postService.getAll();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@RequestHeader("token") String token, @PathVariable("id") int id,
                                           @RequestBody PostReq postReq, HttpServletResponse response) {
        if (!userService.isValidate(token)) {
            response.setStatus(401);
            return null;
        }

        Post post = postService.updatePost(id, postReq);
        if (post == null) {
            response.setStatus(404);
            return null;
        }
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@RequestHeader("token") String token, @PathVariable("id") int id, HttpServletResponse response) {
        if (!userService.isValidate(token)) {
            response.setStatus(401);
            return null;
        }

        if (postService.deletePost(id)) {
            return "Post has been delete.";
        } else {
            response.setStatus(404);
            return null;
        }
    }
}

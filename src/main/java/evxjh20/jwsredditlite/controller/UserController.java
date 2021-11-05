package evxjh20.jwsredditlite.controller;

import evxjh20.jwsredditlite.entity.User;
import evxjh20.jwsredditlite.model.UserReq;
import evxjh20.jwsredditlite.model.UserRes;
import evxjh20.jwsredditlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserReq userReq, HttpServletResponse response) {
        if (userReq.getUsername().equals("") || userReq.getPassword().equals("")) {
            response.setStatus(400);
            return "Input can not be empty";
        }

        User user = userService.register(userReq.getUsername(), userReq.getPassword());

        if (user == null) {
            response.setStatus(409);
            return "Username is taken.";
        } else {
            return "Register success.";
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserRes> login(@RequestHeader("username") String username, @RequestHeader("password") String password,
                                         HttpServletResponse response) {
        UserRes res = userService.login(username, password);
        if (res == null) {
            response.setStatus(406);
            return null;
        } else {
            return ResponseEntity.ok(res);
        }
    }

    @DeleteMapping("/logout")
    public String login(@RequestHeader("token") String token, HttpServletResponse response) {
        if (!userService.logout(token)) {
            response.setStatus(404);
            return "Can not find the user";
        } else {
            return "Logout success";
        }
    }
}

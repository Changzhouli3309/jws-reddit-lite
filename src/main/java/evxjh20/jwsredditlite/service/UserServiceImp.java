package evxjh20.jwsredditlite.service;

import evxjh20.jwsredditlite.entity.Logged;
import evxjh20.jwsredditlite.entity.User;
import evxjh20.jwsredditlite.model.UserRes;
import evxjh20.jwsredditlite.repository.LoggedRepo;
import evxjh20.jwsredditlite.repository.UserRepo;
import evxjh20.jwsredditlite.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final LoggedRepo loggedRepo;

    @Autowired
    public UserServiceImp(UserRepo userRepo, LoggedRepo loggedRepo) {
        this.userRepo = userRepo;
        this.loggedRepo = loggedRepo;
    }

    @Override
    public User register(String username, String password) {
        String hashPassword = Encryption.getHashPassword(password);

        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            return null;
        } else {
            return userRepo.save(new User(username, hashPassword));
        }
    }

    @Override
    public UserRes login(String username, String password) {
        String hashPassword = Encryption.getHashPassword(password);
        Optional<User> optional = userRepo.findByUsername(username);

        if (optional.isPresent()) {
            User user = optional.get();
            Optional<Logged> logged = loggedRepo.findByUser(user);

            if (logged.isPresent()) {
                return null;
            } else {
                String token = UUID.randomUUID().toString();
                loggedRepo.save(new Logged(token, user));
                return new UserRes(user.getId(), username, token);
            }

        } else {
            return null;
        }
    }

    @Override
    public boolean logout(String token) {
        Optional<Logged> logged = loggedRepo.findById(token);

        if (logged.isPresent()) {
            loggedRepo.delete(logged.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValidate(String token) {
        return loggedRepo.findById(token).isPresent();
    }
}

package rpg.service;

import org.springframework.stereotype.Service;
import rpg.model.User;
import rpg.repository.UserRepository;

import java.util.List;

import static rpg.utils.ValidationUtil.*;
@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id){
       return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}

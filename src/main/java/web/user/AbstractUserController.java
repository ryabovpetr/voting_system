package web.user;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import utils.ValidationUtil;

import java.util.List;

public class AbstractUserController {
    private UserService service;
    private final static Logger log = LoggerFactory.getLogger(AbstractUserController.class);

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get id={}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        ValidationUtil.checkNew(user);
        return service.create(user);
    }

    public User getByEmail(String email) {
        log.info("get by email={}", email);
        return service.getByEmail(email);
    }

    public void delete(int id) {
        log.info("delete id={}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        service.update(user);
    }
}

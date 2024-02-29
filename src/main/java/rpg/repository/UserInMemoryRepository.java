package rpg.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import rpg.model.User;
import rpg.utils.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UserInMemoryRepository implements UserRepository {

    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(UserInMemoryRepository.class);

    {
        Util.getUsers().forEach(this::save);
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            return repository.put(user.getId(), user);
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete id={}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("get id={}", id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        log.info("get by email={}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll}");
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
    }
}

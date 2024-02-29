package rpg.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import rpg.model.Restaurant;
import rpg.utils.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class RestaurantInMemoryRepository implements RestaurantRepository {
    private final Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(RestaurantInMemoryRepository.class);

    {
        Util.getRests().forEach(this::save);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        log.info("save {}", restaurant);
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            return repository.put(restaurant.getId(), restaurant);
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete id={}", id);
        return repository.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        log.info("get id={}", id);
        return repository.get(id);
    }

    @Override
    public Restaurant getByName(String name) {
        log.info("get by name={}", name);
        return repository.values().stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(Restaurant::getCountVotes).reversed())
                .collect(Collectors.toList());
    }
}

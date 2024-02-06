package repository;

import model.Restaurant;
import utils.Util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RestaurantInMemoryRepository implements RestaurantRepository {
    private final Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        Util.getRests().forEach(this::save);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()){
            restaurant.setId(counter.incrementAndGet());
            return repository.put(restaurant.getId(), restaurant);
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(Restaurant::getCountVotes).reversed())
                .collect(Collectors.toList());
    }
}

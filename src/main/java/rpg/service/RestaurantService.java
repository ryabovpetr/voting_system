package rpg.service;

import rpg.model.Restaurant;
import rpg.repository.RestaurantRepository;

import java.util.List;

import static rpg.utils.ValidationUtil.*;

public class RestaurantService {
    private RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant getByName(String name) {
        return checkNotFound(repository.getByName(name), "name=" + name);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }
}

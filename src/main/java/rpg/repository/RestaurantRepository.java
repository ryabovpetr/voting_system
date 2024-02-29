package rpg.repository;

import rpg.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();
}

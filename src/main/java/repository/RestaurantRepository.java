package repository;

import model.Restaurant;

import java.util.Collection;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    Collection<Restaurant> getAll();
}

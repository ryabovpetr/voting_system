package web.restaurant;

import model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RestaurantService;
import utils.ValidationUtil;

import java.util.List;

public class RestaurantRestController {
    private RestaurantService service;
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Restaurant get(int id) {
        log.info("get id={}", id);
        return service.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        return service.create(restaurant);
    }

    public Restaurant getByName(String name) {
        log.info("get by name={}", name);
        return service.getByName(name);
    }

    public void delete(int id) {
        log.info("delete id={}", id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }
}

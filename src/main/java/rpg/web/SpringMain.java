package rpg.web;

import rpg.model.Restaurant;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rpg.model.Role;
import rpg.model.User;
import rpg.repository.RestaurantRepository;
import rpg.service.RestaurantService;
import rpg.web.restaurant.RestaurantRestController;
import rpg.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
            restaurantRestController.create(new Restaurant(null, "Волга", null, 100));

            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            adminRestController.create(new User("ryabovpetr@gmail.com", "password", Role.USER));
        }
    }
}

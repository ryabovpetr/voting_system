package web;

import model.Restaurant;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.RestaurantRepository;
import service.RestaurantService;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        RestaurantRepository restaurantRepository = (RestaurantRepository) appCtx.getBean("restaurantInMemoryRepository");
        RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);


        RestaurantService restaurantService = appCtx.getBean(RestaurantService.class);
        restaurantService.create(new Restaurant(null, "Волга", null, 100));

        restaurantRepository.getAll();
        appCtx.close();
    }
}

package rpg.utils;

import rpg.model.Restaurant;
import rpg.model.Role;
import rpg.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    private static final List<User> users = new ArrayList<>();

    static {
        Restaurant tehnikum = new Restaurant();
        tehnikum.setName("Техникум");
        tehnikum.getLunchMenu().put("Селедка", 200);
        tehnikum.getLunchMenu().put("Греческий салат", 350);
        tehnikum.getLunchMenu().put("Компот", 50);
        tehnikum.setCountVotes(10);
        restaurants.add(tehnikum);

        Restaurant galki = new Restaurant();
        galki.setName("Галки");
        galki.getLunchMenu().put("Фуа-гра", 500);
        galki.getLunchMenu().put("Макароны по-флотски", 150);
        galki.getLunchMenu().put("Жмых", 15);
        galki.setCountVotes(3);
        restaurants.add(galki);

        Restaurant domKulturi = new Restaurant();
        domKulturi.setName("Дом Культуры");
        domKulturi.getLunchMenu().put("Ячмень по-французски", 180);
        domKulturi.getLunchMenu().put("Выгода вторичная", 35);
        domKulturi.getLunchMenu().put("Сыворотка", 80);
        domKulturi.setCountVotes(5);
        restaurants.add(domKulturi);

        User user = new User("user@gmail.com", "user", Role.USER);
        User admin = new User("admin@gmail.com", "admin", Role.ADMIN);
        users.add(user);
        users.add(admin);
    }

    public static List<Restaurant> getRests() {
        return restaurants;
    }

    public static List<User> getUsers() {
        return users;
    }
}

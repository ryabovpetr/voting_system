package utils;

import model.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private static final List<Restaurant> restaurants = new ArrayList<>();
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
    }
    public static void main() {
        System.out.println(getSortedRests());

    }

    public static List<Restaurant> getRests() {
        return restaurants;
    }

    public static List<Restaurant> getSortedRests() {
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getCountVotes).reversed())
                .collect(Collectors.toList());
    }
}

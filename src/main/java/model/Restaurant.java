package model;

import java.util.HashMap;

public class Restaurant {
    private final String name;
    private final HashMap<String, Integer> lunchMenu;
    private final int countVotes;

    public Restaurant(String name, HashMap<String, Integer> lunchMenu) {
        this.name = name;
        this.lunchMenu = lunchMenu;
        this.countVotes = 0;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getLunchMenu() {
        return lunchMenu;
    }

    public int getCountVotes() {
        return countVotes;
    }
}

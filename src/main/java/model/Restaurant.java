package model;

import java.util.HashMap;

public class Restaurant {

    private Integer id;
    private final String name;
    private final HashMap<String, Integer> lunchMenu;
    private int countVotes;

    public Restaurant(String name) {
        this.id = null;
        this.name = name;
        this.lunchMenu = new HashMap<>();
        this.countVotes = 0;
    }

    public Restaurant(Integer id, String name, HashMap<String, Integer> lunchMenu, int countVotes) {
        this.id = id;
        this.name = name;
        this.lunchMenu = lunchMenu;
        this.countVotes = countVotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
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

    public void setCountVotes(int countVotes) {
        this.countVotes = countVotes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}

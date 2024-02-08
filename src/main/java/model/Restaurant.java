package model;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {

    private Integer id;
    private String name;
    private Map<String, Integer> lunchMenu;
    private int countVotes;

    public Restaurant(String name) {
        this.id = null;
        this.name = name;
        this.lunchMenu = new HashMap<>();
        this.countVotes = 0;
    }

    public Restaurant(Integer id, String name, Map<String, Integer> lunchMenu, int countVotes) {
        this.id = id;
        this.name = name;
        this.lunchMenu = lunchMenu;
        this.countVotes = countVotes;
    }

    public Restaurant() {
        this.lunchMenu = new HashMap<>();
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

    public Map<String, Integer> getLunchMenu() {
        return lunchMenu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLunchMenu(Map<String, Integer> lunchMenu) {
        this.lunchMenu = lunchMenu;
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

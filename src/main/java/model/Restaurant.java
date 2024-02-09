package model;

import java.util.HashMap;
import java.util.Map;

public class Restaurant extends AbstractBaseEntity {
    private String name;
    private Map<String, Integer> lunchMenu;
    private int countVotes;

    public Restaurant(Integer id, String name, Map<String, Integer> lunchMenu, int countVotes) {
        super(id);
        this.name = name;
        this.lunchMenu = lunchMenu;
        this.countVotes = countVotes;
    }

    public Restaurant() {
        super(null);
        this.lunchMenu = new HashMap<>();
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

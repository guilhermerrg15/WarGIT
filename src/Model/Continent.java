package Model;

import java.awt.Color;

public class Continent {
    private String name;
    private Territory[] territories;
    private int bonusArmies;
    private Color color;

    public Continent(String name, Territory[] territories, int bonusArmies, Color color) {
        this.name = name;
        this.territories = territories;
        this.bonusArmies = bonusArmies;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Territory[] getTerritories() {
        return territories;
    }

    public int getBonusArmies() {
        return bonusArmies;
    }

    public Color getColor() {
        return color;
    }
}


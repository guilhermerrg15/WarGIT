package Model;

import java.awt.Color;

public class Continent {
    private String name;
    private Territory[] territories;
    private int bonusArmies;
    private Color color;
    private int value;

    public String getName() {
        return name;
    }
    
    public int getValue() {
		return value;
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
    
    public Territory findTerritory(String name) {
        for (Territory territory : territories) {
            if (territory.getName().equals(name)) {
                return territory;
            }
        }
        return null;
    }

    public int getNumberTerritories() {
        return territories.length;
    }
    
    
    public Continent(String name, Territory[] territories, int bonusArmies, Color color) {
        this.name = name;
        this.territories = territories;
        this.bonusArmies = bonusArmies;
        this.color = color;
    }
}


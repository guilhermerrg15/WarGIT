package Model;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private String name;
    private Player owner;
    private int armies;
    private List<Territory> adjacentTerritories;
    private Coordinate coordinates;

    public Territory(String name, Player owner, Coordinate coordinates) {
        this.name = name;
        this.owner = owner;
        this.armies = 0;
        this.adjacentTerritories = new ArrayList<>();
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getArmies() {
        return armies;
    }

    public void addArmies(int numArmies) {
        armies += numArmies;
    }

    public void removeArmies(int numArmies) {
        armies -= numArmies;
        if (armies < 0) {
            armies = 0;
        }
    }

    public void addAdjacentTerritory(Territory adjacentTerritory) {
        adjacentTerritories.add(adjacentTerritory);
    }

    public List<Territory> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }
}


package Model;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private String name;
    private Player owner;
    private int armies;
    private List<Territory> neighbors;
    private Coordinate center;
    private Coordinate[] vertices;

    public Territory(String name, Coordinate center, Coordinate[] vertices) {
        this.name = name;
        this.owner = null;
        this.armies = 0;
        this.neighbors = new ArrayList<>();
        this.center = center;
        this.vertices = vertices;
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

    public void addArmies(int count) {
        armies += count;
    }

    public void removeArmies(int count) {
        armies -= count;
        if (armies < 0) {
            armies = 0;
        }
    }

    public List<Territory> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Territory neighbor) {
        neighbors.add(neighbor);
    }

    public Coordinate getCenter() {
        return center;
    }

    public Coordinate[] getVertices() {
        return vertices;
    }
}



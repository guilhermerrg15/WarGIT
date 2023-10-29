package Model;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private String name;
    private Player owner;
    private int armies;
    private List<Territory> neighbours;
   private Coordinates center;
   private Coordinates[] vertices;

    public Territory(String name) {
        this.name = name;
        this.owner = null;
        this.armies = 0;
        this.neighbours = new ArrayList<>();
    //    this.center = center;
    //    this.vertices = vertices;
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

    public List<Territory> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Territory neighbour) {
        neighbours.add(neighbour);
    }

   public Coordinates getCenter() {
       return center;
   }

   public Coordinates[] getVertices() {
       return vertices;
   }
}



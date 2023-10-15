package Model;

public class TerritoryCard {
    private String name;
    private Shape geometricShape; 
    private int id;

    public TerritoryCard(String name, Shape geometricShape) {
        this.name = name;
        this.geometricShape = geometricShape;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Shape getShape() {
        return geometricShape;
    }

    @Override
    public String toString() {
        return name + " (" + geometricShape + ")";
    }
}


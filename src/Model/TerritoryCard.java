package Model;

public class TerritoryCard {
    private String name;
    private String geometricShape; 

    public TerritoryCard(String name, String geometricShape) {
        this.name = name;
        this.geometricShape = geometricShape;
    }

    public String getName() {
        return name;
    }

    public String getGeometricShape() {
        return geometricShape;
    }

    @Override
    public String toString() {
        return name + " (" + geometricShape + ")";
    }
}


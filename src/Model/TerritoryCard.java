package Model;

public class TerritoryCard {
    private String name;
    private Shape geometricShape; 
    private int id;
    private Territory territory;  // Add this field

    public TerritoryCard(int id, String name, Shape geometricShape) {
        this.id = id;
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

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return name + " (" + geometricShape + ")";
    }
}



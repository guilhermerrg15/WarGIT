package Model;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List <Territory> territories;
    private List<TerritoryCard> territoryCards;
    private int armies;
    private PlayerColor color;
    private ObjectiveCard objective;


    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.territoryCards = new ArrayList<>();
        this.armies = 0;
        this.objective = null;
    }

    // Métodos para adicionar objetivos, cartas e territórios

    public void removeArmies(Territory territory, int numArmies) {
        territory.removeArmies(numArmies);
    }

    

    public String getName() {
        return name;
    }

    public List <Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }

    public void removeTerritory(Territory territory) {
        territories.remove(territory);
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

    public List<TerritoryCard> getTerritoryCardList() {
        return territoryCards;
    }

    public ObjectiveCard getObjective() {
        return objective;
    }


    public void setObjective(ObjectiveCard objective) {
        this.objective = objective;
    }

    public PlayerColor getColor() {
        return color;
    }
}

package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Hashtable;

public class Player {
    String name;
    private List <Territory> territories;
    private int armies;
    private PlayerColor color;
    private ObjectiveCard objective;
    private ArrayList<TerritoryCard> territoryCards;

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.armies = 0;
        this.objective = null;
        this.territoryCards = new ArrayList<TerritoryCard>();
    }

    public void removeArmies(int count) {
        if (count > armies) {
            armies = 0;
        } else {
            armies -= count;
        }
    }

    public String getName() {
        return name;
    }

    public List <Territory> getTerritories() {
        return territories;
    }

    public List<TerritoryCard> getTerritoryCardList() {
		return Collections.unmodifiableList(territoryCards);
	}
    
    public void addTerritoryCard(TerritoryCard card) {
		territoryCards.add(card);
	}
    
    public void removeTerritoryCard(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
        territoryCards.remove(card1);
        territoryCards.remove(card2);
        territoryCards.remove(card3);
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }
    
    public boolean ownsTerritory(Territory t) {
        return territories.contains(t);
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

    public boolean hasEntireContinent(Continent c) {
		for (Territory t : c.getTerritories()) {
			if (t.getOwner() != this) {
				return false;
			}
		}
		return true;
	}
    
    private Hashtable<Continent, Integer> unspentContinentalSoldiers = new Hashtable<Continent, Integer>();
    
    public int getUnspentContinentalSoldierCount(Continent c) {
		return unspentContinentalSoldiers.get(c);
	}
    
    public void addContinentalSoldiers(Continent c, int amount) {
		unspentContinentalSoldiers.putIfAbsent(c, 0);
		
		Integer i = unspentContinentalSoldiers.get(c);
		unspentContinentalSoldiers.put(c, i + amount);
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
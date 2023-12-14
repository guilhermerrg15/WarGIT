package Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um jogador no jogo.
 */
class Player {

    private String name;
    private PlayerColor color;
    protected ArrayList <Territory> territories;
    private List<TerritoryCard> territoryCards;
    private int armies;
    private boolean conqueredTerritory;
    private int index;
    private ObjectiveCard objective;
    protected List<Army> continentalArmies;
	private int numTerritories = 0;  
	private boolean eliminatedThisRound = false; 
	private Player jKilled;
	private boolean conqueredThisRound = false; 
    
     /**
     * Construtor da classe Player.
    *
    * @param name  O nome do jogador.
    * @param color A cor do jogador.
    */
    public Player(String name, PlayerColor color, int index) {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.armies = 0;
        this.index = index;
        this.territoryCards = new ArrayList<TerritoryCard>();
        this.continentalArmies = new ArrayList<Army>();
    }


    public void reset() {
		this.territories.clear();
		this.objective = null;
		this.eliminatedThisRound = false;
		this.conqueredThisRound = false;
        this.conqueredTerritory = false;
        this.numTerritories =0;
        this.armies = 0;
        this.eliminatedThisRound = false;
		this.conqueredThisRound = false;
        this.territoryCards.clear();
        this.territories.clear();
    }

    public int getCards () {
        return territoryCards.size();
    }

    public ArrayList<Territory> getConqueredTerritories() {
        return territories;
    }

    public int getIndex(){
        return this.index;
    }

    public int setIndex(int index){
        return this.index = index;

    }

    public List<TerritoryCard> getAllCards() {
        List<TerritoryCard> cards = this.getCard();
        territoryCards.clear();
        return cards;
    }


    public void setArmies(int armies) {
        this.armies = armies;
    }

    public int getArmies() {
        return this.armies;
    }


    public int getTerritoryNumber() {
        return numTerritories;
    }
    
    public void loseTerritory(Territory territory) {
        territories.remove(territory);

        this.numTerritories--;
    }


    public void setEliminatedThisRound(boolean eliminatedThisRound) {
        this.eliminatedThisRound = eliminatedThisRound;
    }


	public boolean getEliminatedThisRound() {
		return eliminatedThisRound;
	}

	public void setConqueredThisRound(boolean conqueredThisRound) {
		this.conqueredThisRound = conqueredThisRound;
	}


	public boolean getConqueredThisRound() {
		return conqueredThisRound;
	}

    public Player getPlayerKilled() {
		return jKilled;
	}

	public void setJMatou(Player jKilled) {
		this.jKilled = jKilled;
	}
    
    public void receiveObjective(ObjectiveCard obj) {
        if (obj != null) {
            objective = obj;
            obj.checkOwner(this);
        }
    }

    
    public ObjectiveCard getObjective() {
       return objective;
    }

    public void setObjective(ObjectiveCard objective) {
        this.objective = objective;
        if (objective != null) {
            objective.checkOwner(this);
        }
    }
    
    public String getObjectiveName() {
    	return objective.getName();
    }

    public List<TerritoryCard> getCard(){
    	return this.territoryCards;
    }

    public boolean addCard(TerritoryCard card) {
            this.conqueredTerritory = false;
            if(this.territoryCards.size() < 5) {
                this.territoryCards.add(card);
                return true;
            }
        return false;
    }

	public void addTerritorio(Territory t) {
		territories.add(t);
		this.numTerritories++;
	}

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }
}
package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//
/**
 * Representa um jogador no jogo.
 */
class Player {

    private String name;
    private PlayerColor color;
    protected ArrayList <Territory> territories;
    private List<TerritoryCard> territoryCards; // cartas para troca
    private int armies;
    private boolean conqueredTerritory;
    private static int cont = 0;
    private int index;
    private ObjectiveCard objective;
    private List<Continent> continents;
    protected List<Army> continentalArmies;
    //Guarda a quantidade de territórios em sua posse
	private int numTerritories = 0;  
    //Guarda se o jogador foi eliminado nessa rodada
	private boolean eliminatedThisRound = false; 
	private Player jMatou;
    //Guarda se o jogador conquistou um território nessa rodada
	private boolean conquistouNessaRodada = false; 
    
    // Pegar número de cartas
    public int getCards () {
        return territoryCards.size();
    }

    // Pegar lista de territórios conquistados
    public ArrayList<Territory> getConqueredTerritories() {
        return territories;
    }

    public int getIndex(){
        return this.index;
    }

    public int setIndex(int index){
        return this.index = index;

    }

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
        // for(Continent continent : continents){
        //     this.continentalArmies.add(new Army(continent));
        // }
    }

    // Resetar o jogador
    public void reset() {
		this.territories.clear();
		this.objective = null;
		this.eliminatedThisRound = false;
		this.conquistouNessaRodada = false;
        this.conqueredTerritory = false;
        this.numTerritories =0;
        this.armies = 0;
        this.eliminatedThisRound = false;
		this.conquistouNessaRodada = false;
        this.territoryCards.clear();
        this.territories.clear();
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

    //Altera se o jogador foi eliminado nessa rodada para verificação de objetivos
    public void setEliminatedThisRound(boolean eliminatedThisRound) {
        this.eliminatedThisRound = eliminatedThisRound;
    }

	//Retorna se o jogador foi eliminado nessa rodada
	public boolean getEliminadoNessaRodada() {
		return eliminatedThisRound;
	}

    //Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public void setConqueredThisRound(boolean conqueredThisRound) {
		this.conquistouNessaRodada = conqueredThisRound;
	}

    //Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public boolean getConquistouNessaRodada() {
		return conquistouNessaRodada;
	}

    public Player getJMatou() {
		return jMatou;
	}

	public void setJMatou(Player jMatou) {
		this.jMatou = jMatou;
	}
    
    public void receiveObjective(ObjectiveCard objetivo) {
        if (objetivo != null) {
            objective = objetivo;
            objetivo.checkOwner(this);
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


    //Adiciona um território ao jogador
	public void addTerritorio(Territory t) {
		territories.add(t);
		// Aumenta em 1 a quantidade de territórios
		this.numTerritories++;
	}

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }
}

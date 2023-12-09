package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//
/**
 * Representa um jogador no jogo.
 */
public class Player  {

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
	private boolean eliminadoNessaRodada = false; 
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
		this.eliminadoNessaRodada = false;
		this.conquistouNessaRodada = false;
        this.conqueredTerritory = false;
        this.numTerritories =0;
        this.armies = 0;
        this.eliminadoNessaRodada = false;
		this.conquistouNessaRodada = false;

        // for(TerritoryCard territoryCard : this.territoryCards) {
        //     territoryCardDeck.returnCard(territoryCard);
        // }
        this.territoryCards.clear();
        this.territories.clear();
    }

    //nao entendi objetivo desse metodo
     public void backToDeckPlayerDestroyed(List<TerritoryCard> cards ,TerritoryCardDeck territoryDeck){
     	this.territoryCards.addAll(cards);
	 	Random rand = new Random();
     	while(this.territoryCards.size() > 5) {
     		TerritoryCard card = this.territoryCards.remove(rand.nextInt(this.territoryCards.size()));
     		territoryDeck.returnCard(card);
     	}
     }

    public List<TerritoryCard> getAllCards() {
        List<TerritoryCard> cards = this.getCard();
        territoryCards.clear();
        return cards;
    }

    public String getTerritoryRegion(String territory) {
        return territories.stream()
                .filter(terr -> terr.getName().equals(territory))
                .map(Territory::getContinent)
                .findFirst()
                .orElse(null);
    }
    

    public boolean checkBorder(String pais, String border) {
        return territories.stream()
                .filter(territory -> territory.getName().equals(pais))
                .findFirst()
                .map(territory -> territory.isNeighbor(border))
                .orElse(false);
    }
    
    public boolean verifyTerritory(String country) {
        return territories.stream()
                .anyMatch(territory -> territory.getName().equals(country));
    }
    

    public boolean verifyContinentTerritory(String region) {
        for(Territory territory : this.territories) {
            if(territory.getName() == region) {
                for(Army army : this.continentalArmies) {
                    if(territory.getContinent() == army.retrieveContinent().getName() && army.retrieveArmyCount() > 0) {
                        return true;
                    }
                }

                return false;
            }
        }

        return false;
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

    public void winTerritory(Territory territory) {
    	territory.setOwner(this);
        territories.add(territory);
    }


    //Altera se o jogador foi eliminado nessa rodada para verificação de objetivos
    public void setEliminadoNessaRodada(boolean eliminadoNessaRodada) {
        this.eliminadoNessaRodada = eliminadoNessaRodada;
    }

    //Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public void setConquistouNessaRodada(boolean conquistouNessaRodada) {
		this.conquistouNessaRodada = conquistouNessaRodada;
	}

    public Player getJMatou() {
		return jMatou;
	}

	public void setJMatou(Player jMatou) {
		this.jMatou = jMatou;
	}

    public boolean verifyDestroyed() {
        if (territories.isEmpty()) {
            return false;
        }
        return true;
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
    
    public String getObjectiveName() {
    	return objective.getName();
    }

    public List<TerritoryCard> getCard(){
    	return this.territoryCards;
    }

    public boolean addCard(TerritoryCard card) {
        if(this.conqueredTerritory
) {
            this.conqueredTerritory
     = false;
            if(this.territoryCards.size() < 5) {
                this.territoryCards.add(card);
                return true;
            }
        }
        return false;
    }

    public boolean getConquered() {
        return this.conqueredTerritory
;
    }

    // Adicionar exércitos conquistados por troca de cartas
    public void addArmyTraded(int army) {
        this.armies += army;
    }

    // Adicionar exércitos
    public void addArmy() { 
        
        if(this.armies > 1) {
            // Adicionar a metade da quantidade de territórios em posse do jogador
            this.armies += this.territories.size()/2;
        } else {
            // Caso em que o jogador tenha apenas um terriório
            this.armies = 1;
        }
    }

    public void showTerritories() {
        for (Territory territory : this.territories) {
            System.out.println(territory.getName());
        }
    }

    // Pegar quantidade de exércitos em um continente
    public int countContinentalArmy(Continent continent) {
        for(Army continentalArmy : this.continentalArmies) {
            if(continentalArmy.retrieveContinent() == continent) {
                return continentalArmy.retrieveArmyCount();
            }
        }

        return 0;
    }

    // Adicionar exércitos em um continente
    public void addContinentalArmy(Continent continent, int num){
        for(Army army : this.continentalArmies) {
            if(army.retrieveContinent() == continent) {
                army.addArmy(num);
            }
        }
    }

    // Posicionar exércitos no continente
    public boolean positionContinentalArmies(Continent continent, Territory territory, int num) {
        int countArmies = 0;
        for(Army army : this.continentalArmies) {
            if(army.retrieveContinent() == continent) {
                countArmies = army.retrieveArmyCount();
            }
        }

        if(num > countArmies) {
            return false;
        }

        for(Territory region : this.territories) {
            if(region.getName().equals(territory.getName()) && territory.getContinent().equals(region.getName())) {
                territory.addArmies(num);
                this.addContinentalArmy(continent, -num);
                return true;
            }
        }

        return false;
    }


    //Adiciona um território ao jogador
	public void addTerritorio(Territory t) {
		territories.add(t);
		// Aumenta em 1 a quantidade de territórios
		this.numTerritories++;
	}

    

    /**
     * Obtém o nome do jogador.
     *
     * @return O nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém a cor do jogador.
     *
     * @return A cor do jogador.
     */
    public PlayerColor getColor() {
        return color;
    }
}

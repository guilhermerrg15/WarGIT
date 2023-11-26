package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
//
/**
 * Representa um jogador no jogo.
 */
public class Player {

    private String name;
    private PlayerColor color;
    protected List <Territory> territories;
    private List<TerritoryCard> territoryCards; // cartas para troca
    private int armies;
    private boolean conqueredTerritory;
    private Player enemy;
    private DestroyOpponentObjectiveCard destroyObjective;
    private ConquerTwoContinentsObjectiveCard conquerTwoContinentsObjective;
    private ConquerThreeContinentsObjectiveCard conquerThreeContinentsObjective;
    private Conquer18TerritoriesObjectiveCard conquer18TerritoriesObjectiveCard;
    private Conquer24TerritoriesObjectiveCard conquer24TerritoriesObjectiveCard;
    private TerritoryCardDeck cardDeck;
    private ObjectiveCard objective;
    private List<Continent> continents;
    protected List<Army> continentalArmies;
    
    
    // Pegar número de cartas
    public int getCards () {
        return territoryCards.size();
    }

    // Pegar lista de territórios conquistados
    public List<Territory> getConqueredTerritories() {
        return this.territories;
    }

    // Resetar o jogador
    public void reset(TerritoryCardDeck territoryCardDeck, ObjectiveCardDeck objectiveCardDeck) {
        this.conqueredTerritory = false;
        this.enemy = null;
        this.armies = 0;

        for(TerritoryCard territoryCard : this.territoryCards) {
            territoryCardDeck.returnCard(territoryCard);
        }

        objectiveCardDeck.returnObjectiveCard(objective); // verificar se é objective
        this.objective = null;
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
                .map(territory -> territory.faz_fronteira(border))
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
        return territories.size();
    }



    /**
     * Construtor da classe Player.
    *
    * @param name  O nome do jogador.
    * @param color A cor do jogador.
    */

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.armies = 0;
        this.enemy = null;
        this.territoryCards = new ArrayList<TerritoryCard>();
        this.continentalArmies = new ArrayList<Army>();
        for(Continent continent : continents){
            this.continentalArmies.add(new Army(continent));
        }
    }
    
    
    public void loseTerritory(Territory territory) {
        territories.remove(territory);
    }

    public void winTerritory(Territory territory) {
    	territory.setOwner(this);
        territories.add(territory);
    }

    public boolean verifyDestroyed() {
        if (territories.isEmpty()) {
            return false;
        }
        return true;
    }

    public void destroyedPlayer(Player player) {
        this.enemy = player;
    }

    public Player getEnemy() {
        return this.enemy;
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

    ////metodos que precisam da classe exercitoRegiao
    
    // public int getArmyRegion(String continent) {
        //precisa da classe exercitoRegiao
    // }

    /////

    // public void switchTerritoryArmies(List<String> territories, TerritoryCardDeck territoryCardDeck) {
    //     List<TerritoryCard> cards = new ArrayList<TerritoryCard>();
    //     for(String territory: territories) {
    //         for(TerritoryCard card: territoryCards) {
    //             if(card.getTerritory().getName().equals(territory)) {
    //                 cards.add(card);
    //             }
    //         }
    //     }
    // }



    // Posicionar exércitos ao final de uma conquista
    public boolean placeArmy(int army, String destiny) {
        if(army > this.armies) {
            return false;
        }
        for (Territory territory : this.territories) {
            if(territory.getName() == destiny) {
                territory.addArmies(army);
                this.armies -= army;
                return true;
            }
        }
        return false;
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


    // Posicionar exércitos em um território
    public boolean placeArmy(Territory territory, int count) {
        if(count > this.armies) {
            return false;
        }

        for(Territory territory2 : this.territories) {
            if(territory2.equals(territory)) {
                territory.addArmies(count);
                this.armies -= count;
            }
        }

        return false;
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

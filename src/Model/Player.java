package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Hashtable;
//
/**
 * Representa um jogador no jogo.
 */
public class Player {

    private String name;
    private PlayerColor color;
    protected List <Territory> territories;
    private List<TerritoryCard> territoryCards; // cartas para troca
//    private ObjectiveCardDeck objective;
    private int armies;
    private boolean conquered;
    private Player enemy;
    private DestroyOpponentObjectiveCard destroyObjective;
    private ConquerTwoContinentsObjectiveCard conquerTwoContinentsObjective;
    private ConquerThreeContinentsObjectiveCard conquerThreeContinentsObjective;
    private Conquer18TerritoriesObjectiveCard conquer18TerritoriesObjectiveCard;
    private Conquer24TerritoriesObjectiveCard conquer24TerritoriesObjectiveCard;
    private TerritoryCardDeck cardDeck;
    
    
    public int getCards () {
        return territoryCards.size();
    }

    public String getObjectiveName() {
        Object objective = getObjective();

        if (objective instanceof DestroyOpponentObjectiveCard) {
            return ((DestroyOpponentObjectiveCard) objective).getName();
        } else if (objective instanceof ConquerTwoContinentsObjectiveCard) {
            return ((ConquerTwoContinentsObjectiveCard) objective).getName();
        } else if (objective instanceof ConquerThreeContinentsObjectiveCard) {
            return ((ConquerThreeContinentsObjectiveCard) objective).getName();
        } else if (objective instanceof Conquer18TerritoriesObjectiveCard) {
            return ((Conquer18TerritoriesObjectiveCard) objective).getName();
        } else if (objective instanceof Conquer24TerritoriesObjectiveCard) {
            return ((Conquer24TerritoriesObjectiveCard) objective).getName();
        } else {
            return "No objective";
        }
    }

    public List<Territory> getConqueredTerritories() {
        return this.territories;
    }

    // public void reset() {

    // }

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
    
    // public boolean verifica_territorio_reg(String pais) {
    // 	for (Territory territory : this.territories) {
    // 		if(terr.get_nome() == pais) {
    // 			for(ExercitoRegiao exer_reg : this.exercitos_regiao) {
    // 				if((terr.get_Regiao() == exer_reg.get_regiao() && exer_reg.get_exercito() > 0)) {
    //     				return true;
    //     			}
    // 			}
    // 			return false;
    // 		}
    // 	}
    // 	return false;
    // }

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

    public void riceiveObjective(DestroyOpponentObjectiveCard objetivo) {
        destroyObjective = objetivo;
        objetivo.checkOwner(this);
    }
    public void riceiveObjective(ConquerTwoContinentsObjectiveCard objetivo) {
        conquerTwoContinentsObjective = objetivo;
        objetivo.checkOwner(this);
    }
    public void riceiveObjective(ConquerThreeContinentsObjectiveCard objetivo) {
        conquerThreeContinentsObjective = objetivo;
        objetivo.checkOwner(this);
    }
    public void riceiveObjective(Conquer18TerritoriesObjectiveCard objetivo) {
        conquer18TerritoriesObjectiveCard = objetivo;
        objetivo.checkOwner(this);
    }
    public void riceiveObjective(Conquer24TerritoriesObjectiveCard objetivo) {
        conquer24TerritoriesObjectiveCard = objetivo;
        objetivo.checkOwner(this);
    }
    
    public DestroyOpponentObjectiveCard getDestroyObjective() {
        return this.destroyObjective;
    }

    public ConquerTwoContinentsObjectiveCard getConquerTwoContinentsObjective() {
        return this.conquerTwoContinentsObjective;
    }
    
    public ConquerThreeContinentsObjectiveCard getConquerThreeContinentsObjective() {
        return this.conquerThreeContinentsObjective;
    }
    public Conquer18TerritoriesObjectiveCard getConquer18TerritoriesObjectiveCard() {
        return this.conquer18TerritoriesObjectiveCard;
    }
    public Conquer24TerritoriesObjectiveCard getConquer24TerritoriesObjectiveCard() {
        return this.conquer24TerritoriesObjectiveCard;
    }
    
    public Object getObjective() {
        if (destroyObjective != null) {
            return destroyObjective;
        } else if (conquerTwoContinentsObjective != null) {
            return conquerTwoContinentsObjective;
        }else if (conquerThreeContinentsObjective != null) {
            return conquerThreeContinentsObjective;
        }else if (conquer18TerritoriesObjectiveCard != null) {
            return conquer18TerritoriesObjectiveCard;
        }else if (conquer24TerritoriesObjectiveCard != null) {
            return conquer24TerritoriesObjectiveCard;
        }
        
        return null;
    }

    public List<TerritoryCard> getCard(){
    	return this.territoryCards;
    }

    public boolean addCard(TerritoryCard card) {
        if(this.conquered) {
            this.conquered = false;
            if(this.territoryCards.size() < 5) {
                this.territoryCards.add(card);
                return true;
            }
        }
        return false;
    }

    public boolean getConquered() {
        return this.conquered;
    }

    public void addArmyTraded(int army) {
        this.armies += army;
    }

    public void addArmy() { 
    	this.armies += this.territories.size()/2;
    }

    // Troca de cartas e adição de soldados após validação de troca
    public void exchangeCards(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3, List<TerritoryCard> cards, List <Territory> territories){
        // Verificação de troca
        if(cardDeck.evaluateCardTrade(card1, card2, card3)){
            // Adicionar exércitos com base na troca realizada
            this.addArmyTraded(cardDeck.calculateTradeBonus());

            // Remover cartas trocadas da lista
            cards.removeAll(Arrays.asList(card1, card2, card3));

            // Iterar sobre as cartas trocadas
            for(TerritoryCard cardTraded : Arrays.asList(card1, card2, card3)) {
                // Iterar sobre os territórios
                for(Territory territory : territories) {
                    // Verificar se a carta trocada é um território do jogador
                    if(territory.getName() == cardTraded.getName()) {
                        // Adicionar dois exércitos ao território
                        territory.addArmies(2);
                    }
                }
            }
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

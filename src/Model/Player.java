package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Hashtable;
//
/**
 * Representa um jogador no jogo.
 */
public class Player {

    private String name;
    private PlayerColor color;
    private List <Territory> territories;
    private List<TerritoryCard> territoryCards;
    private ObjectiveCardDeck objective;
    private int armies;
    private boolean conquered;
    private Player enemy;

    
    public int getCards () {
        return territoryCards.size();
    }

    // public String getObjectiveName() {
    //     return objective.getName();
    // }

    public List<Territory> getConqueredTerritories() {
        return this.territories;
    }

    // public void reset() {

    // }

    // public void ganha_cartas_jogador_destruido(List<CartaConquista> cartas,ConjuntoCartaConquista deck){
    // 	this.cartaTroca.addAll(cartas);
	// 	Random rand = new Random();
    // 	while(this.cartaTroca.size() > 5) {
    // 		CartaConquista carta_escolhida = this.cartaTroca.remove(rand.nextInt(this.cartaTroca.size()));
    // 		deck.carta_retorna_deck(carta_escolhida);
    // 	}
    // }

    public List<TerritoryCard> getAllCards() {
        List<TerritoryCard> cartas = getCard().stream().collect(Collectors.toList());
        territoryCards.clear();
        return cartas;
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

    public int getArmySize() {
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
        territories.add(territory);
    }

    public boolean verifyDestroyed() {
        if (territories.isEmpty()) {
            return false;
        }
        return true;
    }

    public Player destroyedPlayer(Player player) {
        this.enemy = player;
        return this.enemy;
    }

    ///rever depois
    public ObjectiveCardDeck getObjective(ObjectiveCardDeck objective) {
        this.objective = objective;
        return this.objective;  
    }
    ///

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

    public void addArmy(int army) {
        this.armies += army;
    }

    public void addArmy() { //add exercito de acordo com metade dos territórios
    	//System.out.println("CHAMOU AUMENTA EXERCITO BASEADO TERRITORIO = " + this.domina.size()/2);
    	this.armies += this.territories.size()/2;
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


package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Hashtable;

/**
 * Representa um jogador no jogo.
 */
public class Player {

    private String name;
    private List <Territory> territories;
    private int armies;
    private PlayerColor color;
    private ObjectiveCard objective;
    private ArrayList<TerritoryCard> territoryCards;

    
    //cartas
    private List<String> deckTerritoryCards;

    //dados
    private int d1 = 6;
    private int d2 = 6;
    private boolean estaEscolhendoDados = false;
    private boolean jogouDadosNaRodada = false;

    /**
     * Construtor da classe Player.
    *
    * @param name  O nome do jogador.
    */
    public Player(String name) {
        this.name = name;
        this.deckTerritoryCards = new ArrayList<String>();
        this.territories = new ArrayList<>();
        this.armies = 0;
    }

    // public Player(String name, PlayerColor color) {
    //     this.name = name;
    //     this.color = color;
    //     this.territories = new ArrayList<>();
    //     this.armies = 0;
    //     this.objective = null;
    //     this.territoryCards = new ArrayList<TerritoryCard>();
    // }

    /**
     * Remove uma quantidade específica de exércitos do jogador.
     *
     * @param numArmies A quantidade de exércitos a ser removida.
     * @param territory O territorio em que sera removido os esxercitos
     */
    public void removeArmies(Territory territory, int numArmies) {
        territory.removeArmies(numArmies);
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
     * Adiciona uma carta de território ao deck do jogador.
     *
     * @param card A carta de território a ser adicionada ao deck.
     */
    public void addTerritoryCard(String card) {
        this.deckTerritoryCards.add(card);
    }

    /**
     * Obtém a lista de cartas de território no deck do jogador.
     *
     * @return A lista de cartas de território no deck do jogador.
     */
    public List<String> getTerritoryDeck() {
        return this.deckTerritoryCards;
    }

    //dados
    /**
     * Define os valores dos dados lançados pelo jogador.
     *
     * @param d1 O valor do primeiro dado.
     * @param d2 O valor do segundo dado.
     */
    public void setDados(int d1, int d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    /**
     * Obtém o valor do primeiro dado.
     *
     * @return O valor do primeiro dado.
     */
    public int getD1() {
        return this.d1;
    }

    /**
     * Obtém o valor do segundo dado.
     *
     * @return O valor do segundo dado.
     */
    public int getD2() {
        return this.d2;
    }

    /**
     * Obtém o estado da jogada de dados na rodada.
     *
     * @return true se os dados foram lançados na rodada, false caso contrário.
     */
    public boolean getEstadoJogadaDados() {
        return this.jogouDadosNaRodada;
    }

    /**
     * Define o estado da jogada de dados na rodada.
     *
     * @param j true se os dados foram lançados na rodada, false caso contrário.
     */
    public void setEstadoJogadaDados(boolean j) {
        this.jogouDadosNaRodada = j;
    }

    /**
     * Obtém a lista de territórios do jogador.
     *
     * @return A lista de territórios do jogador.
     */
    public List <Territory> getTerritories() {
        return territories;
    }

    /**
     * Obtém a lista de cartas de território do jogador.
     *
     * @return A lista de cartas de território do jogador.
     */
    public List<TerritoryCard> getTerritoryCardList() {
		return Collections.unmodifiableList(territoryCards);
	}
    
    /**
     * Adiciona uma carta de território à lista do jogador.
     *
     * @param card A carta de território a ser adicionada.
     */
    // public void addTerritoryCard(TerritoryCard card) {
	// 	territoryCards.add(card);
	// }

    /**
     * Remove cartas de território da lista do jogador.
     *
     * @param card1 A primeira carta a ser removida.
     * @param card2 A segunda carta a ser removida.
     * @param card3 A terceira carta a ser removida.
     */
    public void removeTerritoryCard(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
        territoryCards.remove(card1);
        territoryCards.remove(card2);
        territoryCards.remove(card3);
    }
    
//    public void removeTerritoryCard(TerritoryCard card) {
//		territoryCards.remove(card);
//	}
    
    
    
    
    /**
     * Adiciona um território à lista de territórios do jogador.
     *
     * @param territory O território a ser adicionado.
     */
    public void addTerritory(Territory territory) {
        territories.add(territory);
    }
    
    
    /**
     * Verifica se o jogador é dono de um território específico.
     *
     * @param t O território a ser verificado.
     * @return true se o jogador for dono do território, false caso contrário.
     */
    public boolean ownsTerritory(Territory t) {
        return territories.contains(t);
    }

    
    /**
     * Remove um território da lista de territórios do jogador.
     *
     * @param territory O território a ser removido.
     */
    public void removeTerritory(Territory territory) {
        territories.remove(territory);
    }
    
    /**
     * Obtém a quantidade de exércitos do jogador.
     *
     * @return A quantidade de exércitos do jogador.
     */
    public int getArmies() {
        return armies;
    }

    /**
     * Adiciona uma quantidade específica de exércitos ao jogador.
     *
     * @param count A quantidade de exércitos a ser adicionada.
     */
    public void addArmies(int count) {
        armies += count;
    }

    /**
     * Remove uma quantidade específica de exércitos do jogador.
     *
     * @param count A quantidade de exércitos a ser removida.
     */
    public void removeArmies(int count) {
        armies -= count;
        if (armies < 0) {
            armies = 0;
        }
    }

    /**
     * Verifica se o jogador possui todos os territórios de um continente específico.
     *
     * @param c O continente a ser verificado.
     * @return true se o jogador possuir todos os territórios do continente, false caso contrário.
     */
    public boolean hasEntireContinent(Continent c) {
		for (Territory t : c.getTerritories()) {
			if (t.getOwner() != this) {
				return false;
			}
		}
		return true;
	}
    
    private Hashtable<Continent, Integer> unspentContinentalSoldiers = new Hashtable<Continent, Integer>();
    
    /**
     * Obtém a quantidade de soldados continentais não gastos para um continente específico.
     *
     * @param c O continente para o qual obter a quantidade de soldados continentais.
     * @return A quantidade de soldados continentais não gastos para o continente.
     */
    public int getUnspentContinentalSoldierCount(Continent c) {
		return unspentContinentalSoldiers.get(c);
	}
    
    /**
     * Adiciona soldados continentais não gastos para um continente específico.
     *
     * @param c      O continente para o qual adicionar soldados continentais.
     * @param amount A quantidade de soldados continentais a ser adicionada.
     */
    public void addContinentalSoldiers(Continent c, int amount) {
		unspentContinentalSoldiers.putIfAbsent(c, 0);
		
		Integer i = unspentContinentalSoldiers.get(c);
		unspentContinentalSoldiers.put(c, i + amount);
	}

    /**
     * Obtém o objetivo do jogador.
     *
     * @return O objetivo do jogador.
     */
    public ObjectiveCard getObjective() {
        return objective;
    }

    /**
     * Define o objetivo do jogador.
     *
     * @param objective O objetivo a ser definido.
     */
    public void setObjective(ObjectiveCard objective) {
        this.objective = objective;
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


package Model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Representa uma partida do jogo.
 */
class Match {
	private Map map;	
	private List <Player> players;
	private boolean hasMatchedStarted;
	private ArrayList<TerritoryCard> allCards = new ArrayList<TerritoryCard>();
	private int tradeCounter = 0;
	private static final int[] tradeBonusAmount = new int[] {
            4, 6, 8, 10, 12, 15
    };
	
	/**
     * Construtor da classe Match.
     *
     * @param players Lista de jogadores que participarão da partida.
     * @param map     Mapa do jogo.
     */
	public Match(List<Player> players, Map map) {
	    this.players = players;
	    this.map = map;
	}
	
	/**
     * Verifica se a partida já começou.
     *
     * @return true se a partida já começou, false caso contrário.
     */
	public boolean hasStarted() {  
		return hasMatchedStarted;
	}
	
	/**
     * Obtém a lista de jogadores na partida.
     *
     * @return Lista de jogadores.
     */
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	/**
     * Obtém o mapa do jogo.
     *
     * @return Mapa do jogo.
     */
	public Map getMap() {
		return map;
	}

	/**
     * Obtém uma carta de território com base em seu ID.
     *
     * @param id ID da carta de território.
     * @return Carta de território correspondente ao ID.
     */
	public TerritoryCard getCardById(int id) {
		return allCards.get(id);
	}

	/**
     * Distribui territórios aleatórios para os jogadores.
     */
	private void giveRandomTerritoriesToPlayers() {
	    List<Player> players = getPlayers();
	    int numPlayers = players.size();
	    int numTerritories = map.getNumberTerritories();

	    int territoriesPerPlayer = numTerritories / numPlayers;
	    int remainder = numTerritories % numPlayers;

	    int territoryIndex = 0;

	    List<Integer> territoryIndices = new ArrayList<>();
	    for (int i = 0; i < numTerritories; i++) {
	        territoryIndices.add(i);
	    }

	    
	    Collections.shuffle(territoryIndices, new Random());

	    
	    for (Player player : players) {
	        int territoriesToAssign = territoriesPerPlayer;
	        if (remainder > 0) {
	            territoriesToAssign++;
	            remainder--;
	        }


	        for (int i = 0; i < territoriesToAssign; i++) {
	            int index = territoryIndices.get(territoryIndex);
	            Territory territory = map.getTerritory(index); 
	            player.addTerritory(territory);
	            territory.setOwner(player);
	            territory.addArmies(1); 
	            territoryIndex++;
	        }
	    }
	}


	/**
     * Distribui exércitos para os jogadores com base na metade do número de territórios.
     */
	private void distributeArmiesToPlayers() {
		for (Player player : players) {
			int amt = player.getTerritories().size() / 2;
			player.addArmies(amt); 
		}
	}

	/**
     * Posiciona exércitos para jogadores que possuem um continente inteiro.
     */
	private void distributeContinentalArmiesToPlayers() {
    for (Player player : players) {
        for (Continent continent : map.getContinents()) {
            if (player.hasEntireContinent(continent)) { 
                player.addContinentalSoldiers(continent, continent.getBonusArmies());
            }
        }
    }
	}
	
	/**
     * Avalia se um trio de cartas de território é elegível para um bônus.
     *
     * @param c1 Carta 1
     * @param c2 Carta 2
     * @param c3 Carta 3
     * @return true se o trio é elegível para um bônus, false caso contrário.
     */
	private static boolean evaluateCardTrioBonus(TerritoryCard c1, TerritoryCard c2, TerritoryCard c3) {
	    
	    String shape1 = c1.getShape();
	    String shape2 = c2.getShape();
	    String shape3 = c3.getShape();

	    // Verifica se são 3 cartas com formas diferentes (círculo, quadrado, triângulo)
	    if ((shape1.equals("Círculo") && shape2.equals("Quadrado") && shape3.equals("Triângulo")) ||
	        (shape1.equals("Triângulo") && shape2.equals("Círculo") && shape3.equals("Quadrado")) ||
	        (shape1.equals("Quadrado") && shape2.equals("Triângulo") && shape3.equals("Círculo"))) {
	        return true;
	    }

	    // Verifica se são 3 cartas com a mesma forma
	    if (shape1.equals(shape2) && shape2.equals(shape3)) {
	        return true;
	    }

	    // Verifica se são 2 cartas com a mesma forma + coringa
	    if ((shape1.equals(shape2) || shape1.equals(shape3) || shape2.equals(shape3)) &&
	        (shape1.equals("Coringa") || shape2.equals("Coringa") || shape3.equals("Coringa"))) {
	        return true;
	    }

	    // Verifica se são 2 cartas com formas diferentes + coringa
	    if ((shape1.equals("Círculo") && shape2.equals("Quadrado") && shape3.equals("Coringa")) ||
	        (shape1.equals("Quadrado") && shape2.equals("Triângulo") && shape3.equals("Coringa")) ||
	        (shape1.equals("Triângulo") && shape2.equals("Círculo") && shape3.equals("Coringa")) ||
	        (shape1.equals("Círculo") && shape2.equals("Coringa") && shape3.equals("Quadrado")) ||
	        (shape1.equals("Quadrado") && shape2.equals("Coringa") && shape3.equals("Triângulo")) ||
	        (shape1.equals("Triângulo") && shape2.equals("Coringa") && shape3.equals("Círculo")) ||
	        (shape1.equals("Coringa") && shape2.equals("Círculo") && shape3.equals("Quadrado")) ||
	        (shape1.equals("Coringa") && shape2.equals("Quadrado") && shape3.equals("Triângulo")) ||
	        (shape1.equals("Coringa") && shape2.equals("Triângulo") && shape3.equals("Círculo"))) {
	        return true;
	    }

	    // Verifica se é 1 carta com forma + 2 coringas
	    if ((shape1.equals("Círculo") && shape2.equals("Coringa") && shape3.equals("Coringa")) ||
	        (shape1.equals("Quadrado") && shape2.equals("Coringa") && shape3.equals("Coringa")) ||
	        (shape1.equals("Triângulo") && shape2.equals("Coringa") && shape3.equals("Coringa"))||
	        
	        (shape1.equals("Coringa") && shape2.equals("Círculo") && shape3.equals("Coringa")) ||
	        (shape1.equals("Coringa") && shape2.equals("Quadrado") && shape3.equals("Coringa")) ||
	        (shape1.equals("Coringa") && shape2.equals("Triângulo") && shape3.equals("Coringa"))||
	        
	        (shape1.equals("Coringa") && shape2.equals("Coringa") && shape3.equals("Círculo")) ||
	        (shape1.equals("Coringa") && shape2.equals("Coringa") && shape3.equals("Quadrado")) ||
	        (shape1.equals("Coringa") && shape2.equals("Coringa") && shape3.equals("Triângulo"))) {
	        return true;
	    }

	    // Verifica se são 3 coringas
	    if (shape1.equals("Coringa") && shape2.equals("Coringa") && shape3.equals("Coringa")) {
	        return true;
	    }

	    
	    return false;
	}


	/**
     * Realiza a troca de cartas, adicionando exércitos ao jogador com base nas cartas trocadas.
     *
     * @param player Jogador que está realizando a troca.
     * @param card1  Carta 1
     * @param card2  Carta 2
     * @param card3  Carta 3
     */
	public void doCardsTrade(Player player, TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
	    
	    if (evaluateCardTrioBonus(card1, card2, card3)) {
	    	addArmiesForTradedCards(player, card1, 2);
            addArmiesForTradedCards(player, card2, 2);
            addArmiesForTradedCards(player, card3, 2);

	        int bonus = calculateTradeBonus();

	        player.addArmies(bonus);

	        player.removeTerritoryCard(card1, card2, card3);

	        tradeCounter++;
	    }
	}
	
	/**
     * Adiciona exércitos a um território associado à carta trocada.
     *
     * @param player Jogador que está realizando a troca.
     * @param card   Carta que está sendo trocada.
     * @param armies Número de exércitos a serem adicionados.
     */
	private void addArmiesForTradedCards(Player player, TerritoryCard card, int armies) {
        if (player.ownsTerritory(card.getTerritory())) {
            card.getTerritory().addArmies(armies);
        }
    }

	/**
     * Calcula o bônus de troca com base no contador de trocas.
     *
     * @return Bônus de troca calculado.
     */
	private int calculateTradeBonus() {
	    int bonus;
	    if (tradeCounter >= tradeBonusAmount.length) {
	        bonus = tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5;
	    } else {
	        bonus = tradeBonusAmount[tradeCounter];
	    }
	    return bonus;
	}

	/**
     * Inicia a partida, distribuindo territórios, exércitos e bônus continentais.
     */
	public void startMatch() {
		giveRandomTerritoriesToPlayers();
		distributeArmiesToPlayers();
		distributeContinentalArmiesToPlayers();

		hasMatchedStarted = true;
	}
}
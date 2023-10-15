package Model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Match {
	private Map map;	
	private List <Player> players;
	private boolean hasMatchedStarted;
	private ArrayList<TerritoryCard> allCards = new ArrayList<TerritoryCard>();
	private int tradeCounter = 0;
	private static int[] tradeBonusAmount = new int[] {
			4, 6, 8, 10, 12, 15
	};
	
	public boolean hasStarted() {
		return hasMatchedStarted;
	}
	
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public Map getMap() {
		return map;
	}

	public TerritoryCard getCardById(int id) {
		return allCards.get(id);
	}

	// tarefa 2 - distribuição de territórios para os jogadores
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


	// tarefa 3 - Distribuir exércitos correspondentes à metade do número de territórios que o jogador da vez possui
	private void distributeArmiesToPlayers() {
    for (Player player : players) {
        int amt = player.getTerritories().size() / 2;
        player.addArmies(amt); 
    }
	}

	// tarefa 4 - Posicionar exércitos para jogadores que possuem um continente inteiro
	private void distributeContinentalArmiesToPlayers() {
    for (Player player : players) {
        for (Continent continent : map.getContinents()) {
            if (player.hasEntireContinent(continent)) { 
                player.addContinentalSoldiers(continent, continent.getValue());
            }
        }
    }
	}
	
	// Avaliar com as cartas de território
	private static boolean evaluateCardTrioBonus(TerritoryCard c1, TerritoryCard c2, TerritoryCard c3) {
    return c1.getShape().isSameShape(c2.getShape()) && c1.getShape().isSameShape(c3.getShape());
}

	// tarefa 5 - Posicionar exércitos de acordo com a troca de cartas
	public void doCardsTrade(Player player, TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
	    
	    if (evaluateCardTrioBonus(card1, card2, card3)) {
	        if (player.ownsTerritory(card1.getTerritory())) {
	            card1.getTerritory().addArmies(2);
	        }
	        if (player.ownsTerritory(card2.getTerritory())) {
	            card2.getTerritory().addArmies(2);
	        }
	        if (player.ownsTerritory(card3.getTerritory())) {
	            card3.getTerritory().addArmies(2);
	        }

	        // Calculate the trade bonus based on the tradeCounter
	        int bonus = calculateTradeBonus();

	        // Add the bonus soldiers to the player's total
	        player.addArmies(bonus);

	        // Remove the traded cards from the player's hand
	        player.removeTerritoryCard(card1);
	        player.removeTerritoryCard(card2);
	        player.removeTerritoryCard(card3);

			// Increment the trade counter
	        tradeCounter++;
	    }
	}

	private int calculateTradeBonus() {
	    int bonus;
	    if (tradeCounter >= tradeBonusAmount.length) {
	        bonus = tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5;
	    } else {
	        bonus = tradeBonusAmount[tradeCounter];
	    }
	    return bonus;
	}


	public void startMatch() {
		giveRandomTerritoriesToPlayers();
		distributeArmiesToPlayers();
		distributeContinentalArmiesToPlayers();

		hasMatchedStarted = true;
	}
}
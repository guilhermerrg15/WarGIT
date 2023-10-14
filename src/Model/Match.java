package Model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Match {
	private Map map;	
	private List <Player> players;
	private boolean hasMatchedStarted;
	private ArrayList<TerritoryCard> allCards = new ArrayList<TerritoryCard>();

	public boolean startMatch() {
		return hasMatchedStarted;
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
    List<Territory> territories = map.getAllTerritories(); // Fazer um método que pegue todos os territórios
    
    // Embaralhar lista de territórios
    Collections.shuffle(territories, new Random());
    
    int numTerritories = territories.size();
    int numPlayers = players.size();
    
    int territoriesPerPlayer = numTerritories / numPlayers;
    int remainder = numTerritories % numPlayers;

    int territoryIndex = 0;

		// Atribuir territórios para cada jogador
    for (Player player : players) {
        int territoriesToAssign = territoriesPerPlayer;
        if (remainder > 0) {
            territoriesToAssign++;
            remainder--;
        }
				
				// Designar territórios para o jogador atual
        for (int i = 0; i < territoriesToAssign; i++) {
            Territory territory = territories.get(territoryIndex);
            player.addTerritory(territory);
            territory.setOwner(player);
            territory.addArmies(1); // Adicionar exército no território designado ao jogador
            territoryIndex++;
        }
    }
	}

	// tarefa 3 - Distribuir exércitos correspondentes à metade do número de territórios que o jogador da vez possui
	private void distributeGlobalArmiesToPlayers() {
    for (Player player : players) {
        int amt = player.getTerritoryCount() / 2;
        player.addGlobalArmies(amt); // adicionar método no model Player?
    }
	}

	// tarefa 4 - Posicionar exércitos para jogadores que possuem um continente inteiro
	private void distributeContinentalArmiesToPlayers() {
    for (Player player : players) {
        for (Continent continent : map.getContinents()) {
            if (player.hasEntireContinent(continent)) { // criar hasEntireContinent no model Player
                player.addContinentalSoldiers(continent, continent.getValue()); // criar addContinentalSoldiers no model Player
            }
        }
    }
	}
	
	// Avaliar com as cartas de território
	private static boolean evaluateCardTrioBonus(TerritoryCard c1, TerritoryCard c2, TerritoryCard c3) {
    return c1.getShape().isSameShape(c2.getShape()) && c1.getShape().isSameShape(c3.getShape());
}

	// tarefa 5 - Posicionar exércitos de acordo com a troca de cartas
	public void doCardTrade(Player player, TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
    if (evaluateCardTrioBonus(card1, card2, card3)) {
        for (TerritoryCard card : Arrays.asList(card1, card2, card3)) {
            Territory territory = card.getTerritory();
            if (player.ownsTerritory(territory)) {
                territory.addArmies(2);
            }
            player.removeTerritoryCard(card); // adicionar isso no model Player?
            unclaimedTerritoryCards.addLast(card); // precisa disso?
        }
        
        int bonus = tradeCounter >= tradeBonusAmount.length ?
            tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5 :
            tradeBonusAmount[tradeCounter];
        
        player.addGlobalArmies(bonus); // adicionar método no model Player?
        tradeCounter++;
    } 
	}

	public void startMatch() {
		giveRandomTerritoriesToPlayers()
		distributeGlobalArmiesToPlayers()
		distributeContinentalArmiesToPlayers()

		hasMatchedStarted = true
	}
}
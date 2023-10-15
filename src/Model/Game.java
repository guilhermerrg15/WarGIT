package Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
	private List<Player> players;
	private List<ObjectiveCard> availableObjectives;
	private Match match;
	private Shape shape; // nao sei se precisa disso 
	
    public Game() {
    	players = new ArrayList<>();
        availableObjectives = initializeObjectiveCards();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void startGame() {
    	
        shufflePlayers();

    }

    public Player getPlayerByColor(PlayerColor color) {
        for (Player player : players) {
            if (player.getColor() == color) {
                return player;
            }
        }
        return null;
    }
    
    private void shufflePlayers() {
        long seed = System.nanoTime();
        Collections.shuffle(players, new Random(seed));
    }
    
    private List<ObjectiveCard> initializeObjectiveCards() {
        List<ObjectiveCard> objectives = new ArrayList<>();

        objectives.add(new ObjectiveCard("Conquistar 24 territórios a sua escolha", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a Asia e a Africa", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a Asia e a America do Sul", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a America do Norte e a Africa", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a America do Norte e a Oceania", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a Europa, a Oceania e mais um continente a sua escolha", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar na totalidade a Europa, a America do Sul e mais um continente a sua escolha", "Território alvo"));
        objectives.add(new ObjectiveCard("Conquistar 18 territórios com pelo menos 2 exércitos em cada", "Território alvo"));
        

        return objectives;
    }

    public List<ObjectiveCard> getAvailableObjectives() {
        return availableObjectives;
    }

		public void doCardTrade(PlayerColor player, int card1, int card2, int card3) {
			match.doCardTrade(getPlayerByColor(player),match.getCardById(card1),match.getCardById(card2),match.getCardById(card3));
		}

		// public String territoryCardDescription(int cardId) {
		// 	return match.getCardById(cardId).getDescription();
		// }

		public String getTerritoryCardTerritory(int id) {
			if(id == 0 || id == 1) {
				return "Joker Card";
			}
			return match.getCardById(id).getName();
		}

		public void startMatch() {
			match = new Match(players, map);
			match.startMatch();
		}

		public Shape getTerritoryCardShapeById(int id) {
			return match.getCardById(id).getShape();
		}

		public PlayerColor getTerritoryCardOwner(int id) {
	    for (Player player : match.getPlayers()) {
        for (TerritoryCard card : player.getTerritoryCardList()) {
            if (card.getId() == id) {
                return player.getColor();
            }
        }
	    }
    
	    return null;
		}
}
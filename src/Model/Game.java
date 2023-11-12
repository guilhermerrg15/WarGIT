package Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * A classe Game representa o estado e a lógica do jogo.
 */
class Game {
	private List<Player> players = new ArrayList<>();
    private List<ObjectiveCard> availableObjectives;
    private Match match;
    private Map map;
    private String mapForegroundPath;
    private String mapBackgroundPath;
    private ObjectiveCardDeck objectiveCardDeck = ObjectiveCardDeck.getInstance();
	
    /**
     * Obtém o caminho para a imagem de primeiro plano do mapa.
     *
     * @return O caminho para a imagem de primeiro plano do mapa.
     */
    public String getMapForegroundPath() {
        return mapForegroundPath;
    }
	
    /**
     * Obtém o caminho para a imagem de fundo do mapa.
     *
     * @return O caminho para a imagem de fundo do mapa.
     */
    public String getMapBackgroundPath() {
        return mapBackgroundPath;
    }

	 /**
     * Construtor da classe Game.
     */
    public Game() {
    	initializeObjectiveCards();
    }

    /**
     * Adiciona um jogador à lista de jogadores.
     *
     * @param player O jogador a ser adicionado.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    /**
     * Obtém um jogador com base na cor.
     *
     * @param color A cor do jogador.
     * @return O jogador com a cor especificada ou null se não encontrado.
     */
    public Player getPlayerByColor(PlayerColor color) {
        for (Player player : players) {
            if (player.getColor() == color) {
                return player;
            }
        }
        return null;
    }
    
    /**
     * Embaralha a ordem dos jogadores.
     */
    private void shufflePlayers() {
        long seed = System.nanoTime();
        Collections.shuffle(players, new Random(seed));
    }

    

    /**
     * Obtém os nomes de todos os territórios no jogo.
     *
     * @return Uma lista de nomes de territórios.
     */
    public List<String> getAllTerritoryNames() {
		ArrayList<String> ret = new ArrayList<String>();
		
		for (Continent c : map.getContinents()) {
			for (Territory t : c.getTerritories()) {
				ret.add(t.getName());
			}
		}
		
		return ret;
	}

    /**
     * Obtém as coordenadas do centro de um território.
     *
     * @param territoryName O nome do território.
     * @return As coordenadas do centro do território.
     */
    public Coordinates getTerritoryCenter(String territoryName) {
		return map.findTerritory(territoryName).getCenter();
	}

    /**
     * Obtém as coordenadas dos vértices de um território.
     *
     * @param territoryName O nome do território.
     * @return As coordenadas dos vértices do território.
     */
	public Coordinates[] getTerritoryVertices(String territoryName) {
		return map.findTerritory(territoryName).getVertices();
	}

	/**
     * Inicializa as cartas de objetivo do jogo usando o ObjectiveCardDeck.
     */
    private void initializeObjectiveCards() {
        ObjectiveCard[] objectiveArray = objectiveCardDeck.setCartasEmbaralhadas();
        availableObjectives = new ArrayList<>(List.of(objectiveArray));
    }
    
	/**
     * Obtém as cartas de objetivo disponíveis.
     *
     * @return Uma lista de cartas de objetivo disponíveis.
     */
    public List<ObjectiveCard> getAvailableObjectives() {
        return availableObjectives;
    }

    /**
     * Realiza a troca de cartas de território por um jogador.
     *
     * @param player O jogador que realiza a troca.
     * @param card1  O ID da primeira carta.
     * @param card2  O ID da segunda carta.
     * @param card3  O ID da terceira carta.
     */
	public void doCardTrade(PlayerColor player, int card1, int card2, int card3) {
		match.doCardsTrade(getPlayerByColor(player),match.getCardById(card1),match.getCardById(card2),match.getCardById(card3));
	}



	/**
     * Obtém o nome do território associado a uma carta de território.
     *
     * @param id O ID da carta de território.
     * @return O nome do território associado à carta.
     */
	public String getTerritoryCardTerritory(int id) {
		if(id == 0 || id == 1) {
			return "Joker Card";
		}
		return match.getCardById(id).getName();
	}

	 /**
     * Inicia uma nova partida, embaralhando a ordem dos jogadores.
     */
	public void startMatch() {
        if (players.size() >= 2) {
            shufflePlayers();
            match = new Match(players, map);
            match.startMatch();
        } else {
            System.out.println("Número insuficiente de jogadores para iniciar a partida.");
        }
    }

    /**
     * Obtém a cor do jogador que possui uma determinada carta de território.
     *
     * @param id O ID da carta de território.
     * @return A cor do jogador que possui a carta.
     */
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
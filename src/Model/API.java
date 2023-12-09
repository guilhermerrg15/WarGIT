package Model;
import View.ViewAPI;

import java.util.*;

import Controller.APIController;

// //import Controller.TabuleiroObservador;

public class API {

    private static API apiInstance = null;
    private ViewAPI viewInstance = ViewAPI.getInstance();
    private Map map;
    private Game game = Game.getInstance();
    private Dado dado;
    private Player player;
    private ObjectiveCardDeck objectiveDeck;
    private ArrayList<Player> players;
    public int turn;
    private int tradeCounter = 0;
	private static final int[] tradeBonusAmount = new int[] { 4, 6, 8, 10, 12, 15 };

    private API() {
        map = this.initMap();
        dado = new Dado();
        // players = new ArrayList<>();
    }

    // Singleton
    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }
        return apiInstance;
    }

    // Método para reiniciar o jogo
    public void reiniciarJogo(){
        game.reiniciarJogo();
        game.setMod1(null);
        game.setMod2(null);
    }


    // Método para obter a quantidade de territórios de um jogador específico
    public int getNumTerritoryPlayer(PlayerColor corDoJogador) {
        int quantidadeTerritorios = 0;

        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o território pertence ao jogador da cor específica
            if (territory.getOwner().getColor() == corDoJogador) {
                quantidadeTerritorios++;
            }
        }
        return quantidadeTerritorios;
    }

    public String[] getTerritoriosDoJogador (PlayerColor corDoJogador){
        int quant = 0;

        // Obtém a lista de territórios do objeto Map
        ArrayList<Territory> territories = map.getTerritoriesList();
        String[] territoryList = new String[getNumTerritoryPlayer(corDoJogador)];

        // Adiciona na lista os nomes dos territórios
    	for (Territory t: territories) {
            if (t.getOwner().getColor() == corDoJogador) {
                territoryList[quant] = t.getName();
                quant++;
            }
    		
    	}
    	return territoryList;


    }

    //pega vizinhos que não são do jogador
    public String[] getNeiboursNotDominated(String t, int vez) {
        List<Territory> listaTerritorios = map.getTerritoriesList();
        List<String> territoriosNaoDominados = new ArrayList<>();
    
        for (Territory ter : listaTerritorios) {
            // Verifica se o território é vizinho e não é dominado pelo jogador
            if (ter.isNeighbor(t) && !ter.getOwner().getName().equals(game.getJogadorVez(vez).getName())) {
                territoriosNaoDominados.add(ter.getName());
            }
        }
    
        // Se não houver territórios não dominados, retorna null
        if (territoriosNaoDominados.isEmpty()) {
            return null;
        }
    
        // Converte a lista para um array
        return territoriosNaoDominados.toArray(new String[0]);
    }

    //pega territorios vizinhos que tem mais de um exercito
   public String[] getTerritoryMoreOne(PlayerColor corDoJogador) {
        int quant = 0;
    
        // Obtém a lista de territórios do objeto Map
        ArrayList<Territory> territories = map.getTerritoriesList();
        List<String> territoriosComMaisDeUmExercito = new ArrayList<>();
    
        // Adiciona na lista os nomes dos territórios com mais de um exército
        for (Territory t : territories) {
            if (t.getOwner().getColor() == corDoJogador && t.getArmies() > 1) {
                territoriosComMaisDeUmExercito.add(t.getName());
                quant++;
            }
        }
    
        // Se não houver territórios com mais de um exército, retorna null
        if (territoriosComMaisDeUmExercito.isEmpty()) {
            return null;
        }
    
        // Copia a lista para uma lista final, removendo espaços vazios
        String[] territoriosFinal = new String[quant];
        territoriosComMaisDeUmExercito.toArray(territoriosFinal);
    
        return territoriosFinal;
    }
    

    // Retornar todos os jogadores
    public ArrayList<Player> getAllPlayers() {
        return this.game.getPlayers();
    }

    // Método que retorna a quantidade de jogadores
    public int getNumPlayers(){
        return game.getPlayers().size();
    }

     // Retorna lista de nomes de territórios
     public String[] getTerritoriesList() {
		String [] terr = new String[51]; 
		int cont = 0;
		for (Territory t: map.getTerritoriesList()) {
            terr[cont] = t.getName();
            cont ++;
		}

		return terr;
	}




    public PlayerColor getTerritoryColor(String t) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();
    
        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(t)) {
                // Retorna a cor do território encontrado
                return territory.getCor();
            }
        }
        // Retorna null se o território não for encontrado
        return null;
    }

    // Retorna quantidade de exércitos que tem em um território
    public Integer getNumArmiesTerritory(String territorio) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(territorio)) {
                // Retorna a quantidade de exércitos do território encontrado
                return territory.getArmies();
            }
        }
        // Retorna null se o território não for encontrado
        return null;
    }

    // Retorna quantidade de exércitos que tem em um território
        public Integer getQntExTerritorioAntigos(String territorio) {
            // Obtém a lista de territórios do objeto Map
            List<Territory> territories = map.getTerritoriesList();

            // Percorre a lista de territórios
            for (Territory territory : territories) {
                // Verifica se o nome do território é igual ao território desejado
                if (territory.getName().equals(territorio)) {
                    // Retorna a quantidade de exércitos do território encontrado
                    return territory.getInicialArmie();
                }
            }
            // Retorna null se o território não for encontrado
            return null;
        }


     // Atualiza a quantidade de exércitos em um território
     public void incrementarNumArmiesTerritory(String territorio, int count) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(territorio)) {
                // Usa o método addArmies para incrementar a quantidade de exércitos
                territory.addArmies(count);
                return;
            }
        }
    }


    //Método de realizar ataque
    public int[] makeAttack(String atacante,String defensor) {
        Integer numAtaque = 0;
        Integer numDefesa = 0;
    	Territory Tatacante = map.findTerritory(atacante);
    	Territory Tdefensor = map.findTerritory(defensor);

        // Realiza ataque e retorna array com os resultados
        int[] array = game.RealizaAtaque(Tatacante, Tdefensor, numAtaque, numDefesa);

        // Verifica se jogador ganhou após essa rodada
		APIController.getInstance().verificaGanhou(APIController.getInstance().getTurn());
        return array;
    }


    //Verifica se o jogador da vez ganhou o jogo
    public boolean verificaGanhou(int vez){
        // Se a vez for -1, verifica todos os jogadores
        if (vez == -1){
            for (Player j: game.getPlayers()) {
                if (j.getObjective().checkStatus()){
                    // Se algum jogador ganhou, atualiza a vez
                    APIController.getInstance().setTurn(game.getPlayers().indexOf(j));
                    return true;
                }
            }
            return false;
        }
        // Se não, verifica se o jogador da vez cumpriu seu objetivo, condicao para ganhar o jogo
        Player j = game.getJogadorVez(vez);
        return j.getObjective().checkStatus();
    }


    public String[] getNomesJogadores() {
    	String[] nomes = new String[getAllPlayers().size()];
    	int cont = 0;
    	for (Player j : getAllPlayers()) {
    		nomes[cont] = j.getName();
    		cont++;
    	}
    	return nomes;
    }
    
    public Map initMap() {
        Map map = Map.getMap();
        return map;
    }
    
    public boolean startGame() {
        boolean r = game.initiateGame();
        game.add(viewInstance.getObserver());
        return r;
    }

    // Pegar carta de objetivo do jogador
    public String playerObjective(String name) {
        for(Player player : game.getPlayers()){
            if(player.getName() == name) {
                return player.getObjectiveName();
            }
        }
        return game.getPlayers().get(this.turn).getObjectiveName();
    }

    public static TerritoryCard[] getCartasTerritorio() {
        return null;
    }

    // Pegar todas as cartas de território do jogador
    public List<TerritoryCard> retrieveTerritoryCards() {
        return game.getPlayers().get(this.turn).getAllCards();
    }

    // Pegar lista de territórios em posse do jogador
    public List<Territory> retrieveTerritories() {
        return game.getPlayers().get(this.turn).getConqueredTerritories();
    }

    // Adicionar jogadores
    public boolean addPlayer(String name, PlayerColor color, int index) {
        Player player = new Player(name, color, index);
        return game.addPlayer(player);
    }

    // Pegar o nome do jogador da vez
    public String getNomeJogadorVez(int i){

        return game.getJogadorVez(i).getName();
    }
    // Método que retorna a cor do jogador da vez
    public PlayerColor getCorJogadorVez(int i){
        return game.getJogadorVez(i).getColor();
    }

    // Pegar quantidade de exércitos de um território
    public int retrieveTerritoryArmies(Territory territory) {
        return territory.getArmies();
    }

    // Pegar todos os territórios vizinhos de um dado território
    public List<String> retrieveNeighbours(Territory territory) {
        return territory.getNeighbours();
    }


    public ObjectiveCardDeck getDeckCardObjective(){
		return this.objectiveDeck;
	}
    
    public void initDeckObjective() {
    	objectiveDeck = new ObjectiveCardDeck(this.map,this.game.getPlayers());
	} 

    // Pegar o número de cartas de um jogador
    public int getPlayerNumberCards() {
        return game.getPlayers().get(this.turn).getCards();
    }

    // Verificar status de objetivo do jogador
	public boolean verifica_vez_jogador_objetivo() {
		return game.getPlayers().get(this.turn).getObjective().checkStatus();
	}

    public boolean checkPlayerTerritoryBorder(String territory, String border) {
        return game.getPlayers().get(this.turn).checkBorder(territory, border);
    }

    public boolean checkPlayerTerritory(String territory) {
        return game.getPlayers().get(this.turn).verifyTerritory(territory);
    }

    public int getQtdExercitosPosic(int turn) {
        return game.getJogadorVez(turn).getArmies();
    }

    public boolean placeArmy(int army, String territory) {
        return game.getPlayers().get(this.turn).placeArmy(army, territory);
    }
//
    // Adicionar exércitos em território da posse do jogador
    public void getPlayerAddArmy(String territory) {
        Player player = game.getPlayers().get(this.turn);
        player.setArmies(0);
        player.addArmy();
        for(Territory region : player.territories) {
            if(region.getName() == territory) {
                // Temos que fazer isso aqui!
                // player.add_exercito_regiao(region.getName(),region.getArmies());
            }
        }
    }

    public void resetPlayers() {
        game.getPlayers().clear();
    }
    
    // Sorteia objetivos para todos os jogadores
    public void shuffleObjectives(List<Player> players, ObjectiveCardDeck objectiveDeck) {
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveDeck.shuffleObjective(player);
		}
	}

    // Conferir vencedor da batalha
    public Integer[] battleWinner(List<Integer> diceAttack, List<Integer> diceDefense) {
        // Criar lista de quantidade de derrotas
        Integer[] battle = new Integer[2];
        
        // Quantidade de dados
        int attackCount = diceAttack.size();
        int defenseCount = diceDefense.size();

        // Contador de derrotas
        int countAttack = 0;
        int countDefense = 0;
        
        // Verifica que ambos ainda possuem dados a serem comparados
        while(attackCount != 0 && defenseCount != 0){
            int attackValue = 0;
            int defenseValue = 0;

            // Encontrar o maior valor de ataque
            for(int i = 0; i < attackCount; i++) {
                if(diceAttack.get(attackValue) < diceAttack.get(i)) {
                    attackValue = i;
                }
            }

            // Encontrar o maior valor de defesa
            for(int i = 0; i< defenseCount; i++) {
                if(diceDefense.get(defenseValue) < diceDefense.get(i)) {
                    defenseValue = i;
                }
            }

            // Comparar os dois maiores valores encontrados
            if(diceAttack.get(attackValue) < diceDefense.get(defenseValue)) {
                countAttack++;
            } else {
                countDefense++;
            }
        }

        // Salvar quantidade de derrotas
        battle[0] = countAttack;
        battle[1] = countDefense;

        return battle;
    }


    /**
	 * Calcula o bônus de troca com base no contador de trocas.
	 *
	 * @return Bônus de troca calculado.
	 */
	public int calculateTradeBonus() {
		int bonus;
		if (tradeCounter >= tradeBonusAmount.length) {
			bonus = tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5;
		} else {
			bonus = tradeBonusAmount[tradeCounter];
		}
		return bonus;
	}

	// Verifica troca de cartas
	public boolean evaluateCardTrade(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
		// Caso em que as 3 cartas possuem símbolos disintos
		if (card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape()
				&& card2.getShape() != card3.getShape()) {
			return true;
		}

		// Caso de uma ou mais cartas serem coringas
		if (card1.getShape() == Shape.Joker) {
			// Caso em que as duas cartas restantes possuem o mesmo símbolo
			if (card2.getShape() == card3.getShape()) {
				return true;
			}

			// Caso em que a segunda ou a terceira carta seja Coringa
			if (card2.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		} else if (card2.getShape() == Shape.Joker) {
			if (card1.getShape() == card3.getShape()) {
				return true;
			}

			if (card1.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		} else if (card1.getShape() == Shape.Joker) {
			if (card2.getShape() == card3.getShape()) {
				return true;
			}

			if (card2.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		}

		// Caso em que as 3 cartas possuem o mesmo símbolo
		if (card1.getShape() == card2.getShape() && card2.getShape() == card3.getShape()) {
			return true;
		}
		return false;
	}


    // Troca de cartas e adição de soldados após validação de troca
    public void exchangeCards(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3, List<TerritoryCard> cards, List <Territory> territories){
        // Verificação de troca
        if(evaluateCardTrade(card1, card2, card3)) {
            // Adicionar exércitos com base na troca realizada
            game.getPlayers().get(this.turn).addArmyTraded(calculateTradeBonus());

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

    // Notifica observadores de jogo
    public void notificaObsJogo(){
        game.notifyObservers();
    }

}

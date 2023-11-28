package Model;
import View.ViewAPI;

import java.awt.Color;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

import javax.swing.plaf.TreeUI;

// //import Controller.TabuleiroObservador;

public class API {

    private static API apiInstance = null;
    private ViewAPI viewInstance = ViewAPI.getInstance();
    private Map map;
    private Game game = Game.getInstance();
    private Dado dado;
    private ObjectiveCardDeck objectiveDeck;
    // private ArrayList<Player> players;
    public int turn;
    private int tradeCounter = 0;
	private static final int[] tradeBonusAmount = new int[] { 4, 6, 8, 10, 12, 15 };

    public API() {
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

    // Retornar todos os jogadores
    public ArrayList<Player> getAllPlayers() {
        return this.game.getPlayers();
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
    
    public Map getMap() {
        return map;
    }
    
    public Map initMap() {
        Map map = new Map();
        return map;
    }
    
    // Iniciar jogo
    // public boolean startGame() {
    //     return game.initiateGame();
    // }
    public boolean startGame() {
        boolean r = game.initiateGame();
        game.add(viewInstance.getObserver());
        return r;
    }

    // Pegar carta de objetivo do jogador
    public String playerObjective() {
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

//    public void addObserver(TabuleiroObservador observer) {
//        game.addObserver(observer);
//    }

    // Adicionar jogadores
    public boolean addPlayer(String name, PlayerColor color) {
        Player player = new Player(name, color);
        return game.addPlayer(player);
    }

    // Pegar o nome do jogador
    public String playerName() { 
       return game.getPlayers().get(this.turn).getName();
    }

    // Pegar o nome do jogador da vez
    public String getNomeJogadorVez(int i){
        return game.getJogadorVez(i).getName();
    }
    // Método que retorna a cor do jogador da vez
    public PlayerColor getCorJogadorVez(int i){
        return game.getJogadorVez(i).getColor();
    }


    // Pegar a cor do jogador
    public PlayerColor playerColor() {
		return game.getPlayers().get(this.turn).getColor();
	}

    // Pegar quantidade de exércitos de um território
    public int retrieveTerritoryArmies(Territory territory) {
        return territory.getArmies();
    }

    // Pegar todos os territórios vizinhos de um dado território
    public List<String> retrieveNeighbours(Territory territory) {
        return territory.getNeighbours();
    }

    // Verificar se jogador está dentro das regras de ataque
    public boolean verifyAttack(Territory target, Territory origin) {
        for(String neighbour : origin.getNeighbours()) {
            if(neighbour == target.getName() && target.getOwner() != origin.getOwner() && origin.getArmies() > 1) {
                return true;
            }
        }

        return false;
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

    public void nextPlayer() {
        this.turn++;
        if(this.turn >= this.game.getPlayers().size())this.turn = 0;
        while(game.getPlayers().get(this.turn).verifyDestroyed()) {
            this.turn++;
            if(this.turn >= this.game.getPlayers().size())this.turn = 0;
        }
    }

    public boolean placeArmy(int army, String territory) {
        return game.getPlayers().get(this.turn).placeArmy(army, territory);
    }

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










    
//    public void reset_all() {
//		for(Map map: map) {
//			for(Territory terr: map.get_paises()) {
//				terr.reset();
//			}
//		}
//		for(Player players : players) {
//			game.getPlayers().reset(deck, objectiveDeck);
//		}
//		this.vez = 0;
//	}
    

    // static ObjectiveCard[] cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    // static Player[] jogadores;

    // static TerritoryCard[] cartasTerritorio = TerritoryCardDeck.getInstance().setCartasEmbaralhadas();
    

    // private static int numJogadores = 0;

    // public static void setDono(String carta, Player jog) {
    //     for (ObjectiveCard cart:cartas) {
    //         if (cart.description.equals(carta))
    //             cart.dono = jog;
    //     }
    // }



    // public static ArrayList<String> getCartasDono(String dono) {
    //     ArrayList<String> retorno = new ArrayList<String>();
    //     Player jog;
    //     for(int i=0;i<21;i++) {
    //         jog = getDono(cartas[i].description);
    //         if (jog != null && jog.name.equals(dono)) {
    //             retorno.add(cartas[i].description);
    //         }
    //     }
    //     return retorno;
    // }

    // public static Player getDono(String carta) {
    //     return ObjectiveCard.retornaDono(cartas, carta);
    // }

    // public static void setNumJogadores(int i) {
    //     numJogadores = i;
    //     return;
    // }

    // public static void restartCartas() {
    //     ObjectiveCardDeck.getInstance().nullCartas();
    //     cartas = ObjectiveCardDeck.getInstance().setCartasEmbaralhadas();
    // }

    // public static ObjectiveCard getCarta(String nome) {
    //     for (ObjectiveCard cart:API.cartas) {
    //         if (cart.description.equals(nome))
    //             return cart;
    //     }
    //     return null;
    // }
    
    // public static ArrayList<String> getDescricoesCartas() {
    // 	ArrayList<String> descricoes = new ArrayList<>();
    //     for (ObjectiveCard cart : cartas) {
    //         descricoes.add(cart.getDescription());
    //     }
    //     return descricoes;
    // }

    // public static int getQtdJogadores() {
    //     return numJogadores;
    // }

    // public static void setQtdJogadores(int qtdJogadores) {
    //     API.numJogadores = qtdJogadores;
    // }

    // public static void incQtdJogadores(boolean bool) {
    //     if (bool) {numJogadores++;return;}
    //     numJogadores--;
        
    // }

    // public static int jogaDado() {
    //     Dado.jogaDado();
    //     return Dado.dado;
    // }
    
    // public static Integer idCarta(String carta) {
    //     return ObjectiveCard.idCarta(carta);
    // }

	// public static TerritoryCard[] getCartasTerritorio() {
	// 	return cartasTerritorio;
	// }


}

package Model;
import View.ViewAPI;
import java.io.IOException;
import java.util.*;

import Controller.TabuleiroObservador;

public class API {

    private static API apiInstance = null;
//    private Game game;
    private ViewAPI viewInstance;
    private Map map;
    private Dado dado;
    private ObjectiveCardDeck objectiveDeck;
    private List<Player> players;
    public int turn;

    public API() {
        map = this.initMap();
        dado = new Dado();
        players = new ArrayList<>();
    }

    // Singleton
    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }
        return apiInstance;
    }

    // Retornar todos os jogadores
    public List<Player> getAllPlayers() {
        return this.players;
    }
    
    public Map getMap() {
        return map;
    }
    
    public Map initMap() {
        Map map = new Map();
        return map;
    }
    
//    public void createGame() {
//        this.game = new Game();
//        game.addObserver(viewInstance.getTabuleiroObservador());
//        viewInstance.redraw();
//    }

    // Pegar carta de objetivo do jogador
    public String playerObjective() {
        return players.get(this.turn).getObjectiveName();
    }

    public static TerritoryCard[] getCartasTerritorio() {
        return null;
    }

//    public void addObserver(TabuleiroObservador observer) {
//        game.addObserver(observer);
//    }

    // Adicionar jogadores
    public void addPlayer(String name, PlayerColor color) {
        players.add(new Player(name, color));
    }

    // Pegar o nome do jogador
    public static String playerName(Player player) { // verificar se é static mesmo
       return player.getName();
    }

    // Pegar a cor do jogador
    public PlayerColor get_vez_jogador_color() {
		return players.get(this.turn).getColor();
	}

    public ObjectiveCardDeck getDeckCardObjective(){
		return this.objectiveDeck;
	}
    
    public void initDeckObjective() {
    	objectiveDeck = new ObjectiveCardDeck(this.map,this.players);
	} 

    // Pegar o número de cartas de um jogador
    public int getPlayerNumberCards() {
        return players.get(this.turn).getCards();
    }

    // Verificar status de objetivo do jogador
	public boolean verifica_vez_jogador_objetivo() {
		return players.get(this.turn).getObjective().checkStatus();
	}

    public boolean checkPlayerTerritoryBorder(String territory, String border) {
        return players.get(this.turn).checkBorder(territory, border);
    }

    public boolean checkPlayerTerritory(String territory) {
        return players.get(this.turn).verifyTerritory(territory);
    }

    public void nextPlayer() {
        this.turn++;
        if(this.turn >= this.players.size())this.turn = 0;
        while(players.get(this.turn).verifyDestroyed()) {
            this.turn++;
            if(this.turn >= this.players.size())this.turn = 0;
        }
    }

    public boolean placeArmy(int army, String territory) {
        return players.get(this.turn).placeArmy(army, territory);
    }

    // Adicionar exércitos em território da posse do jogador
    public void getPlayerAddArmy(String territory) {
        Player player = players.get(this.turn);
        player.setArmies(0);
        player.addArmy();
        for(Territory region : player.territories) {
            if(region.getName() == territory) {
                // Temos que fazer isso aqui!
                // player.add_exercito_regiao(region.getName(),region.getArmies());
            }
        }
    }
    
    // Sorteia objetivos para todos os jogadores
    public void shuffleObjectives(List<Player> players, ObjectiveCardDeck objectiveDeck) {
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveDeck.sorteia_objetivo(player);
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


    
//    public void reset_all() {
//		for(Map map: map) {
//			for(Territory terr: map.get_paises()) {
//				terr.reset();
//			}
//		}
//		for(Player players : players) {
//			players.reset(deck, objectiveDeck);
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

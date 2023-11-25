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
    }

    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }
        return apiInstance;
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

    public static TerritoryCard[] getCartasTerritorio() {
        return null;
    }

//    public void addObserver(TabuleiroObservador observer) {
//        game.addObserver(observer);
//    }

    public ObjectiveCardDeck getDeckCardObjective(){
		return this.objectiveDeck;
	}
    
    public void initDeckObjective() {
    	objectiveDeck = new ObjectiveCardDeck(this.map,this.players);
	} 

    public int getPlayerNumberCards() {
        return players.get(this.turn).getCards();
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


    public void getPlayerAddArmy() {
        Player player = players.get(this.turn);
        player.setArmies(0);
        player.addArmy();
    }
    
    public void sorteia_obj_todos_jogadores(List<Player> players, ObjectiveCardDeck objectiveDeck) {
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveDeck.sorteia_objetivo(player);
			System.out.printf("O objetivo do jogador %s é %s.\n",player.getName(),player.getObjective().getClass());
		}
	}

    // Conferir vencedor da batalha
    /// batalhas podem ter no mínimo um exército e no máximo três exércitos
    /// retirar a quantidade de acordo com o resultado dos dados
    /// List com os resultados dos dados de ataque e de defesa


    // Código do menino
    public boolean[] confere_vencedor() {
		List<Integer> ataque = new ArrayList<>((List<Integer>)dado.get('a'));
		List<Integer> defesa = new ArrayList<>((List<Integer>)dado.get('d'));
		boolean[] batalhas = new boolean [(defesa.size() > ataque.size()) ? ataque.size() : defesa.size()];
		int batalha=0;
		while(defesa.size()!=0 && ataque.size()!=0) {
			int maioratk=0;
			int maiordef=0;
			for(int i=0;i<ataque.size(); i++) {
				 if(ataque.get(maioratk)<ataque.get(i)) {
					 maioratk=i;
				 }
			}
			for(int i=0;i<defesa.size(); i++) {
				 if(defesa.get(maiordef)<defesa.get(i)) {
					 maiordef=i;
				 }
			}
			if(ataque.get(maioratk)>defesa.get(maiordef)) {
				batalhas[batalha]=true;
			}
			else{
				batalhas[batalha]=false;
			}
			batalha++;
			ataque.remove(maioratk);
			defesa.remove(maiordef);
		}
		return batalhas;
	}
    ////////////////////////////////////////////////////////////////////////////


    
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

//    public static String getNomeJogador(Player jogador) {
//        return jogador.getName();
//    }

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

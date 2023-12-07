package Model;

import java.util.ArrayList;
import java.util.List;

import View.Observed;
import View.Observer;

class Game implements Observed{
    private static Game game = null;
    
    // Lista de jogadores
    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Observer> lst = new ArrayList<Observer>();

    // Cartas de objetivo
    private ObjectiveCardDeck objectiveCardDeck;

    private Map map = Map.getMap();

    // Inicializa o jogo
    public boolean initiateGame(){

        //Inicializa o tabuleiro
    	// map.Inicializa();

        map.distribuiTerritorios(players);

        return true;
    }
    private Game() {}

    // Singleton
    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public void add(Observer o){
        lst.add(o);
    }

    public void remove(Observer o){
        lst.remove(o);
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Pegar o jogador da vez
    public Player getJogadorVez(int turn){
        for (Player player : players) {
            System.out.println(player.getName());
        }
        System.out.println("-----------------");
        
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getIndex() == turn) {
                return players.get(i);
            }
        }
		return players.get(turn);
        
	}

    //implementar 
    public Object get() {
        return this;
    }

    public void notifyObservers() {
        for (Observer o : lst) {
            o.notify(this);
        }
    }


    // Adiciona jogador na partida
    public boolean addPlayer(Player jogador){
        for (Player j: players){
            if (j.getName().equals(jogador.getName()) || j.getColor() == jogador.getColor())
                return false;
        }
        players.add(jogador);
        for (Player player : players) {
            System.out.println(player.getName());
        }
        return true;
    }
}
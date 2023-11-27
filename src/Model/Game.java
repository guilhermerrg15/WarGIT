package Model;

import java.util.ArrayList;
import java.util.Collections;

class Game {
    private static Game game = null;

    // Lista de jogadores
    private ArrayList<Player> players = new ArrayList<Player>();

    // Cartas de objetivo
    private ObjectiveCardDeck objectiveCardDeck;

    // Inicializa o jogo
    public boolean initiateGame(){
        return true;
    }

    // Pegar o jogador da vez
    public Player getJogadorVez(int i){
		return players.get(i);
	}

    // Adiciona jogador na partida
    public boolean addPlayer(Player jogador){
        for (Player j: players){
            if (j.getName().equals(jogador.getName()) || j.getColor() == jogador.getColor())
                return false;
        }
        players.add(jogador);
        return true;
    }

    // Singleton
    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }
}
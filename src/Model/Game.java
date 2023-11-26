package Model;

import java.util.ArrayList;
import java.util.Collections;

class Game {
    // Lista de jogadores
    private ArrayList<Player> players = new ArrayList<Player>();

    // Cartas de objetivo
    private ObjectiveCardDeck objectiveCardDeck;

    // Inicializa o jogo
    public boolean startGame(){
        return true;
    }
}
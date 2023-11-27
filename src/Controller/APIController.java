package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Model.API;
import View.ViewAPI;
import java.awt.Color;

public class APIController {
    public static APIController controller = null;

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API game = API.getInstance();

    public boolean startMatch(ArrayList<String> nomes, ArrayList<Color> cores) {
        int cont = 0;
        for(String nome : nomes){
            // Verificar se há um nome igual a null
            if(nome == null || nome.equals("")) {
                return false;
            }
            if(game.addPlayer(nome) == false){
                return false;
            };
            cont++;
        }
        
        if(game.startGame()) {
            view.determinaPrimeiroJogador(game.getNomeJogadorVez(0), game.playerColor());
            return true;
        }

        return false;
    }

    // Pegar o nome dos jogadores
    public String[] getNomesJogadores(){
        return game.getNomesJogadores();
    }

    // Singleton
    public static APIController getInstance(){
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }   
}

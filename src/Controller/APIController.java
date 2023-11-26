package Controller;

import Model.API;
import View.ViewAPI;

public class APIController {
    public static APIController controller = null;

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API game = API.getInstance();

    public boolean startMatch() {
        if(game.startGame()) {
            view.determinaPrimeiroJogador(game.playerName(), game.playerColor());
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

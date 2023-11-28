package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Model.API;
import Model.PlayerColor;
import View.ViewAPI;
import java.awt.Color;

public class APIController {
    public static APIController controller = null;

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API api = API.getInstance();

    public boolean startMatch(ArrayList<String> nomes, ArrayList<PlayerColor> cores) {
        int numPlayers = nomes.size();
        for(int i = 0; i < numPlayers; i++){
            // Verificar se há um nome igual a null
            if(nomes.get(i) == null || nomes.get(i).equals("")) {
                api.resetPlayers();
                return false;
            }
            if(api.addPlayer(nomes.get(i), cores.get(i)) == false){
                api.resetPlayers();
                return false;
            };
            // cont++;
        }
        if(api.startGame()) {
            view.determinaPrimeiroJogador(api.getNomeJogadorVez(0), api.playerColor());
            return true;
        }

        return false;
    }

    //init deck cartas obj(chamar na view do map)
    public void showObjCards() {
        api.initDeckObjective();
        api.shuffleObjectives(api.getAllPlayers(), api.getDeckCardObjective());
        System.out.println("CLICKED");
        
    }

    // Pegar o nome dos jogadores
    public String[] getNomesJogadores(){
        return api.getNomesJogadores();
    }

    // Singleton
    public static APIController getInstance(){
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }   
}

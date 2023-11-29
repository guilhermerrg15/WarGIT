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

    private boolean firstRound = true;
    private int estado = 0;
    private int continent;
    private int turn = 0;
    private boolean podeSalvar = true;

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

    // Método que retorna a lista de territórios do jogo
    public String[] getTerritoriosLista(){
        return api.getTerritoriosLista();
    }

    public Integer getQtdExercitos(String t){
        return api.getQntExTerritorio(t);
    }

    // Método que retorna a cor de um território
    public PlayerColor getCorTerritorio(String t){
        return api.getCorTerritorio(t);
    }

    public void verifyWin() {
        if (api.verifica_vez_jogador_objetivo()) {
            view.showWin(api.getNomeJogadorVez(turn));
        }
    }

    public void clicouPosicionar(String territory, Integer qtd){
        if (estado == 0) {
            this.podeSalvar = true;

            api.placeArmy(estado, territory); //verificar essa func
            verifyWin();
            Integer qtdExercitos = api.getQtdExercitosPosic(turn);
            if(qtdExercitos == 0){
                if (continent < 6) {
                    // Atualiza a view para posicionar no próximo continente se for dominado
                    return;
                }
                if (continent == 6) {
                    // Se já posicionou em todos os continentes, posiciona no resto dos territórios
                    return;
                }
                // Se não tiver mais exércitos para posicionar
                // Zera contador de continentes
                continent = 0;
                if (firstRound) {
                    // turn = (turn + 1) % api.getQtdPlayers(); 
                    // Se for a primeira rodada, só pode posicionamento para todos
                    return;
                }
                // Atualiza a view para ataque (implementar codigo embaixo)
                return;
            }
            // Se ainda tiver exércitos para posicionar
            // apiView.atualizaQtdPosic(qtdEx);
            return;
        }
    }


    // Singleton
    public static APIController getInstance(){  
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }   
}

package Controller;

import java.util.ArrayList;
import Model.API;
import Model.PlayerColor;
import View.ViewAPI;

public class APIController {
    public static APIController controller = null;

    private boolean firstRound = true;
    private int turn = 0;
    

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
            view.determinaPrimeiroJogador(api.getNomeJogadorVez(0), api.getCorJogadorVez(0));
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

    public int getQuantidadeTerritoriosJogador(PlayerColor corDoJogador) {
        return API.getInstance().getQuantidadeTerritoriosJogador(corDoJogador);
    }


    // Pegar o nome dos jogadores
    public String[] getNomesJogadores(){
        return api.getNomesJogadores();
    }

    // Método que retorna a lista de territórios do jogo
    public String[] getTerritoriosLista(){
        return api.getTerritoriosLista();
    }

    
     // Método chamado quando ocorre o clique na bolinha
     public void incrementarExercitos(String territorio, int count) {
        api.incrementarQntExTerritorio(territorio, count);
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


    public boolean clicouContinuar() {
        if (firstRound) {
            turn = (turn + 1) % api.getNumPlayers();
            view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));

            if (turn == 0){
                firstRound = false;
            }

            return true;
        } else {
            view.atualizaAtacantes(api.getTerritoryMoreOne(api.getCorJogadorVez(turn)));
            return false;
        }
    }

    // Método chamado quando o jogador seleciona um território para atacar
    public void selecionouAtacante(String atacante){
        // Se estiver na etapa de ataque
            if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes 
            view.atualizaDefensores(api.getNeiboursNotDominated(atacante, turn));
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
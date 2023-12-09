package Controller;

import java.util.ArrayList;
import Model.API;
import Model.PlayerColor;
import View.ViewAPI;

public class APIController {
    public static APIController controller = null;

    private boolean firstRound = true;
    private int turn = 0;
    private String[] territoriesReplacementName;
    private Integer[] numArmiesReplacement;

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API api = API.getInstance();

    // Guarda os nomes dos jogadores eliminados nessa rodada
    ArrayList<String> eliminadosNessaRodada = new ArrayList<String>();

    public boolean startMatch(ArrayList<String> nomes, ArrayList<PlayerColor> cores) {
        int numPlayers = nomes.size();
        for(int i = 0; i < numPlayers; i++){
            // Verificar se há um nome igual a null
            if(nomes.get(i) == null || nomes.get(i).equals("")) {
                api.resetPlayers();
                return false;
            }
            if(api.addPlayer(nomes.get(i), cores.get(i), i) == false){
                api.resetPlayers();
                return false;
            };
            // cont++;
        }
        if(api.startGame()) {
            view.determineFirstPlayer(api.getNomeJogadorVez(0), api.getCorJogadorVez(0));
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

    public int getNumTerritoryPlayer(PlayerColor corDoJogador) {
        return API.getInstance().getNumTerritoryPlayer(corDoJogador);
    }

     //get vez do jogador
     public int getTurn(){
        return turn;
    }

    //set vez do jogador
    public void setTurn(int i){
        this.turn = i;
    }

    // Pegar o nome dos jogadores
    public String[] getNomesJogadores(){
        return api.getNomesJogadores();
    }

    // Método que retorna a lista de territórios do jogo
    public String[] getTerritoriesList(){
        return api.getTerritoriesList();
    }

    
     // Método chamado quando ocorre o clique na bolinha
     public void incrementArmies(String territorio, int count) {
        api.incrementarNumArmiesTerritory(territorio, count);
    }

    public Integer getNumArmiesTerritory(String t){
        return api.getNumArmiesTerritory(t);
    }

    public Integer getQtdExercitosAntigos(String t){
        return api.getQntExTerritorioAntigos(t);
    }


    // Método que retorna a cor de um território
    public PlayerColor getTerritoryColor(String t){
        return api.getTerritoryColor(t);
    }


    public boolean clickedContinue() {
        if (firstRound) {
            turn = (turn + 1) % api.getNumPlayers();

            view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));

            if (turn == 0){
                firstRound = false;
            }

            return true;
        } else {
            // view.atualizaAtacantes(api.getTerritoryMoreOne(api.getCorJogadorVez(turn)));
            territoriesReplacementName = api.getTerritoryMoreOne(api.getCorJogadorVez(turn));
            if (numArmiesReplacement != null) {
                numArmiesReplacement = new Integer[territoriesReplacementName.length];
            }
            for (int i = 0; i < territoriesReplacementName.length; i++){
                numArmiesReplacement[i] = (api.getNumArmiesTerritory(territoriesReplacementName[i]) - 1);
            }
            view.updateReplacement(territoriesReplacementName);
            return false;
        }
    }

    public void selectedOrigin(String origin) {
        if (origin == null) {
            return;
        }
        view.updateDestiny(api.getNeiboursDominated(origin, turn));
        int i = 0;
        for(; i < territoriesReplacementName.length; i++) {
            if (territoriesReplacementName[i].equals(origin)) {
                break;
            }
        }
        view.updateNumReplacement(numArmiesReplacement[i]);
    }

    // Método chamado quando o jogador seleciona um território para atacar
    public void selecionouAtacante(String atacante){
        if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes 
            view.atualizaDefensores(api.getNeiboursNotDominated(atacante, turn));
        }
    }

    // Método que verifica se jogador ganhou e lida com o resultado
    public void verificaGanhou(int pos){
        // Se vez = -1, verifica todos os jogadores
        if (api.verificaGanhou(pos)){
            view.jogadorGanhou(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
        }
    }

     // Adiciona um nome à lista de eliminados nessa rodada
     public void addEliminado(String nome){
        eliminadosNessaRodada.add(nome);
    }
    
    
    public void reiniciarJogo(){
        // Reinicia dados de model
        api.reiniciarJogo();
        
        // Reinicia dados do controller
        this.turn = 0;
        this.firstRound = true;

        // Reinicia dados da view
        api.notificaObsJogo();

        view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
        // primeiroPosicionamento();
    }
    

    // Singleton
    public static APIController getInstance(){  
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }   
}
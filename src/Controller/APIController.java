package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.API;
import Model.PlayerColor;
import Model.TerritoryCard;
import View.ViewAPI;

public class APIController {
    public static APIController controller = null;

    private boolean firstRound = true;
    private int turn = 0;
    private String[] territoriesReplacementName;
    private Integer[] numArmiesReplacement;
    private boolean canTrade = true;
    

    // Guarda o bônus de troca de cartas
    private Integer bonusTroca = 0;
    // Guarda o número de trocas de cartas
    private Integer numDeTrocas = 0;

    // Guarda os nomes dos jogadores eliminados nessa rodada
    ArrayList<String> eliminadosNessaRodada = new ArrayList<String>();

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

    }

    public void initTerritoryDeck(){
        api.initDeckTerritory();
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

    public void  giveTerritoryCard(){
        api.giveTerritoryCard(turn);
    }

    public List<TerritoryCard> getTerritoryCards(){
        return api.getTerritoryCards(turn);
    }

     public void resetConquista(){
        api.resetConquista(turn);
     }
     
     // Método chamado quando ocorre o clique na bolinha
     public void setNumArmiesTerritory(String territorio, int count) {
        api.setNumArmiesTerritory(territorio, count);
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

    public Boolean canTradeCards(){
        return api.canTradeCards(turn);
    }

    // Método para trocar cartas
    public void clicouTrocar(){
        this.bonusTroca = api.trocarCartas(turn, numDeTrocas);
        if (bonusTroca != 0){
            numDeTrocas++;
            view.updateBonusTroca(bonusTroca);
            this.bonusTroca = 0;
            return;
        }
}

    //Metodo chamado qaundo o jogador clica em posicionar tropa para aicionar territorios se tiver continente dominado
    public void clickedPlaceArmy(){
        api.continentDomain(turn);

        // Verifica se ganhou após reposicionar
        verificaGanhou(turn);
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
            String[] la = api.getTerritoryMoreOne(api.getCorJogadorVez(turn));
            if (la == null || api.getTerritorioJogadorVez(turn) == 0){
                clickedChangePlayer();
            }
            view.atualizaAtacantes(la);

            
            verificaGanhou(turn);
            return false;
        }

    }

    public void clickedAttack() {
        
        view.atualizaAtacantes(api.getTerritoryMoreOne(api.getCorJogadorVez(turn)));
        
        // Verifica se tem algum jogador eliminado nessa rodada
        if (eliminadosNessaRodada.size() != 0){
            verificaGanhou(-1);
            for (int i = 0; i < eliminadosNessaRodada.size();i++){
                api.retiraEliminado(eliminadosNessaRodada.get(i));
                eliminadosNessaRodada.remove(0);
            }
        }
    }

    public void clickedEndAtack(){
        
        // Verifica se tem algum jogador eliminado nessa rodada
        if (eliminadosNessaRodada.size() != 0){
            verificaGanhou(-1);
            for (int i = 0; i < eliminadosNessaRodada.size();i++){
                api.retiraEliminado(eliminadosNessaRodada.get(i));
                eliminadosNessaRodada.remove(0);
            }
        }

        giveTerritoryCard();
        resetConquista();

        territoriesReplacementName = api.getTerritoryMoreOne(api.getCorJogadorVez(turn));

            // Atualiza a view para reposicionamento
            if (territoriesReplacementName == null){
                clickedChangePlayer();
            }
            // Se tiver algum território com mais de 1 exército para reposicionar
            if (territoriesReplacementName != null) {
                numArmiesReplacement = new Integer[territoriesReplacementName.length];
            }
            // Pega a quantidade de exércitos que pode reposicionar em cada território
            for (int i = 0; i < territoriesReplacementName.length; i++){
                numArmiesReplacement[i] = (api.getNumArmiesTerritory(territoriesReplacementName[i]) - 1);
            }

            
            view.updateReplacement(territoriesReplacementName);
    }

    public void clickedChangePlayer(){

        turn = (turn + 1) % api.getNumPlayers();
        view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));

        // se proximo jogador foi eliminado nessa rodada passa para o proximo
        if (api.getJogadorVezEliminadoRodada(turn) || api.getTerritorioJogadorVez(turn) == 0){
            turn = (turn + 1) % api.getNumPlayers();
            view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
        }
        // Verifica se ganhou após reposicionar
        verificaGanhou(turn);

    }

    // Método chamado quando o jogador seleciona um território para reposicionar
    public void clicouReposicionar(String origem, String destino, Integer qtd){

        // Reposiciona os exércitos
        api.reposicionarExercitos(origem, destino, qtd);

        // Pega o index do território selecionado para diminuir a quantidade que ainda pode reposicionar
        int i = 0;

        for (; i < territoriesReplacementName.length; i++) {
            if (territoriesReplacementName[i].equals(origem)) {
                break;
            }
        }

        // Diminui a quantidade que ainda pode reposicionar
        numArmiesReplacement[i] -= qtd;

        // Se tiver exércitos para reposicionar continua na etapa de reposicionamento
        for (int j = 0; j < territoriesReplacementName.length; j++){
            if (numArmiesReplacement[j] > 0){
                    territoriesReplacementName = api.getTerritoryMoreOne(api.getCorJogadorVez(turn));
                // Se tiver algum território com mais de 1 exército para reposicionar
                if (territoriesReplacementName != null) {
                    numArmiesReplacement = new Integer[territoriesReplacementName.length];
                }
                // Pega a quantidade de exércitos que pode reposicionar em cada território
                for (int k = 0; k < territoriesReplacementName.length; k++){
                    numArmiesReplacement[k] = (api.getNumArmiesTerritory(territoriesReplacementName[k]) - 1);
                }
                view.updateReplacement(territoriesReplacementName);
                view.updateNumReplacement(0);
                return;
            }
        }

        // Verifica se ganhou após reposicionar
        verificaGanhou(turn);
    }

    // Adiciona um nome à lista de eliminados nessa rodada
    public void addEliminado(String nome){
        eliminadosNessaRodada.add(nome);
    }

    // Método chamado quando o jogador seleciona um território para defender
    public void selectedOrigin(String origin) {
         // Se selecionado for nulo não faz nada
        if (origin == null) {
            return;
        }
        // Atualiza comboBox do destino com adjacentes
        view.updateDestiny(api.getNeiboursDominated(origin, turn));

        // Pega o index do território selecionado para ver quantidade que ainda pode reposicionar
        int i = 0;
        for(; i < territoriesReplacementName.length; i++) {
            if (territoriesReplacementName[i].equals(origin)) {
                break;
            }
        }
        // Atualiza a quantidade de exércitos que ainda pode reposicionar
        view.updateNumReplacement(numArmiesReplacement[i]);
    }

    // Método chamado quando o jogador seleciona um território para atacar
    public void selecionouAtacante(String atacante){
        if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes
            if(api.getNeiboursNotDominated(atacante, turn) != null){
                view.atualizaDefensores(api.getNeiboursNotDominated(atacante, turn));
            } else {
                view.atualizaDefensores(new String[0]);
            }
        }
    }

    // Método que verifica se jogador ganhou e lida com o resultado
    public void verificaGanhou(int pos){
        if (api.verificaGanhou(pos)){
            view.jogadorGanhou(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
        }
    }

    public boolean getFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound){
        this.firstRound = firstRound;
    }

    public Integer getNumTrocas() {
        return numDeTrocas;
    }

    public void clickedSave() {
        api.saveGame();
        view.showWarning("Jogo salvo com sucesso!");
    }

    // public void clickedLoad() {
    //     api.loadGame();
    // }


    public void reiniciarJogo(){
        // Reinicia dados de model
        api.reiniciarJogo();

        // Reinicia dados do controller
        this.turn = 0;
        this.firstRound = true;

        // Reinicia dados da view
        api.notificaObsJogo();

        view.mudaJogador(api.getNomeJogadorVez(turn), api.getCorJogadorVez(turn));
        view.setFirstRound(firstRound);
        
       
        
    }

    // Singleton
    public static APIController getInstance(){
        if(controller == null){
            controller = new APIController();
        }
        return controller;
    }
}
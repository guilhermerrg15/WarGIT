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
    private boolean isSaveEnabled = false;

    // Guarda o bônus de troca de cartas
    private Integer tradeBonus = 0;
    // Guarda o número de trocas de cartas
    private Integer numTrades = 0;

    // Guarda os nomes dos jogadores eliminados nessa rodada
    ArrayList<String> eliminatedThisRound = new ArrayList<String>();

    // Instância de APIs
    private ViewAPI view = ViewAPI.getInstance();
    private API api = API.getInstance();


    public boolean startMatch(ArrayList<String> names, ArrayList<PlayerColor> colors) {
        int numPlayers = names.size();
        for(int i = 0; i < numPlayers; i++){
            
            if(names.get(i) == null || names.get(i).equals("")) {
                api.resetPlayers();
                return false;
            }
            if(api.addPlayer(names.get(i), colors.get(i), i) == false){
                api.resetPlayers();
                return false;
            };
        }
        if(api.startGame()) {
            view.determineFirstPlayer(api.getPlayerTurn(0), api.getPlayerColorTurn(0));
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

    public int getNumTerritoryPlayer(PlayerColor playerColor) {
        return API.getInstance().getNumTerritoryPlayer(playerColor);
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
    public String[] getPlayersNames(){
        return api.getPlayersNames();
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

     public void resetConquer(){
        api.resetConquer(turn);
     }
     
     // Método chamado quando ocorre o clique na bolinha
     public void setNumArmiesTerritory(String territory, int count) {
        api.setNumArmiesTerritory(territory, count);
    }

    public Integer getNumArmiesTerritory(String t){
        return api.getNumArmiesTerritory(t);
    }

    public Integer getNumOldArmies(String t){
        return api.getNumOldArmies(t);
    }

    // Método que retorna a cor de um território
    public PlayerColor getTerritoryColor(String t){
        return api.getTerritoryColor(t);
    }

    public Boolean canTradeCards(){
        return api.canTradeCards(turn);
    }

    // Método para trocar cartas
    public void clickedTrade(){
        this.tradeBonus = api.tradeCards(turn, numTrades);
        if (tradeBonus != 0){
            numTrades++;
            view.updateBonusTrade(tradeBonus);
            this.tradeBonus = 0;
            return;
        }
}

    //Metodo chamado quando o jogador clica em posicionar tropa para aicionar territorios se tiver continente dominado
    public void clickedPlaceArmy(){
        this.isSaveEnabled = false;
        api.continentDomain(turn);

        // Verifica se ganhou após reposicionar
        verifyWin(turn);
        
    }

    public boolean clickedContinue() {
        if (firstRound) {
            isSaveEnabled = true;

            turn = (turn + 1) % api.getNumPlayers();
            view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));

            if (turn == 0){
                firstRound = false;
            } 

            return true;
        } else {

            this.isSaveEnabled = false;
            String[] la = api.getTerritoryMoreOne(api.getPlayerColorTurn(turn));
            if (la == null){
                clickedChangePlayer();
            }
            view.updateAttackers(la);

            verifyWin(turn);
            return false;
        }

    }

    public void clickedAttack() {
        
        this.isSaveEnabled = false;

        view.updateAttackers(api.getTerritoryMoreOne(api.getPlayerColorTurn(turn)));
        
        // Verifica se tem algum jogador eliminado nessa rodada
        if (eliminatedThisRound.size() != 0){
            verifyWin(-1);
            for (int i = 0; i < eliminatedThisRound.size();i++){
                api.withdrawEliminated(eliminatedThisRound.get(i));
                eliminatedThisRound.remove(0);
            }
        }
    }

    public void clickedEndAtack(){

        this.isSaveEnabled = false;

        // Verifica se tem algum jogador eliminado nessa rodada
        if (eliminatedThisRound.size() != 0){
            verifyWin(-1);
            for (int i = 0; i < eliminatedThisRound.size();i++){
                api.withdrawEliminated(eliminatedThisRound.get(i));
                eliminatedThisRound.remove(0);
            }
        }

        giveTerritoryCard();
        resetConquer();

        territoriesReplacementName = api.getTerritoryMoreOne(api.getPlayerColorTurn(turn));

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
        view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));

        // se proximo jogador foi eliminado nessa rodada passa para o proximo
        if (api.getPlayerTurnEliminated(turn)){
            turn = (turn + 1) % api.getNumPlayers();
            view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));
        }
        // Verifica se ganhou após reposicionar
        verifyWin(turn);

        this.isSaveEnabled = true;

    }

    // Método chamado quando o jogador seleciona um território para reposicionar
    public void clickedReplace(String origin, String destino, Integer qtd){

        this.isSaveEnabled = false;

        // Reposiciona os exércitos
        api.replaceArmies(origin, destino, qtd);

        // Pega o index do território selecionado para diminuir a quantidade que ainda pode reposicionar
        int i = 0;

        for (; i < territoriesReplacementName.length; i++) {
            if (territoriesReplacementName[i].equals(origin)) {
                break;
            }
        }

        // Diminui a quantidade que ainda pode reposicionar
        numArmiesReplacement[i] -= qtd;

        // Se tiver exércitos para reposicionar continua na etapa de reposicionamento
        for (int j = 0; j < territoriesReplacementName.length; j++){
            if (numArmiesReplacement[j] > 0){
                    territoriesReplacementName = api.getTerritoryMoreOne(api.getPlayerColorTurn(turn));
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
        verifyWin(turn);
    }

    // Adiciona um nome à lista de eliminados nessa rodada
    public void addEliminated(String name){
        eliminatedThisRound.add(name);
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
    public void attackerSelected(String atacante){
        if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes
            if(api.getNeiboursNotDominated(atacante, turn) != null){
                view.updateDefenders(api.getNeiboursNotDominated(atacante, turn));
            } else {
                view.updateDefenders(new String[0]);
            }
        }
    }

    // Método que verifica se jogador ganhou e lida com o resultado
    public void verifyWin(int pos){
        if (api.verifyWin(pos)){
            view.playerWon(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));
        }
    }

    public boolean getFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound){
        this.firstRound = firstRound;
    }

    public Integer getNumTrades() {
        return numTrades;
    }

    // Adicione este método para obter o estado do botão de salvar
    public boolean isSaveEnabled() {
        return this.isSaveEnabled;
}

    public void clickedSave() {
        if (isSaveEnabled()) {
            api.saveGame();
            view.showWarning("Jogo salvo com sucesso!");
        } else {
            view.showWarning("Só é possível salvar o jogo após um jogador ter concluído a sua jogada.");
        }
    }

    // public void clickedLoad() {
    //     api.loadGame();
    // }


    public void restartGame(){
        // Reinicia dados de model
        api.restartGame();

        // Reinicia dados do controller
        this.turn = 0;
        this.firstRound = true;

        // Reinicia dados da view
        api.notifyGameObserver();

        view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));
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
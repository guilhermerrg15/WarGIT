package Controller;

import java.util.ArrayList;
import java.util.List;
import Model.API;
import Model.PlayerColor;
import Model.TerritoryCard;
import View.ViewAPI;


//API CONTROLLER
public class APIController {
    public static APIController controller = null;

    private boolean firstRound = true;
    private int turn = 0;
    private String[] territoriesReplacementName;
    private Integer[] numArmiesReplacement;
    private boolean isSaveEnabled = false;

    
    private Integer tradeBonus = 0;
    private Integer numTrades = 0;

  
    ArrayList<String> eliminatedThisRound = new ArrayList<String>();

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

    
     public int getTurn(){
        return turn;
    }


    public void setTurn(int i){
        this.turn = i;
    }


    public String[] getPlayersNames(){
        return api.getPlayersNames();
    }


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
     
     public void setNumArmiesTerritory(String territory, int count) {
        api.setNumArmiesTerritory(territory, count);
    }

    public Integer getNumArmiesTerritory(String t){
        return api.getNumArmiesTerritory(t);
    }

    public Integer getNumOldArmies(String t){
        return api.getNumOldArmies(t);
    }

    public PlayerColor getTerritoryColor(String t){
        return api.getTerritoryColor(t);
    }

    public Boolean canTradeCards(){
        return api.canTradeCards(turn);
    }


    public void clickedTrade(){
        this.tradeBonus = api.tradeCards(turn, numTrades);
        if (tradeBonus != 0){
            numTrades++;
            view.updateBonusTrade(tradeBonus);
            this.tradeBonus = 0;
            return;
        }
}

  
    public void clickedPlaceArmy(){
        this.isSaveEnabled = false;
        api.continentDomain(turn);

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

            
            if (territoriesReplacementName == null){
                clickedChangePlayer();
            }
            if (territoriesReplacementName != null) {
                numArmiesReplacement = new Integer[territoriesReplacementName.length];
            }
            for (int i = 0; i < territoriesReplacementName.length; i++){
                numArmiesReplacement[i] = (api.getNumArmiesTerritory(territoriesReplacementName[i]) - 1);
            }

            
            view.updateReplacement(territoriesReplacementName);
    }

    public void clickedChangePlayer(){

        turn = (turn + 1) % api.getNumPlayers();
        view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));
        if (api.getPlayerTurnEliminated(turn)){
            turn = (turn + 1) % api.getNumPlayers();
            view.changePlayer(api.getPlayerTurn(turn), api.getPlayerColorTurn(turn));
        }
        verifyWin(turn);

        this.isSaveEnabled = true;

    }


    public void clickedReplace(String origin, String destino, Integer qtd){

        this.isSaveEnabled = false;

        api.replaceArmies(origin, destino, qtd);
        int i = 0;

        for (; i < territoriesReplacementName.length; i++) {
            if (territoriesReplacementName[i].equals(origin)) {
                break;
            }
        }

        numArmiesReplacement[i] -= qtd;

        for (int j = 0; j < territoriesReplacementName.length; j++){
            if (numArmiesReplacement[j] > 0){
                    territoriesReplacementName = api.getTerritoryMoreOne(api.getPlayerColorTurn(turn));
                if (territoriesReplacementName != null) {
                    numArmiesReplacement = new Integer[territoriesReplacementName.length];
                }
                for (int k = 0; k < territoriesReplacementName.length; k++){
                    numArmiesReplacement[k] = (api.getNumArmiesTerritory(territoriesReplacementName[k]) - 1);
                }
                view.updateReplacement(territoriesReplacementName);
                view.updateNumReplacement(0);
                return;
            }
        }

        verifyWin(turn);
    }

    public void addEliminated(String name){
        eliminatedThisRound.add(name);
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

    public void attackerSelected(String atacante){
        if(atacante != null){
            if(api.getNeiboursNotDominated(atacante, turn) != null){
                view.updateDefenders(api.getNeiboursNotDominated(atacante, turn));
            } else {
                view.updateDefenders(new String[0]);
            }
        }
    }

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
        api.restartGame();

        this.turn = 0;
        this.firstRound = true;
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
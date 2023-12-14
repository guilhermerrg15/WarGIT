package Model;
import View.ViewAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import javax.swing.JFileChooser;

import Controller.APIController;

public class API {

    private static API apiInstance = null;
    private ViewAPI viewInstance = ViewAPI.getInstance();
    private Map map;
    private Game game = Game.getInstance();
    private Dado dado;
    private Player player;
    private ObjectiveCardDeck objectiveDeck;
    private TerritoryCardDeck territoryCardDeck;
    private ArrayList<Player> players;
    public int turn;
    FileWriter inputStream;

    JFileChooser chooser;
    private API() {
        map = this.initMap();
        dado = new Dado();
    }
    // Singleton
    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }
        return apiInstance;
    }


    public void restartGame(){
        game.restartGame(objectiveDeck, territoryCardDeck);
        game.setAlt1(null);
        game.setAlt2(null);
    }

    public void giveTerritoryCard(int t){
        if(game.getPlayerTurn(t).getConqueredThisRound()){
            territoryCardDeck.pickRandomCard(game.getPlayerTurn(t));
        }
    }

    public void resetConquer(int t){
        game.getPlayerTurn(t).setConqueredThisRound(false);
    }

    public Integer getPlayerTerritoryTurn(int turn){
        return game.getPlayerTurn(turn).getTerritoryNumber();
    }

    public int getNumTerritoryPlayer(PlayerColor playerColor) {
        int numTerritories = 0;

        List<Territory> territories = map.getTerritoriesList();
        for (Territory territory : territories) {
            if (territory.getOwner().getColor() == playerColor) {
                numTerritories++;
            }
        }
        return numTerritories;
    }

    public String[] getPlayerTerritories (PlayerColor playerColor){
        int num = 0;
        ArrayList<Territory> territories = map.getTerritoriesList();
        String[] territoryList = new String[getNumTerritoryPlayer(playerColor)];

    	for (Territory t: territories) {
            if (t.getOwner().getColor() == playerColor) {
                territoryList[num] = t.getName();
                num++;
            }

    	}
    	return territoryList;


    }

    public String[] getNeiboursNotDominated(String t, int vez) {
        List<Territory> territoriesList = map.getTerritoriesList();
        List<String> territoriesNotDominated = new ArrayList<>();

        for (Territory ter : territoriesList) {
            if (ter.isNeighbor(t) && !ter.getOwner().getName().equals(game.getPlayerTurn(vez).getName())) {
                territoriesNotDominated.add(ter.getName());
            }
        }
        if (territoriesNotDominated.isEmpty()) {
            return null;
        }
        return territoriesNotDominated.toArray(new String[0]);
    }

   public String[] getTerritoryMoreOne(PlayerColor playerColor) {
        int quant = 0;

        ArrayList<Territory> territories = map.getTerritoriesList();
        List<String> territoriesMoreOneArmy = new ArrayList<>();

        for (Territory t : territories) {
            if (t.getOwner().getColor() == playerColor && t.getArmies() > 1) {
                territoriesMoreOneArmy.add(t.getName());
                quant++;
            }
        }

        if (territoriesMoreOneArmy.isEmpty()) {
            return null;
        }

        String[] finalTerritories = new String[quant];
        territoriesMoreOneArmy.toArray(finalTerritories);

        return finalTerritories;
    }

    public ArrayList<Player> getAllPlayers() {
        return this.game.getPlayers();
    }

    public int getNumPlayers(){
        return game.getPlayers().size();
    }

    public void withdrawEliminated(String name){
        game.getPlayer(name).setEliminatedThisRound(false);
    }

    public Integer getNumArmiesTerritory(String territorio) {
        List<Territory> territories = map.getTerritoriesList();
        for (Territory territory : territories) {
            if (territory.getName().equals(territorio)) {
                return territory.getArmies();
            }
        }
        return null;
    }

    public void replaceArmies(String origin, String destiny, Integer num){
        List<Territory> territories = map.getTerritoriesList();
        Territory TOrigin = null;
        Territory TDestiny = null;

        for (Territory territory : territories) {
            if (territory.getName().equals(origin)) {
                TOrigin = territory;
            }
            if (territory.getName().equals(destiny)) {
                TDestiny = territory;
            }
        }

        if (TOrigin != null && TDestiny != null) {
            game.reposicionarExercitos(TOrigin, TDestiny, num);
        } else {
            System.out.println("Territórios de origem ou destino não encontrados.");
        }
    }

    public Integer getNumOldArmies(String territorio) {
        List<Territory> territories = map.getTerritoriesList();
        for (Territory territory : territories) {
            if (territory.getName().equals(territorio)) {
                return territory.getInitialArmy();
            }
        }
        return null;
    }

    public String[] getTerritoriesList() {
		String [] terr = new String[51];
		int cont = 0;
		for (Territory t: map.getTerritoriesList()) {
            terr[cont] = t.getName();
            cont ++;
		}

		return terr;
	}

    public PlayerColor getTerritoryColor(String t) {

        List<Territory> territories = map.getTerritoriesList();

        for (Territory territory : territories) {
            if (territory.getName().equals(t)) {
                return territory.getCor();
            }
        }
        return null;
    }

    public String[] getNeiboursDominated(String t, int turn) {
        List<Territory> territoriesList = map.getTerritoriesList();
        List<String> dominatedTerritories = new ArrayList<>();
        for (Territory ter: territoriesList) {
            if (ter.isNeighbor(t) && ter.getOwner().getName().equals(game.getPlayerTurn(turn).getName())) {
                dominatedTerritories.add(ter.getName());
            }
        }
        if (dominatedTerritories.isEmpty()) {
            return null;
        }
        return dominatedTerritories.toArray(new String[0]);
    }

     public void setNumArmiesTerritory(String territorio, int count) {
        List<Territory> territories = map.getTerritoriesList();
        for (Territory territory : territories) {
            if (territory.getName().equals(territorio)) {
                territory.setArmies(count);
                return;
            }
        }
    }

    public int[] makeAttack(String attacker,String defender, Integer numAttack, Integer numDefense) {

    	Territory Tattacker = map.findTerritory(attacker);
    	Territory Tdefender = map.findTerritory(defender);

        int[] array = game.makeAttack(Tattacker, Tdefender, numAttack, numDefense);

		APIController.getInstance().verifyWin(APIController.getInstance().getTurn());
        return array;
    }

    public String[] getPlayerCardNames(int turn){
        List<TerritoryCard> cards = game.getPlayerTurn(turn).getCard();
        String[] cardArray = new String[cards.size()];
        int cont = 0;
        for (TerritoryCard c: cards) {
            cardArray[cont] = c.getName();
            cont++;
        }

        return cardArray;
    }

    public boolean verifyWin(int turn){
        if (turn == -1){
            for (Player player : game.getPlayers()) {
                if (player.getObjective().checkStatus()){
                    APIController.getInstance().setTurn(game.getPlayer(player.getName()).getIndex());
                    return true;
                }
            }
            return false;
        }

        Player player = game.getPlayerTurn(turn);
        return player.getObjective().checkStatus();
    }

    public String[] getPlayersNames() {
    	String[] nomes = new String[getAllPlayers().size()];
    	int cont = 0;
    	for (Player j : getAllPlayers()) {
    		nomes[cont] = j.getName();
    		cont++;
    	}
    	return nomes;
    }

    public Map initMap() {
        Map map = Map.getMap();
        return map;
    }

    public boolean startGame() {
        boolean r = game.initiateGame();
        game.add(viewInstance.getObserver());
        return r;
    }

    public String playerObjective(String name) {
        for(Player player : game.getPlayers()){
            if(player.getName() == name) {
                return player.getObjectiveName();
            }
        }
        return game.getPlayers().get(this.turn).getObjectiveName();
    }

    public static TerritoryCard[] getTerritoryCards() {
        return null;
    }

    public List<TerritoryCard> retrieveTerritoryCards() {
        return game.getPlayers().get(this.turn).getAllCards();
    }

    public List<TerritoryCard> getTerritoryCards(int t) {
        return game.getPlayerTurn(t).getCard();
    }

    public List<Territory> retrieveTerritories() {
        return game.getPlayers().get(this.turn).getConqueredTerritories();
    }

    public boolean addPlayer(String name, PlayerColor color, int index) {
        Player player = new Player(name, color, index);
        return game.addPlayer(player);
    }

    public String getPlayerTurn(int i){
        return game.getPlayerTurn(i).getName();
    }

    public PlayerColor getPlayerColorTurn(int i){
        return game.getPlayerTurn(i).getColor();
    }

    public boolean getPlayerTurnEliminated(int i){
        return game.getPlayerTurn(i).getEliminatedThisRound();
    }

    public ObjectiveCardDeck getDeckCardObjective(){
		return this.objectiveDeck;
	}

    public void initDeckObjective() {
    	objectiveDeck = new ObjectiveCardDeck(this.map,this.game.getPlayers());
	}

    public void initDeckTerritory() {
    	territoryCardDeck = new TerritoryCardDeck();
	}

    public void resetPlayers() {
        game.getPlayers().clear();
    }

    public void shuffleObjectives(List<Player> players, ObjectiveCardDeck objectiveDeck) {
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveDeck.shuffleObjective(player);
		}
	}
    
    public Integer[] battleWinner(List<Integer> diceAttack, List<Integer> diceDefense) {
        Integer[] battle = new Integer[2];

        int attackCount = diceAttack.size();
        int defenseCount = diceDefense.size();
        
        int countAttack = 0;
        int countDefense = 0;

        while(attackCount != 0 && defenseCount != 0){
            int attackValue = 0;
            int defenseValue = 0;

            
            for(int i = 0; i < attackCount; i++) {
                if(diceAttack.get(attackValue) < diceAttack.get(i)) {
                    attackValue = i;
                }
            }
            
            for(int i = 0; i< defenseCount; i++) {
                if(diceDefense.get(defenseValue) < diceDefense.get(i)) {
                    defenseValue = i;
                }
            }
            if(diceAttack.get(attackValue) < diceDefense.get(defenseValue)) {
                countAttack++;
            } else {
                countDefense++;
            }
        }

        battle[0] = countAttack;
        battle[1] = countDefense;

        return battle;
    }

    public boolean canTradeCards(int turn){
        Player player = game.getPlayerTurn(turn);
        return game.hasTrade(player);
    }

    
    public Integer tradeCards(int turn, int numTrades){
        Player player = game.getPlayerTurn(turn);

        if (game.hasTrade(player)){
            return game.changeCards(numTrades, territoryCardDeck, map, player);
        }

        return 0;
    }

    public void continentDomain(Integer turn){
        game.continentDomain(turn);
    }

    public void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
    
        int userSelection = fileChooser.showSaveDialog(null);
    
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
    
            
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }
    
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8"));
    
                writer.write(String.valueOf(APIController.getInstance().getFirstRound()));
                writer.write("\n");
                
                writer.write(String.valueOf(game.getPlayers().size()));
                writer.write("\n");
                writer.write(String.valueOf(APIController.getInstance().getTurn()));
                writer.write("\n");
    
                
                for (Player player : game.getPlayers()) {
                    writer.write("Jogador: " + player.getName() + ", Cor: " + player.getColor() + "\n");
                    writer.write("Objetivo: " + player.getObjectiveName() + "\n");

                    if(player.getCard().size() == 0){
                        writer.write("0" + "\n");
                    }

                    writer.write("Quantidade de cartas de territorio: " + player.getCard().size() + "\n"); 

                    for (TerritoryCard territoryCard : player.getCard()) {
                        if(territoryCard.getContinent() == null){
                            writer.write("Carta de território: Coringa"+ "\n") ;
                        } else{
                            writer.write("Carta de território: " + territoryCard.getName() + "\n"); 
                        }
                    }
                }
                writer.write("Numeros de trocas de carta: " + APIController.getInstance().getNumTrades().toString());
    
                
                for (Territory territory : map.getTerritoriesList()) {
                    writer.write("Território: " + territory.getName() + ", Tropas: " + territory.getArmies() + " / ");
    
                    
                    if (territory.getOwner() != null) {
                        writer.write("Proprietário: " + territory.getOwner().getName() + "\n");
                    } else {
                        writer.write("Sem Proprietário\n");
                    }
                }
                writer.close();
    
                System.out.println("Jogo salvo com sucesso em " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erro ao salvar o jogo: " + e.getMessage());
            }
        }
    }
        
    // Notifica observadores de jogo
    public void notifyGameObserver(){
        game.notifyObservers();
    }
}
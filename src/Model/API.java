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

// //import Controller.TabuleiroObservador;

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

    // Método para reiniciar o jogo
    public void restartGame(){
        game.reiniciarJogo(objectiveDeck, territoryCardDeck);
        game.setAlt1(null);
        game.setAlt2(null);
    }

    public void giveTerritoryCard(int t){
        if(game.getPlayerTurn(t).getConquistouNessaRodada()){
            territoryCardDeck.pickRandomCard(game.getPlayerTurn(t));
        }
    }

    public void resetConquer(int t){
        game.getPlayerTurn(t).setConqueredThisRound(false);
    }

    public Integer getPlayerTerritoryTurn(int turn){
        return game.getPlayerTurn(turn).getTerritoryNumber();
    }

    // Método para obter a quantidade de territórios de um jogador específico
    public int getNumTerritoryPlayer(PlayerColor playerColor) {
        int quantidadeTerritorios = 0;

        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o território pertence ao jogador da cor específica
            if (territory.getOwner().getColor() == playerColor) {
                quantidadeTerritorios++;
            }
        }
        return quantidadeTerritorios;
    }

    public String[] getTerritoriosDoJogador (PlayerColor playerColor){
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

    //pega vizinhos que não são do jogador
    public String[] getNeiboursNotDominated(String t, int vez) {
        List<Territory> territoriesList = map.getTerritoriesList();
        List<String> territoriesNotDominated = new ArrayList<>();

        for (Territory ter : territoriesList) {
            // Verifica se o território é vizinho e não é dominado pelo jogador
            if (ter.isNeighbor(t) && !ter.getOwner().getName().equals(game.getPlayerTurn(vez).getName())) {
                territoriesNotDominated.add(ter.getName());
            }
        }

        // Se não houver territórios não dominados, retorna null
        if (territoriesNotDominated.isEmpty()) {
            return null;
        }

        // Converte a lista para um array
        return territoriesNotDominated.toArray(new String[0]);
    }

    //pega territorios vizinhos que tem mais de um exercito
   public String[] getTerritoryMoreOne(PlayerColor playerColor) {
        int quant = 0;

        // Obtém a lista de territórios do objeto Map
        ArrayList<Territory> territories = map.getTerritoriesList();
        List<String> territoriesMoreOneArmy = new ArrayList<>();

        // Adiciona na lista os nomes dos territórios com mais de um exército
        for (Territory t : territories) {
            if (t.getOwner().getColor() == playerColor && t.getArmies() > 1) {
                territoriesMoreOneArmy.add(t.getName());
                quant++;
            }
        }

        // Se não houver territórios com mais de um exército, retorna null
        if (territoriesMoreOneArmy.isEmpty()) {
            return null;
        }

        // Copia a lista para uma lista final, removendo espaços vazios
        String[] finalTerritories = new String[quant];
        territoriesMoreOneArmy.toArray(finalTerritories);//

        return finalTerritories;
    }

    // Retornar todos os jogadores
    public ArrayList<Player> getAllPlayers() {
        return this.game.getPlayers();
    }

    // Método que retorna a quantidade de jogadores
    public int getNumPlayers(){
        return game.getPlayers().size();
    }

    // Altera o estado de eliminação do jogador
    public void withdrawEliminated(String name){
        game.getPlayer(name).setEliminatedThisRound(false);
    }

     // Retorna lista de nomes de territórios
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

    // Retorna quantidade de exércitos que tem em um território
    public Integer getNumArmiesTerritory(String territorio) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(territorio)) {
                // Retorna a quantidade de exércitos do território encontrado
                return territory.getArmies();
            }
        }
        // Retorna null se o território não for encontrado
        return null;
    }

    // Chama reposicionarExercitos de Jogo
    public void replaceArmies(String origin, String destiny, Integer qtd){
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();
        Territory TOrigin = null;
        Territory TDestiny = null;

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(origin)) {
                // Retorna a quantidade de exércitos do território encontrado
                TOrigin = territory;
            }
            if (territory.getName().equals(destiny)) {
                // Retorna a quantidade de exércitos do território encontrado
                TDestiny = territory;
            }
        }

        // Verifica se encontrou os territórios de origem e destino
        if (TOrigin != null && TDestiny != null) {
            // Chama o método de reposicionarExercitos
            game.reposicionarExercitos(TOrigin, TDestiny, qtd);
        } else {
            System.out.println("Territórios de origem ou destino não encontrados.");
        }
    }

    // Retorna quantidade de exércitos que tem em um território
    public Integer getNumOldArmies(String territorio) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(territorio)) {
                // Retorna a quantidade de exércitos do território encontrado
                return territory.getInicialArmie();
            }
        }
        // Retorna null se o território não for encontrado
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


     // Atualiza a quantidade de exércitos em um território
     public void setNumArmiesTerritory(String territorio, int count) {
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(territorio)) {
                // Usa o método addArmies para incrementar a quantidade de exércitos
                territory.setArmies(count);
                return;
            }
        }
    }


    //Método de realizar ataque
    public int[] makeAttack(String attacker,String defender, Integer numAttack, Integer numDefense) {

    	Territory Tattacker = map.findTerritory(attacker);
    	Territory Tdefender = map.findTerritory(defender);

        // Realiza ataque e retorna array com os resultados
        int[] array = game.realizaAtaque(Tattacker, Tdefender, numAttack, numDefense);

        // Verifica se jogador ganhou após essa rodada
		APIController.getInstance().verifyWin(APIController.getInstance().getTurn());
        return array;
    }

    // Retorna array com os nomes dos territórios das cartas do jogador
    public String[] getPlayerCardNames(int turn){
        List<TerritoryCard> cards = game.getPlayerTurn(turn).getCard();
        String[] arrayCartas = new String[cards.size()];
        int cont = 0;

        // Adiciona na lista os nomes dos territórios das cartas
        for (TerritoryCard c: cards) {
            arrayCartas[cont] = c.getName();
            cont++;
        }

        return arrayCartas;
    }


    //Verifica se o jogador da vez ganhou o jogo
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

    // Pegar carta de objetivo do jogador
    public String playerObjective(String name) {
        for(Player player : game.getPlayers()){
            if(player.getName() == name) {
                return player.getObjectiveName();
            }
        }
        return game.getPlayers().get(this.turn).getObjectiveName();
    }

    public static TerritoryCard[] getCartasTerritorio() {
        return null;
    }

    // Pegar todas as cartas de território do jogador
    public List<TerritoryCard> retrieveTerritoryCards() {
        return game.getPlayers().get(this.turn).getAllCards();
    }

    // Pegar todas as cartas de território do jogador
    public List<TerritoryCard> getTerritoryCards(int t) {
        return game.getPlayerTurn(t).getCard();
    }

    // Pegar lista de territórios em posse do jogador
    public List<Territory> retrieveTerritories() {
        return game.getPlayers().get(this.turn).getConqueredTerritories();
    }

    // Adicionar jogadores
    public boolean addPlayer(String name, PlayerColor color, int index) {
        Player player = new Player(name, color, index);
        return game.addPlayer(player);
    }

    // Pegar o nome do jogador da vez
    public String getPlayerTurn(int i){
        return game.getPlayerTurn(i).getName();
    }

    // Método que retorna a cor do jogador da vez
    public PlayerColor getPlayerColorTurn(int i){
        return game.getPlayerTurn(i).getColor();
    }

    public boolean getJogadorVezEliminadoRodada(int i){
        return game.getPlayerTurn(i).getEliminadoNessaRodada();
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

    // Sorteia objetivos para todos os jogadores
    public void shuffleObjectives(List<Player> players, ObjectiveCardDeck objectiveDeck) {
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveDeck.shuffleObjective(player);
		}
	}

    // Conferir vencedor da batalha
    public Integer[] battleWinner(List<Integer> diceAttack, List<Integer> diceDefense) {
        // Criar lista de quantidade de derrotas
        Integer[] battle = new Integer[2];

        // Quantidade de dados
        int attackCount = diceAttack.size();
        int defenseCount = diceDefense.size();

        // Contador de derrotas
        int countAttack = 0;
        int countDefense = 0;

        // Verifica que ambos ainda possuem dados a serem comparados
        while(attackCount != 0 && defenseCount != 0){
            int attackValue = 0;
            int defenseValue = 0;

            // Encontrar o maior valor de ataque
            for(int i = 0; i < attackCount; i++) {
                if(diceAttack.get(attackValue) < diceAttack.get(i)) {
                    attackValue = i;
                }
            }

            // Encontrar o maior valor de defesa
            for(int i = 0; i< defenseCount; i++) {
                if(diceDefense.get(defenseValue) < diceDefense.get(i)) {
                    defenseValue = i;
                }
            }

            // Comparar os dois maiores valores encontrados
            if(diceAttack.get(attackValue) < diceDefense.get(defenseValue)) {
                countAttack++;
            } else {
                countDefense++;
            }
        }

        // Salvar quantidade de derrotas
        battle[0] = countAttack;
        battle[1] = countDefense;

        return battle;
    }

    public boolean canTradeCards(int turn){
        Player player = game.getPlayerTurn(turn);
        return game.hasTrade(player);
    }

    // Troca cartas do jogador da vez
    public Integer tradeCards(int turn, int numDeTrocas){
        Player player = game.getPlayerTurn(turn);

        // Se o jogador puder trocar cartas, chama o método de trocar cartas de Jogo
        if (game.hasTrade(player)){
            return game.trocarCartas(numDeTrocas, territoryCardDeck, map, player);
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
    
            // Adicione a extensão .txt se ainda não estiver presente
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }
    
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8"));
    
                writer.write(String.valueOf(APIController.getInstance().getFirstRound()));
                writer.write("\n");
    
                // Escreve qtd de jogadores e vez do jogador
                writer.write(String.valueOf(game.getPlayers().size()));
                writer.write("\n");
                writer.write(String.valueOf(APIController.getInstance().getTurn()));
                writer.write("\n");
    
                // Informações dos jogadores (nome, cor, objetivo)
                for (Player player : game.getPlayers()) {
                    writer.write("Jogador: " + player.getName() + ", Cor: " + player.getColor() + "\n");
                    writer.write("Objetivo: " + player.getObjectiveName() + "\n");
                }
    
                // Informações dos territórios e seus proprietários
                for (Territory territory : map.getTerritoriesList()) {
                    writer.write("Território: " + territory.getName() + ", Tropas: " + territory.getArmies() + " / ");
    
                    // Verificar se o território tem um proprietário
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
    public void notificaObsJogo(){
        game.notifyObservers();
    }
}

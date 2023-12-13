package Model;
import View.ViewAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        // players = new ArrayList<>();
    }



    // Singleton
    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }
        return apiInstance;
    }

    // Método para reiniciar o jogo
    public void reiniciarJogo(){
        game.reiniciarJogo(objectiveDeck, territoryCardDeck);
        game.setMod1(null);
        game.setMod2(null);
    }

    public void giveTerritoryCard(int t){
        if(game.getJogadorVez(t).getConquistouNessaRodada()){
            territoryCardDeck.pickRandomCard(game.getJogadorVez(t));
        }
    }

    public void resetConquista(int t){
        game.getJogadorVez(t).setConquistouNessaRodada(false);
    }

    public Integer getTerritorioJogadorVez(int turn){
        return game.getJogadorVez(turn).getTerritoryNumber();
    }

    // Método para obter a quantidade de territórios de um jogador específico
    public int getNumTerritoryPlayer(PlayerColor corDoJogador) {
        int quantidadeTerritorios = 0;

        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o território pertence ao jogador da cor específica
            if (territory.getOwner().getColor() == corDoJogador) {
                quantidadeTerritorios++;
            }
        }
        return quantidadeTerritorios;
    }

    public String[] getTerritoriosDoJogador (PlayerColor corDoJogador){
        int quant = 0;

        // Obtém a lista de territórios do objeto Map
        ArrayList<Territory> territories = map.getTerritoriesList();
        String[] territoryList = new String[getNumTerritoryPlayer(corDoJogador)];

        // Adiciona na lista os nomes dos territórios
    	for (Territory t: territories) {
            if (t.getOwner().getColor() == corDoJogador) {
                territoryList[quant] = t.getName();
                quant++;
            }

    	}
    	return territoryList;


    }

    //pega vizinhos que não são do jogador
    public String[] getNeiboursNotDominated(String t, int vez) {
        List<Territory> listaTerritorios = map.getTerritoriesList();
        List<String> territoriosNaoDominados = new ArrayList<>();

        for (Territory ter : listaTerritorios) {
            // Verifica se o território é vizinho e não é dominado pelo jogador
            if (ter.isNeighbor(t) && !ter.getOwner().getName().equals(game.getJogadorVez(vez).getName())) {
                territoriosNaoDominados.add(ter.getName());
            }
        }

        // Se não houver territórios não dominados, retorna null
        if (territoriosNaoDominados.isEmpty()) {
            return null;
        }

        // Converte a lista para um array
        return territoriosNaoDominados.toArray(new String[0]);
    }

    //pega territorios vizinhos que tem mais de um exercito
   public String[] getTerritoryMoreOne(PlayerColor corDoJogador) {
        int quant = 0;

        // Obtém a lista de territórios do objeto Map
        ArrayList<Territory> territories = map.getTerritoriesList();
        List<String> territoriosComMaisDeUmExercito = new ArrayList<>();

        // Adiciona na lista os nomes dos territórios com mais de um exército
        for (Territory t : territories) {
            if (t.getOwner().getColor() == corDoJogador && t.getArmies() > 1) {
                territoriosComMaisDeUmExercito.add(t.getName());
                quant++;
            }
        }

        // Se não houver territórios com mais de um exército, retorna null
        if (territoriosComMaisDeUmExercito.isEmpty()) {
            return null;
        }

        // Copia a lista para uma lista final, removendo espaços vazios
        String[] territoriosFinal = new String[quant];
        territoriosComMaisDeUmExercito.toArray(territoriosFinal);//

        return territoriosFinal;
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
    public void retiraEliminado(String name){
        game.getPlayer(name).setEliminadoNessaRodada(false);
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
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(t)) {
                // Retorna a cor do território encontrado
                return territory.getCor();
            }
        }
        // Retorna null se o território não for encontrado
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
    public void reposicionarExercitos(String origem, String destino, Integer qtd){
        // Obtém a lista de territórios do objeto Map
        List<Territory> territories = map.getTerritoriesList();
        Territory TOrigem = null;
        Territory TDestino = null;

        // Percorre a lista de territórios
        for (Territory territory : territories) {
            // Verifica se o nome do território é igual ao território desejado
            if (territory.getName().equals(origem)) {
                // Retorna a quantidade de exércitos do território encontrado
                TOrigem = territory;
            }
            if (territory.getName().equals(destino)) {
                // Retorna a quantidade de exércitos do território encontrado
                TDestino = territory;
            }
        }

        // Verifica se encontrou os territórios de origem e destino
        if (TOrigem != null && TDestino != null) {
            // Chama o método de reposicionarExercitos
            game.reposicionarExercitos(TOrigem, TDestino, qtd);
        } else {
            System.out.println("Territórios de origem ou destino não encontrados.");
        }
    }

    // Retorna quantidade de exércitos que tem em um território
    public Integer getQntExTerritorioAntigos(String territorio) {
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
            List<Territory> listaTerritorios = map.getTerritoriesList();
            List<String> territoriosDominados = new ArrayList<>();

            for (Territory ter: listaTerritorios) {
                if (ter.isNeighbor(t) && ter.getOwner().getName().equals(game.getJogadorVez(turn).getName())) {
                    territoriosDominados.add(ter.getName());
                }
            }
            if (territoriosDominados.isEmpty()) {
                return null;
            }
            return territoriosDominados.toArray(new String[0]);

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
    public int[] makeAttack(String atacante,String defensor, Integer numAtaque, Integer numDefesa) {

    	Territory Tatacante = map.findTerritory(atacante);
    	Territory Tdefensor = map.findTerritory(defensor);

        // Realiza ataque e retorna array com os resultados
        int[] array = game.realizaAtaque(Tatacante, Tdefensor, numAtaque, numDefesa);

        // Verifica se jogador ganhou após essa rodada
		APIController.getInstance().verificaGanhou(APIController.getInstance().getTurn());
        return array;
    }

    // Retorna array com os nomes dos territórios das cartas do jogador
    public String[] getNomesCartasJogador(int turn){
        List<TerritoryCard> cartas = game.getJogadorVez(turn).getCard();
        String[] arrayCartas = new String[cartas.size()];
        int cont = 0;

        // Adiciona na lista os nomes dos territórios das cartas
        for (TerritoryCard c: cartas) {
            arrayCartas[cont] = c.getName();
            cont++;
        }

        return arrayCartas;
    }


    //Verifica se o jogador da vez ganhou o jogo
    public boolean verificaGanhou(int turn){
        if (turn == -1){
            for (Player player : game.getPlayers()) {
                if (player.getObjective().checkStatus()){
                    APIController.getInstance().setTurn(game.getPlayer(player.getName()).getIndex());
                    return true;
                }
            }
            return false;
        }

        Player player = game.getJogadorVez(turn);
        return player.getObjective().checkStatus();
    }

    public String[] getNomesJogadores() {
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
        return game.getJogadorVez(t).getCard();
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
    public String getNomeJogadorVez(int i){
        return game.getJogadorVez(i).getName();
    }

    // Método que retorna a cor do jogador da vez
    public PlayerColor getCorJogadorVez(int i){
        return game.getJogadorVez(i).getColor();
    }

    public boolean getJogadorVezEliminadoRodada(int i){
        return game.getJogadorVez(i).getEliminadoNessaRodada();
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
        Player player = game.getJogadorVez(turn);
        return game.temTroca(player);
    }

    // Troca cartas do jogador da vez
    public Integer trocarCartas(int turn, int numDeTrocas){
        Player player = game.getJogadorVez(turn);

        // Se o jogador puder trocar cartas, chama o método de trocar cartas de Jogo
        if (game.temTroca(player)){
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
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));

                writer.write(String.valueOf(APIController.getInstance().getFirstRound()));
                writer.write("\n");

                //Escreve qtd de jogadores e vez do jogador
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

    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();

        // Configurar o JFileChooser para abrir a caixa de diálogo de abrir arquivo
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileToLoad));

                // Leitura de informações do arquivo
                // APIController.getInstance().setFirstRound(Boolean.parseBoolean(reader.readLine()));

                // Ler quantidade de jogadores e vez do jogador
                int numPlayers = Integer.parseInt(reader.readLine());
                int currentTurn = Integer.parseInt(reader.readLine());
                APIController.getInstance().setTurn(currentTurn);

                // Limpar jogadores existentes
                game.getPlayers().clear();

                // Ler informações dos jogadores
                for (int i = 0; i < numPlayers; i++) {
                    String playerName = reader.readLine().split(":")[1].trim();
                    String playerColor = reader.readLine().split(":")[1].trim();
                    String objective = reader.readLine().split(":")[1].trim();
                    Player player = new Player(playerName, PlayerColor.valueOf(playerColor), i);
                    // player.setObjective(ObjectiveFactory.createObjective(objective)); // Substitua ObjectiveFactory.createObjective pela lógica real da sua fábrica
                    game.addPlayer(player);
                }

                // Limpar proprietários existentes dos territórios
                for (Territory territory : map.getTerritoriesList()) {
                    territory.setOwner(null);
                }

                // Ler informações dos territórios
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] territoryInfo = line.split("/");
                    String territoryName = territoryInfo[0].split(":")[1].trim();
                    int armies = Integer.parseInt(territoryInfo[1].split(":")[1].trim());
                    String ownerName = territoryInfo[2].split(":")[1].trim();
                    
                    // Encontrar o território pelo nome
                    Territory territory = map.findTerritory(territoryName);
                    if (territory != null) {
                        territory.setArmies(armies);

                        // Atribuir proprietário se disponível
                        if (!ownerName.equals("Sem Proprietário")) {
                            Player owner = game.getPlayer(ownerName);
                            territory.setOwner(owner);
                        }
                    }
                }

                reader.close();

                System.out.println("Jogo carregado com sucesso de " + fileToLoad.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erro ao carregar o jogo: " + e.getMessage());
            }
        }
    }


    // Notifica observadores de jogo
    public void notificaObsJogo(){
        game.notifyObservers();
    }
}

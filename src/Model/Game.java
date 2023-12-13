package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import Controller.APIController;
import View.Observed;
import View.Observer;
import java.util.List;

class Game implements Observed{
    private static Game game = null;

    // Lista de jogadores
    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Observer> lst = new ArrayList<Observer>();

	private List<TerritoryCard> territoryCards;


	private Integer numContinent = 0;

    private Map map = Map.getMap();


	private Territory alt1 = null;
	private Territory alt2 = null;

    // Inicializa o jogo
    public boolean initiateGame(){
        map.distributeTerritories(players);

        return true;
    }
    private Game() {}

    // Singleton
    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public void add(Observer o){
        lst.add(o);
    }

    public void remove(Observer o){
        lst.remove(o);
    }

	// Método para passar informações observadores
    public Object get(){
        // Array de informações
        Object infos[] = new Object[4];

        
        ArrayList<String> numArmies = new ArrayList<String>();

        ArrayList<PlayerColor> colors = new ArrayList<PlayerColor>();

        for (Territory t: map.getTerritoriesList()){
            numArmies.add(((Integer)t.getArmies()).toString());
            colors.add(t.getCor());
        }
        infos[0] = numArmies;
        infos[1] = colors;

        if (alt1 == null){
            infos[2] = -1;
        }
        else{
            infos[2] = map.getTerritoriesList().indexOf(alt1);
        }
        if (alt2 == null){
            infos[3] = -1;
        }
        else{
            infos[3] = map.getTerritoriesList().indexOf(alt2);
        }
        return infos;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayerTurn(int turn){

        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getIndex() == turn) {
                return players.get(i);
            }
        }
		return players.get(turn);

	}

	public void setAlt1(Territory t){
		alt1 = t;
	}

	public void setAlt2(Territory t){
		alt2 = t;
	}

    public void notifyObservers() {
        for (Observer o : lst) {
            o.notify(this);
        }
    }

	//Verifica se o jogador pode trocar cartas
	public boolean hasTrade(Player player){
		int circles = 0, squares = 0, triangles = 0;
		territoryCards = player.getCard();

		// Conta quantas cartas de cada formato o jogador possui
		for (TerritoryCard cards: territoryCards){
			if (cards.getShape().equals(Shape.Circle))
				circles++;
			else if (cards.getShape().equals(Shape.Square))
				squares++;
			else if (cards.getShape().equals(Shape.Triangle))
				triangles++;
			//se for jocker adiciona 1 em todos pois o jocker entra em qualquer caso
			else{
				circles++;
				squares++;
				triangles++;
			}
		}

		// Se o jogador possui 3 cartas de um formato ou 1 de cada formato, pode trocar
		if (circles >= 3 || squares >= 3 || triangles >= 3 || (circles >= 1 && squares >= 1 && triangles >= 1)){
			return true;
		}

		return false;
	}

	public Integer changeCards (int numDeTrocas, TerritoryCardDeck territoryCardDeck, Map map, Player player) {

		ArrayList<TerritoryCard> circles = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> squares = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> triangles = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> jokers = new ArrayList<TerritoryCard>();

		// Separa as cartas por formato
		for (TerritoryCard card: territoryCards){
			if (card.getShape().equals(Shape.Circle))
				circles.add(card);
			else if (card.getShape().equals(Shape.Square))
				squares.add(card);
			else if (card.getShape().equals(Shape.Triangle))
				triangles.add(card);
			else
				jokers.add(card);
		}


		if (circles.size() >= 3){
			// Troca três cartas de círculo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				useCards(circles, territoryCardDeck, map, player);
			}
		}

		else if (squares.size() >= 3){
			// Troca três cartas de quadrado e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				useCards(squares, territoryCardDeck, map, player);
			}
		}

		else if (triangles.size() >= 3){
			// Troca três cartas de triângulo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				useCards(triangles, territoryCardDeck, map, player);
			}
		}

		else {
			if(jokers.size() == 0 );{
				// Troca uma de cada e devolve elas para o baralho
				useCards(circles, territoryCardDeck, map, player);
				useCards(squares, territoryCardDeck, map, player);
				useCards(triangles, territoryCardDeck, map, player);
			}
			if (jokers.size() == 1 ){
				useCards(jokers, territoryCardDeck, map, player);
				if (circles.size() == 0){
					// Remove um coringa, um quadrado e um triângulo
					useCards(squares, territoryCardDeck, map, player);
					useCards(triangles, territoryCardDeck, map, player);
					// break;
				}
			
				else if (squares.size() == 0){
					// Remove um coringa, um círculo e um triângulo
					useCards(circles, territoryCardDeck, map, player);
					useCards(triangles, territoryCardDeck, map, player);
				}

				else{
					// Remove um coringa, um círculo e um quadrado
					useCards(circles, territoryCardDeck, map, player);
					useCards(squares, territoryCardDeck, map, player);
				}

			}
			if (jokers.size() == 2){
				// Remove dois coringas e uma carta de qualquer formato
				useCards(jokers, territoryCardDeck, map, player);
				useCards(jokers, territoryCardDeck, map, player);
				if (circles.size() == 0 && squares.size() == 0){
					useCards(triangles, territoryCardDeck, map, player);
				}

				else if (squares.size() == 0 && triangles.size() == 0){
					useCards(circles, territoryCardDeck, map, player);
				}

				else if (circles.size() == 0 && triangles.size() == 0){
					useCards(squares, territoryCardDeck, map, player);
				}

				else if (circles.size() == 1){
					useCards(circles, territoryCardDeck, map, player);
				}

				else if (squares.size() == 1){
					useCards(squares, territoryCardDeck, map, player);
				}

				else{
					useCards(triangles, territoryCardDeck, map, player);
				}
			}

		}

		Integer qtd;
		if (numDeTrocas <= 5) {
			qtd = 4 + (2 * (numDeTrocas));
		}

		else if (numDeTrocas == 6) {
			qtd = 15;
		}
		else {
			int diferenca = numDeTrocas - 6;
			qtd = 15 + (diferenca * 5);
		}

		return qtd;
	}

	// Remove a carta do topo do baralho e adiciona ao jogador
	private void useCards(ArrayList<TerritoryCard> lista, TerritoryCardDeck territoryCardDeck, Map map, Player player){

		TerritoryCard terrCard = lista.get(0);

		territoryCards.remove(terrCard);
		lista.remove(terrCard);

		territoryCardDeck.returnCard(terrCard);

		// Se o território da carta pertence ao jogador, aumenta em 2 a quantidade de exércitos
		if (terrCard.getName() != null){
			Territory territory = map.findTerritory(terrCard.getName());
			if (territory != null && territory.getOwner() == player) {
				territory.changeNumArmies(2);
				//Atualiza os territórios modificados
				alt1 = territory;
				alt2 = null;
				this.notifyObservers();
			}
		}
	}
	
	public void continentDomain(Integer turn){
        List<Territory> territorios;
        Integer qtd;
		int cont = 0;
		String[] continents = new String[map.getContinents().size()];

		for (Continent c: map.getContinents()) {
            continents[cont] = c.getName();
            cont++;
        }

        for (numContinent = 0; numContinent < 6; numContinent++){
            if (dominaCont(turn, continents[numContinent])){
                territorios = getTerritoriosCont(continents[numContinent]);
                qtd = getExCont(turn, continents[numContinent]);

				 // Embaralha a lista de territórios
				Collections.shuffle(territorios);

				// Pega apenas o primeiro território da lista (aleatório)
				Territory randomTerritory = territorios.get(0);

				randomTerritory.changeNumArmies(qtd);
				// Atualiza os territórios modificados
				alt1 = randomTerritory;
				alt2 = null;
				this.notifyObservers();
            }
        }
    }

    // Retorna se o jogador domina o continente
    public boolean dominaCont(int turn, String continent){
        return map.findContinent(continent).checkContinentDomain(game.getPlayerTurn(turn));
    }

    // Retorna lista de nomes dos territórios de um continente
    public List<Territory> getTerritoriosCont(String c){
        List<Territory> listaTerritorios = map.findContinent(c).getTerritories();
        return listaTerritorios;
    }

     // Retorna quantidade de exércitos a posicionar no continente
     public Integer getExCont(int turn, String c){
        Integer qtd = map.findContinent(c).getBonusArmies();
        return qtd;
    }

    // Adiciona jogador na partida
    public boolean addPlayer(Player jogador){
        for (Player j: players){
            if (j.getName().equals(jogador.getName()) || j.getColor() == jogador.getColor())
                return false;
        }
        players.add(jogador);
        for (Player player : players) {
        }
        return true;
    }

    //Valida um ataque
	public boolean verifyAttack(Territory tAtacante, Territory tDefensor) {
		// Verifica se o atacante tem mais de um exército e se o defensor não é dele
		if(tAtacante.getArmies() > 1 && tAtacante.getOwner() != tDefensor.getOwner())
			return true;
		return false;
	}

    //Realiza um ataque
	public int[] makeAttack(Territory atacante, Territory defensor, Integer numAtaque, Integer numDefesa) {

		if(verifyAttack(atacante, defensor)){

			int qtdAtaque = atacante.getArmies() - 1;
			if  (qtdAtaque > 3) {qtdAtaque = 3;}

			int qtdDefesa = defensor.getArmies();
			if  (qtdDefesa > 3) {qtdDefesa = 3;}

			//Cria os arrays de dados
			int[] dadosAtaque = new int[3];
			int[] dadosDefesa = new int[3];
			//Cria um dado
			Dado dado = new Dado();
			//Variáveis para contar quantos exércitos foram perdidos
			int qtdAtaquePerdidos = 0;
			int qtdDefesaPerdidos = 0;


			int i;

			if (numAtaque != 0){
				for (i = 0;i < 3;i++) {
					if (i < qtdAtaque)
						dadosAtaque[i] = numAtaque;
					else
						dadosAtaque[i] = 0;
				}
			}
			else{
				for (i = 0;i < 3;i++) {
					if (i < qtdAtaque)
						dadosAtaque[i] = dado.throwDice();
					else
						dadosAtaque[i] = 0;
				}
				
				Arrays.sort(dadosAtaque);
			}

			
			if (numDefesa != 0){
				for (i = 0;i < 3;i++) {
					if (i < qtdDefesa)
						dadosDefesa[i] = numDefesa;
					else
						dadosDefesa[i] = 0;
				}
			}
			else{
				for (i = 0;i < 3;i++) {
					if (i < qtdDefesa)
						dadosDefesa[i] = dado.throwDice();
					else
						dadosDefesa[i] = 0;
				}
				
				Arrays.sort(dadosDefesa);
			}

			
			for (i = 0;i < 3;i++) {
				if (dadosAtaque[i] != 0 && dadosDefesa[i] != 0){
					if (dadosAtaque[i] > dadosDefesa[i]) {
						qtdDefesaPerdidos++;
					}
					else {
						qtdAtaquePerdidos++;
					}
				}
			}

			
			atacante.setArmies(atacante.getArmies() - qtdAtaquePerdidos);
			defensor.setArmies(defensor.getArmies() - qtdDefesaPerdidos);

			
			alt1 = atacante;
			alt2 = defensor;

			
			if (defensor.getArmies()==0) {
				
				defensor.getOwner().loseTerritory(defensor);

				if (defensor.getOwner().getTerritoryNumber() == 0){
					
					defensor.getOwner().setEliminatedThisRound(true);
					defensor.getOwner().setJMatou(atacante.getOwner());
					APIController.getInstance().addEliminated(defensor.getOwner().getName());
				}

				defensor.setOwner(atacante.getOwner());

				atacante.getOwner().addTerritorio(defensor);

				atacante.getOwner().setConqueredThisRound(true);

				int qtdPassada = atacante.getArmies() - 1;
				if (qtdPassada > 3) {qtdPassada = 3;}

				atacante.changeNumArmies(-qtdPassada);
				defensor.setArmies(qtdPassada);
			}

			//Notifica os observadores
			this.notifyObservers();

			int[] dados = new int[6];
			for (i = 0;i < 3;i++) {
				dados[i] = dadosAtaque[i];
			}
			for (int j = 0;j < 3;j++) {
				dados[i] = dadosDefesa[j];
				i++;
			}

			return dados;
		}

		System.out.println("Nao foi possivel realizar o ataque");
		return new int [] {0,0,0,0,0,0};
	}

	// Método para reposicionar exércitos
	public void reposicionarExercitos(Territory origem, Territory destino, Integer qtd){
		origem.changeNumArmies(-qtd);
		destino.changeNumArmies(qtd);
		alt1 = origem;
		alt2 = destino;
		this.notifyObservers();
	}

    // Método para reiniciar o jogo
	public void restartGame(ObjectiveCardDeck objectiveCardDeck, TerritoryCardDeck territoryCardDeck){

		for (Player player: players){
			// Devolve objetivo do jogador para lista
			objectiveCardDeck.returnObjectiveCard(player.getObjective());
	
			// Devolve cartas do jogador para lista
			for (TerritoryCard terrCard: player.getCard()){
				territoryCardDeck.returnCard(terrCard);
			}
			player.reset();
		}
		// Redistribui territórios
		map.distributeTerritories(players);

		// Redistribui objetivos
		Collections.shuffle(players);
		for(Player player : players) {
			objectiveCardDeck.shuffleObjective(player);
		}
	}

	//Retorna o jogador pelo nome
	public Player getPlayer(String name) {
		for(Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

}




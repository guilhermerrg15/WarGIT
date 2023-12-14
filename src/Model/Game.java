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

	

    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Observer> lst = new ArrayList<Observer>();

	private List<TerritoryCard> territoryCards;

	private static final int MAX_DICE = 3;
    private static final int MIN_ARMIES_TO_ATTACK = 3;

	private Integer numContinent = 0;

    private Map map = Map.getMap();

	protected Integer qtd = 0;


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
		int circles = 0, squares = 0, triangles = 0, jockers = 0;
		territoryCards = player.getCard();

		// Conta quantas cartas de cada formato o jogador possui
		for (TerritoryCard cards: territoryCards){
			if (cards.getShape().equals(Shape.Circle))
				circles++;
			else if (cards.getShape().equals(Shape.Square))
				squares++;
			else if (cards.getShape().equals(Shape.Triangle))
				triangles++;
			else{
				jockers++;
			}
		}

		// Se o jogador possui 3 cartas de um formato ou 1 de cada formato, pode trocar
		if (circles >= 3 || squares >= 3 || triangles >= 3 || (circles >= 1 && squares >= 1 && triangles >= 1) || (jockers == 1 && circles >= 2) || (jockers == 1 && triangles >= 2) || (jockers == 1 && squares >= 2) || (jockers == 2 && circles >= 1) || (jockers == 2 && triangles >= 1) || (jockers == 2 && squares >= 1) || (jockers == 1 && circles >= 1 && squares >= 1) || (jockers == 1 && circles >= 1 && triangles >= 1) || (jockers == 1 && triangles >= 1 && squares >= 1)){
			return true;
		}

		return false;
	}

	public Integer changeCards (int num, TerritoryCardDeck territoryCardDeck, Map map, Player player) {

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


		int i;


		if (squares.size() >= 3){
			for (i = 0; i < 3; i++){
				useCards(squares, territoryCardDeck, map, player);
			}
		}

		else if (triangles.size() >= 3){
			for (i = 0; i < 3; i++){
				useCards(triangles, territoryCardDeck, map, player);
			}
		}

		 else if (circles.size() >= 3){
			for (i = 0; i < 3; i++){
				useCards(circles, territoryCardDeck, map, player);
			}
		}
		else {
			if(jokers.size() == 0 );{
				useCards(squares, territoryCardDeck, map, player);
				useCards(circles, territoryCardDeck, map, player);
				useCards(triangles, territoryCardDeck, map, player);
			}
			if (jokers.size() == 1 ){
				useCards(jokers, territoryCardDeck, map, player);
				if (squares.size() == 0){
					useCards(triangles, territoryCardDeck, map, player);
					useCards(circles, territoryCardDeck, map, player);
				}
				 else if (circles.size() == 0){
					useCards(squares, territoryCardDeck, map, player);
					useCards(triangles, territoryCardDeck, map, player);
				 }
				else{
					useCards(squares, territoryCardDeck, map, player);
					useCards(circles, territoryCardDeck, map, player);
				}

			}
			if (jokers.size() == 2){
				useCards(jokers, territoryCardDeck, map, player);
				useCards(jokers, territoryCardDeck, map, player);
				if (triangles.size() == 1 || circles.size() == 0 && squares.size() == 0){
					useCards(triangles, territoryCardDeck, map, player);
				}

				else if (circles.size() == 1 || squares.size() == 0 && triangles.size() == 0){
					useCards(circles, territoryCardDeck, map, player);
				}

				else if (squares.size() == 1 || circles.size() == 0 && triangles.size() == 0){
					useCards(squares, territoryCardDeck, map, player);
				}
			}

		}
		
		if (num <= 5 ) {
			qtd = 4 + (2 * (num));
		}

		else if (num == 6) {
			qtd = 15;
		}
		else if (num > 6) {
			qtd += 5;
		}
		return qtd;
	}

	// Remove a carta do topo do baralho e adiciona ao jogador
	private void useCards(ArrayList<TerritoryCard> lista, TerritoryCardDeck territoryCardDeck, Map map, Player player){

		TerritoryCard terrCard = lista.get(0);

		territoryCards.remove(terrCard);
		lista.remove(terrCard);

		territoryCardDeck.returnCard(terrCard);
		if (terrCard.getName() != null){
			Territory territory = map.findTerritory(terrCard.getName());
			if (territory != null && territory.getOwner() == player) {
				territory.changeNumArmies(2);
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
				Collections.shuffle(territorios);
				Territory randomTerritory = territorios.get(0);
				randomTerritory.changeNumArmies(qtd);
				alt1 = randomTerritory;
				alt2 = null;
				this.notifyObservers();
            }
        }
    }

    public boolean dominaCont(int turn, String continent){
        return map.findContinent(continent).checkContinentDomain(game.getPlayerTurn(turn));
    }

    public List<Territory> getTerritoriosCont(String c){
        List<Territory> listaTerritorios = map.findContinent(c).getTerritories();
        return listaTerritorios;
    }

     public Integer getExCont(int turn, String c){
        Integer qtd = map.findContinent(c).getBonusArmies();
        return qtd;
    }


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

	public boolean verifyAttack(Territory tAtacante, Territory tDefensor) {
		if(tAtacante.getArmies() > 1 && tAtacante.getOwner() != tDefensor.getOwner())
			return true;
		return false;
	}

	public int[] makeAttack(Territory attacker, Territory defender, Integer numAttack, Integer numDefense) {
        if (!verifyAttack(attacker, defender)) {
            System.out.println("Não foi possível realizar o ataque");
            return new int[]{0, 0, 0, 0, 0, 0};
        }

		int qtdAtaque = attacker.getArmies() - 1;
		if  (qtdAtaque > 3) {qtdAtaque = 3;}

		int qtdDefesa = defender.getArmies();
		if  (qtdDefesa > 3) {qtdDefesa = 3;}

        int[] attackDice = rollDice(numAttack, attacker.getArmies());
        int[] defenseDice = rollDice(numDefense, defender.getArmies());

        Arrays.sort(attackDice);
        Arrays.sort(defenseDice);

        int attackLosses = calculateLosses(attackDice, defenseDice);
        int defenseLosses = MAX_DICE - attackLosses;

		attacker.setArmies(attacker.getArmies() - attackLosses);
		defender.setArmies(defender.getArmies() - defenseLosses);

		alt1 = attacker;
		alt2 = defender;

        if (defender.getArmies() == 0) {
            handleConqueredTerritory(attacker, defender);
        }

        this.notifyObservers();

        int[] result = new int[MAX_DICE * 2];
        for (int i = 0; i < MAX_DICE; i++) {
            result[i] = attackDice[i];
            result[i + MAX_DICE] = defenseDice[i];
        }

        return result;
    }

	private int[] rollDice(Integer numDice, int armies) {
        int[] dice = new int[MAX_DICE];
        Dado dado = new Dado();

        for (int i = 0; i < MAX_DICE; i++) {
            dice[i] = (numDice != null && i < numDice) ? numDice : dado.throwDice();
        }

        return dice;
    }

	private int calculateLosses(int[] attackDice, int[] defenseDice) {
        int losses = 0;

        for (int i = 0; i < MAX_DICE; i++) {
            if (attackDice[i] > defenseDice[i]) {
                losses++;
            }
        }

        return losses;
    }

	private void handleConqueredTerritory(Territory attacker, Territory defender) {
        defender.getOwner().loseTerritory(defender);

        if (defender.getOwner().getTerritoryNumber() == 0) {
            defender.getOwner().setEliminatedThisRound(true);
			defender.getOwner().setJMatou(attacker.getOwner());
			APIController.getInstance().addEliminated(defender.getOwner().getName());
        }

        defender.setOwner(attacker.getOwner());
        attacker.getOwner().addTerritorio(defender);
        attacker.getOwner().setConqueredThisRound(true);

        int armiesToMove = attacker.getArmies() - 1;
		if (armiesToMove > 3) {armiesToMove = 3;}
        attacker.changeNumArmies(-armiesToMove);
        defender.setArmies(armiesToMove);
    }

	public void reposicionarExercitos(Territory origem, Territory destino, Integer qtd){
		origem.changeNumArmies(-qtd);
		destino.changeNumArmies(qtd);
		alt1 = origem;
		alt2 = destino;
		this.notifyObservers();
	}

	public void restartGame(ObjectiveCardDeck objectiveCardDeck, TerritoryCardDeck territoryCardDeck){

		for (Player player: players){
			objectiveCardDeck.returnObjectiveCard(player.getObjective());
	

			for (TerritoryCard terrCard: player.getCard()){
				territoryCardDeck.returnCard(terrCard);
			}
			player.reset();
		}
		map.distributeTerritories(players);

		Collections.shuffle(players);
		for(Player player : players) {
			objectiveCardDeck.shuffleObjective(player);
		}
	}

	public Player getPlayer(String name) {
		for(Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

}
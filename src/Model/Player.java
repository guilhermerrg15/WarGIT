package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//
/**
 * Representa um jogador no jogo.
 */
public class Player  {

    private String name;
    private PlayerColor color;
    protected ArrayList <Territory> territories;
    private List<TerritoryCard> territoryCards; // cartas para troca
    private int armies;
    private boolean conqueredTerritory;
    private static int cont = 0;
    private int index;
    private ObjectiveCard objective;
    private List<Continent> continents;
    protected List<Army> continentalArmies;
    //Guarda a quantidade de territórios em sua posse
	private int numTerritories = 0;  
    //Guarda se o jogador foi eliminado nessa rodada
	private boolean eliminadoNessaRodada = false; 
	private Player jMatou;
    //Guarda se o jogador conquistou um território nessa rodada
	private boolean conquistouNessaRodada = false; 
    

    
    // Pegar número de cartas
    public int getCards () {
        return territoryCards.size();
    }

    // Pegar lista de territórios conquistados
    public ArrayList<Territory> getConqueredTerritories() {
        return territories;
    }

    public int getIndex(){
        return this.index;
    }

    public int setIndex(int index){
        return this.index = index;

    }

     /**
     * Construtor da classe Player.
    *
    * @param name  O nome do jogador.
    * @param color A cor do jogador.
    */
    public Player(String name, PlayerColor color, int index) {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.armies = 0;
        this.index = index;
        this.territoryCards = new ArrayList<TerritoryCard>();
        this.continentalArmies = new ArrayList<Army>();
        // for(Continent continent : continents){
        //     this.continentalArmies.add(new Army(continent));
        // }
    }

    // Resetar o jogador
    public void reset() {
		this.territories.clear();
		this.objective = null;
		this.eliminadoNessaRodada = false;
		this.conquistouNessaRodada = false;
        this.conqueredTerritory = false;
        this.numTerritories =0;
        this.armies = 0;
        this.eliminadoNessaRodada = false;
		this.conquistouNessaRodada = false;

        // for(TerritoryCard territoryCard : this.territoryCards) {
        //     territoryCardDeck.returnCard(territoryCard);
        // }
        this.territoryCards.clear();
        this.territories.clear();
    }

    public List<TerritoryCard> getAllCards() {
        List<TerritoryCard> cards = this.getCard();
        territoryCards.clear();
        return cards;
    }


    public void setArmies(int armies) {
        this.armies = armies;
    }

    public int getArmies() {
        return this.armies;
    }


    public int getTerritoryNumber() {
        return numTerritories;
    }
    
    public void loseTerritory(Territory territory) {
        territories.remove(territory);

        this.numTerritories--;
    }

    //Altera se o jogador foi eliminado nessa rodada para verificação de objetivos
    public void setEliminadoNessaRodada(boolean eliminadoNessaRodada) {
        this.eliminadoNessaRodada = eliminadoNessaRodada;
    }

    //Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public void setConquistouNessaRodada(boolean conquistouNessaRodada) {
		this.conquistouNessaRodada = conquistouNessaRodada;
	}

    //Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public boolean getConquistouNessaRodada() {
		return conquistouNessaRodada;
	}

    public Player getJMatou() {
		return jMatou;
	}

	public void setJMatou(Player jMatou) {
		this.jMatou = jMatou;
	}
    
    public void receiveObjective(ObjectiveCard objetivo) {
        if (objetivo != null) {
            objective = objetivo;
            objetivo.checkOwner(this);
        }
    }
    
    public ObjectiveCard getObjective() {
       return objective;
    }
    
    public String getObjectiveName() {
    	return objective.getName();
    }

    public List<TerritoryCard> getCard(){
    	return this.territoryCards;
    }

    public boolean addCard(TerritoryCard card) {
            this.conqueredTerritory = false;
            if(this.territoryCards.size() < 5) {
                this.territoryCards.add(card);
                return true;
            }
        return false;
    }


    //Verifica se o jogador pode trocar cartas
	public boolean temTroca(){
		int circulos = 0, quadrados = 0, triangulos = 0;

		// Conta quantas cartas de cada formato o jogador possui
		for (TerritoryCard cards: territoryCards){
			if (cards.getShape().equals(Shape.Circle))
				circulos++;
			else if (cards.getShape().equals(Shape.Square))
				quadrados++;
			else if (cards.getShape().equals(Shape.Triangle))
				triangulos++;
            //se for jocker adiciona 1 em todos pois o jocker entra em qualquer caso
			else{
				circulos++;
				quadrados++;
				triangulos++;
			}
		}

		// Se o jogador possui 3 cartas de um formato ou 1 de cada formato, pode trocar
		if (circulos >= 3 || quadrados >= 3 || triangulos >= 3 || (circulos >= 1 && quadrados >= 1 && triangulos >= 1)){
			return true;
        }
		
		return false;
	}

    //Retorna a quantidade de bonus de exércitos que o jogador recebe de bonus na troca
	public Integer trocarCartas (int numDeTrocas, TerritoryCardDeck territoryCardDeck, Map map) {
		
  
		ArrayList<TerritoryCard> circulos = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> quadrados = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> triangulos = new ArrayList<TerritoryCard>();
		ArrayList<TerritoryCard> coringas = new ArrayList<TerritoryCard>();

		// Separa as cartas por formato
		for (TerritoryCard carta: territoryCards){
			if (carta.getShape().equals(Shape.Circle))
				circulos.add(carta);
			else if (carta.getShape().equals(Shape.Square))
				quadrados.add(carta);
			else if (carta.getShape().equals(Shape.Triangle))
				triangulos.add(carta);
			else
				coringas.add(carta);
		}
		

		if (circulos.size() >= 3){
			// Troca três cartas de círculo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(circulos, territoryCardDeck, map);
			}
		}

		else if (quadrados.size() >= 3){
			// Troca três cartas de quadrado e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(quadrados, territoryCardDeck, map);
			}
		}

		else if (triangulos.size() >= 3){
			// Troca três cartas de triângulo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(triangulos, territoryCardDeck, map);
			}
		}

		else {
			int cont = coringas.size();
			switch (cont){
				case 0:
					// Troca uma de cada e devolve elas para o baralho
					usaCarta(circulos, territoryCardDeck, map);
					usaCarta(quadrados, territoryCardDeck, map);
					usaCarta(triangulos, territoryCardDeck, map);
					break;
				case 1:
					usaCarta(coringas, territoryCardDeck, map);
					if (circulos.size() == 0){
						// Remove um coringa, um quadrado e um triângulo
						usaCarta(quadrados, territoryCardDeck, map);
						usaCarta(triangulos, territoryCardDeck, map);
					}

					else if (quadrados.size() == 0){
						// Remove um coringa, um círculo e um triângulo
						usaCarta(circulos, territoryCardDeck, map);
						usaCarta(triangulos, territoryCardDeck, map);
					}

					else{
						// Remove um coringa, um círculo e um quadrado
						usaCarta(circulos, territoryCardDeck, map);
						usaCarta(quadrados, territoryCardDeck, map);
					}

					break;

				case 2:
					// Remove dois coringas e uma carta de qualquer formato
					usaCarta(coringas, territoryCardDeck, map);
					usaCarta(coringas, territoryCardDeck, map);
					if (circulos.size() == 0 && quadrados.size() == 0){
						usaCarta(triangulos, territoryCardDeck, map);
					}

					else if (quadrados.size() == 0 && triangulos.size() == 0){
						usaCarta(circulos, territoryCardDeck, map);
					}

					else if (circulos.size() == 0 && triangulos.size() == 0){
						usaCarta(quadrados, territoryCardDeck, map);
					}

					else if (circulos.size() == 1){
						usaCarta(circulos, territoryCardDeck, map);
					}

					else if (quadrados.size() == 1){
						usaCarta(quadrados, territoryCardDeck, map);
					}

					else{
						usaCarta(triangulos, territoryCardDeck, map);
					}
			}
		}


		Integer qtd;
		//Quando temos até 5 trocas já efetuadas
		if (numDeTrocas <= 5) {
			qtd = 4 + (2 * (numDeTrocas));
		}

		else if (numDeTrocas == 6) {
			qtd = 15;
		}

		//Temos mais de 6 trocas já efetuadas
		else {
			int diferenca = numDeTrocas - 6;
			qtd = 15 + (diferenca * 5);
		}

		return qtd;
	}

    // Remove a carta do topo do baralho e adiciona ao jogador
	private void usaCarta(ArrayList<TerritoryCard> lista, TerritoryCardDeck territoryCardDeck, Map map){

		TerritoryCard terrCard = lista.get(0);

		territoryCards.remove(terrCard);
		lista.remove(terrCard);

		territoryCardDeck.returnCard(terrCard);

        System.out.println("nome carta de territorio: " + terrCard.getName());
		System.out.println("lista restante carta de territorio: " + territoryCards);
		
		// Se o território da carta pertence ao jogador, aumenta em 2 a quantidade de exércitos
		if (terrCard.getName() != null){
            Territory territory = map.findTerritory(terrCard.getName());
			if (territory != null && territory.getOwner() == this) {
                territory.alterarQndExercitos(2);
            }
		}
	}

    //Adiciona um território ao jogador
	public void addTerritorio(Territory t) {
		territories.add(t);
		// Aumenta em 1 a quantidade de territórios
		this.numTerritories++;
	}

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }
}

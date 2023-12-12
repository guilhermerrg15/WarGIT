package Model;

import java.util.ArrayList;
import java.util.Arrays;

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


    // Últimos territórios alterados
	private Territory mod1 = null;
	private Territory mod2 = null;

    // Inicializa o jogo
    public boolean initiateGame(){
        map.distribuiTerritorios(players);

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

        // Array de quantidade de exércitos
        ArrayList<String> qtdExercitos = new ArrayList<String>();

        // Array de cores
        ArrayList<PlayerColor> cores = new ArrayList<PlayerColor>();

        // Preenche arrays com informações atuais do model
        for (Territory t: map.getTerritoriesList()){
            qtdExercitos.add(((Integer)t.getArmies()).toString());
            cores.add(t.getCor());
        }
        infos[0] = qtdExercitos;
        infos[1] = cores;

        // Preenche no array qual o índice dos territórios que foram modificados
        if (mod1 == null){
            infos[2] = -1;
        }
        else{
            infos[2] = map.getTerritoriesList().indexOf(mod1);
        }
        if (mod2 == null){
            infos[3] = -1;
        }
        else{
            infos[3] = map.getTerritoriesList().indexOf(mod2);
        }
        return infos;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Pegar o jogador da vez
    public Player getJogadorVez(int turn){
        
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getIndex() == turn) {
                return players.get(i);
            }
        }
		return players.get(turn);
        
	}

    //Altera o mod1 e o mod2
	public void setMod1(Territory t){
		mod1 = t;
	}

	public void setMod2(Territory t){
		mod2 = t;
	}

    public void notifyObservers() {
        for (Observer o : lst) {
            o.notify(this);
        }
    }

	//Verifica se o jogador pode trocar cartas
	public boolean temTroca(Player player){
		int circulos = 0, quadrados = 0, triangulos = 0;
		territoryCards = player.getCard();

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
	public Integer trocarCartas (int numDeTrocas, TerritoryCardDeck territoryCardDeck, Map map, Player player) {
		
	
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
				usaCarta(circulos, territoryCardDeck, map, player);
			}
		}

		else if (quadrados.size() >= 3){
			// Troca três cartas de quadrado e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(quadrados, territoryCardDeck, map, player);
			}
		}

		else if (triangulos.size() >= 3){
			// Troca três cartas de triângulo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(triangulos, territoryCardDeck, map, player);
			}
		}

		else {
			if(coringas.size() == 0 );{
				// Troca uma de cada e devolve elas para o baralho
				usaCarta(circulos, territoryCardDeck, map, player);
				usaCarta(quadrados, territoryCardDeck, map, player);
				usaCarta(triangulos, territoryCardDeck, map, player);
			} 
			if (coringas.size() == 1 ){
				usaCarta(coringas, territoryCardDeck, map, player);
				if (circulos.size() == 0){
					// Remove um coringa, um quadrado e um triângulo
					usaCarta(quadrados, territoryCardDeck, map, player);
					usaCarta(triangulos, territoryCardDeck, map, player);
				}

				else if (quadrados.size() == 0){
					// Remove um coringa, um círculo e um triângulo
					usaCarta(circulos, territoryCardDeck, map, player);
					usaCarta(triangulos, territoryCardDeck, map, player);
				}

				else{
					// Remove um coringa, um círculo e um quadrado
					usaCarta(circulos, territoryCardDeck, map, player);
					usaCarta(quadrados, territoryCardDeck, map, player);
				}

			} 
			if (coringas.size() == 2){
				// Remove dois coringas e uma carta de qualquer formato
				usaCarta(coringas, territoryCardDeck, map, player);
				usaCarta(coringas, territoryCardDeck, map, player);
				if (circulos.size() == 0 && quadrados.size() == 0){
					usaCarta(triangulos, territoryCardDeck, map, player);
				}

				else if (quadrados.size() == 0 && triangulos.size() == 0){
					usaCarta(circulos, territoryCardDeck, map, player);
				}

				else if (circulos.size() == 0 && triangulos.size() == 0){
					usaCarta(quadrados, territoryCardDeck, map, player);
				}

				else if (circulos.size() == 1){
					usaCarta(circulos, territoryCardDeck, map, player);
				}

				else if (quadrados.size() == 1){
					usaCarta(quadrados, territoryCardDeck, map, player);
				}

				else{
					usaCarta(triangulos, territoryCardDeck, map, player);
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
	private void usaCarta(ArrayList<TerritoryCard> lista, TerritoryCardDeck territoryCardDeck, Map map, Player player){

		TerritoryCard terrCard = lista.get(0);

		territoryCards.remove(terrCard);
		lista.remove(terrCard);

		territoryCardDeck.returnCard(terrCard);
		
		// Se o território da carta pertence ao jogador, aumenta em 2 a quantidade de exércitos
		if (terrCard.getName() != null){
			Territory territory = map.findTerritory(terrCard.getName());
			if (territory != null && territory.getOwner() == player) {
				territory.alterarQndExercitos(2);
				//Atualiza os territórios modificados
				mod1 = territory;
				mod2 = null;
				this.notifyObservers();
			}
		}
	}
	public void continentDomain(Integer turn){
        List<Territory> territorios;
        Integer qtd;

        for (numContinent = 0; numContinent < 6; numContinent++){
            if (dominaCont(turn, getContinentesLista()[numContinent])){
                territorios = getTerritoriosCont(getContinentesLista()[numContinent]);
                qtd = getExCont(turn, getContinentesLista()[numContinent]);

				for (Territory territory: territorios){
					territory.alterarQndExercitos(qtd);
					//Atualiza os territórios modificados
					mod1 = territory;
					mod2 = null;
					this.notifyObservers();
				}
                numContinent++;
                return;
            }
        }
    }


    // Retorna lista de nomes dos continentes
    public String[] getContinentesLista(){
        String[] listaContinentes = new String[map.getContinents().size()];
        int cont = 0;

        // Adiciona na lista os nomes dos continentes
        for (Continent c: map.getContinents()) {
            listaContinentes[cont] = c.getName();
            cont++;
        }

        return listaContinentes;
    }

    // Retorna se o jogador domina o continente
    public boolean dominaCont(int turn, String continent){
        return map.findContinent(continent).checkContinentDomain(game.getJogadorVez(turn));
    }

    // Retorna lista de nomes dos territórios de um continente
    public List<Territory> getTerritoriosCont(String c){
        List<Territory> listaTerritorios = map.findContinent(c).getTerritories();
        return listaTerritorios;
    }

     // Retorna quantidade de exércitos a posicionar no continente 
     public Integer getExCont(int vez, String c){
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
            System.out.println(player.getName());
        }
        return true;
    }
 
    //Valida um ataque
	public boolean VerificarAtaque(Territory tAtacante, Territory tDefensor) {
		// Verifica se o atacante tem mais de um exército e se o defensor não é dele
		if(tAtacante.getArmies() > 1 && tAtacante.getOwner() != tDefensor.getOwner())
			return true;	
		return false;
	}

    //Realiza um ataque 
	public int[] RealizaAtaque(Territory atacante, Territory defensor, Integer numAtaque, Integer numDefesa) {
		if(VerificarAtaque(atacante, defensor)){
			//Verifica se o atacante tem mais de 3 exércitos
			int qtdAtaque = atacante.getArmies() - 1;
			if  (qtdAtaque > 3) {qtdAtaque = 3;}

			//Verifica se o defensor tem mais de 3 exércitos
			//ta dando 17 exercitos sendo que tem 9
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
			//Verifica se o jogador escolheu um número forçado
			//acho que não precisa desse if -> tirei esse forçado
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
						dadosAtaque[i] = dado.jogaDado();
					else
						dadosAtaque[i] = 0;
				}
				//Ordena os dados se for aleatório
				Arrays.sort(dadosAtaque);
			}

			//debugar essa parte de novo
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
						dadosDefesa[i] = dado.jogaDado();
					else
						dadosDefesa[i] = 0;
				}
				//Ordena os dados se for aleatório
				Arrays.sort(dadosDefesa);
			}
			
			//Compara os dados
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
			
			//Atualiza os exércitos
			atacante.setArmies(atacante.getArmies() - qtdAtaquePerdidos);
			defensor.setArmies(defensor.getArmies() - qtdDefesaPerdidos);

			//Atualiza os territórios modificados
			mod1 = atacante;
			mod2 = defensor;

			// Se conquistou
			if (defensor.getArmies()==0) {
				// Atualiza defensor
				defensor.getOwner().loseTerritory(defensor);

				if (defensor.getOwner().getTerritoryNumber() == 0){
					// Jogador foi eliminado nessa rodada
					defensor.getOwner().setEliminadoNessaRodada(true);
					defensor.getOwner().setJMatou(atacante.getOwner());
					APIController.getInstance().addEliminado(defensor.getOwner().getName());
				}
				
				defensor.setOwner(atacante.getOwner());

				// Adiciona território conquistado ao jogador que conquistou
				atacante.getOwner().addTerritorio(defensor);

				// Conquistou nessa rodada ao jogador que conquistou
				atacante.getOwner().setConquistouNessaRodada(true);

				// Calcula quantos exércitos o jogador pode colocar no território conquistado (sempre máximo possível)
				int qtdPassada = atacante.getArmies() - 1;
				if (qtdPassada > 3) {qtdPassada = 3;}

				// Altera a quantidade de exércitos dos territórios
				atacante.alterarQndExercitos(-qtdPassada);
				defensor.setArmies(qtdPassada);

			}

			//Notifica os observadores
			this.notifyObservers();

			
			// Retorna os dados em um array único 

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
		origem.alterarQndExercitos(-qtd);
		destino.alterarQndExercitos(qtd);
		mod1 = origem;
		mod2 = destino;
		this.notifyObservers();
	}

    // Método para reiniciar o jogo
	public void reiniciarJogo(){

		for (Player player: players){
			// Devolve objetivo do jogador para lista
			// objectiveCardDeck.add(j.getObj());

			// Devolve cartas do jogador para lista
			// for (Carta c: j.getCartas()){
			// 	listaCartas.add(c);
			// }
			player.reset();
		}
		

		// Redistribui territórios
		map.distribuiTerritorios(players);

		// Redistribui objetivos
		// Collections.shuffle(objetivos);
		// for (int i = 0;i < jogadores.size();i++){
		// 	Objetivo obj = objetivos.get(0);
		// 	jogadores.get(i).setObj(obj);
		// 	objetivos.remove(obj);
		// }
    }

}
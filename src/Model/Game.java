package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Controller.APIController;
import View.Observed;
import View.Observer;

class Game implements Observed{
    private static Game game = null;
    
    // Lista de jogadores
    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Observer> lst = new ArrayList<Observer>();

    // Cartas de objetivo
    private ObjectiveCardDeck objectiveCardDeck;

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

	public Player getPlayer(String name) {
		for (Player player : players) {
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}

    //Altera o mod1 e o mod2
	public void setMod1(Territory t){
		mod1 = t;
	}

	public void setMod2(Territory t){
		mod2 = t;
	}

    //implementar 
    public Object get() {
        return this;
    }

    public void notifyObservers() {
        for (Observer o : lst) {
            o.notify(this);
        }
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

	//olhar de novo essa classe, até a 191 ta certa acho que não está passando certo para a view
    //Realiza um ataque 
	public int[] RealizaAtaque(Territory atacante, Territory defensor, Integer numAtaque, Integer numDefesa) {
		System.out.println("entrei no ataque");
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
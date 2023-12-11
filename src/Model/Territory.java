package Model;

import java.util.ArrayList;
import java.util.List;

import View.Observer;
/**
 * Representa um território no jogo War.
 */
class Territory {

    private String name;
    private Player owner;
    private int armies;
    private String continent;
    // private List<Territory> neighbours;
    private List<String> neighbours;
    private List<Observer> lst = new ArrayList<Observer>();
    private int inicialArmie;

    //Guarda a cor do território 
	private PlayerColor cor;

    private int i1, i2 = -1;
    /**
     * Cria um novo território com o nome fornecido.
     * Inicializa o proprietário como nulo e o número de exércitos como 0.
     * Inicializa a lista de territórios vizinhos como uma lista vazia.
     *
     * @param name O nome do território.
     */

    public Territory(String name, String continent, List<String> neighbours) {
        this.name = name;
        this.neighbours = neighbours;
        this.continent = continent;
   
    }

    

    /**
     * Obtém o nome do território.
     * @return O nome do território.
     */
    public String getName() {
        return name;
    }

    public void add(Observer o) {
		lst.add(o);
	}

	public void remove(Observer o) {
		lst.remove(o);
	}
    
    public int get(int i) {
		if (i == 1)
			return i1;
		else if (i == 2)
			return i2;
		return -1;
	}


    public boolean isNeighbor(String territory) {
		return getNeighbours().contains(territory);
	}

    public String getContinent() {
        return this.continent;
    }

    /**
     * Obtém o jogador que possui o território.
     *
     * @return O jogador que possui o território.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Define o jogador que possui o território.
     *
     * @param owner O novo proprietário do território.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        this.cor = owner.getColor();
    }

    /**
     * Obtém o número de exércitos no território.
     *
     * @return O número de exércitos no território.
     */
    public int getArmies() {
        return armies;
    }

    //Altera a quantidade de exércitos
    public void setArmies(int armies) {
        this.armies = armies;
    }

    public int getInicialArmie(){
        return inicialArmie;
    }

    //Altera a quantidade de exércitos
    public void setInicialArmies(int armies) {
        this.inicialArmie = armies;
    }

    public void addArmiesInicial(int count) {
        this.inicialArmie += count;
    }

    

    /**
     * Adiciona exércitos ao território.
     *
     * @param count O número de exércitos a serem adicionados.
     */
    public void addArmies(int count) {
        this.armies += count;
    }


    // Int positivo add exercitos e negativo subtrai 
    protected boolean alterarQndExercitos (int qnd) {
        // se tentar subtrair mais exércitos do que tem (sem poder zerar)
        if (qnd < 0)
            if ((qnd * (-1)) >= this.armies)
                return false;
        this.armies += qnd;
        return true;
    }



    /**
     * Remove exércitos do território.
     *
     * @param count O número de exércitos a serem removidos.
     */
    public void removeArmies(int count) {
        armies -= count;
        if (armies < 0) {
            armies = 0;
        }
    } 

    
    /**
     * Obtém a lista de territórios vizinhos.
     *
     * @return A lista de territórios vizinhos.
     */
    // public List<Territory> getNeighbours() {
    //     return neighbours;
    // }
    public List<String> getNeighbours() {
        return neighbours;
    }

    //Retorna a cor do territorio
    public PlayerColor getCor() {
        return cor;
    }

}



package Model;

import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * Cria um novo território com o nome fornecido.
     * Inicializa o proprietário como nulo e o número de exércitos como 0.
     * Inicializa a lista de territórios vizinhos como uma lista vazia.
     *
     * @param name O nome do território.
     */
    // public Territory(String name) {
    //     this.name = name;
    //     this.owner = null;
    //     this.armies = 0;
    //     this.neighbours = new ArrayList<>();
    // //    this.center = center;
    // //    this.vertices = vertices;
    // }
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

    public String getContinent() {
        return continent;
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
    }

    /**
     * Obtém o número de exércitos no território.
     *
     * @return O número de exércitos no território.
     */
    public int getArmies() {
        return armies;
    }

    /**
     * Adiciona exércitos ao território.
     *
     * @param count O número de exércitos a serem adicionados.
     */
    public void addArmies(int count) {
        armies += count;
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

    /**
     * Adiciona um território à lista de territórios vizinhos.
     *
     * @param neighbour O território vizinho a ser adicionado.
     */
    // public void addNeighbour(Territory neighbour) {
    //     neighbours.add(neighbour);
    // }

//   public Coordinates getCenter() {
//       return center;
//   }

//   public Coordinates[] getVertices() {
//       return vertices;
//   }
}



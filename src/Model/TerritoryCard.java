package Model;

/**
 * Representa uma carta de território no jogo War.
 * Cada carta possui um nome, um território associado e uma forma.
 */
class TerritoryCard {
	private String name;
    private Shape shape;

    /**
     * Cria uma nova carta de território com o nome e a forma fornecidos.
     * @param name O nome da carta de território.
     * @param shape A forma da carta de território.
     */
    public TerritoryCard(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }

    /**
     * Retorna o nome da carta de território.
     * @return O nome da carta de território.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna a forma da carta de território.
     * @return A forma da carta de território.
     */
    public Shape getShape() {
        return shape;
    }
}
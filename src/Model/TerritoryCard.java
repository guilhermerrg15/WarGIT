package Model;

/**
 * Representa uma carta de território no jogo War.
 * Cada carta possui um nome, um território associado e uma forma.
 */
public class TerritoryCard {
	private String name;
    private Shape shape;
    private String continent;
    private String imageName;

    /**
     * Cria uma nova carta de território com o nome e a forma fornecidos.
     * @param name O nome da carta de território.
     * @param shape A forma da carta de território.
     */
    public TerritoryCard(String name, Shape shape, String continent, String imageName) {
        this.name = name;
        this.shape = shape;
        this.continent = continent;
        this.imageName = imageName;
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

    public String getContinent() {
        return continent;
    }

    public String getImageName() {
        return imageName;
    }
    
    /**
     * Retorna um objeto Territory correspondente ao nome da carta de território.
     * @param map O mapa do jogo.
     * @return O objeto Territory correspondente ao nome, ou null se não for encontrado.
     */
    // public Territory toTerritory(Map map) {
    //     return map.findTerritory(name);
    // }
}
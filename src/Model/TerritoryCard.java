package Model;

/**
 * Representa uma carta de território no jogo War.
 * Cada carta possui um nome, um território associado e uma forma.
 */
class TerritoryCard {
	String name;
    private int id;
    Territory territory;
    private String shape;

    /**
     * 
     * Cria uma nova carta de território com o nome e a forma fornecidos.
     * @param name O nome da carta de território.
     * @param shape A forma da carta de território.
     * @param id de cada carta 
     */
    public TerritoryCard(String name, String shape, int id) {
        this.name = name;
        this.shape = shape;
        this.id = id;
    }

    /**
     * Define o território associado à carta.
     * @param territory O território a ser associado à carta.
     */
    void setTerritory(Territory territory) {
        this.territory = territory;
    }
    /**
     * Retorna o território associado à carta com o nome especificado.
     * @param cards A lista de cartas de território.
     * @param carta O nome da carta cujo território deve ser retornado.
     * @return O território associado à carta com o nome especificado, ou null se não encontrado.
     */
    static Territory retornaTerritory(TerritoryCard[] cards, String carta) {
        for (TerritoryCard cart : cards) {
            if (cart.name.equals(carta)) {
                return cart.territory;
            }
        }
        return null;
    }

    /**
     * Retorna o ID da carta de território.
     * @return O ID da carta de território.
     */
    public int getId() {
        return id;
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
    public String getShape() {
        return shape;
    }

    /**
     * Retorna o território associado à carta de território.
     * @return O território associado à carta de território.
     */
    public Territory getTerritory() {
        return territory;
    }

    /**
     * Converte a carta de território para uma representação de string.
     * @return Uma representação de string da carta de território.
     */
    @Override
    public String toString() {
        return name;
    }
}



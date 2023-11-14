package Model;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

class Continent {
	private String name;
    // private Territory[] territories;
    private List<Territory> territories;
    private int bonusArmies;
    private Color color;

    /**
     * Obtém o nome do continente.
     *
     * @return O nome do continente.
     */
    public String getName() {
        return name;
    }
    

    /**
     * Obtém os territórios que compõem o continente.
     *
     * @return Os territórios do continente.
     */
    // public Territory[] getTerritories() {
    //     return territories;
    // }
    public List<Territory> getTerritories() {
        return territories;
    }

    /**
     * Obtém o número de exércitos de bônus concedidos ao dono do continente.
     *
     * @return O número de exércitos de bônus.
     */
    public int getBonusArmies() {
        return bonusArmies;
    }

    /**
     * Obtém a cor associada ao continente.
     *
     * @return A cor do continente.
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Adiciona um território ao continente.
     *
     * @param territory O território a ser adicionado.
     */
    // public void addTerritory(Territory territory) {
    //     if (territories != null) {
    //         Territory[] newTerritories = Arrays.copyOf(territories, territories.length + 1);
    //         newTerritories[territories.length] = territory;
    //         territories = newTerritories;
    //     }
    // }
    public void addTerritory(Territory territory) {
        if (territories != null) {
            territories.add(territory);
        }
    }
    
    /**
     * Obtém um território pelo nome no continente.
     *
     * @param name O nome do território a ser obtido.
     * @return O objeto Territory correspondente ao território encontrado, ou null se não for encontrado.
     */
    public Territory getTerritory(String name) {
        for (Territory territory : territories) {
            if (territory.getName().equals(name)) {
                return territory;
            }
        }
        return null; // Retorna null se o território não for encontrado no continente
    }


    /**
     * Obtém o número de territórios no continente.
     *
     * @return O número de territórios.
     */
    // public int getNumberTerritories() {
    //     return territories.length;
    // }
    public int getNumberTerritories() {
        return territories.size();
    }
    
    /**
     * Construtor da classe Continent.
     *
     * @param name         O nome do continente.
     * @param territories  Os territórios que compõem o continente.
     * @param bonusArmies  O número de exércitos de bônus concedidos ao dono do continente.
     * @param color        A cor associada ao continente.
     */
    public Continent(String name, int bonusArmies, List<Territory> territories) {
        this.name = name;
        this.bonusArmies = bonusArmies;
        this.territories = territories;
    }
    
    // public Continent(String name, Territory[] territories, int bonusArmies, Color color) {
    //     if (name == null || territories == null || territories.length == 0 || bonusArmies < 0) {
    //         throw new IllegalArgumentException("Parâmetros inválidos para criar um continente.");
    //     }

    //     this.name = name;
    //     this.territories = territories;
    //     this.bonusArmies = bonusArmies;
    //     this.color = color;
    // }
}


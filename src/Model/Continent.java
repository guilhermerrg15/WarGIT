package Model;

import java.awt.Color;
import java.util.Hashtable;
import java.util.List;

class Continent {
	private String name;
    private List<Territory> territories;
    private int bonusArmies;
    private Color color;
    private static Hashtable<String, Continent> continents = new Hashtable<String, Continent>();

    /**
     * Obtém o nome do continente.
     *
     * @return O nome do continente.
     */
    public String getName() {
        return name;
    }
    
    public static Continent[] getContinent() {
    	return continents.values().toArray(new Continent[continents.size()]);
    }

    /**
     * Obtém os territórios que compõem o continente.
     *
     * @return Os territórios do continente.
     */
    public List<Territory> getTerritories() {
        return territories;
    }

    /**
     * Obtém o número de exércitos de bônus concedidos ao dono do continente.
     *
     * @return O número de exércitos de bônus.
     */
    public int getBonusArmies() {
        return this.bonusArmies;
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
        return null; 
    }


    /**
     * Obtém o número de territórios no continente.
     *
     * @return O número de territórios.
     */
    public int getNumberTerritories() {
        return territories.size();
    }
    
    public boolean checkContinentDomain(Player player) {
		for(Territory territory: territories) {
			if(player != territory.getOwner()) { //verifica se o jogador dos paises é diferente do jogador que queremos conferir
				return false;
			}
		}
		return true;
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
        continents.put(name, this);
        
    }
    

}


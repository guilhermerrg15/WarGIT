package Model;

import java.awt.Color;

/**
 * A classe Map representa o mapa do jogo, que consiste em continentes, territórios e suas interconexões.
 * É responsável por gerar o mapa, inicializar os continentes, territórios e definir os vizinhos.
 */
class Map {
	
	private Continent[] continents;
	
	/**
     * Obtém a lista de continentes no mapa.
     *
     * @return Um array de objetos Continent representando os continentes no mapa.
     */
	public Continent[] getContinents() {
		return continents;
	}
	
	/**
     * Construtor privado para impedir a criação de instâncias diretas da classe Map.
     */
	private Map() {
	}
	
	/**
     * Método estático para gerar um mapa do jogo.
     *
     * @return Uma instância da classe Map representando o mapa do jogo.
     */
	public static Map mapGenerator() {
        Map map = new Map();
        map.initContinents();
        map.initTerritories();
        map.setNeighbours();
        return map;
    }
	
	private void initContinents() {
	    continents = new Continent[6];
	    
	    continents[0] = new Continent("Africa", new Territory[0], 3, new Color(101, 45, 144));
	    continents[1] = new Continent("America do Norte", new Territory[0], 5, new Color(238, 64, 54));
	    continents[2] = new Continent("Asia", new Territory[0], 7, new Color(246, 146, 30));
	    continents[3] = new Continent("America do Sul", new Territory[0], 2, new Color(0, 104, 58));
	    continents[4] = new Continent("Europa", new Territory[0], 5, new Color(43, 56, 143));
	    continents[5] = new Continent("Oceania", new Territory[0], 2, new Color(38, 169, 224));
	}
	
	private void initTerritories() {
		continents[0].addTerritory(new Territory("Africa do Sul"));
		continents[0].addTerritory(new Territory("Angola"));
		continents[0].addTerritory(new Territory("Argelia"));
		continents[0].addTerritory(new Territory("Egito"));
		continents[0].addTerritory(new Territory("Nigeria"));
		continents[0].addTerritory(new Territory("Somalia"));
		
		continents[1].addTerritory(new Territory("Alasca"));
		continents[1].addTerritory(new Territory("Calgary"));
		continents[1].addTerritory(new Territory("California"));
		continents[1].addTerritory(new Territory("Groenlandia"));
		continents[1].addTerritory(new Territory("Mexico"));
		continents[1].addTerritory(new Territory("Nova Iorque"));
		continents[1].addTerritory(new Territory("Quebec"));
		continents[1].addTerritory(new Territory("Texas"));
		continents[1].addTerritory(new Territory("Vancouver"));
		
		
		continents[2].addTerritory(new Territory("Arabia Saudita"));
		continents[2].addTerritory(new Territory("Bangladesh"));
		continents[2].addTerritory(new Territory("Cazaquistao"));
		continents[2].addTerritory(new Territory("China"));
		continents[2].addTerritory(new Territory("Coreia do Norte"));
		continents[2].addTerritory(new Territory("Coreia do Sul"));
		continents[2].addTerritory(new Territory("Estonia"));
		continents[2].addTerritory(new Territory("India"));
		continents[2].addTerritory(new Territory("Ira"));
		continents[2].addTerritory(new Territory("Iraque"));
		continents[2].addTerritory(new Territory("Japao"));
		continents[2].addTerritory(new Territory("Jordania"));
		continents[2].addTerritory(new Territory("Letonia"));
		continents[2].addTerritory(new Territory("Mongolia"));
		continents[2].addTerritory(new Territory("Paquistao"));
		continents[2].addTerritory(new Territory("Russia"));
		continents[2].addTerritory(new Territory("Siberia"));
		continents[2].addTerritory(new Territory("Siria"));
		continents[2].addTerritory(new Territory("Tailandia"));
		continents[2].addTerritory(new Territory("Turquia"));
		
		continents[3].addTerritory(new Territory("Argentina"));
		continents[3].addTerritory(new Territory("Brasil"));
		continents[3].addTerritory(new Territory("Peru"));
		continents[3].addTerritory(new Territory("Venezuela"));
		
		continents[4].addTerritory(new Territory("Espanha"));
		continents[4].addTerritory(new Territory("Franca"));
		continents[4].addTerritory(new Territory("Italia"));
		continents[4].addTerritory(new Territory("Polonia"));
		continents[4].addTerritory(new Territory("Reino Unido"));
		continents[4].addTerritory(new Territory("Romenia"));
		continents[4].addTerritory(new Territory("Suecia"));
		continents[4].addTerritory(new Territory("Ucrania"));
		
		continents[5].addTerritory(new Territory("Australia"));
		continents[5].addTerritory(new Territory("Indonesia"));
		continents[5].addTerritory(new Territory("Nova Zelandia"));
		continents[5].addTerritory(new Territory("Perth"));
	}
	
	
	private void setNeighbours() {
		//Africa
		continents[0].getTerritory("Africa do Sul").addNeighbour(continents[0].getTerritory("Angola"));
	    continents[0].getTerritory("Africa do Sul").addNeighbour(continents[0].getTerritory("Somalia"));
		
		continents[0].getTerritory("Angola").addNeighbour(continents[0].getTerritory("Nigeria"));
	    continents[0].getTerritory("Angola").addNeighbour(continents[0].getTerritory("Somalia"));
	    
	    continents[0].getTerritory("Argelia").addNeighbour(continents[4].getTerritory("Espanha"));
	    continents[0].getTerritory("Argelia").addNeighbour(continents[4].getTerritory("Italia"));
	    continents[0].getTerritory("Argelia").addNeighbour(continents[0].getTerritory("Nigeria"));
	    continents[0].getTerritory("Argelia").addNeighbour(continents[0].getTerritory("Egito"));
	    

	    continents[0].getTerritory("Egito").addNeighbour(continents[0].getTerritory("Nigeria"));
	    continents[0].getTerritory("Egito").addNeighbour(continents[0].getTerritory("Somalia"));
	    continents[0].getTerritory("Egito").addNeighbour(continents[4].getTerritory("Romenia"));
	    continents[0].getTerritory("Egito").addNeighbour(continents[2].getTerritory("Jordania"));

	    continents[0].getTerritory("Nigeria").addNeighbour(continents[0].getTerritory("Somalia"));
	    continents[0].getTerritory("Nigeria").addNeighbour(continents[3].getTerritory("Brasil"));

	    continents[0].getTerritory("Somalia").addNeighbour(continents[2].getTerritory("Arabia Saudita"));
	    
	 // América do Norte
	    continents[1].getTerritory("Alasca").addNeighbour(continents[1].getTerritory("Calgary"));
	    continents[1].getTerritory("Alasca").addNeighbour(continents[1].getTerritory("Vancouver"));
	    continents[1].getTerritory("Alasca").addNeighbour(continents[2].getTerritory("Siberia"));

	    continents[1].getTerritory("Calgary").addNeighbour(continents[1].getTerritory("Vancouver"));
	    continents[1].getTerritory("Calgary").addNeighbour(continents[1].getTerritory("Groenlandia"));

	    continents[1].getTerritory("California").addNeighbour(continents[1].getTerritory("Vancouver"));
	    continents[1].getTerritory("California").addNeighbour(continents[1].getTerritory("Texas"));
	    continents[1].getTerritory("California").addNeighbour(continents[1].getTerritory("Mexico"));

	    continents[1].getTerritory("Groenlandia").addNeighbour(continents[1].getTerritory("Quebec"));
	    continents[1].getTerritory("Groenlandia").addNeighbour(continents[4].getTerritory("Reino Unido"));

	    continents[1].getTerritory("Mexico").addNeighbour(continents[1].getTerritory("Texas"));
	    continents[1].getTerritory("Mexico").addNeighbour(continents[3].getTerritory("Venezuela"));

	    continents[1].getTerritory("Nova Iorque").addNeighbour(continents[1].getTerritory("Texas"));
	    continents[1].getTerritory("Nova Iorque").addNeighbour(continents[1].getTerritory("Quebec"));

	    continents[1].getTerritory("Quebec").addNeighbour(continents[1].getTerritory("Texas"));
	    continents[1].getTerritory("Quebec").addNeighbour(continents[1].getTerritory("Vancouver"));

	    continents[1].getTerritory("Texas").addNeighbour(continents[1].getTerritory("Vancouver"));

	    
	 // Ásia
	    continents[2].getTerritory("Arabia Saudita").addNeighbour(continents[2].getTerritory("Iraque"));
	    continents[2].getTerritory("Arabia Saudita").addNeighbour(continents[2].getTerritory("Jordania"));

	    continents[2].getTerritory("Bangladesh").addNeighbour(continents[2].getTerritory("Tailandia"));
	    continents[2].getTerritory("Bangladesh").addNeighbour(continents[2].getTerritory("India"));
	    continents[2].getTerritory("Bangladesh").addNeighbour(continents[2].getTerritory("Coreia do Sul"));
	    continents[2].getTerritory("Bangladesh").addNeighbour(continents[5].getTerritory("Indonesia"));

	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Japao"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Siberia"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Russia"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Mongolia"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("China"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Turquia"));
	    continents[2].getTerritory("Cazaquistao").addNeighbour(continents[2].getTerritory("Letonia"));

	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("Mongolia"));
	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("Coreia do Norte"));
	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("Coreia do Sul"));
	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("India"));
	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("Paquistao"));
	    continents[2].getTerritory("China").addNeighbour(continents[2].getTerritory("Turquia"));

	    continents[2].getTerritory("Coreia do Norte").addNeighbour(continents[2].getTerritory("Japao"));
	    continents[2].getTerritory("Coreia do Norte").addNeighbour(continents[2].getTerritory("Coreia do Sul"));

	    continents[2].getTerritory("Coreia do Sul").addNeighbour(continents[2].getTerritory("Tailandia"));
	    continents[2].getTerritory("Coreia do Sul").addNeighbour(continents[2].getTerritory("India"));

	    continents[2].getTerritory("Estonia").addNeighbour(continents[2].getTerritory("Russia"));
	    continents[2].getTerritory("Estonia").addNeighbour(continents[2].getTerritory("Letonia"));
	    continents[2].getTerritory("Estonia").addNeighbour(continents[4].getTerritory("Suecia"));

	    continents[2].getTerritory("India").addNeighbour(continents[2].getTerritory("Paquistao"));
	    continents[2].getTerritory("India").addNeighbour(continents[5].getTerritory("Indonesia"));
	    
	    continents[2].getTerritory("Ira").addNeighbour(continents[2].getTerritory("Paquistao"));
	    continents[2].getTerritory("Ira").addNeighbour(continents[2].getTerritory("Siria"));
	    continents[2].getTerritory("Ira").addNeighbour(continents[2].getTerritory("Iraque"));

	    continents[2].getTerritory("Iraque").addNeighbour(continents[2].getTerritory("Siria"));
	    continents[2].getTerritory("Iraque").addNeighbour(continents[2].getTerritory("Jordania"));

	    continents[2].getTerritory("Japao").addNeighbour(continents[2].getTerritory("Mongolia"));

	    continents[2].getTerritory("Jordania").addNeighbour(continents[2].getTerritory("Siria"));

	    continents[2].getTerritory("Letonia").addNeighbour(continents[2].getTerritory("Russia"));
	    continents[2].getTerritory("Letonia").addNeighbour(continents[2].getTerritory("Turquia"));
	    continents[2].getTerritory("Letonia").addNeighbour(continents[4].getTerritory("Ucrania"));
	    continents[2].getTerritory("Letonia").addNeighbour(continents[4].getTerritory("Polonia"));
	    continents[2].getTerritory("Letonia").addNeighbour(continents[4].getTerritory("Suecia"));

	    continents[2].getTerritory("Paquistao").addNeighbour(continents[2].getTerritory("Turquia"));
	    continents[2].getTerritory("Paquistao").addNeighbour(continents[2].getTerritory("Siria"));

	    continents[2].getTerritory("Russia").addNeighbour(continents[2].getTerritory("Siberia"));

	    continents[2].getTerritory("Siria").addNeighbour(continents[2].getTerritory("Turquia"));

	    continents[2].getTerritory("Turquia").addNeighbour(continents[4].getTerritory("Ucrania"));
	    
	    // América do Sul
	    continents[3].getTerritory("Argentina").addNeighbour(continents[3].getTerritory("Peru"));
	    continents[3].getTerritory("Argentina").addNeighbour(continents[3].getTerritory("Brasil"));

	    continents[3].getTerritory("Brasil").addNeighbour(continents[3].getTerritory("Peru"));
	    continents[3].getTerritory("Brasil").addNeighbour(continents[3].getTerritory("Venezuela"));

	    continents[3].getTerritory("Peru").addNeighbour(continents[3].getTerritory("Venezuela"));

	    // Europa
	    continents[4].getTerritory("Espanha").addNeighbour(continents[4].getTerritory("Franca"));

	    continents[4].getTerritory("Franca").addNeighbour(continents[4].getTerritory("Italia"));
	    continents[4].getTerritory("Franca").addNeighbour(continents[4].getTerritory("Suecia"));
	    continents[4].getTerritory("Franca").addNeighbour(continents[4].getTerritory("Reino Unido"));

	    continents[4].getTerritory("Italia").addNeighbour(continents[4].getTerritory("Romenia"));
	    continents[4].getTerritory("Italia").addNeighbour(continents[4].getTerritory("Polonia"));
	    continents[4].getTerritory("Italia").addNeighbour(continents[4].getTerritory("Suecia"));

	    continents[4].getTerritory("Polonia").addNeighbour(continents[4].getTerritory("Ucrania"));
	    continents[4].getTerritory("Polonia").addNeighbour(continents[4].getTerritory("Romenia"));

	    continents[4].getTerritory("Romenia").addNeighbour(continents[4].getTerritory("Ucrania"));

	    // Oceania
	    continents[5].getTerritory("Australia").addNeighbour(continents[5].getTerritory("Perth"));
	    continents[5].getTerritory("Australia").addNeighbour(continents[5].getTerritory("Indonesia"));
	    continents[5].getTerritory("Australia").addNeighbour(continents[5].getTerritory("Nova Zelandia"));

	    continents[5].getTerritory("Perth").addNeighbour(continents[5].getTerritory("Indonesia"));

	}
	

	
	 /**
     * Encontra um território pelo nome no mapa.
     *
     * @param name O nome do território a ser procurado.
     * @return O objeto Territory correspondente ao território encontrado, ou null se não for encontrado.
     */
	public Territory findTerritory(String name) {
	    for (Continent continent : continents) {
	        Territory territory = continent.getTerritory(name);
	        if (territory != null) {
	            return territory;
	        }
	    }
	    return null;
	}
	
	/**
     * Obtém o número total de territórios no mapa.
     *
     * @return O número total de territórios no mapa.
     */
	 public int getNumberTerritories() {
	        int count = 0;
	        for (Continent continent : continents) {
	            count += continent.getNumberTerritories();
	        }
	        return count;
	    }

	    /**
	     * Obtém um território com base no índice fornecido.
	     *
	     * @param index O índice do território desejado.
	     * @return O objeto Territory correspondente ao território no índice fornecido, ou null se o índice estiver fora dos limites.
	     */
	public Territory getTerritory(int index) {
		int currentIndex = 0;
		for (Continent continent : continents) {
			Territory[] territories = continent.getTerritories();
			for (Territory territory : territories) {
				if (currentIndex == index) {
					return territory;
				}
				currentIndex++;
			}
		}
		return null; // Return null if the index is out of bounds
	}
	
    /**
     * Encontra o continente ao qual um território pertence com base no nome do território.
     *
     * @param territoryName O nome do território cujo continente está sendo procurado.
     * @return O objeto Continent correspondente ao continente do território encontrado, ou null se o território não for encontrado em nenhum continente.
     */
	public Continent findContinentFromTerritoryName(String territoryName) {
	    for (Continent continent : continents) {
	        Territory[] territories = continent.getTerritories();
	        for (Territory territory : territories) {
	            if (territory.getName().equals(territoryName)) {
	                return continent;
	            }
	        }
	    }
	    return null; // Retorna nulo se o território não for encontrado em nenhum continente
	}
	
	
    /**
     * Encontra um continente com base no nome fornecido.
     *
     * @param name O nome do continente a ser procurado.
     * @return O objeto Continent correspondente ao continente encontrado, ou null se o continente não for encontrado.
     */
	public Continent findContinent(String name) {
	    for (Continent continent : continents) {
	        if (continent.getName().equals(name)) {
	            return continent;
	        }
	    }
	    return null;
	}
	
	
}
	
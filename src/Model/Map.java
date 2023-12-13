package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * A classe Map representa o mapa do jogo, que consiste em continentes, territórios e suas interconexões.
 * É responsável por gerar o mapa, inicializar os continentes, territórios e definir os vizinhos.
 */
class Map {
	
	// private Continent[] continents;
	private List<Continent> continents = new ArrayList<>();
	private static Map map = null;
	private ArrayList<Territory> territoriesList = new ArrayList<Territory>();
	
	public Map() {
		
		//nao sei se ta certo
		initializeContinents();
		initializeTerritories(continents);
	}

	//Retorna a instância do tabuleiro
	public static synchronized Map getMap() {
		if (map == null){
			map = new Map();
		}
		return map;
	}

	// Inicializando os continentes
    private void initializeContinents() {
        continents.add(createOceania());
        continents.add(createEurope());
        continents.add(createAsia());
        continents.add(createSouthAmerica());
        continents.add(createNorthAmerica());
        continents.add(createAfrica());
    }

	private void initializeTerritories(List<Continent> continents) {
		for ( Continent c : continents) {
			territoriesList.addAll(c.getTerritories());
		}

		for (Territory t: territoriesList) {
			t.getNeighbours();
		}
	}

	public void distributeTerritories(ArrayList<Player> player){
		
		Collections.shuffle(territoriesList);

		int numPlayers = player.size();
		int qtdTerritorios = territoriesList.size();

		for (int i = 0; i < qtdTerritorios; i++) {
			Territory t = territoriesList.get(i);
			Player j = player.get(i % numPlayers);


			t.setOwner(j);

			// Define 1 para a quantidade de exércitos do território
			t.setArmies(1);

			t.setInicialArmies(1);

			j.addTerritorio(t);

		}
	}

	//Oceania
	private Continent createOceania() {
        List<Territory> oceaniaTerritories = Arrays.asList(
            new Territory("Austrália", "Oceania", Arrays.asList("Indonésia", "Nova Zelândia", "Perth")),
            new Territory("Indonésia", "Oceania", Arrays.asList("Austrália", "Nova Zelândia", "Bangladesh", "Índia")),
            new Territory("Nova Zelândia", "Oceania", Arrays.asList("Indonésia", "Austrália")),
            new Territory("Perth", "Oceania", Arrays.asList("Austrália"))
        );
        return new Continent("Oceania", 2, oceaniaTerritories);
    }

	//Europe
	private Continent createEurope() {
        List<Territory> europaTerritories = Arrays.asList(
            new Territory("Reino Unido", "Europa", Arrays.asList("Groelândia", "França")),
            new Territory("França", "Europa", Arrays.asList("Espanha", "Itália", "Suécia", "Reino Unido")),
            new Territory("Espanha", "Europa", Arrays.asList("Argélia", "França")),
            new Territory("Itália", "Europa", Arrays.asList("França", "Argélia", "Polônia", "Romênia", "Suécia")),
            new Territory("Suécia", "Europa", Arrays.asList("França", "Itália", "Letônia", "Estônia")),
            new Territory("Polônia", "Europa", Arrays.asList("Itália", "Letônia", "Romênia", "Ucrânia")),
            new Territory("Romênia", "Europa", Arrays.asList("Egito", "Itália", "Polônia", "Ucrânia")),
            new Territory("Ucrânia", "Europa", Arrays.asList("Polônia", "Romênia", "Letônia", "Turquia"))
        );
        return new Continent("Europa", 5, europaTerritories);
    }

	//Asia
	private Continent createAsia() {
		List<Territory> asiaTerritories = Arrays.asList(
			new Territory("Síria", "Ásia", Arrays.asList("Paquistão","Turquia","Irã","Jordânia","Iraque")),
			new Territory("Letônia", "Ásia", Arrays.asList("Polônia", "Ucrânia", "Suécia", "Estônia", "Turquia", "Cazaquistão", "Rússia")),
        	new Territory("Estônia", "Ásia", Arrays.asList("Suécia", "Rússia", "Letônia")),
        	new Territory("Turquia", "Ásia", Arrays.asList("Letônia", "Cazaquistão", "Ucrânia", "Paquistão", "Síria", "China")),
        	new Territory("Sibéria", "Ásia", Arrays.asList("Rússia", "Cazaquistão", "Alasca")),
        	new Territory("Rússia", "Ásia", Arrays.asList("Estônia", "Letônia", "Cazaquistão", "Sibéria")),
        	new Territory("Cazaquistão", "Ásia", Arrays.asList("Letônia", "Turquia", "Rússia", "Sibéria", "Japão", "China", "Mongólia")),
        	new Territory("Arábia Saudita", "Ásia", Arrays.asList("Jordânia", "Iraque", "Somália")),
        	new Territory("Bangladesh", "Ásia", Arrays.asList("Coreia do Sul", "Índia", "Tailândia", "Indonésia")),
        	new Territory("China", "Ásia", Arrays.asList("Turquia", "Cazaquistão", "Mongólia", "Coreia do Norte", "Coreia do Sul", "Paquistão", "Índia")),
        	new Territory("Coreia do Norte", "Ásia", Arrays.asList("Japão", "China", "Coreia do Sul")),
        	new Territory("Coreia do Sul", "Ásia", Arrays.asList("China", "Coreia do Norte", "Índia", "Bangladesh", "Tailândia")),
        	new Territory("Índia", "Ásia", Arrays.asList("Paquistão", "China", "Coreia do Sul", "Bangladesh","Indonésia")),
        	new Territory("Irã", "Ásia", Arrays.asList("Iraque", "Síria", "Paquistão")),
        	new Territory("Iraque", "Ásia", Arrays.asList("Arábia Saudita", "Jordânia", "Síria", "Irã")),
        	new Territory("Japão", "Ásia", Arrays.asList("Cazaquistão", "Mongólia", "Coreia do Norte")),
        	new Territory("Jordânia", "Ásia", Arrays.asList("Arábia Saudita", "Síria", "Iraque", "Egito")),
        	new Territory("Mongólia", "Ásia", Arrays.asList("China", "Japão", "Cazaquistão")),
        	new Territory("Tailândia", "Ásia", Arrays.asList("Bangladesh", "Coreia do Sul")),
        	new Territory("Paquistão", "Ásia", Arrays.asList("Irã", "China","Índia","Turquia","Síria"))
		);

		return new Continent("Asia", 7, asiaTerritories);

	}

	//South America
	private Continent createSouthAmerica() {
		List<Territory> southAmericaTerritories = Arrays.asList(
			new Territory("Brasil", "América do Sul", Arrays.asList("Argentina", "Peru", "Venezuela", "Nigéria")),
        	new Territory("Argentina", "América do Sul", Arrays.asList("Brasil", "Peru")),
        	new Territory("Peru", "América do Sul", Arrays.asList("Brasil", "Argentina", "Venezuela")),
        	new Territory("Venezuela", "América do Sul", Arrays.asList("México", "Brasil", "Peru"))
		);
		return new Continent("South America", 2, southAmericaTerritories);
	}

	//North America
	private Continent createNorthAmerica() {
		List<Territory> northAmericaTerritories = Arrays.asList(
			new Territory("México", "América do Norte", Arrays.asList("Venezuela", "Califórnia", "Texas")),
        	new Territory("Califórnia", "América do Norte", Arrays.asList("Vancouver", "Texas", "México")),
        	new Territory("Texas", "América do Norte", Arrays.asList("México", "Califórnia", "Vancouver", "Nova York", "Quebec")),
        	new Territory("Vancouver", "América do Norte", Arrays.asList("Califórnia", "Texas", "Quebec", "Alasca", "Calgary")),
        	new Territory("Nova York", "América do Norte", Arrays.asList("Texas", "Quebec")),
        	new Territory("Quebec", "América do Norte", Arrays.asList("Nova York", "Texas", "Vancouver", "Groelândia")),
        	new Territory("Alasca", "América do Norte", Arrays.asList("Vancouver", "Calgary", "Sibéria")),
        	new Territory("Calgary", "América do Norte", Arrays.asList("Vancouver", "Alasca", "Groelândia")),
        	new Territory("Groelândia", "América do Norte", Arrays.asList("Calgary", "Quebec", "Reino Unido"))
		);
		return new Continent("North America", 5, northAmericaTerritories);
	}

	//Africa
	private Continent createAfrica() {
		List<Territory> africaTerritories = Arrays.asList(
			new Territory("Argélia", "África", Arrays.asList("Espanha", "Itália", "Nigéria", "Egito")),
			new Territory("Nigéria", "África", Arrays.asList("Brasil", "Argélia", "Egito", "Somália", "Angola")),
			new Territory("Angola", "África", Arrays.asList("África do Sul", "Somália", "Nigéria")),
			new Territory("Egito", "África", Arrays.asList("Romênia", "Argélia", "Nigéria", "Somália", "Jordânia")),
	    	new Territory("Somália", "África", Arrays.asList("Egito", "Nigéria", "Angola", "África do Sul", "Arábia Saudita")),
	    	new Territory("África do Sul", "África", Arrays.asList("Angola", "Somália"))
		);
		return new Continent("Africa", 3, africaTerritories);
	}	
	

	/**
     * Obtém a lista de continentes no mapa.
     *
     * @return Um array de objetos Continent representando os continentes no mapa.
     */
	//Retorna o array de territorios
	public ArrayList<Territory> getTerritoriesList() {
    	return territoriesList;
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
	
	public List<Continent> getContinents() {
		return continents;
	}
	
}
	
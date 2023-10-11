package Model;

import java.awt.Color;

class Map {
	
	private Continent[] continents;
	public Continent[] getContinents() {
		return continents;
	}
	
	private Map() {
	}
	
	public static Map mapGenerator() {

		Map map = new Map();
		
		map.continents = new Continent[6];
		
		Territory africaDoSul = new Territory("Africa Do Sul", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory angola = new Territory("Angola", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory argelia = new Territory("Argelia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory egito = new Territory("Egito", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory nigeria = new Territory("Nigeria", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory somalia = new Territory("Somalia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		Territory alasca = new Territory("Alasca", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory calgary = new Territory("Calgary", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory california = new Territory("California", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory groelandia = new Territory("Groelandia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory mexico = new Territory("México", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory novaYork = new Territory("Nova York", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory quebec = new Territory("Quebec", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory texas = new Territory("Texas", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory vancouver = new Territory("Vancouver", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		Territory arabiaSaudita = new Territory("Arabia Saudita", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory bangladesh = new Territory("Bangladesh", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory cazaquistao = new Territory("Cazaquistao", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory china = new Territory("China", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory coreiaDoNorte = new Territory("Coreia Do Norte", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory coreiaDoSul = new Territory("Coreia Do Sul", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory estonia = new Territory("Estonia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory india = new Territory("India", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory ira = new Territory("Ira", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory iraque = new Territory("Iraque", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory japao = new Territory("Japao", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory jordania = new Territory("Jordania", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory letonia = new Territory("Letonia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory mongolia = new Territory("Mongolia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory paquistao = new Territory("Paquistao", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory russia = new Territory("Russia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory siberia = new Territory("Siberia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory siria = new Territory("Siria", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory tailandia = new Territory("Tailandia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory turquia = new Territory("Turquia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		Territory argentina = new Territory("Argentina", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory brasil = new Territory("Brasil", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory peru = new Territory("Peru", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory venezuela = new Territory("Venezuela", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		Territory espanha = new Territory("Espanha", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory franca = new Territory("Franca", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory italia = new Territory("Italia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory polonia = new Territory("Polonia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory reinoUnido = new Territory("Reino Unido", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory romenia = new Territory("Romenia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory suecia = new Territory("Suecia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory ucrania = new Territory("Ucrania", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		
		Territory australia = new Territory("Australia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory indonesia = new Territory("Indonesia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory novaZelandia = new Territory("NovaZelandia", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory perth = new Territory("Perth", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		
		
		angola.addNeighbour(somalia);
		angola.addNeighbour(nigeria);
		angola.addNeighbour(africaDoSul);
		
		nigeria.addNeighbour(argelia);
		nigeria.addNeighbour(brasil);
		
		egito.addNeighbour(argelia);
		egito.addNeighbour(nigeria);
		egito.addNeighbour(romenia);
		egito.addNeighbour(jordania);

		somalia.addNeighbour(nigeria);
		somalia.addNeighbour(egito);
		somalia.addNeighbour(arabiaSaudita);
		somalia.addNeighbour(africaDoSul);
		
		argelia.addNeighbour(italia);
		argelia.addNeighbour(espanha);
		
		map.continents[0] = new Continent("Africa", new Territory[] {
				argelia, egito,
				nigeria, somalia,
				angola, africaDoSul
		}, 3, new Color(101, 45, 144));

		return map;
		
	}
	
	public Territory findTerritory(String name) {
	    for (Continent continent : continents) {
	        Territory territory = continent.findTerritory(name);
	        if (territory != null) {
	            return territory;
	        }
	    }
	    return null;
	}
	
	public int getNumberTerritory() {
	    int count = 0;
	    for (int i = 0; i < continents.length; ++i) {
			count += continents[i].getNumberTerritory();
		}
	    return count;
	}
	
	
	public Continent findContinent(String name) {
	    for (Continent continent : continents) {
	        if (continent.getName().equals(name)) {
	            return continent;
	        }
	    }
	    return null;
	}
}
	



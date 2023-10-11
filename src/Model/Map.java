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
		
		
		africaDoSul.addNeighbor(angola);
		africaDoSul.addNeighbor(somalia);
		
		angola.addNeighbor(somalia);
		angola.addNeighbor(nigeria);
		
		argelia.addNeighbor(italia);
		argelia.addNeighbor(espanha);
		argelia.addNeighbor(nigeria);
		argelia.addNeighbor(egito);
		
		egito.addNeighbor(nigeria);
		egito.addNeighbor(somalia);
		egito.addNeighbor(romenia);
		egito.addNeighbor(jordania);
		
		nigeria.addNeighbor(somalia);
		nigeria.addNeighbor(brasil);

		somalia.addNeighbor(arabiaSaudita);
		argelia.addNeighbor(italia);
		argelia.addNeighbor(espanha);
		
		map.continents[0] = new Continent("Africa", new Territory[] {
				africaDoSul,angola, argelia, egito, nigeria, somalia
		}, 3);
		
		
		alasca.addNeighbor(calgary);
		alasca.addNeighbor(vancouver);
		alasca.addNeighbor(siberia);
		
		calgary.addNeighbor(vancouver);
		calgary.addNeighbor(groelandia);
		
		california.addNeighbor(vancouver);
		california.addNeighbor(texas);
		california.addNeighbor(mexico);
		
		groelandia.addNeighbor(quebec);
		groelandia.addNeighbor(reinoUnido);
		
		mexico.addNeighbor(texas);
		mexico.addNeighbor(venezuela);
		
		novaYork.addNeighbor(texas);
		novaYork.addNeighbor(quebec);

		quebec.addNeighbor(texas);
		quebec.addNeighbor(vancouver);
		
		texas.addNeighbor(vancouver);

		
		
		map.continents[1] = new Continent("America do Norte", new Territory[] {
				alasca, calgary, california, groelandia, mexico, novaYork, quebec, texas, vancouver
		}, 5);
		
		
		arabiaSaudita.addNeighbor(iraque);
		arabiaSaudita.addNeighbor(jordania);
		
		bangladesh.addNeighbor(tailandia);
		bangladesh.addNeighbor(india);
		bangladesh.addNeighbor(coreiaDoSul);
		bangladesh.addNeighbor(indonesia);
		
		cazaquistao.addNeighbor(japao);
		cazaquistao.addNeighbor(siberia);
		cazaquistao.addNeighbor(russia);
		cazaquistao.addNeighbor(mongolia);
		cazaquistao.addNeighbor(china);
		cazaquistao.addNeighbor(turquia);
		cazaquistao.addNeighbor(letonia);
		
		china.addNeighbor(mongolia);
		china.addNeighbor(coreiaDoNorte);
		china.addNeighbor(coreiaDoSul);
		china.addNeighbor(india);
		china.addNeighbor(paquistao);
		china.addNeighbor(turquia);
		
		coreiaDoNorte.addNeighbor(japao);
		coreiaDoNorte.addNeighbor(coreiaDoSul);
		
		coreiaDoSul.addNeighbor(tailandia);
		coreiaDoSul.addNeighbor(india);
		
		estonia.addNeighbor(russia);
		estonia.addNeighbor(letonia);
		estonia.addNeighbor(suecia);
		
		india.addNeighbor(paquistao);
		india.addNeighbor(indonesia);
		
		ira.addNeighbor(paquistao);
		ira.addNeighbor(siria);
		ira.addNeighbor(iraque);
		
		iraque.addNeighbor(siria);
		iraque.addNeighbor(jordania);
		
		japao.addNeighbor(mongolia);
		
		jordania.addNeighbor(siria);
		
		
		letonia.addNeighbor(russia);
		letonia.addNeighbor(turquia);
		letonia.addNeighbor(ucrania);
		letonia.addNeighbor(polonia);
		letonia.addNeighbor(suecia);
		
		paquistao.addNeighbor(turquia);
		paquistao.addNeighbor(siria);
		
		russia.addNeighbor(siberia);
		
		siria.addNeighbor(turquia);
		
		turquia.addNeighbor(ucrania);
		
		map.continents[2] = new Continent("Asia", new Territory[] {
				arabiaSaudita, bangladesh, cazaquistao, china, coreiaDoNorte, coreiaDoSul, 
				estonia, india, ira, iraque, japao, jordania, letonia, mongolia, paquistao, 
				russia, siberia, siria, tailandia, turquia  
		}, 7);
		
		
		
		argentina.addNeighbor(peru);
		argentina.addNeighbor(brasil);
		
		brasil.addNeighbor(peru);
		brasil.addNeighbor(venezuela);
		
		peru.addNeighbor(venezuela);
		
		map.continents[3] = new Continent("America do Sul", new Territory[] {
				argentina, brasil, peru, venezuela
		}, 2);
		
		
		espanha.addNeighbor(franca);
		
		franca.addNeighbor(italia);
		franca.addNeighbor(suecia);
		franca.addNeighbor(reinoUnido);
		
		italia.addNeighbor(romenia);
		italia.addNeighbor(polonia);
		italia.addNeighbor(suecia);
		
		polonia.addNeighbor(ucrania);
		polonia.addNeighbor(romenia);
		
		romenia.addNeighbor(ucrania);
		
		map.continents[4] = new Continent("Europa", new Territory[] {
				espanha, franca, italia, polonia, reinoUnido, romenia, suecia, ucrania
		}, 5);
		
		australia.addNeighbor(perth);
		australia.addNeighbor(indonesia);
		australia.addNeighbor(novaZelandia);
		

		map.continents[5] = new Continent("Oceania", new Territory[] {
				indonesia, novaZelandia,
				australia, perth
		}, 2);
				
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
	
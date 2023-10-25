package Model;

import java.awt.Color;

class Map {
	
	private Continent[] continents;
	public Continent[] getContinents() {
		return continents;
	}
	
	// tava como private 
	public Map() {
	}
	
	
	public static Map mapGenerator() {

		Map map = new Map();
		
		map.continents = new Continent[6];
		
		Territory africaDoSul = new Territory("Africa do Sul");
		Territory angola = new Territory("Angola");
		Territory argelia = new Territory("Argelia");
		Territory egito = new Territory("Egito");
		Territory nigeria = new Territory("Nigeria");
		Territory somalia = new Territory("Somalia");
		
		Territory alasca = new Territory("Alasca");
		Territory calgary = new Territory("Calgary");
		Territory california = new Territory("California");
		Territory groelandia = new Territory("Groenlandia");
		Territory mexico =  new Territory("Mexico");
		Territory novaYork = new Territory("Nova Iorque");
		Territory quebec = new Territory("Quebec");
		Territory texas = new Territory("Texas");
		Territory vancouver = new Territory("Vancouver");
		
		Territory arabiaSaudita = new Territory("Arabia Saudita");
		Territory bangladesh = new Territory("Bangladesh");
		Territory cazaquistao = new Territory("Cazaquistao");
		Territory china = new Territory("China");
		Territory coreiaDoNorte = new Territory("Coreia do Norte");
		Territory coreiaDoSul = new Territory("Coreia do Sul");
		Territory estonia = new Territory("Estonia");
		Territory india = new Territory("India");
		Territory ira = new Territory("Ira");
		Territory iraque = new Territory("Iraque");
		Territory japao = new Territory("Japao");
		Territory jordania = new Territory("Jordania");
		Territory letonia = new Territory("Letonia");
		Territory mongolia = new Territory("Mongolia");
		Territory paquistao = new Territory("Paquistao");
		Territory russia = new Territory("Russia");
		Territory siberia = new Territory("Siberia");
		Territory siria = new Territory("Siria");
		Territory tailandia = new Territory("Tailandia");
		Territory turquia = new Territory("Turquia");
		
		Territory argentina = new Territory("Argentina");
		Territory brasil = new Territory("Brasil");
		Territory peru = new Territory("Peru");
		Territory venezuela = new Territory("Venezuela");
		
		Territory espanha = new Territory("Espanha");
		Territory franca = new Territory("Franca");
		Territory italia = new Territory("Italia");
		Territory polonia = new Territory("Polonia");
		Territory reinoUnido = new Territory("Reino Unido");
		Territory romenia = new Territory("Romenia");
		Territory suecia = new Territory("Suecia");
		Territory ucrania = new Territory("Ucrania");
		
		Territory australia = new Territory("Australia");
		Territory indonesia = new Territory("Indonesia");
		Territory novaZelandia = new Territory("Nova Zelandia");
		Territory perth = new Territory("Perth");
		
		africaDoSul.addNeighbour(angola);
		africaDoSul.addNeighbour(somalia);
		
		angola.addNeighbour(somalia);
		angola.addNeighbour(nigeria);
		
		argelia.addNeighbour(italia);
		argelia.addNeighbour(espanha);
		argelia.addNeighbour(nigeria);
		argelia.addNeighbour(egito);
		
		egito.addNeighbour(nigeria);
		egito.addNeighbour(somalia);
		egito.addNeighbour(romenia);
		egito.addNeighbour(jordania);
		
		nigeria.addNeighbour(somalia);
		nigeria.addNeighbour(brasil);

		somalia.addNeighbour(arabiaSaudita);
		argelia.addNeighbour(italia);
		argelia.addNeighbour(espanha);
		
		map.continents[0] = new Continent("Africa", new Territory[] {
				africaDoSul,angola, argelia, egito, nigeria, somalia
		}, 3, new Color(101, 45, 144));
		
		
		alasca.addNeighbour(calgary);
		alasca.addNeighbour(vancouver);
		alasca.addNeighbour(siberia);
		
		calgary.addNeighbour(vancouver);
		calgary.addNeighbour(groelandia);
		
		california.addNeighbour(vancouver);
		california.addNeighbour(texas);
		california.addNeighbour(mexico);
		
		groelandia.addNeighbour(quebec);
		groelandia.addNeighbour(reinoUnido);
		
		mexico.addNeighbour(texas);
		mexico.addNeighbour(venezuela);
		
		novaYork.addNeighbour(texas);
		novaYork.addNeighbour(quebec);

		quebec.addNeighbour(texas);
		quebec.addNeighbour(vancouver);
		
		texas.addNeighbour(vancouver);

		
		
		map.continents[1] = new Continent("America do Norte", new Territory[] {
				alasca, calgary, california, groelandia, mexico, novaYork, quebec, texas, vancouver
		}, 5, new Color(238, 64, 54));
		
		
		arabiaSaudita.addNeighbour(iraque);
		arabiaSaudita.addNeighbour(jordania);
		
		bangladesh.addNeighbour(tailandia);
		bangladesh.addNeighbour(india);
		bangladesh.addNeighbour(coreiaDoSul);
		bangladesh.addNeighbour(indonesia);
		
		cazaquistao.addNeighbour(japao);
		cazaquistao.addNeighbour(siberia);
		cazaquistao.addNeighbour(russia);
		cazaquistao.addNeighbour(mongolia);
		cazaquistao.addNeighbour(china);
		cazaquistao.addNeighbour(turquia);
		cazaquistao.addNeighbour(letonia);
		
		china.addNeighbour(mongolia);
		china.addNeighbour(coreiaDoNorte);
		china.addNeighbour(coreiaDoSul);
		china.addNeighbour(india);
		china.addNeighbour(paquistao);
		china.addNeighbour(turquia);
		
		coreiaDoNorte.addNeighbour(japao);
		coreiaDoNorte.addNeighbour(coreiaDoSul);
		
		coreiaDoSul.addNeighbour(tailandia);
		coreiaDoSul.addNeighbour(india);
		
		estonia.addNeighbour(russia);
		estonia.addNeighbour(letonia);
		estonia.addNeighbour(suecia);
		
		india.addNeighbour(paquistao);
		india.addNeighbour(indonesia);
		
		ira.addNeighbour(paquistao);
		ira.addNeighbour(siria);
		ira.addNeighbour(iraque);
		
		iraque.addNeighbour(siria);
		iraque.addNeighbour(jordania);
		
		japao.addNeighbour(mongolia);
		
		jordania.addNeighbour(siria);
		
		
		letonia.addNeighbour(russia);
		letonia.addNeighbour(turquia);
		letonia.addNeighbour(ucrania);
		letonia.addNeighbour(polonia);
		letonia.addNeighbour(suecia);
		
		paquistao.addNeighbour(turquia);
		paquistao.addNeighbour(siria);
		
		russia.addNeighbour(siberia);
		
		siria.addNeighbour(turquia);
		
		turquia.addNeighbour(ucrania);
		
		map.continents[2] = new Continent("Asia", new Territory[] {
				arabiaSaudita, bangladesh, cazaquistao, china, coreiaDoNorte, coreiaDoSul, 
				estonia, india, ira, iraque, japao, jordania, letonia, mongolia, paquistao, 
				russia, siberia, siria, tailandia, turquia  
		}, 7, new Color(246, 146, 30));
		
		
		
		argentina.addNeighbour(peru);
		argentina.addNeighbour(brasil);
		
		brasil.addNeighbour(peru);
		brasil.addNeighbour(venezuela);
		
		peru.addNeighbour(venezuela);
		
		map.continents[3] = new Continent("America do Sul", new Territory[] {
				argentina, brasil, peru, venezuela
		}, 2, new Color(0, 104, 58));
		
		
		espanha.addNeighbour(franca);
		
		franca.addNeighbour(italia);
		franca.addNeighbour(suecia);
		franca.addNeighbour(reinoUnido);
		
		italia.addNeighbour(romenia);
		italia.addNeighbour(polonia);
		italia.addNeighbour(suecia);
		
		polonia.addNeighbour(ucrania);
		polonia.addNeighbour(romenia);
		
		romenia.addNeighbour(ucrania);
		
		map.continents[4] = new Continent("Europa", new Territory[] {
				espanha, franca, italia, polonia, reinoUnido, romenia, suecia, ucrania
		}, 5, new Color(43, 56, 143));
		
		australia.addNeighbour(perth);
		australia.addNeighbour(indonesia);
		australia.addNeighbour(novaZelandia);
		

		map.continents[5] = new Continent("Oceania", new Territory[] {
				indonesia, novaZelandia,
				australia, perth
		}, 2, new Color(38, 169, 224));
				
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
	
	
	public int getNumberTerritories() {
	    int count = 0;
	    for (int i = 0; i < continents.length; ++i) {
			count += continents[i].getNumberTerritories();
		}
	    return count;
	}

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
	
	
	
	public Continent findContinent(String name) {
	    for (Continent continent : continents) {
	        if (continent.getName().equals(name)) {
	            return continent;
	        }
	    }
	    return null;
	}
}
	
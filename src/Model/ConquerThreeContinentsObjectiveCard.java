package Model;

class ConquerThreeContinentsObjectiveCard {
	private Player dono;
    private String nome;
    private Continent continente1, continente2;
    private Map map;
    
    public ConquerThreeContinentsObjectiveCard(String name, Continent continente1, Continent continente2, Map map) {
        this.nome = name;
        this.continente1 = continente1;
        this.continente2 = continente2;
        this.map = map;
        
    }
    public String getName() {
        return this.nome;
    }

    public boolean verifica_status() {
        if (continente1.verifica_monopolio(dono) && continente2.verifica_monopolio(dono)) {
        	if (continente2.getName().equals("Oceania")) {
                // Verifica se o jogador tem o monopólio de um dos continentes: "North America", "Africa", "Asia", "South America"
                return continenteMonopolio(dono, "North America", "Africa", "Asia", "South America");
            }
        	else if (continente2.getName().equals("South America")) {
                // Verifica se o jogador tem o monopólio de um dos continentes: "North America", "Africa", "Asia", "Oceania"
                return continenteMonopolio(dono, "North America", "Africa", "Asia", "Oceania");
            }
        	
            
        }
    }
    
    private boolean continenteMonopolio(Player dono, String... continentes) {
        for (String continente : continentes) {
            if (map.findContinent(continente).verifica_monopolio(dono)) {
                return true;
            }
        }
        return false;
    }

    public void ganha_dono(Player dono) {
        this.dono = dono;
    }
    
}

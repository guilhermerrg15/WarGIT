package Model;

class ConquerThreeContinentsObjectiveCard implements Objective {
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

    @Override
    public boolean checkStatus() {
        if (continente1.checkContinentDomain(dono) && continente2.checkContinentDomain(dono)) {
        	if (continente2.getName().equals("Oceania")) {
                // Verifica se o jogador tem o monopólio de um dos continentes: "North America", "Africa", "Asia", "South America"
                return continenteMonopolio(dono, "North America", "Africa", "Asia", "South America");
            }
        	else if (continente2.getName().equals("South America")) {
                // Verifica se o jogador tem o monopólio de um dos continentes: "North America", "Africa", "Asia", "Oceania"
                return continenteMonopolio(dono, "North America", "Africa", "Asia", "Oceania");
            }
        }
		return false;
    }
    
    private boolean continenteMonopolio(Player dono, String... continentes) {
        for (String continente : continentes) {
            if (map.findContinent(continente).checkContinentDomain(dono)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkOwner(Player dono) {
        this.dono = dono;
    }
    
}

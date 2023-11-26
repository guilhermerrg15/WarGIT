package Model;

import java.util.Map;

class ConquerThreeContinentsObjectiveCard extends ObjectiveCard {
    private String nome;
    private Continent continent1, continent2;
    
    public ConquerThreeContinentsObjectiveCard(String name, Continent continent1, Continent continent2) {
        this.nome = name;
        this.continent1 = continent1;
        this.continent2 = continent2;
        this.image = "war_carta_" + name + ".png";
        this.description = "Conquistar na totalidade a " + continent1 + " e a " + continent2 + " e mais um continente a sua escolha";
        
    }
    public String getName() {
        return this.nome;
    }

    public boolean checkStatus() {
        if (continent1.checkContinentDomain(owner) && continent2.checkContinentDomain(owner)) {
        	for(Continent continent: Continent.getContinent()){
                if(!continent.equals(this.continent1) && !continent.equals(this.continent2) && continent.checkContinentDomain(owner)){
                    return true;
                }

            }
        	
        }
		return false;
    }

     public void checkOwner(Player dono) {
         this.owner = dono;
     }
    
}


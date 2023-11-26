package Model;

class Conquer24TerritoriesObjectiveCard extends ObjectiveCard {
    protected String name;
    
    public Conquer24TerritoriesObjectiveCard(String name) {
        this.name = name;
        this.image = "war_carta_" + name + ".png";
        this.description = "Conquistar 24 territorios a sua escolha";
     }

    public String getName() {
        return this.name;
    }

    public boolean checkStatus() {
		if(owner.getTerritoryNumber()>=24) {
			return true;
		}
		return false;
	}


     public void checkOwner(Player dono) {
         this.owner = dono;
     }
}

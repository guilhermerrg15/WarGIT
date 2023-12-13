package Model;

class Conquer18TerritoriesObjectiveCard extends ObjectiveCard {
    protected String nome;
    
    public Conquer18TerritoriesObjectiveCard(String name) {
        this.nome = name;
        this.image = "war_carta_" + name + ".png";
        this.description = "Conquistar 18 territorios com pelo menos 2 exércitos em cada";
     }

    public String getName() {
        return this.nome;
    }

    public boolean checkStatus() {
        if(owner.getTerritoryNumber() >= 18) {
            for(Territory terr : owner.getConqueredTerritories()) {
                if(terr.getArmies() < 2) {
                    return false;
                }
            }
            return true;
        } 
        return false;
    }

     public void checkOwner(Player owner) {
         this.owner = owner;
     }
}
package Model;

class Conquer18TerritoriesObjectiveCard {
    protected Player dono;
    protected String nome;
    
    public Conquer18TerritoriesObjectiveCard(String name) {
        this.nome = name;
     }

    public String getName() {
        return this.nome;
    }

    public boolean verifica_status() {
        if(dono.getArmySize() >= 18) {
            for(Territory terr : dono.territories) {
                if(terr.getArmies() < 2) {
                    return false;
                }
            }
            return true;
        } 
        return false;
    }

    public void checkOwner(Player dono) {
        this.dono = dono;
    }
}

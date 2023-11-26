package Model;

class Conquer18TerritoriesObjectiveCard implements Objective {
    protected Player dono;
    protected String nome;
    
    public Conquer18TerritoriesObjectiveCard(String name) {
        this.nome = name;
     }

    public String getName() {
        return this.nome;
    }

    @Override
    public boolean checkStatus() {
        if(dono.getTerritoryNumber() >= 18) {
            for(Territory terr : dono.territories) {
                if(terr.getArmies() < 2) {
                    return false;
                }
            }
            return true;
        } 
        return false;
    }

    @Override
    public void checkOwner(Player dono) {
        this.dono = dono;
    }
}

package Model;

class Conquer24TerritoriesObjectiveCard implements Objective {
    protected Player dono;
    protected String nome;
    
    public Conquer24TerritoriesObjectiveCard(String name) {
        this.nome = name;
     }

    public String getName() {
        return this.nome;
    }

    @Override
    public boolean checkStatus() {
		if(dono.getTerritoryNumber()>=24) {
			return true;
		}
		return false;
	}

    @Override
    public void checkOwner(Player dono) {
        this.dono = dono;
    }
}

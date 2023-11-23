package Model;

class Conquer24TerritoriesObjectiveCard {
    protected Player dono;
    protected String nome;
    
    public Conquer24TerritoriesObjectiveCard(String name) {
        this.nome = name;
     }

    public String getName() {
        return this.nome;
    }

    public boolean verifica_status() {
		if(dono.qtd_territorios()>=24) {
			return true;
		}
		return false;
	}

    public void ganha_dono(Player dono) {
        this.dono = dono;
    }
}

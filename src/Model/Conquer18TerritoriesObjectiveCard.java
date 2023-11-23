package Model;

class Conquer18TerritoriesObjectiveCard {
    protected Player dono;
    protected String nome;
    
    public Conquer18TerritoriesObjectiveCard(String name) {
        this.nome = name;
     }

    public String get_nome() {
        return this.nome;
    }

    public boolean verifica_status() {
        if(dono.qtd_territorios() >= 18) {
            for(Territory terr : dono.domina) {
                if(terr.get_exercitos() < 2) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void ganha_dono(Player dono) {
        this.dono = dono;
    }
}

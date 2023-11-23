package Model;

class ConquerTwoContinentsObjectiveCard {
    private Player dono;
    private String nome;
    private Continent continente1, continente2;

    public ConquerTwoContinentsObjectiveCard( String name, Continent continente1, Continent continente2) {
        this.nome = name;
        this.continente1 = continente1;
        this.continente2 = continente2;
    }

    public String getName() {
        return this.nome;
    }

    public boolean verifica_status() {
        return continente1.verifica_monopolio(dono) && continente2.verifica_monopolio(dono);
    }

    public void ganha_dono(Player dono) {
        this.dono = dono;
    }
}

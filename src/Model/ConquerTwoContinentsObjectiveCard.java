package Model;

class ConquerTwoContinentsObjectiveCard implements Objective{
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

    @Override
    public boolean checkStatus() {
        return continente1.checkContinentDomain(dono) && continente2.checkContinentDomain(dono);
    }

    @Override
    public void checkOwner(Player dono) {
        this.dono = dono;
    }
}

package Model;


class ConquerTwoContinentsObjectiveCard extends ObjectiveCard{
    private String nome;
    private Continent continent1, continent2;

    public ConquerTwoContinentsObjectiveCard( String name, Continent continent1, Continent continent2) {
        this.nome = name;
        this.continent1 = continent1;
        this.continent2 = continent2;
        this.image = "war_carta_" + name + ".png";
        this.description = "Conquistar na totalidade a " + continent1 + " e a " + continent2;
    }

    public String getName() {
        return this.nome;
    }

    public boolean checkStatus() {
        return continent1.checkContinentDomain(owner) && continent2.checkContinentDomain(owner);
    }

    public Continent getFirstCont() {
        return this.continent1;
    }

    public Continent getSecondCont() {
        return this.continent2;
    }

     public void checkOwner(Player dono) {
         this.owner = dono;
     }
}


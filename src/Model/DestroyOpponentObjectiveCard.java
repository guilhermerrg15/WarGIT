package Model;

class DestroyOpponentObjectiveCard extends ObjectiveCard{
    Player enemy;
    private String name;
    private String color;

    public DestroyOpponentObjectiveCard( String name, String color, Player enemy) {
        this.name = name;
        this.color = color;
        this.enemy = enemy;
        this.image = "war_carta_" + name + ".png";
        this.description = "Destruir todos os exércitos"+ color +". Se você é quem possui os exércitos " + color + "ou se esses exércitos já foram destruídos por outro jogador, o seu objetivo passa a ser conquistar 24 territorios a sua escolha.";
    }

    public String getName() {
        return this.name;
    } 

    public boolean checkStatus() {

        if (owner.getColor() != enemy.getColor()) {

            if (enemy.getTerritoryNumber() > 0){
                return false;
            }
            if (enemy.getEliminatedThisRound() && enemy.getPlayerKilled() == owner){
                return true;
            }
            if (enemy.getEliminatedThisRound() &&  enemy.getPlayerKilled() != owner && owner.getTerritoryNumber() >= 24){
                return true;
            }
            if (enemy.getTerritoryNumber() == 0 && enemy.getPlayerKilled() != owner  && owner.getTerritoryNumber() >= 24)
            {
                return true;
            }
        }
        else {
            if (owner.getTerritoryNumber() >= 24){
                    return true;
            }
        }
        
        return false;
       
    }

    public void checkOwner(Player owner) {
        this.owner = owner;
    }

}

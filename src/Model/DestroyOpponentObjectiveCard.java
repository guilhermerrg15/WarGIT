package Model;

import java.util.List;

class DestroyOpponentObjectiveCard implements Objective {
    private Player dono;
    private List<Player> todos_jogadores;
    private String nome;
    private String color;

    public DestroyOpponentObjectiveCard(List<Player> players, String name, String color) {
        this.todos_jogadores = players;
       
        this.nome = name;
        this.color = color;
    }

    public String getName() {
        return this.nome;
    }

    @Override
    public boolean checkStatus() {
        for (Player player : this.todos_jogadores) {
            if (player.getColor().equals(color)) {
                if (player.getEnemy() != null && player.getEnemy().equals(dono)) {
                    return true;
                }
                if (player.getEnemy() == null) {
                    return false;
                }
            }
        }
        return dono.getConqueredTerritories().size() >= 24;
    }

    @Override
    public void checkOwner(Player dono) {
        this.dono = dono;
    }
}
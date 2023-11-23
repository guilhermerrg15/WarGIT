package Model;

import java.util.List;

class DestroyOpponentObjectiveCard {
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

    public boolean checkStatus() {
        for (Player player : this.todos_jogadores) {
            if (player.getColor().equals(color)) {
                if (player.destroyedPlayer(dono) != null && player.destroyedPlayer(dono).equals(dono)) {
                    return true;
                }
                if (player.destroyedPlayer(dono) == null) {
                    return false;
                }
            }
        }
        return dono.getConqueredTerritories().size() >= 24;
    }

    public void ganhaDono(Player dono) {
        this.dono = dono;
    }
}

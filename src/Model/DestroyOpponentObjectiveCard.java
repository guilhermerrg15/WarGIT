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

    public String getNome() {
        return this.nome;
    }

    public boolean verificaStatus() {
        for (Player player : this.todos_jogadores) {
            if (player.getColor().equals(color)) {
                if (player.getDestruidoPor() != null && player.getDestruidoPor().equals(dono)) {
                    return true;
                }
                if (player.getDestruidoPor() == null) {
                    return false;
                }
            }
        }
        return dono.getDomina().size() >= 24;
    }

    public void ganhaDono(Player dono) {
        this.dono = dono;
    }
}

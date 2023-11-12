package Model;

import java.util.Hashtable;
import java.util.Collections;

class ObjectiveCard {
    private String description;
    private boolean completed;
    private String target;
    private Player dono;

    ObjectiveCard(String description, String target) {
        this.description = description;
        this.completed = false;
        this.target = target;
    }

    void setDono(Player dono) {
        this.dono = dono;
    }

    static Player getOwnerByCardDescription(ObjectiveCard[] cards, String cardDescription) {
        for (ObjectiveCard cart : cards) {
            if (cart.description.equals(cardDescription)) {
                return cart.dono;
            }
        }
        return null;
    }

    static Integer idCarta(String carta) {
        Hashtable<String, Integer> nomeImagens = new Hashtable<String, Integer>();
        nomeImagens.put("Conquistar 24 territórios a sua escolha", 0);
        nomeImagens.put("Conquistar na totalidade a Asia e a Africa", 1);
        nomeImagens.put("Conquistar na totalidade a Asia e a America do Sul", 2);
        nomeImagens.put("Conquistar na totalidade a America do Norte e a Africa", 3);
        nomeImagens.put("Conquistar na totalidade a America do Norte e a Oceania", 4);
        nomeImagens.put("Conquistar na totalidade a Europa, a Oceania e mais um continente a sua escolha", 5);
        nomeImagens.put("Conquistar na totalidade a Europa, a America do Sul e mais um continente a sua escolha", 6);
        nomeImagens.put("Conquistar 18 territórios com pelo menos 2 exércitos em cada", 7);
        return nomeImagens.get(carta);
    }


    String getDescription() {
        return description;
    }

    boolean isCompleted() {
        return completed;
    }

    void setCompleted(boolean completed) {
        this.completed = completed;
    }

    String getTarget() {
        return target;
    }
}

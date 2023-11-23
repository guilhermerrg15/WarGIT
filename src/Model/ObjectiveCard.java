package Model;

import java.util.Hashtable;
import java.util.Collections;

/**
 * Classe que representa uma carta de objetivo no jogo.
 */
class ObjectiveCard {
    private String description;
    private boolean completed;
    private String target;
    private Player dono;
    private String name;

    /**
     * Construtor da classe ObjectiveCard.
     *
     * @param description Descrição da carta de objetivo.
     * @param target      Alvo do objetivo.
     */
    ObjectiveCard(String description, String target) {
        this.description = description;
        this.completed = false;
        this.target = target;
    }

    public String getName() {
        return name;
    }
    /**
     * Define o jogador dono da carta.
     *
     * @param dono Jogador dono da carta.
     */
    void setDono(Player dono) {
        this.dono = dono;
    }

    /**
     * Obtém o jogador dono de uma carta com base na descrição.
     *
     * @param cards          Array de cartas de objetivo.
     * @param cardDescription Descrição da carta de objetivo.
     * @return Jogador dono da carta ou null se não encontrado.
     */
    static Player getOwnerByCardDescription(ObjectiveCard[] cards, String cardDescription) {
        for (ObjectiveCard cart : cards) {
            if (cart.description.equals(cardDescription)) {
                return cart.dono;
            }
        }
        return null;
    }


    /**
     * Obtém a descrição da carta de objetivo.
     *
     * @return Descrição da carta de objetivo.
     */
    String getDescription() {
        return description;
    }

    /**
     * Verifica se o objetivo associado à carta foi concluído.
     *
     * @return true se o objetivo foi concluído, false caso contrário.
     */
    boolean isCompleted() {
        return completed;
    }

    /**
     * Define se o objetivo associado à carta foi concluído.
     *
     * @param completed Valor indicando se o objetivo foi concluído.
     */
    void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Obtém o alvo do objetivo associado à carta.
     *
     * @return Alvo do objetivo.
     */
    String getTarget() {
        return target;
    }
}

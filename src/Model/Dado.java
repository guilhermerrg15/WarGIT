package Model;

import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A classe Dado representa um dado utilizado no jogo.
 * Este dado é usado para simular lançamentos de dados para ataques e defesas.
 */
class Dado {
    private static int value;

    Dado() {} // Construtor privado para impedir a criação de instâncias
    
    /**
     * Simula o lançamento do dado e define seu valor.
     *
     * @return O valor do dado após o lançamento.
     */
    public int throwDice() {
        value = Math.abs((ThreadLocalRandom.current().nextInt()) % 6) + 1;
        return value;
    }

    /**
     * Obtém o valor atual do dado.
     *
     * @return O valor do dado.
     */
    public static int getDice() {
        return value;
    }
}




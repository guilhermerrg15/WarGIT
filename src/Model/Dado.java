package Model;

//import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A classe Dado representa um dado utilizado no jogo.
 * Este dado é usado para simular lançamentos de dados para ataques e defesas.
 */
public class Dado {
    private static int valor;

    Dado() {} // Construtor privado para impedir a criação de instâncias
    
    /**
     * Simula o lançamento do dado e define seu valor.
     *
     * @return O valor do dado após o lançamento.
     */
    public static int jogaDado() {
        valor = Math.abs((ThreadLocalRandom.current().nextInt()) % 6) + 1;
        return valor;
    }

    /**
     * Obtém o valor atual do dado.
     *
     * @return O valor do dado.
     */
    public static int getDado() {
        return valor;
    }

    // Lançamento de dados de ataque
    protected List<Integer> rollDiceAttack(int attackDiceNumber) {
        List<Integer> diceValuesAttack = new ArrayList<>();
        
        for(int i = 0; i < attackDiceNumber; i++) {
            int random = jogaDado();
            diceValuesAttack.add(random);
        }

        return diceValuesAttack;
    }

    // Lançamento de dados de defesa
    protected List<Integer> rollDiceDefense(int defenseDiceNumber) {
        List<Integer> diceValuesDefense = new ArrayList<>();

        for(int i = 0; i < defenseDiceNumber; i++) {
            int random = jogaDado();
            diceValuesDefense.add(random);
        }

        return diceValuesDefense;
    }
}




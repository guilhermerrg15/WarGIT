package Model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DiceTest {

    @Test
    public void testJogaDado() {
        Dado dado = new Dado();

        for (int i = 0; i < 1000; i++) {
            int result = dado.jogaDado();
            assertTrue("Invalid dice value: " + result, result >= 1 && result <= 6);
        }
    }

    @Test
    public void testGetDado() {
        Dado dado = new Dado();
        int result = dado.jogaDado();
        assertTrue(Dado.getDado() == result);
    }
}


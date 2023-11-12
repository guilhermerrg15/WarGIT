package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map;


/**
 * Representa o baralho de cartas de objetivo no jogo.
 */
class ObjectiveCardDeck {
    private static ObjectiveCard[] cards;
    private static ObjectiveCardDeck objectiveSingleton;

    private static final String[] descricao = {
            "Conquistar 24 territórios a sua escolha",
            "Conquistar na totalidade a Asia e a Africa",
            "Conquistar na totalidade a Asia e a America do Sul",
            "Conquistar na totalidade a America do Norte e a Africa",
            "Conquistar na totalidade a America do Norte e a Oceania",
            "Conquistar na totalidade a Europa, a Oceania e mais um continente a sua escolha",
            "Conquistar na totalidade a Europa, a America do Sul e mais um continente a sua escolha",
            "Conquistar 18 territórios com pelo menos 2 exércitos em cada"
    };
    
 // Mapa que associa descrições a IDs
    private static final Map<String, Integer> descricaoIdMap;

    static {
        descricaoIdMap = new HashMap<>();
    }

    static {
        for (int i = 0; i < descricao.length; i++) {
            descricaoIdMap.put(descricao[i], i);
        }
    }

    private ObjectiveCardDeck() {
    }

    /**
     * Obtém a instância única do baralho de cartas de objetivo.
     *
     * @return Instância única do baralho de cartas de objetivo.
     */
    public static ObjectiveCardDeck getInstance() {
        if (objectiveSingleton == null) {
            objectiveSingleton = new ObjectiveCardDeck();
        }
        return objectiveSingleton;
    }

    /**
     * Obtém as descrições das cartas de objetivo.
     *
     * @return Array de descrições das cartas de objetivo.
     */
    public String[] getDescricao() {
        return descricao;
    }
    
 

    /**
     * Obtém uma descrição aleatória de uma carta de objetivo.
     *
     * @return Descrição aleatória de uma carta de objetivo.
     */
    String getRandomObjective() {
        if (cards == null) {
            initializeCards();
        }
        Random random = new Random();
        int randomIndex = random.nextInt(cards.length);
        return cards[randomIndex].getDescription();
    }
    
    private void initializeCards() {
        cards = new ObjectiveCard[8];
        setCartasEmbaralhadas();
    }

    /**
     * Torna as cartas nulas.
     */
    void nullCartas() {
        cards = null;
    }

    /**
     * Obtém as cartas de objetivo.
     *
     * @return Array de cartas de objetivo.
     */
    ObjectiveCard[] getCards() {
        if (cards == null) {
            initializeCards();
        }
        return cards;
    }

    /**
     * Inicializa e embaralha as cartas de objetivo.
     *
     * @return Array de cartas de objetivo embaralhadas.
     */
    ObjectiveCard[] setCartasEmbaralhadas() {
        if (cards == null) {
            initializeCards();
        }
        embaralhaCards();
        return cards;
    }

    ObjectiveCard[] setCards() {
        cards = new ObjectiveCard[8];
        return cards;
    }

    private void embaralhaCards() {
        List<ObjectiveCard> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
        cardList.toArray(cards);
    }
    
    public List<ObjectiveCard> getObjectiveCardList() {
        return Arrays.asList(cards);
    }

	public static Map<String, Integer>  getDescricaoIdMap() {
		return descricaoIdMap;
	}
	
	
}

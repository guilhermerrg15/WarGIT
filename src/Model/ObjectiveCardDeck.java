package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class ObjectiveCardDeck {
    private static ObjectiveCard[] cards;
    private static ObjectiveCardDeck objectiveSingleton;

    private static final String[] descricao = {"Conquistar 24 territórios a sua escolha",
    "Conquistar na totalidade a Asia e a Africa", "Conquistar na totalidade a Asia e a America do Sul",
    "Conquistar na totalidade a America do Norte e a Africa", "Conquistar na totalidade a America do Norte e a Oceania",
    "Conquistar na totalidade a Europa, a Oceania e mais um continente a sua escolha",
    "Conquistar na totalidade a Europa, a America do Sul e mais um continente a sua escolha",
    "Conquistar 18 territórios com pelo menos 2 exércitos em cada"};

    private ObjectiveCardDeck() {}

    static ObjectiveCardDeck getInstance() {
        if (objectiveSingleton == null) {
            objectiveSingleton = new ObjectiveCardDeck();
        }
        return objectiveSingleton;
    }
    void nullCartas() {
		cards = null;
	}

    ObjectiveCard[] getCards() {
        return cards;
    }
    ObjectiveCard[] setCartasEmbaralhadas() {
        if (cards == null) {
            cards = new ObjectiveCard[8];
        }
        embaralhaCards();
        return cards;
    }

    ObjectiveCard[] setCards() {
        cards = new ObjectiveCard[8];
        return cards;
    }

    ObjectiveCard[] embaralhaCards() {
        List<ObjectiveCard> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
        cardList.toArray(cards);
        return cards;
    }
}

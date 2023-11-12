package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TerritoryCardDeck {
    private static TerritoryCard[] cards;
    private static TerritoryCardDeck territorySingleton;

    private static final String[] europeus = {"Espanha","Franca", "Itália", "Polonia", "Reino Unido", "Romenia", "Suecia", "Ucrania"};
    private static final String[] sulamericanos = {"Argentina", "Brasil", "Peru", "Venezuela"};
    private static final String[] asiaticos = {"Arábia Saudita", "Bangladesh", "Cazaquistao", "China",
    "Coreia do Norte", "Coreia do Sul", "Estonia", "India", "Ira", "Iraque", "Japao", "Jordania", "Letonia",
    "Mongolia", "Paquistao", "Russia", "Sibéria", "Síria", "Tailandia", "Turquia"};
    private static final String[] africanos = {"Africa do Sul", "Egito", "Madagascar", "Nigeria"};
    private static final String[] oceanicos = {"Australia", "Indonesia", "Nova Zelandia"};

    private TerritoryCardDeck() {
    	setCartas();
        embaralhaCards();
    }

    public static TerritoryCardDeck getInstance() {
        if (territorySingleton == null) {
            territorySingleton = new TerritoryCardDeck();
        }
        return territorySingleton;
    }

    private TerritoryCard[] setCartas() {
        ArrayList<TerritoryCard> cardList = new ArrayList<>();

        for (String territorio : europeus) {
            cardList.add(new TerritoryCard(territorio));
        }

        for (String territorio : sulamericanos) {
            cardList.add(new TerritoryCard(territorio));
        }

        for (String territorio : asiaticos) {
            cardList.add(new TerritoryCard(territorio));
        }

        for (String territorio : africanos) {
            cardList.add(new TerritoryCard(territorio));
        }

        for (String territorio : oceanicos) {
            cardList.add(new TerritoryCard(territorio));
        }

        cards = cardList.toArray(new TerritoryCard[0]);
        return cards;
    }

    public TerritoryCard[] getCards() {
        return cards;
    }

    public TerritoryCard[] setCartasEmbaralhadas() {
        if (cards == null) {
            setCartas();
        }
        embaralhaCards();
        return cards;
    }

    public TerritoryCard[] embaralhaCards() {
        List<TerritoryCard> cartaList = new ArrayList<>(Arrays.asList(cards));
        Collections.shuffle(cartaList);
        cards = cartaList.toArray(new TerritoryCard[0]);
        return cards;
    }
}

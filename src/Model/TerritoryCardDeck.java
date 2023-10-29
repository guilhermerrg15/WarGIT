package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TerritoryCardDeck {
    private static TerritoryCard[] cards;
    private static TerritoryCardDeck territorySingleton;

    private static final String[] europeus = {"Espanha","Franca", "Itália", "Polonia", "Reino Unido", "Romenia", "Suecia", "Ucrania"};
    private static final String[] americanos = {"Argentina", "Brasil", "Peru", "Venezuela"};
    private static final String[] asiaticos = {"Arábia Saudita", "Bangladesh", "Cazaquistao", "China",
    "Coreia do Norte", "Coreia do Sul", "Estonia", "India", "Ira", "Iraque", "Japao", "Jordania", "Letonia",
    "Mongolia", "Paquistao", "Russia", "Sibéria", "Síria", "Tailandia", "Turquia"};
    private static final String[] africanos = {"Africa do Sul", "Egito", "Madagascar", "Nigeria"};
    private static final String[] oceanicos = {"Australia", "Indonesia", "Nova Zelandia"};

    //
    private TerritoryCardDeck() {
    	cards = setCartas();
        embaralhaCards();
    }
    
    static TerritoryCardDeck getInstance() {
        if (territorySingleton == null) {
            territorySingleton = new TerritoryCardDeck();
        }
        return territorySingleton;
    }

    void nullCartas() {
        cards = null;
    }

    TerritoryCard[] getCards() {
        return cards;
    }
    
    TerritoryCard[] setCartasEmbaralhadas() {
        if (cards == null) {
        	setCartas();
            embaralhaCards();

        }
        return cards;
    }
    
    TerritoryCard[] setCartas() {
        cards = new TerritoryCard[44];
        return cards;
    }

    TerritoryCard[] embaralhaCards() {
        List <TerritoryCard> cartaList = Arrays.asList(cards);
        Collections.shuffle(cartaList);
        cartaList.toArray(cards);
        return cards;
    }

}
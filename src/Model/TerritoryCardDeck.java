package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Representa um baralho singleton contendo objetos TerritoryCard.
 */
class TerritoryCardDeck {
    private static TerritoryCard[] cards;
    private static TerritoryCardDeck territorySingleton;


    /**
     * Construtor privado para impor o padrão singleton e inicializar o baralho.
     */
    private TerritoryCardDeck() {
    	setCartas();
        embaralhaCards();
    }

    
    /**
     * Obtém a instância singleton de TerritoryCardDeck.
     *
     * @return A instância singleton.
     */
    public static TerritoryCardDeck getInstance() {
        if (territorySingleton == null) {
            territorySingleton = new TerritoryCardDeck();
        }
        return territorySingleton;
    }
    

    /**
     * Inicializa o baralho com objetos TerritoryCard.
     *
     * @return O array de objetos TerritoryCard.
     */
    private TerritoryCard[] setCartas() {
        ArrayList<TerritoryCard> cardList = new ArrayList<>();
        
        int idCounter = 0;
        
        cardList.add(new TerritoryCard("Africa do Sul", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Angola", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Argelia", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Egito", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Nigeria", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Somalia", "quadrado", idCounter++));
		
        cardList.add(new TerritoryCard("Alasca", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Calgary", "circulo", idCounter++));
        cardList.add(new TerritoryCard("California", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Groenlandia", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Mexico", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Nova Iorque", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Quebec", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Texas", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Vancouver", "triangulo", idCounter++));
		
		
        cardList.add(new TerritoryCard("Arabia Saudita", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Bangladesh", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Cazaquistao", "circulo", idCounter++));
        cardList.add(new TerritoryCard("China", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Coreia do Norte", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Coreia do Sul", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Estonia", "circulo", idCounter++));
        cardList.add(new TerritoryCard("India", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Ira", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Iraque", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Japao", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Jordania", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Letonia", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Mongolia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Paquistao", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Russia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Siberia", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Siria", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Tailandia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Turquia", "triangulo", idCounter++));
		
        cardList.add(new TerritoryCard("Argentina", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Brasil", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Peru", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Venezuela", "triangulo", idCounter++));
		
        cardList.add(new TerritoryCard("Espanha", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Franca", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Italia", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Polonia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Reino Unido", "circulo", idCounter++));
        cardList.add(new TerritoryCard("Romenia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Suecia", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Ucrania", "circulo", idCounter++));
		
        cardList.add(new TerritoryCard("Australia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Indonesia", "triangulo", idCounter++));
        cardList.add(new TerritoryCard("Nova Zelandia", "quadrado", idCounter++));
        cardList.add(new TerritoryCard("Perth", "circulo", idCounter++));
        
        

        cards = cardList.toArray(new TerritoryCard[0]);
        return cards;
    }

    /**
     * Obtém o array de objetos TerritoryCard.
     *
     * @return O array de objetos TerritoryCard.
     */
    public TerritoryCard[] getCards() {
        return cards;
    }

    /**
     * Inicializa e embaralha o baralho, retornando o array embaralhado.
     *
     * @return O array embaralhado de objetos TerritoryCard.
     */
    public TerritoryCard[] setCartasEmbaralhadas() {
        if (cards == null) {
            setCartas();
        }
        embaralhaCards();
        return cards;
    }

    /**
     * Embaralha o baralho.
     *
     * @return O array embaralhado de objetos TerritoryCard.
     */
    public TerritoryCard[] embaralhaCards() {
        List<TerritoryCard> cartaList = new ArrayList<>(Arrays.asList(cards));
        Collections.shuffle(cartaList);
        cards = cartaList.toArray(new TerritoryCard[0]);
        return cards;
    }
}

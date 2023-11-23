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
    private int maximumCards;

    /**
     * Construtor privado para impor o padrão singleton e inicializar o baralho.
     */
    private TerritoryCardDeck() {
    	setShuffleCards();
        maximumCards = 5;
    }
    

    /**
     * Inicializa o baralho com objetos TerritoryCard.
     *
     * @return O array de objetos TerritoryCard.
     */
    private TerritoryCard[] setCartas() {
        ArrayList<TerritoryCard> cardList = new ArrayList<>();
        
        cardList.add(new TerritoryCard("Africa do Sul", "triangulo"));
        cardList.add(new TerritoryCard("Angola", "quadrado"));
        cardList.add(new TerritoryCard("Argelia", "circulo"));
        cardList.add(new TerritoryCard("Egito", "triangulo"));
        cardList.add(new TerritoryCard("Nigeria", "circulo"));
        cardList.add(new TerritoryCard("Somalia", "quadrado"));
		
        cardList.add(new TerritoryCard("Alasca", "triangulo"));
        cardList.add(new TerritoryCard("Calgary", "circulo"));
        cardList.add(new TerritoryCard("California", "quadrado"));
        cardList.add(new TerritoryCard("Groenlandia", "circulo"));
        cardList.add(new TerritoryCard("Mexico", "quadrado"));
        cardList.add(new TerritoryCard("Nova Iorque", "quadrado"));
        cardList.add(new TerritoryCard("Quebec", "circulo"));
        cardList.add(new TerritoryCard("Texas", "triangulo"));
        cardList.add(new TerritoryCard("Vancouver", "triangulo"));
		
        cardList.add(new TerritoryCard("Arabia Saudita", "circulo"));
        cardList.add(new TerritoryCard("Bangladesh", "circulo"));
        cardList.add(new TerritoryCard("Cazaquistao", "circulo"));
        cardList.add(new TerritoryCard("China", "quadrado"));
        cardList.add(new TerritoryCard("Coreia do Norte", "quadrado"));
        cardList.add(new TerritoryCard("Coreia do Sul", "triangulo"));
        cardList.add(new TerritoryCard("Estonia", "circulo"));
        cardList.add(new TerritoryCard("India", "triangulo"));
        cardList.add(new TerritoryCard("Ira", "quadrado"));
        cardList.add(new TerritoryCard("Iraque", "triangulo"));
        cardList.add(new TerritoryCard("Japao", "circulo"));
        cardList.add(new TerritoryCard("Jordania", "quadrado"));
        cardList.add(new TerritoryCard("Letonia", "quadrado"));
        cardList.add(new TerritoryCard("Mongolia", "triangulo"));
        cardList.add(new TerritoryCard("Paquistao", "circulo"));
        cardList.add(new TerritoryCard("Russia", "triangulo"));
        cardList.add(new TerritoryCard("Siberia", "quadrado"));
        cardList.add(new TerritoryCard("Siria", "quadrado"));
        cardList.add(new TerritoryCard("Tailandia", "triangulo"));
        cardList.add(new TerritoryCard("Turquia", "triangulo"));
		
        cardList.add(new TerritoryCard("Argentina", "quadrado"));
        cardList.add(new TerritoryCard("Brasil", "circulo"));
        cardList.add(new TerritoryCard("Peru", "triangulo"));
        cardList.add(new TerritoryCard("Venezuela", "triangulo"));
		
        cardList.add(new TerritoryCard("Espanha", "circulo"));
        cardList.add(new TerritoryCard("Franca", "triangulo"));
        cardList.add(new TerritoryCard("Italia", "quadrado"));
        cardList.add(new TerritoryCard("Polonia", "triangulo"));
        cardList.add(new TerritoryCard("Reino Unido", "circulo"));
        cardList.add(new TerritoryCard("Romenia", "triangulo"));
        cardList.add(new TerritoryCard("Suecia", "quadrado"));
        cardList.add(new TerritoryCard("Ucrania", "circulo"));
		
        cardList.add(new TerritoryCard("Australia", "triangulo"));
        cardList.add(new TerritoryCard("Indonesia", "triangulo"));
        cardList.add(new TerritoryCard("Nova Zelandia", "quadrado"));
        cardList.add(new TerritoryCard("Perth", "circulo"));

        // Cartas coringa
        cardList.add(new TerritoryCard("Coringa 1", "coringa"));
        cardList.add(new TerritoryCard("Coringa 2", "coringa"));


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
    public TerritoryCard[] setShuffleCards() {
        if (cards == null) {
            setCartas();
        }
        shuffleCards();
        return cards;
    }

    /**
     * Embaralha o baralho.
     *
     * @return O array embaralhado de objetos TerritoryCard.
     */
    public TerritoryCard[] shuffleCards() {
        List<TerritoryCard> cartaList = new ArrayList<>(Arrays.asList(cards));
        Collections.shuffle(cartaList);
        cards = cartaList.toArray(new TerritoryCard[0]);
        return cards;
    }

    // Pegar uma carta após o fim de jogada com conquista
    public void pickRandomCard(Player player) {
        int count = 0;

        while(cards[count] == null) {
            count++;
        }
        player.addCard(cards[count]);
        cards[count] = null;
    }

    // Retorna uma carta ao deck 
    public void returnCard(TerritoryCard card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                cards[i] = card;
                return;  // Se encontrou uma posição nula, adicionou e saiu do método
            }
        }
    }

    //maximo de cartas por jogador 
    public int getMaximumCards() {
        return this.maximumCards;
    }

    // public int doCardTrade(){
    //     return 0;
    // }


    // public boolean evaluateCardTrade() {

    // }
}
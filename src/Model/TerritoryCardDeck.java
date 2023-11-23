package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Representa um baralho singleton contendo objetos TerritoryCard.
 */
class TerritoryCardDeck {
	private static List<TerritoryCard> cards;
    private int maximumCards;
    private int tradeCounter = 0;
	private static final int[] tradeBonusAmount = new int[] {
            4, 6, 8, 10, 12, 15
    };

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
    private List<TerritoryCard> setCartas() {
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


        return cardList;
    }

    /**
     * Obtém o array de objetos TerritoryCard.
     *
     * @return O array de objetos TerritoryCard.
     */
    public List<TerritoryCard> getCards() {
        return cards;
    }

    /**
     * Inicializa e embaralha o baralho, retornando o array embaralhado.
     *
     * @return O array embaralhado de objetos TerritoryCard.
     */
    public List<TerritoryCard> setShuffleCards() {
        if (cards == null) {
            cards = setCartas();
        }
        shuffleCards();
        return cards;
    }

    /**
     * Embaralha o baralho.
     *
     * @return O array embaralhado de objetos TerritoryCard.
     */
    public List<TerritoryCard> shuffleCards() {
        Collections.shuffle(cards);
        return cards;
    }

    // Pegar uma carta após o fim de jogada com conquista
    public void pickRandomCard(Player player) {
        int count = 0;

        while (cards.get(count) == null) {
            count++;
        }
        player.addCard(cards.get(count));
        cards.set(count, null);
    }

    // Retorna uma carta ao deck 
    public void returnCard(TerritoryCard card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == null) {
                cards.set(i, card);
                return;  // Se encontrou uma posição nula, adicionou e saiu do método
            }
        }
    }

    //maximo de cartas por jogador 
    public int getMaximumCards() {
        return this.maximumCards;
    }

    /**
     * Calcula o bônus de troca com base no contador de trocas.
     *
     * @return Bônus de troca calculado.
     */
	private int calculateTradeBonus() {
	    int bonus;
	    if (tradeCounter >= tradeBonusAmount.length) {
	        bonus = tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5;
	    } else {
	        bonus = tradeBonusAmount[tradeCounter];
	    }
	    return bonus;
	}


    public boolean evaluateCardTrade(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
        // Caso em que as 3 cartas possuem símbolos disintos
        if(card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape() && card2.getShape() != card3.getShape()) {
            return true;
        }
        
        // Caso de uma ou mais cartas serem coringas
        if(card1.getShape() == "Coringa") {
            // Caso em que as duas cartas restantes possuem o mesmo símbolo
            if(card2.getShape() == card3.getShape()) {
                return true;
            }

            // Caso em que a segunda ou a terceira carta seja Coringa
            if(card2.getShape() == "Coringa" || card3.getShape() == "Coringa") {
                return true;
            }            
        } else if(card2.getShape() == "Coringa") {
            if(card1.getShape() == card3.getShape()) {
                return true;
            }

            if(card1.getShape() == "Coringa" || card3.getShape() == "Coringa") {
                return true;
            }
        } else if(card1.getShape() == "Coringa") {
            if(card2.getShape() == card3.getShape()) {
                return true;
            }

            if(card2.getShape() == "Coringa" || card3.getShape() == "Coringa") {
                return true;
            }
        }
        
        // Caso em que as 3 cartas possuem o mesmo símbolo
        if(card1.getShape() == card2.getShape() && card2.getShape() == card3.getShape()) {
            return true;
        }
        return false;
    }
}
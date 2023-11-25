package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Representa o baralho de cartas de território.
 */
class TerritoryCardDeck {
	private static List<TerritoryCard> cards;
	private int maximumCards;
	private int tradeCounter = 0;
	private static final int[] tradeBonusAmount = new int[] { 4, 6, 8, 10, 12, 15 };

	
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

		cardList.add(new TerritoryCard("Africa do Sul", Shape.Triangle));
		cardList.add(new TerritoryCard("Angola", Shape.Square));
		cardList.add(new TerritoryCard("Argelia", Shape.Circle));
		cardList.add(new TerritoryCard("Egito", Shape.Triangle));
		cardList.add(new TerritoryCard("Nigeria", Shape.Circle));
		cardList.add(new TerritoryCard("Somalia", Shape.Square));

		cardList.add(new TerritoryCard("Alasca", Shape.Triangle));
		cardList.add(new TerritoryCard("Calgary", Shape.Circle));
		cardList.add(new TerritoryCard("California", Shape.Square));
		cardList.add(new TerritoryCard("Groenlandia", Shape.Circle));
		cardList.add(new TerritoryCard("Mexico", Shape.Square));
		cardList.add(new TerritoryCard("Nova Iorque", Shape.Square));
		cardList.add(new TerritoryCard("Quebec", Shape.Circle));
		cardList.add(new TerritoryCard("Texas", Shape.Triangle));
		cardList.add(new TerritoryCard("Vancouver", Shape.Triangle));

		cardList.add(new TerritoryCard("Arabia Saudita", Shape.Circle));
		cardList.add(new TerritoryCard("Bangladesh", Shape.Circle));
		cardList.add(new TerritoryCard("Cazaquistao", Shape.Circle));
		cardList.add(new TerritoryCard("China", Shape.Square));
		cardList.add(new TerritoryCard("Coreia do Norte", Shape.Square));
		cardList.add(new TerritoryCard("Coreia do Sul", Shape.Triangle));
		cardList.add(new TerritoryCard("Estonia", Shape.Circle));
		cardList.add(new TerritoryCard("India", Shape.Triangle));
		cardList.add(new TerritoryCard("Ira", Shape.Square));
		cardList.add(new TerritoryCard("Iraque", Shape.Triangle));
		cardList.add(new TerritoryCard("Japao", Shape.Circle));
		cardList.add(new TerritoryCard("Jordania", Shape.Square));
		cardList.add(new TerritoryCard("Letonia", Shape.Square));
		cardList.add(new TerritoryCard("Mongolia", Shape.Triangle));
		cardList.add(new TerritoryCard("Paquistao", Shape.Circle));
		cardList.add(new TerritoryCard("Russia", Shape.Triangle));
		cardList.add(new TerritoryCard("Siberia", Shape.Square));
		cardList.add(new TerritoryCard("Siria", Shape.Square));
		cardList.add(new TerritoryCard("Tailandia", Shape.Triangle));
		cardList.add(new TerritoryCard("Turquia", Shape.Triangle));

		cardList.add(new TerritoryCard("Argentina", Shape.Square));
		cardList.add(new TerritoryCard("Brasil", Shape.Circle));
		cardList.add(new TerritoryCard("Peru", Shape.Triangle));
		cardList.add(new TerritoryCard("Venezuela", Shape.Triangle));

		cardList.add(new TerritoryCard("Espanha", Shape.Circle));
		cardList.add(new TerritoryCard("Franca", Shape.Triangle));
		cardList.add(new TerritoryCard("Italia", Shape.Square));
		cardList.add(new TerritoryCard("Polonia", Shape.Triangle));
		cardList.add(new TerritoryCard("Reino Unido", Shape.Circle));
		cardList.add(new TerritoryCard("Romenia", Shape.Triangle));
		cardList.add(new TerritoryCard("Suecia", Shape.Square));
		cardList.add(new TerritoryCard("Ucrania", Shape.Circle));

		cardList.add(new TerritoryCard("Australia", Shape.Triangle));
		cardList.add(new TerritoryCard("Indonesia", Shape.Triangle));
		cardList.add(new TerritoryCard("Nova Zelandia", Shape.Square));
		cardList.add(new TerritoryCard("Perth", Shape.Circle));

		// Cartas coringa
		cardList.add(new TerritoryCard("Coringa 1", Shape.Joker));
		cardList.add(new TerritoryCard("Coringa 2", Shape.Joker));

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
				return; // Se encontrou uma posição nula, adicionou e saiu do método
			}
		}
	}

	// maximo de cartas por jogador
	public int getMaximumCards() {
		return this.maximumCards;
	}

	/**
	 * Calcula o bônus de troca com base no contador de trocas.
	 *
	 * @return Bônus de troca calculado.
	 */
	public int calculateTradeBonus() {
		int bonus;
		if (tradeCounter >= tradeBonusAmount.length) {
			bonus = tradeBonusAmount[tradeBonusAmount.length - 1] + (tradeCounter - tradeBonusAmount.length + 1) * 5;
		} else {
			bonus = tradeBonusAmount[tradeCounter];
		}
		return bonus;
	}

	// Verifica troca de cartas
	public boolean evaluateCardTrade(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3) {
		// Caso em que as 3 cartas possuem símbolos disintos
		if (card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape()
				&& card2.getShape() != card3.getShape()) {
			return true;
		}

		// Caso de uma ou mais cartas serem coringas
		if (card1.getShape() == Shape.Joker) {
			// Caso em que as duas cartas restantes possuem o mesmo símbolo
			if (card2.getShape() == card3.getShape()) {
				return true;
			}

			// Caso em que a segunda ou a terceira carta seja Coringa
			if (card2.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		} else if (card2.getShape() == Shape.Joker) {
			if (card1.getShape() == card3.getShape()) {
				return true;
			}

			if (card1.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		} else if (card1.getShape() == Shape.Joker) {
			if (card2.getShape() == card3.getShape()) {
				return true;
			}

			if (card2.getShape() == Shape.Joker || card3.getShape() == Shape.Joker) {
				return true;
			}
		}

		// Caso em que as 3 cartas possuem o mesmo símbolo
		if (card1.getShape() == card2.getShape() && card2.getShape() == card3.getShape()) {
			return true;
		}
		return false;
	}
}
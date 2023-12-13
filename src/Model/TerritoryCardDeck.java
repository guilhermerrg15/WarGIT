package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa o baralho de cartas de território.
 */
class TerritoryCardDeck {
	private static List<TerritoryCard> cards;
	private int maximumCards;

	
	public TerritoryCardDeck() {
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

		cardList.add(new TerritoryCard("África do Sul", Shape.Triangle, "af", "africadosul"));
		cardList.add(new TerritoryCard("Angola", Shape.Square, "af", "angola"));
		cardList.add(new TerritoryCard("Argélia", Shape.Circle, "af", "argelia"));
		cardList.add(new TerritoryCard("Egito", Shape.Triangle, "af", "egito"));
		cardList.add(new TerritoryCard("Nigéria", Shape.Circle, "af", "nigeria"));
		cardList.add(new TerritoryCard("Somália", Shape.Square, "af", "somalia"));

		cardList.add(new TerritoryCard("Alasca", Shape.Triangle, "an", "alasca"));
		cardList.add(new TerritoryCard("Calgary", Shape.Circle, "an", "calgary"));
		cardList.add(new TerritoryCard("Califórnia", Shape.Square, "an", "california"));
		cardList.add(new TerritoryCard("Groelândia", Shape.Circle, "an", "groelandia"));
		cardList.add(new TerritoryCard("México", Shape.Square, "an", "mexico"));
		cardList.add(new TerritoryCard("Nova York", Shape.Square, "an", "novayork"));
		cardList.add(new TerritoryCard("Quebec", Shape.Circle, "an", "quebec"));
		cardList.add(new TerritoryCard("Texas", Shape.Triangle, "an", "texas"));
		cardList.add(new TerritoryCard("Vancouver", Shape.Triangle, "an", "vancouver"));

		cardList.add(new TerritoryCard("Arábia Saudita", Shape.Circle, "as", "arabiasaudita"));
		cardList.add(new TerritoryCard("Bangladesh", Shape.Circle, "as", "bangladesh"));
		cardList.add(new TerritoryCard("Cazaquistão", Shape.Circle, "as", "cazaquistao"));
		cardList.add(new TerritoryCard("China", Shape.Square, "as", "china"));
		cardList.add(new TerritoryCard("Coreia do Norte", Shape.Square, "as", "coreiadonorte"));
		cardList.add(new TerritoryCard("Coreia do Sul", Shape.Triangle, "as", "coreiadosul"));
		cardList.add(new TerritoryCard("Estônia", Shape.Circle, "as", "estonia"));
		cardList.add(new TerritoryCard("Índia", Shape.Triangle, "as", "india"));
		cardList.add(new TerritoryCard("Irã", Shape.Square, "as", "ira"));
		cardList.add(new TerritoryCard("Iraque", Shape.Triangle, "as", "iraque"));
		cardList.add(new TerritoryCard("Japão", Shape.Circle, "as", "japao"));
		cardList.add(new TerritoryCard("Jordânia", Shape.Square, "as", "jordania"));
		cardList.add(new TerritoryCard("Letônia", Shape.Square, "as", "letonia"));
		cardList.add(new TerritoryCard("Mongólia", Shape.Triangle, "as", "mongolia"));
		cardList.add(new TerritoryCard("Paquistão", Shape.Circle, "as", "paquistao"));
		cardList.add(new TerritoryCard("Rússia", Shape.Triangle, "as", "russia"));
		cardList.add(new TerritoryCard("Sibéria", Shape.Square, "as", "siberia"));
		cardList.add(new TerritoryCard("Síria", Shape.Square, "as", "siria"));
		cardList.add(new TerritoryCard("Tailândia", Shape.Triangle, "as", "tailandia"));
		cardList.add(new TerritoryCard("Turquia", Shape.Triangle, "as", "turquia"));

		cardList.add(new TerritoryCard("Argentina", Shape.Square, "asl", "argentina"));
		cardList.add(new TerritoryCard("Brasil", Shape.Circle, "asl", "brasil"));
		cardList.add(new TerritoryCard("Peru", Shape.Triangle, "asl", "peru"));
		cardList.add(new TerritoryCard("Venezuela", Shape.Triangle, "asl", "venezuela"));

		cardList.add(new TerritoryCard("Espanha", Shape.Circle, "eu", "espanha"));
		cardList.add(new TerritoryCard("França", Shape.Triangle, "eu", "franca"));
		cardList.add(new TerritoryCard("Itália", Shape.Square, "eu", "italia"));
		cardList.add(new TerritoryCard("Polônia", Shape.Triangle, "eu", "polonia"));
		cardList.add(new TerritoryCard("Reino Unido", Shape.Circle, "eu", "reinounido"));
		cardList.add(new TerritoryCard("Romênia", Shape.Triangle, "eu", "romenia"));
		cardList.add(new TerritoryCard("Suécia", Shape.Square, "eu", "suecia"));
		cardList.add(new TerritoryCard("Ucrânia", Shape.Circle, "eu", "ucrania"));

		cardList.add(new TerritoryCard("Austrália", Shape.Triangle, "oc", "australia"));
		cardList.add(new TerritoryCard("Indonésia", Shape.Triangle, "oc", "indonesia"));
		cardList.add(new TerritoryCard("Nova Zelândia", Shape.Square, "oc", "novazelandia"));
		cardList.add(new TerritoryCard("Perth", Shape.Circle, "oc", "perth"));

		// Cartas coringa
		cardList.add(new TerritoryCard("Coringa 1", Shape.Coringa, null, "coringa"));
		cardList.add(new TerritoryCard("Coringa 2", Shape.Coringa, null, "coringa"));
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
		shuffleCards();
		TerritoryCard pickedCard = cards.get(0);

		// System.err.println(pickedCard.getName());
		if (pickedCard != null){
			player.addCard(pickedCard);
			cards.remove(0);
		}

		// System.err.println(pickedCard);
	}

	// Retorna uma carta ao deck
	public void returnCard(TerritoryCard card) {
		cards.add(card);
	}

	// maximo de cartas por jogador
	public int getMaximumCards() {
		return this.maximumCards;
	}

	
}
package Model;
//import java.util.ArrayList;
//import java.util.List;

public class Player {
    private String name;
    private PlayerColor color;
//    private List<Objective> objectives;
//    private List<Card> cards;
//    private List<Territory> territories;

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
//        this.objectives = new ArrayList<>();
//        this.cards = new ArrayList<>();
//        this.territories = new ArrayList<>();
    }

    // Métodos para adicionar objetivos, cartas e territórios
//    public void addObjective(Objective objective) {
//        objectives.add(objective);
//    }

//    public void addCard(Card card) {
//        cards.add(card);
//    }

//    public void addTerritory(Territory territory) {
//        territories.add(territory);
//    }

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }
}

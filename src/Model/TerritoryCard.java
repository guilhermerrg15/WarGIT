package Model;

import java.util.Hashtable;

public class TerritoryCard {
    String name;
    Shape geometricShape; 
    private int id;
    Territory territory;

    public TerritoryCard(String name, Shape geometricShape) {
         this.id = id;
        this.name = name;
        this.geometricShape = geometricShape;
    }


    void setTerritory(Territory territory) {
        this.territory = territory;
    }

    static Territory retornaTerritory(TerritoryCard[] cards, String carta) {
        for (TerritoryCard cart : cards) {
            if (cart.name.equals(carta)) {
                return cart.territory;
            }
        }
        return null;
    }

    static Integer idCarta(String carta) {
        Hashtable<String, Integer> nomeImagens = new Hashtable<String, Integer>();
        nomeImagens.put("Africa do Sul", 0);
        nomeImagens.put("Angola", 1);
        nomeImagens.put("Argélia", 2);
        nomeImagens.put("Egito", 3);
        nomeImagens.put("Nigéria", 4);
        nomeImagens.put("Somália", 5);
        nomeImagens.put("Alasca", 6);
        nomeImagens.put("Calgary", 7);
        nomeImagens.put("Califórnia", 8);
        nomeImagens.put("Groelandia", 9);
        nomeImagens.put("México", 10);
        nomeImagens.put("Nova York", 11);
        nomeImagens.put("Quebec", 12);
        nomeImagens.put("Texas", 13);
        nomeImagens.put("Vancouver", 14);
        nomeImagens.put("Arábia Saudita", 15);
        nomeImagens.put("Bangladesh", 16);
        nomeImagens.put("Cazaquistao", 17);
        nomeImagens.put("China", 18);
        nomeImagens.put("Coreia do Norte", 19);
        nomeImagens.put("Coreia do Sul", 20);
        nomeImagens.put("Estonia", 21);
        nomeImagens.put("India", 22);
        nomeImagens.put("Ira", 23);
        nomeImagens.put("Iraque", 24);
        nomeImagens.put("Japao", 25);
        nomeImagens.put("Jordania", 26);
        nomeImagens.put("Letonia", 27);
        nomeImagens.put("Mongolia", 28);
        nomeImagens.put("Paquistao", 29);
        nomeImagens.put("Russia", 30);
        nomeImagens.put("Sibéria", 31);
        nomeImagens.put("Síria", 32);
        nomeImagens.put("Tailandia", 33);
        nomeImagens.put("Turquia", 34);
        nomeImagens.put("Argentina", 35);
        nomeImagens.put("Brasil", 36);
        nomeImagens.put("Peru", 37);
        nomeImagens.put("Venezuela", 38);
        nomeImagens.put("Espanha", 39);
        nomeImagens.put("Franca", 40);
        nomeImagens.put("Itália", 41);
        nomeImagens.put("Polonia", 42);
        nomeImagens.put("Reino Unido", 43);
        nomeImagens.put("Romenia", 44);
        nomeImagens.put("Suecia", 45);
        nomeImagens.put("Ucrania", 46);
        nomeImagens.put("Austrália", 47);
        nomeImagens.put("Indonesia", 48);
        nomeImagens.put("Nova Zelândia", 49);
        nomeImagens.put("Coringa", 47);
        
        return nomeImagens.get(carta);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Shape getShape() {
        return geometricShape;
    }

    public Territory getTerritory() {
        return territory;
    }


    @Override
    public String toString() {
        return name + " (" + geometricShape + ")";
    }
}



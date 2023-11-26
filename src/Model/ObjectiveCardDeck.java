
package Model;

import java.util.*;
/**
 * Uma lista com todos os objetivos do jogo.
 */
class ObjectiveCardDeck {
	private List<ObjectiveCard> objectiveCards;
	private Map map;
    
    public ObjectiveCardDeck(Map map, List<Player> todos_jogadores) {
    	this.map = map;
        this.objectiveCards = new ArrayList<>(); 
    	
    	//cartas de destruir oponente
    	objectiveCards.add(new DestroyOpponentObjectiveCard("Objetivo1", "azul"));
        objectiveCards.add(new DestroyOpponentObjectiveCard("Objetiv2", "verde"));
        objectiveCards.add(new DestroyOpponentObjectiveCard("Objetivo3", "vermelho"));
        objectiveCards.add(new DestroyOpponentObjectiveCard("Objetivo4", "branco"));
        objectiveCards.add(new DestroyOpponentObjectiveCard("Objetivo5", "preto"));
        objectiveCards.add(new DestroyOpponentObjectiveCard("Objetivo6", "amarelo"));
    	
    	//cartas de conquistar 2 continentes
    	objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo11", map.findContinent("North America"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo9", map.findContinent("Asia"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo12", map.findContinent("North America"), map.findContinent("Oceania")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo10", map.findContinent("Asia"), map.findContinent("South America")));
        
        //cartas de conquistar 3 continentes
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "Objetivo14", map.findContinent("Europe"), map.findContinent("South America")));
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "Objetivo13", map.findContinent("Europe"), map.findContinent("Oceania")));
    	
        //carta para conquistar 18 territorios com 2 exercitos em cada
        objectiveCards.add(new Conquer18TerritoriesObjectiveCard("Objetivo8"));
        
      //carta para conquistar 24 territorios 
        objectiveCards.add(new Conquer24TerritoriesObjectiveCard("Objetivo7"));
    }
    
    public void shuffleObjective(Player player){
    	Random random = new Random();
    	ObjectiveCard objetive = objectiveCards.get(random.nextInt(objectiveCards.size()));

        player.receiveObjective(objetive);
        player.getObjective().checkOwner(player);
    	objectiveCards.remove(player.getObjective());
    }
    
    public ObjectiveCard getObjetive(int index) {
    	return objectiveCards.get(index-1);
    }
    
    public void returnObjectiveCard(ObjectiveCard objetivo) {
        objectiveCards.add(objetivo);
    }
}


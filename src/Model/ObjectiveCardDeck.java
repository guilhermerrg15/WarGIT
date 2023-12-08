
package Model;

import java.util.*;
/**
 * Uma lista com todos os objetivos do jogo.
 */
public class ObjectiveCardDeck {
	private List<ObjectiveCard> objectiveCards;
	private Map map;
    private HashMap<PlayerColor, String> colorToObjectiveMapping; 
    
    public ObjectiveCardDeck(Map map, List<Player> todos_jogadores) {
    	this.map = map;
        this.objectiveCards = new ArrayList<>(); 
        this.colorToObjectiveMapping = new HashMap<>();

        // Mapeamento automático de cor para objetivo
        colorToObjectiveMapping.put(PlayerColor.BLUE, "objetivo1");
        colorToObjectiveMapping.put(PlayerColor.GREEN, "objetivo2");
        colorToObjectiveMapping.put(PlayerColor.RED, "objetivo3");
        colorToObjectiveMapping.put(PlayerColor.WHITE, "objetivo4");
        colorToObjectiveMapping.put(PlayerColor.BLACK, "objetivo5");
        colorToObjectiveMapping.put(PlayerColor.YELLOW, "objetivo6");

        for (Player player : todos_jogadores) {
            String objectiveName = colorToObjectiveMapping.get(player.getColor());
            if (objectiveName != null) {
                objectiveCards.add(new DestroyOpponentObjectiveCard(objectiveName, player.getColor().toString(), player));
            }
        }
    	
    	// //cartas de destruir oponente
    	// objectiveCards.add(new DestroyOpponentObjectiveCard("objetivo1", "azul"));
        // objectiveCards.add(new DestroyOpponentObjectiveCard("objetiv2", "verde"));
        // objectiveCards.add(new DestroyOpponentObjectiveCard("objetivo3", "vermelho"));
        // objectiveCards.add(new DestroyOpponentObjectiveCard("objetivo4", "branco"));
        // objectiveCards.add(new DestroyOpponentObjectiveCard("objetivo5", "preto"));
        // objectiveCards.add(new DestroyOpponentObjectiveCard("objetivo6", "amarelo"));
    	
    	//cartas de conquistar 2 continentes
    	objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "objetivo11", map.findContinent("North America"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "objetivo9", map.findContinent("Asia"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "objetivo12", map.findContinent("North America"), map.findContinent("Oceania")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "objetivo10", map.findContinent("Asia"), map.findContinent("South America")));
        
        //cartas de conquistar 3 continentes
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "objetivo14", map.findContinent("Europe"), map.findContinent("South America")));
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "objetivo13", map.findContinent("Europe"), map.findContinent("Oceania")));
    	
        //carta para conquistar 18 territorios com 2 exercitos em cada
        objectiveCards.add(new Conquer18TerritoriesObjectiveCard("objetivo8"));
        
      //carta para conquistar 24 territorios 
        objectiveCards.add(new Conquer24TerritoriesObjectiveCard("objetivo7"));
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


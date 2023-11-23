
package Model;

import java.util.*;

/**
 * Uma lista com todos os objetivos do jogo.
 */
class ObjectiveCardDeck {
	private List<Object> objectiveCards;
	private Map map;
    
    public ObjectiveCardDeck(Map map, List<Player> todos_jogadores) {
    	
    	this.map = map;
        this.objectiveCards = new ArrayList<>();
    	
    	//cartas de destruir oponente
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 1", "azul"));
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 2", "amarelo"));
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 3", "branco"));
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 4", "verde"));
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 5", "preto"));
    	objectiveCards.add(new DestroyOpponentObjectiveCard(todos_jogadores,"Objetivo 6", "vermelho"));
    	
    	//cartas de conquistar 2 continentes
    	objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo 7", map.findContinent("North America"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo 8", map.findContinent("Asia"), map.findContinent("Africa")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo 9", map.findContinent("North America"), map.findContinent("Oceania")));
        objectiveCards.add(new ConquerTwoContinentsObjectiveCard( "Objetivo 10", map.findContinent("Asia"), map.findContinent("South America")));
        
        //cartas de conquistar 3 continentes
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "Objetivo 11", map.findContinent("Europe"), map.findContinent("South America"), map));
        objectiveCards.add(new ConquerThreeContinentsObjectiveCard( "Objetivo 12", map.findContinent("Europe"), map.findContinent("Oceania"), map));
    	
        //carta para conquistar 18 territorios com 2 exercitos em cada
        objectiveCards.add(new Conquer18TerritoriesObjectiveCard("Objetivo 13"));
        
      //carta para conquistar 24 territorios 
        objectiveCards.add(new Conquer24TerritoriesObjectiveCard("Objetivo 14"));
    }
    public void sorteia_objetivo(Player player){
    	Random rand = new Random();
    	Object objetivoSorteado = objectiveCards.get(rand.nextInt(objectiveCards.size()));
    	
    	if (objetivoSorteado instanceof DestroyOpponentObjectiveCard) {
            player.recebe_objetivo((DestroyOpponentObjectiveCard) objetivoSorteado);
    	} else if (objetivoSorteado instanceof ConquerTwoContinentsObjectiveCard) {
            player.recebe_objetivo((ConquerTwoContinentsObjectiveCard) objetivoSorteado);
        }else if (objetivoSorteado instanceof ConquerThreeContinentsObjectiveCard) {
            player.recebe_objetivo((ConquerThreeContinentsObjectiveCard) objetivoSorteado);
        }else if (objetivoSorteado instanceof Conquer18TerritoriesObjectiveCard) {
            player.recebe_objetivo((Conquer18TerritoriesObjectiveCard) objetivoSorteado);
        }else if (objetivoSorteado instanceof Conquer24TerritoriesObjectiveCard) {
            player.recebe_objetivo((Conquer24TerritoriesObjectiveCard) objetivoSorteado);
        }
            
            
    	player.get_objetivo().ganha_dono(player);
    	objetiveCards.remove(player.get_objetivo());
    }
    
    public String getName(int index) {
        if (index >= 1 && index <= objectiveCards.size()) {
            Object objetivo = objectiveCards.get(index - 1);

            if (objetivo instanceof DestroyOpponentObjectiveCard) {
                return ((DestroyOpponentObjectiveCard) objetivo).getName();
            } else if (objetivo instanceof ConquerTwoContinentsObjectiveCard) {
                return ((ConquerTwoContinentsObjectiveCard) objetivo).getName();
            } else if (objetivo instanceof ConquerThreeContinentsObjectiveCard) {
                return ((ConquerThreeContinentsObjectiveCard) objetivo).getName();
            } else if (objetivo instanceof Conquer18TerritoriesObjectiveCard) {
                return ((Conquer18TerritoriesObjectiveCard) objetivo).getName();
            } else if (objetivo instanceof Conquer24TerritoriesObjectiveCard) {
                return ((Conquer24TerritoriesObjectiveCard) objetivo).getName();
            }
        }

        // Retornar null indicando que o índice é inválido
        return null;
    }
    
    public void objetivoRetornaDeck(Object objetivo) {
        objectiveCards.add(objetivo);
    }
    
    public Object getObjetivo(int index) {
        return objectiveCards.get(index - 1);
    }
}

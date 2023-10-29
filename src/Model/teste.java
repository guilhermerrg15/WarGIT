package Model;
//import View.JanelaInicial;
import View.ObjectiveCardsView;
import View.TerritoryCardsView;

import javax.swing.JFrame;

public class teste {
	public static void main(String[] args) throws Exception{
		JFrame test = new JFrame("Visualização de Objetivo");

        String imagePath = "resources/imagens/war_carta_objetivo_grande.png"; 
        ObjectiveCardsView objectiveView = new ObjectiveCardsView(imagePath); 

        test.add(objectiveView);
        test.setVisible(true);
		
		
		
		System.out.println("Hello World!!!");
		
//		JFrame test = new TerritoryCardsView();
//		test.setVisible(true);
	}
}


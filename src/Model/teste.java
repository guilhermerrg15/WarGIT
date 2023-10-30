package Model;
import View.JanelaInicial;
import View.ObjectiveCardsView;
import View.TerritoryCardsView;
import View.JanelaSelecaoJogadores;
import View.NotificationView;
import View.Map;

import java.io.IOException;

import javax.swing.JFrame;

public class teste {
	public static void main(String[] args) throws Exception{

		System.out.println("Hello World!!!");
		

		
		
//		String nomeVencedor = "Jogador1"; // Substitua pelo nome do vencedor
//		PlayerColor corVencedor = PlayerColor.RED; // Substitua pela cor do vencedor
//
//		NotificationView gameView = new NotificationView();
//		gameView.displayWinnerMessage(nomeVencedor, corVencedor);
//
//		boolean continuar = gameView.askToContinueGame();
//
//		if (continuar) {
//		    // Reiniciar o jogo ou realizar outras ações conforme necessário
//		    // Exemplo: reconfigurar o tabuleiro ou iniciar um novo jogo
//		} else {
//		    // Encerrar o programa
//		}
		
//		JFrame test = new JanelaSelecaoJogadores();
//		test.setVisible(true);
		
//		JFrame frame = new JFrame("Visualização de Objetivo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(350, 602);
//
//        String imagePath = "resources/imagens/war_carta_objetivo_grande.png"; 
//        ObjectiveCardsView objectiveView = new ObjectiveCardsView(imagePath); 
//
//        frame.add(objectiveView);
//        frame.setVisible(true);
		
		
//		
		JFrame test = new TerritoryCardsView();
		test.setVisible(true);
		
	}
}


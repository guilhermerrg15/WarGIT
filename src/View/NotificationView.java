package View;

import javax.swing.JOptionPane;

import Model.PlayerColor;

public class NotificationView {
	public void displayWinnerMessage(String winnerName, PlayerColor winnerColor) {
        String message = "O vencedor é " + winnerName + " de cor " + winnerColor + "!";
        JOptionPane.showMessageDialog(null, message, "Vitória", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean askToContinueGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Continuar?",
                JOptionPane.YES_NO_OPTION);

        return choice == JOptionPane.YES_OPTION;
    }
	
	
}






//chamá-la no momento apropriado no seu código. Por exemplo, após o término da partida, você pode exibir o vencedor 
//e perguntar se os jogadores desejam continuar

//precisará chamar esses métodos no ponto apropriado do  código, 
//provavelmente após a verificação do cumprimento do objetivo por um jogador ou em qualquer 
//outro ponto onde a partida termine e  precise perguntar aos jogadores se desejam continuar ou encerrar o programa.
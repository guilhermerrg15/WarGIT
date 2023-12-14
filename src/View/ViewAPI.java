package View;


import javax.swing.JOptionPane;

import Controller.APIController;
import Model.PlayerColor;

public class ViewAPI {
    private static ViewAPI apiInstance = null;

	String[] nomesJogadores = new String[6];

    StartView start = StartView.getStartView();

    public void showWarning(String warn){
		JOptionPane.showMessageDialog(null, warn);
	}

    public void determineFirstPlayer(String nome, PlayerColor cor){
		MapView.getMapView().determineFirstPlayer(nome, cor);
	}
    
	public void changePlayer(String player, PlayerColor color){
		MapView.getMapView().changePlayer(player, color);
	}

	public void updateAttackers(String[] atacantes){
		MapView.getMapView().updateAttackers(atacantes);
	}

	public void updateDefenders(String[] defensores){
		MapView.getMapView().updateDefenders(defensores);
	}

    public void updateReplacement(String[] territories) {
        MapView.getMapView().updateReplacement(territories);
    }

    public void updateNumReplacement(Integer num){
		MapView.getMapView().updateNumReplacement(num);
	}

    public void updateDestiny(String[] destinies){
		MapView.getMapView().updateDestiny(destinies);
	}

    public void updateBonusTrade(int bonus) {
        MapView.getMapView().updateBonusTrade(bonus);
    }

    public void setFirstRound(boolean first){
        MapView.getMapView().setFirstRound(first);
    }
    private ViewAPI() {}

    public void clearDices(){
        MapView.getMapView().clearDices();
    }

	public void playerWon(String name, PlayerColor color){
		String colorName;

		switch (color) {
			case AMARELO:
                colorName = "Amarelo";
                break;
            case AZUL:
                colorName = "Azul";
                break;
            case BRANCO:
                colorName = "Branco";
                break;               
            case PRETO:
                colorName = "Preto";
                break;
            case VERMELHO:
                colorName = "Vermelho";
            case VERDE:
                colorName = "Verde";
                break;
            default:
                colorName = "Preto";
                break;
		
        }
		showWin(name, colorName);
	}
    

    public static ViewAPI getInstance() {
        if (apiInstance == null) {
            apiInstance = new ViewAPI();
        }
        return apiInstance;
    }

    public Observer getObserver() {
        return MapView.getMapView();
    }

    public void showWin(String nome, String cor) {
        JOptionPane.showMessageDialog(null, nome + " de cor " + cor + " ganhou o jogo! ", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			APIController.getInstance().restartGame();
		} else {
			System.exit(0);
		}
    }

}

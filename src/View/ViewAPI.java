package View;


import javax.swing.JOptionPane;

import Controller.APIController;
import Model.PlayerColor;

// import Controller.TabuleiroObservador;

public class ViewAPI {
    private static ViewAPI apiInstance = null;

    // Array de nomes dos jogadores
	String[] nomesJogadores = new String[6];

    // Singleton
    StartView start = StartView.getStartView();

    // Pegar primeiro jogador
    public void determineFirstPlayer(String nome, PlayerColor cor){
		MapView.getMapView().determineFirstPlayer(nome, cor);
	}
    //Muda o jogador atual
	public void mudaJogador(String player, PlayerColor color){
		MapView.getMapView().changePlayer(player, color);
	}

    //Atalixa os territorios atancantes no painel de ataque
	public void atualizaAtacantes(String[] atacantes){
		MapView.getMapView().updateAttackers(atacantes);
	}

    //Atualiza os territorios defensores no painel de ataque
	public void atualizaDefensores(String[] defensores){
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

    public void updateBonusTroca(int bonus) {
        MapView.getMapView().updateBonusTroca(bonus);
    }

    public void setFirstRound(boolean first){
        MapView.getMapView().setFirstRound(first);
    }
    private ViewAPI() {
    }

    public void clearDices(){
        MapView.getMapView().clearDices();
    }


	//Verifica se o jogador ganhou a partida
	public void jogadorGanhou(String nome, PlayerColor cor){
		String nomeCor;

		//Verifica a cor do jogador
		switch (cor) {
			case AMARELO:
                nomeCor = "Amarelo";
                break;
            case AZUL:
                nomeCor = "Azul";
                break;
            case BRANCO:
                nomeCor = "Branco";
                break;               
            case PRETO:
                nomeCor = "Preto";
                break;
            case VERMELHO:
                nomeCor = "Vermelho";
            case VERDE:
                nomeCor = "Verde";
                break;
            default:
                nomeCor = "Preto";
                break;
		
        }
		showWin(nome, nomeCor);
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

    //Retorna os nomes dos jogadores
	public String[] getNomesJogadores(){
		return nomesJogadores;
	}

    public void showWin(String nome, String cor) {
        JOptionPane.showMessageDialog(null, nome + " de cor " + cor + " ganhou o jogo! ", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			APIController.getInstance().reiniciarJogo();
		} else {
			System.exit(0);
		}
    }

}

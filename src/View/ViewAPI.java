package View;


import javax.swing.JOptionPane;

import Controller.APIController;
import Model.PlayerColor;
import java.awt.Color;
import java.util.ArrayList;

// import Controller.TabuleiroObservador;

public class ViewAPI {
    private static ViewAPI apiInstance = null;
    private String imagesPath = "resources/imagens";

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
        //
        //colocar metodo aqui se quiser remover combo boxes de ataque ->>
        //
        MapView.getMapView().updateReplacement(territories);
    }

    public void updateNumReplacement(Integer num){
		MapView.getMapView().updateNumReplacement(num);
	}

    public void updateDestiny(String[] destinies){
		MapView.getMapView().updateDestiny(destinies);
	}

    private ViewAPI() {
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

        JOptionPane.showMessageDialog(null, nome + "de cor" + cor + "ganhou o jogo!", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			APIController.getInstance().reiniciarJogo();
		} else {
			System.exit(0);
		}
    }

}

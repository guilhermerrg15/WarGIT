package View;


import javax.swing.JOptionPane;

import Controller.APIController;
import Model.PlayerColor;
import java.awt.Color;

// import Controller.TabuleiroObservador;

public class ViewAPI {
    private static ViewAPI apiInstance = null;
    private String imagesPath = "resources/imagens";

    // Array de nomes dos jogadores
	String[] nomesJogadores = new String[6];

    // Singleton
    StartView start = StartView.getStartView();

    // Pegar primeiro jogador
    public void determinaPrimeiroJogador(String nome, PlayerColor cor){
		MapView.getMapView().determinaPrimeiroJogador(nome, cor);
	}
    //Muda o jogador atual
	public void mudaJogador(String jogador, PlayerColor cor){
		MapView.getMapView().mudaJogador(jogador, cor);
	}

    //Atalixa os territorios atancantes no painel de ataque
	public void atualizaAtacantes(String[] atacantes){
		// MapView.getMapView().mudaParaAtaque();
		MapView.getMapView().atualizaAtacantes(atacantes);
	}

    //Atualiza os territorios defensores no painel de ataque
	public void atualizaDefensores(String[] defensores){
		MapView.getMapView().atualizaDefensores(defensores);
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

package View;


import javax.swing.JOptionPane;

import Model.PlayerColor;

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

    private ViewAPI() {
    }

    public void exibirNomeTerritorio(String territorioNome, PlayerColor corDoTerritorio) {
        // Converte a cor do território para um formato mais legível
        String corFormatada = corDoTerritorio.toString().toLowerCase();
    
        // Exibe um JOptionPane com o nome e a cor do território
        JOptionPane.showMessageDialog(null, "Território: " + territorioNome + "\nCor: " + corFormatada, "Informação do Território", JOptionPane.INFORMATION_MESSAGE);
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

    public void showWin(String nome) {

        JOptionPane.showMessageDialog(null, nome + " ganhou o jogo!", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			// APIController.getInstance().reiniciarJogo();
		} else {
			System.exit(0);
		}
    }

    // public void initGame() {
    //     new JanelaInicial();
    // }

    // public String getImagesPath() {
    //     return this.imagesPath;
    // }

    // public void redraw() {
    //     // TODO
    // }
    // public TabuleiroObservador getTabuleiroObservador() {
    //     return null;
    }

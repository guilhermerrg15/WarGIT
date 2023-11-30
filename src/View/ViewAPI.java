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

    private ViewAPI() {
    }

    public void exibirNomeTerritorio(String territorioNome) {
        // Exibe um JOptionPane com o nome do território
        JOptionPane.showMessageDialog(null, "Território: " + territorioNome, "Informação do Território", JOptionPane.INFORMATION_MESSAGE);
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

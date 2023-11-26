package View;

import java.awt.Color;

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

    public ViewAPI() {
    }

    public static ViewAPI getInstance() {
        if (apiInstance == null) {
            apiInstance = new ViewAPI();
        }
        return apiInstance;
    }

    //Retorna os nomes dos jogadores
	public String[] getNomesJogadores(){
		return nomesJogadores;
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

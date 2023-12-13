package View;

import javax.swing.*;

import Controller.APIController;
public class Window extends JFrame {
    // Telas do jogo
    private static Window window = null;
    private StartView startView = StartView.getStartView();
    private PlayerConfig playerConfig = PlayerConfig.getPlayerConfig();
    private NumPlayersView numPlayersView = NumPlayersView.getNumPlayersView();
    private MapView mapView = MapView.getMapView();
    APIController controller = APIController.getInstance();

    // Construtor
    private Window(){
        // Definir título e tamanho da janela
        setTitle("War");
        setSize(1500, 840);

        // Exibir a primeira tela
        setVisible(true);
        getContentPane().add(startView);
    }

    // Singleton
    public static Window getWindow() {
        if (window == null) {
            window = new Window();
        }

        return window;
    }

    // Direcionamento entre os frames (tela inicial)
    public void goToCsPanel() {
        startView.setVisible(false); // Esconde o painel inicial
        getContentPane().add(numPlayersView); // Adiciona o painel de seleção de jogadores
    }

    // Fechar tela inicial e exibir tela de configuração de jogadores
	public void configurePlayers() {
        startView.setVisible(false);
		numPlayersView.setVisible(false);
		getContentPane().add(playerConfig);
	}

    public void configureMap() {
        APIController.getInstance().showObjCards();
        APIController.getInstance().initTerritoryDeck();
        startView.setVisible(false);
        numPlayersView.setVisible(false);
        playerConfig.setVisible(false);
        getContentPane().add(mapView);
    }
}

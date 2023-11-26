package View;

import javax.swing.*;
import Controller.TratadorSelecao;
// import Model.Game;
// import Model.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerConfig extends JPanel {
    private String imagePath = "resources/imagens/war_tabuleiro_fundo.png";
    public static PlayerConfig playerConfig = null;
    private int numPlayers;

    JButton startGameButton = new JButton("Iniciar o Jogo");
    
    //Guarda os componentes de seleção de personagem
	private ArrayList <Selection> selectionComponents = new ArrayList<Selection>();

    //Guarda os nomes e cores dos jogadores em um array
	private ArrayList <String> nomesJogadores = new ArrayList<String>();
    private ArrayList <Color> coresJogadores = new ArrayList<Color>();

    // Construtor da tela de configuração de jogadores
    public PlayerConfig() {
        // Carregar a imagem de fundo
        ImageIcon background = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(background);
        setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(2, 3));
        playersPanel.setOpaque(false);
        add(playersPanel, BorderLayout.SOUTH);

        
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map.getMap();
                Window.getWindow().configureMap();
            }
        });

        playersPanel.add(startGameButton);

        for(Selection selection : selectionComponents) {
            nomesJogadores.add(selection.getName());
            coresJogadores.add(selection.getCor());
        }
    }

    // Desenha jogadores
	public void drawPlayers() {
		for(int i = 0;i < numPlayers;i++) {
			Selection selection = new Selection();
			add(selection);
			selectionComponents.add(selection);
		}
	}

    // Singleton
    public static PlayerConfig getPlayerConfig() {
        if(playerConfig == null) {
            playerConfig = new PlayerConfig();
        } 

        return playerConfig;
    }
}
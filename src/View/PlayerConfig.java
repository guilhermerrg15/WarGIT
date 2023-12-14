package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Controller.APIController;
import Model.PlayerColor;

public class PlayerConfig extends JPanel {
    
    public static PlayerConfig playerConfig = null;
    private int numPlayers;

    JButton startGameButton = new JButton("Iniciar o Jogo");
        
	private ArrayList <Selection> selectionComponents = new ArrayList<Selection>();

	private ArrayList <String> nomesJogadores = new ArrayList<String>();
    private ArrayList <PlayerColor> coresJogadores = new ArrayList<PlayerColor>();

	Image background;

	Graphics2D graphic;


    public PlayerConfig() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        startGameButton.setAlignmentX(CENTER_ALIGNMENT);

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Selection selection : selectionComponents) {
                    nomesJogadores.add(selection.getNome());
                    coresJogadores.add(selection.getCor());
                }
                if(APIController.getInstance().startMatch(nomesJogadores, coresJogadores)) {
                    Window.getWindow().configureMap();  
                } else {
					nomesJogadores.clear();
					coresJogadores.clear();
					JOptionPane.showMessageDialog(null, "Nomes ou cores inválidos ou repetidos", "Erro", JOptionPane.ERROR_MESSAGE);
				}
                
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
		this.graphic.drawImage(background, 0, 0, 1440, 900, null);

	}

	public void drawPlayers() {
        add(Box.createVerticalStrut(150));
		for(int i = 0;i < numPlayers;i++) {
			Selection selection = new Selection();
			add(selection);
			selectionComponents.add(selection);
		}
        add(startGameButton);
        add(Box.createVerticalGlue()); 
        add(Box.createVerticalStrut(250));

        try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo\n");
		}
	}
    
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public ArrayList<String> getNomesJogadores() {
		return nomesJogadores;
	}


	public ArrayList<PlayerColor> getCoresJogadores() {
		return coresJogadores;
	}

    public static PlayerConfig getPlayerConfig() {
        if(playerConfig == null) {
            playerConfig = new PlayerConfig();
        } 

        return playerConfig;
    }
}
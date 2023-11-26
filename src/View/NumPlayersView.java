package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class NumPlayersView extends JPanel {

    public static NumPlayersView numPlayersView = null;

    JRadioButton player3Button = new JRadioButton("3 jogadores");
    JRadioButton player4Button = new JRadioButton("4 jogadores");
    JRadioButton player5Button = new JRadioButton("5 jogadores");
    JRadioButton player6Button = new JRadioButton("6 jogadores");
    ButtonGroup allButtons = new ButtonGroup();
    JButton confirmeButton = new JButton("Confirmar");

    // Adicionar fundo da tela inicial
	Image background;
    Image foreground;

    // Componente gráfico da tela inicial
	Graphics2D graphic;

    public NumPlayersView() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 20, 10); 

        player3Button.setAlignmentX(CENTER_ALIGNMENT);
        player4Button.setAlignmentX(CENTER_ALIGNMENT);
        player5Button.setAlignmentX(CENTER_ALIGNMENT);
        player6Button.setAlignmentX(CENTER_ALIGNMENT);

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        player3Button.setFont(buttonFont);
        player4Button.setFont(buttonFont);
        player5Button.setFont(buttonFont);
        player6Button.setFont(buttonFont);
        confirmeButton.setFont(buttonFont);

        allButtons.add(player3Button);
        allButtons.add(player4Button);
        allButtons.add(player5Button);
        confirmeButton.add(player6Button);

        confirmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = 0;
                if (player3Button.isSelected()) {
                    // PlayerConfig.getPlayerConfig().setNumJogadores(3);
                    selected++;
                }
                if (player4Button.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(4);
					selected++;
				}
				if (player5Button.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(5);
					selected++;
				}
				if (player6Button.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(6);
					selected++;
				}

                if (selected == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o número de jogadores", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PlayerConfig.getPlayerConfig().drawPlayers();
                Window.getWindow().configurePlayers();
            }
        });

        add(player3Button, gbc);
        gbc.gridy++;
        add(player4Button, gbc);
        gbc.gridy++;
        add(player5Button, gbc);
        gbc.gridy++;
        add(player6Button, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Alinha o botão Confirmar ao centro
        add(confirmeButton, gbc);

        //Carrega a imagem de fundo
		try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
            foreground = ImageIO.read(new File("resources/imagens/bgconfiguracao.png"));
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo\n");
		}
    }

   // Desenha a imagem de fundo
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
        
		this.graphic.drawImage(background, 0, 0, 1440, 900, null);
        this.graphic.drawImage(foreground, 520, 120, 400, 600, null);

        // Desenha um retângulo entre a imagem de foreground e os botões
        int rectangleWidth = 340;
        int rectangleHeight = 380;
        int rectangleX = 550;
        int rectangleY = 250;

        this.graphic.setColor(Color.LIGHT_GRAY); // Cor do retângulo
        this.graphic.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        this.graphic.setColor(Color.BLACK); // Cor da borda do retângulo
        this.graphic.drawRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);

	}


    public static NumPlayersView getNumPlayersView() {
        if(numPlayersView == null) {
            numPlayersView = new NumPlayersView();
        } 

        return numPlayersView;
    }
}
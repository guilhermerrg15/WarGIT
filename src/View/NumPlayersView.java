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

    JRadioButton radioButton3 = new JRadioButton("3 jogadores");
    JRadioButton radioButton4 = new JRadioButton("4 jogadores");
    JRadioButton radioButton5 = new JRadioButton("5 jogadores");
    JRadioButton radioButton6 = new JRadioButton("6 jogadores");
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton confirmar = new JButton("Confirmar");

    // Adicionar fundo da tela inicial
	Image background;
    Image foreground;

    // Componente gráfico da tela inicial
	Graphics2D graphic;

    public NumPlayersView() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(250));

        radioButton3.setAlignmentX(CENTER_ALIGNMENT);
        radioButton4.setAlignmentX(CENTER_ALIGNMENT);
        radioButton5.setAlignmentX(CENTER_ALIGNMENT);
        radioButton6.setAlignmentX(CENTER_ALIGNMENT);


        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(radioButton5);
        buttonGroup.add(radioButton6);

        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = 0;
                if (radioButton3.isSelected()) {
                    // PlayerConfig.getPlayerConfig().setNumJogadores(3);
                    selected++;
                }
                if (radioButton4.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(4);
					selected++;
				}
				if (radioButton5.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(5);
					selected++;
				}
				if (radioButton6.isSelected()) {
					// PlayerConfig.getPlayerConfig().setNumJogadores(6);
					selected++;
				}

                if (selected == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o número de jogadores", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // PlayerConfig.getPlayerConfig().drawPlayers();
                Window.getWindow().configurePlayers();
            }
        });

        add(Box.createVerticalGlue()); 

        add(radioButton3);
        add(Box.createVerticalStrut(10));
        add(radioButton4);
        add(Box.createVerticalStrut(10));
        add(radioButton5);
        add(Box.createVerticalStrut(10));
        add(radioButton6);
        add(Box.createVerticalStrut(10));
        add(confirmar);

        add(Box.createVerticalGlue());

        //Carrega a imagem de fundo
		try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
            foreground = ImageIO.read(new File("resources/imagens/war_tabuleiro_fundo.png"));
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
	}


    public static NumPlayersView getNumPlayersView() {
        if(numPlayersView == null) {
            numPlayersView = new NumPlayersView();
        } 

        return numPlayersView;
    }
}
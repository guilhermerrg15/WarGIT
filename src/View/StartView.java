package View;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StartView extends JPanel {
    // Criar botões da tela inicial
    JButton start = new JButton("Iniciar novo jogo");
    JButton continueGame = new JButton("Continuar jogo");

    // Adicionar fundo da tela inicial
	Image i;

    // Componente gráfico da tela inicial
	Graphics2D graphic;

    // Singleton
    public static StartView startView = null;

    // Construtor da tela inicial
    public StartView() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(650));

        start.setAlignmentX(CENTER_ALIGNMENT);
        continueGame.setAlignmentX(CENTER_ALIGNMENT);


        start.addActionListener(new ActionListener() {
            // Implementar ação do botão de iniciar jogo
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerConfig.getPlayerConfig();
                Window.getWindow().configurePlayers();
            }

            // TODO: Adicionar método de retirar visibilidade do panel da tela inicial

            //////////////////////////////////////////////////////////////////////////
        });

        // Carregar fundo da tela inicial
		try {
			i = ImageIO.read(new File ("resources/imagens/war_janelaInicial.png"));
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo\n");
		}

        // Adicionar botões na tela inicial
        add(Box.createVerticalGlue()); // Espaçamento acima dos botões
        add(start);
        add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        add(continueGame);
        add(Box.createVerticalGlue()); // Espaçamento abaixo dos botões
        
    }

    // Desenha a imagem de fundo
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
		this.graphic.drawImage(i, 0, 0, 1440, 900, null);
	}

    //Singleton
	public static StartView getStartView() {
		if (startView == null) {
			startView = new StartView();	
		}
		return startView;
	}
}

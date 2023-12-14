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
    
    JButton start = new JButton("Iniciar novo jogo");
    JButton continueGame = new JButton("Continuar jogo");

    
	Image i;

	Graphics2D graphic;

    public static StartView startView = null;

    public StartView() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(650));

        start.setAlignmentX(CENTER_ALIGNMENT);

        start.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.getWindow().goToCsPanel();
            }
        });

		try {
			i = ImageIO.read(new File ("resources/imagens/war_janelaInicial.png"));
		}
		catch (IOException e) {
			System.out.println("erro na leitura\n");
		}

        add(Box.createVerticalGlue());
        add(start);
        add(Box.createVerticalStrut(10));

    }

	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
		this.graphic.drawImage(i, 0, 0, 1440, 900, null);
	}


	public static StartView getStartView() {
		if (startView == null) {
			startView = new StartView();
		}
		return startView;
	}
}

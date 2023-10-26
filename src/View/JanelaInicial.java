package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaInicial extends JFrame {
	public JanelaInicial(){
		super("Detetive");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		JPanel newp = new JPanel() {
			 @Override
			    public void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        Image i;
					try {
						i = ImageIO.read(new File("resources/imagens/bgconfiguracao.png"));
						g.drawImage(i,0, -50, 1200, 700, null); 
					}
					
					catch(IOException e){
						System.out.println(e.getMessage());
						System.exit(1);
					}
			    }
			
		};
		newp.setLayout(null);
		newp.setBackground(Color.BLACK);
		newp.setPreferredSize(new Dimension(400,500));
		add (newp);
		setLocationRelativeTo(null);
		setBounds(0,0,1200,700);


		//Trocar esse botao pelo botao novo feito no Illustrator / Canva
		JButton carregar = new JButton("Carregar jogo"); 
		carregar.setBounds(50, 130, 600, 60); 
		newp.add(carregar);
		setVisible(true);

	}

}
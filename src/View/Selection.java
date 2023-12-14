package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Model.PlayerColor;

class Selection extends JPanel {
	
	public static Selection Selection = null;
	
	JTextField textField = new JTextField("",20);

	JComboBox<PlayerColor> comboBox = new JComboBox<>(PlayerColor.values());

	private String nome; 

	Image background;

	Graphics2D graphic;

	public Selection() {
		add(textField);
		add(comboBox);

		try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
		}
		catch (IOException e) {
			System.out.println("erro na leitura\n");
		}
	}


	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
		this.graphic.drawImage(background, 0, 0, 1440, 900, null);

	}

	private void setNome() {
		if (!textField.getText().equals("Nome do jogador")) {
			this.nome = textField.getText();
		}
	}

	public PlayerColor getCor() {

		return (PlayerColor) comboBox.getSelectedItem();
	}
	
	public String getNome() {
		setNome();
		return nome;
	}

	
}

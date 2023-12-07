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
	
    // Escrever nome do jogador
	JTextField textField = new JTextField("Nome do jogador",20);

    // Escolher cor do jogador
	JComboBox<PlayerColor> comboBox = new JComboBox<>(PlayerColor.values());
	
	// Guarda a cor escolhida
	private Color cor; 

	// Guarda o nome escolhido
	private String nome; 

	// Adicionar fundo da tela inicial
	Image background;

    // Componente gráfico da tela inicial
	Graphics2D graphic;

	// Construtor
	public Selection() {
		add(textField);
		add(comboBox);

		try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
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

	// Pega o nome do jogador
	private void setNome() {
		if (!textField.getText().equals("Nome do jogador")) {
			this.nome = textField.getText();
		}
	}
	
	// Devolve o nome que está na textField
	public String getNome() {
		setNome();
		return nome;
	}

	// Devolve a cor que está no comboBox
	public PlayerColor getCor() {
		// setCor();
		return (PlayerColor) comboBox.getSelectedItem();
	}
}

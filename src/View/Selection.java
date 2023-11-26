package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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

	// Seta a cor de acordo com a cor que o jogador escolher
	private void setCor() {
        PlayerColor selectedColor = (PlayerColor) comboBox.getSelectedItem();
			
		switch(selectedColor) {
			case AZUL:
                this.cor = Color.BLUE;
                break;
            case PRETO:
                this.cor = Color.BLACK;
                break;
            case VERMELHO:
                this.cor = Color.RED;
                break;
            case VERDE:
                this.cor = Color.GREEN;
                break;
            case AMARELO:
                this.cor = Color.YELLOW;
                break;
            case BRANCO:
                this.cor = Color.WHITE;
                break;
            default:
                this.cor = Color.WHITE; // Padrão para caso de valor inesperado
                break;
		}
			
	}
	
	// Devolve o nome que está na textField
	public String getNome() {
		setNome();
		return nome;
	}

	// Devolve a cor que está no comboBox
	public Color getCor() {
		setCor();
		return cor;
	}
}

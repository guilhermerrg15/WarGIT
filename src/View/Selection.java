package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

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

	// Construtor
	public Selection() {
		add(textField);
		add(comboBox);
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

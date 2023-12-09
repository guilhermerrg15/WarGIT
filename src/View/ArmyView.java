package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import Model.PlayerColor;

import javax.swing.JComponent;
class ArmyView extends JComponent {

	//Coordenada x da bolinha
	private int posX; 

	//Coordenada y da bolinha
	private int posY; 

	// Cor da bolinha
	private Color cor; 

	//Componente gráfica para o desenho 
	Graphics2D g2d;   

	// Quantidade de exércitos "Default" das bolinhas
	private String qntExercitos = "0"; 
	//OBS : Cor do jogador correspondente à cor do exército

	private String qtdExercitosAntigo = "1";

	//Construtor
	public ArmyView(int x, int y,PlayerColor color, String qntExercitos, String qtdExercitosAntigo) {
		this.posX = x;
		this.posY = y;
		this.cor = getColorFromPlayerColor(color);
		this.qntExercitos = qntExercitos;
		this.qtdExercitosAntigo = qtdExercitosAntigo;
		setBounds(0,0, 660, 660);
	}

	
	//Desenha a bolinha
	void drawPlayer(Graphics g) {
		this.g2d = (Graphics2D) g;

		// Pega a cor do jogador
		this.g2d.setPaint(cor);
		Ellipse2D player = new Ellipse2D.Float(posX, posY, 22, 22);

		// Preenche a bolinha com a cor do jogador
		this.g2d.fill(player);

		// Pega nova cor para contraste
		if (cor == Color.BLACK || cor == Color.BLUE){
			this.g2d.setPaint(Color.WHITE);
		}
		else {
			this.g2d.setPaint(Color.BLACK);
		}


		//ajustar posição do texto
		if(qntExercitos.length() == 1)
			this.g2d.drawString(qntExercitos, posX + 6, posY + 14);
		else{
			this.g2d.drawString(qntExercitos, posX + 3, posY + 14);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		drawPlayer(g);
	}
	
	//Retorna a coordenada x da bolinha
	public int getPosX() {
		return posX;
	}
	
	//Altera a coordenada x da bolinha
	public void setPosX(int posX) {
		this.posX = posX;
	}

	//Retorna a coordenada y da bolinha
	public int getPosY() {
		return posY;
	}

	//Altera a coordenada y da bolinha
	public void setPosY(int posY) {
		this.posY = posY;
	}

	//Retorna a cor da bolinha
	public Color getCor() {
		return cor;
	}

	//Altera a cor da bolinha
	public void setCor(Color cor) {
		this.cor = cor;
	}

	//Retorna a componente gráfica
	public Graphics2D getG2d() {
		return g2d;
	}

	private Color getColorFromPlayerColor(PlayerColor playerColor) {
		switch (playerColor) {
			case YELLOW: 
				return Color.YELLOW;
			case BLUE: 
				return Color.BLUE;
			case WHITE:
				return Color.WHITE;
			case BLACK: 
				return Color.BLACK;
			case RED: 
				return Color.RED;
			case GREEN:
				return Color.GREEN;
			default: 
			return Color.BLACK;
		}
	}

	//Altera a componente gráfica
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	//Retorna a quantidade de exércitos
	public String getNumArmies() {
		return qntExercitos;
	}

	//Retorna a quantidade de exércitos
	public String getQntExercitosAntigo() {
		return qtdExercitosAntigo;
	}

	//Altera a quantidade de exércitos
	public void setNumArmies(String qntExercitos) {
		this.qntExercitos = qntExercitos;
	}	

	//Altera a quantidade de exércitos
	public void setQntExercitosAntigo(String qntExercitosAntigo) {
		this.qtdExercitosAntigo = qntExercitosAntigo;
	}	
}

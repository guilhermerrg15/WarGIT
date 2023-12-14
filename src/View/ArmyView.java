package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import Model.PlayerColor;

import javax.swing.JComponent;
class ArmyView extends JComponent {

	private int posX; 
	private int posY; 

	private Color cor; 

	Graphics2D g2d;   

	private String qntExercitos = "0"; 

	private String qtdExercitosAntigo = "1";

	public ArmyView(int x, int y,PlayerColor color, String qntExercitos, String qtdExercitosAntigo) {
		this.posX = x;
		this.posY = y;
		this.cor = getColorFromPlayerColor(color);
		this.qntExercitos = qntExercitos;
		this.qtdExercitosAntigo = qtdExercitosAntigo;
		setBounds(0,0, 660, 660);
	}


	void drawPlayer(Graphics g) {
		this.g2d = (Graphics2D) g;
		this.g2d.setPaint(cor);

		Ellipse2D player = new Ellipse2D.Float(posX, posY, 22, 22);

		this.g2d.fill(player);

		if (cor == Color.BLACK || cor == Color.BLUE){
			this.g2d.setPaint(Color.WHITE);
		}
		else {
			this.g2d.setPaint(Color.BLACK);
		}

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
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	private Color getColorFromPlayerColor(PlayerColor playerColor) {
		switch (playerColor) {
			case AMARELO: 
				return Color.YELLOW;
			case AZUL: 
				return Color.BLUE;
			case BRANCO:
				return Color.WHITE;
			case PRETO: 
				return Color.BLACK;
			case VERMELHO: 
				return Color.RED;
			case VERDE:
				return Color.GREEN;
			default: 
			return Color.BLACK;
		}
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public String getNumArmies() {
		return qntExercitos;
	}

	public String getQntExercitosAntigo() {
		return qtdExercitosAntigo;
	}

	public void setNumArmies(String qntExercitos) {
		this.qntExercitos = qntExercitos;
	}	

	public void setQntExercitosAntigo(String qntExercitosAntigo) {
		this.qtdExercitosAntigo = qntExercitosAntigo;
	}	
}

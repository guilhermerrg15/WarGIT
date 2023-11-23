package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Classe que desenha a bola com o numero de exercitos
 * faz a média entre os pontos do poligono e desenha a bola
 * 
 * 
 */
class Army extends Ellipse2D.Double {
    private int num_exercitos;
    Ellipse2D borda;
    
    public Army(double x, double y, double size, int num_exercitos) {
        super(x, y, size, size);
        this.num_exercitos = num_exercitos;
        borda = new Ellipse2D.Double(x - 1, y - 1, size + 2, size + 2);

    }
    
    public void muda_exercito(int qtd) {
    	num_exercitos=qtd;
    }
    
    public void draw(Graphics2D g2d, Color cor)
    {
        g2d.setColor((cor.equals(new Color(50,50,50)))  ? Color.WHITE: Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(this.borda);
        g2d.setColor(cor);
        g2d.fill(this);
        g2d.setColor((cor.equals(new Color(50,50,50)))  ? Color.WHITE: Color.BLACK);
        Font fonte = new Font("Dialog", Font.BOLD, 12);
        g2d.setFont(fonte);
        int larguraTexto = g2d.getFontMetrics().stringWidth(Integer.toString(this.num_exercitos));
        int alturaTexto = g2d.getFontMetrics().getHeight();
        g2d.drawString(Integer.toString(this.num_exercitos), (int)this.getCenterX() - larguraTexto / 2, (int)this.getCenterY() + alturaTexto / 4);
    }
}
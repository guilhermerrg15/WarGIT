package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
// import Model.Observado;

import javax.swing.JButton;

abstract class LoadScene {
	protected ImagemInfo[] images;
	protected OutlineTerritory[] formas_geometricas;
	protected int count_images,count_formas_geometricas;
	
	public OutlineTerritory[] get_formas() {
		return formas_geometricas;
	}
	
	public void set_count_images(int number) {
		this.count_images = number;
	}
	
	// public void notify(Observado o) {
		
	// }
	
	public void count_images_loaded(ImagemInfo imagem) {
		count_images++;
		this.images[count_images-1] = imagem;
	}
	
	public void count_terras_loaded(OutlineTerritory formas_geometricas) {
		count_formas_geometricas++;
		this.formas_geometricas[count_formas_geometricas-1] = formas_geometricas;
	}
	
	public void desenha(Graphics g) {
		for(int i = 0; i < this.count_images; i++) {
		    if (images[i] != null) {
		    	g.drawImage(images[i].get_image(), images[i].get_x(), images[i].get_y(), images[i].get_w(), images[i].get_h(), null);
		    }
	    }
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < this.count_formas_geometricas; i++) {
		    if (formas_geometricas[i] != null) {
		    	g2d.setColor(formas_geometricas[i].get_cor());
		    	g2d.fill(formas_geometricas[i].get_polygon());
		    	g2d.setColor(Color.BLACK);
		    	g2d.draw(formas_geometricas[i].get_polygon());
		    }
	    }
	}
	
	public OutlineTerritory formas_geometricas_clicada(int x, int y) {
		for(int i = 0; i < this.count_formas_geometricas; i++) {
			if (formas_geometricas[i].clicou(x,y)) {
		    	return formas_geometricas[i];
		    }
		}
		return null;
	}
	
	abstract List<String> get_jogares_name();
	
	abstract List<String> get_jogares_color();
	
	abstract JButton get_button(int pos);

}


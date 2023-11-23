package View;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;

class OutlineTerritory {
	protected String nome;
	protected Color cor;
	protected final Color borda;
	protected boolean redesenha;
	protected Path2D terra;
	protected Army exercito;
	protected boolean selected;
	
	public void set_slctd(boolean status) {
		this.selected = status;
	}
	
	public boolean get_slctd() {
		return this.selected;
	}
	
	public OutlineTerritory(int[] xs, int[] ys, Color color,Color border, String name) {
		this.borda = border;
		this.cor = color;
		this.nome = name;
		this.terra = new Path2D.Double();
		for (int i = 0; i < xs.length; i++) {
			if (i == 0) {
				this.terra.moveTo(xs[i], ys[i]);
			} else {
				this.terra.lineTo(xs[i], ys[i]);
			}
		}
		this.terra.closePath();

		// ********* EXERCITO 2D *******
		Point coord = this.get_centroDeMassa(this.terra, xs, ys);

		this.exercito = new Army(coord.x, coord.y, 22, 1); // Tamanho configurável aqui
	}

	// OVERLOAD, para ajustar a posicao dos exercitos
	public OutlineTerritory(int[] xs, int[] ys, Color color,Color border, String name, int ajx, int ajy) {
		this.borda = border;
		this.cor = color;
		this.nome = name;
		this.terra = new Path2D.Double();
		for (int i = 0; i < xs.length; i++) {
			if (i == 0) {
				this.terra.moveTo(xs[i], ys[i]);
			} else {
				this.terra.lineTo(xs[i], ys[i]);
			}
		}
		this.terra.closePath();

		// ********* EXERCITO 2D *******
		Point coord = this.get_centroDeMassa(this.terra, xs, ys);

		this.exercito = new Army(coord.x + ajx, coord.y + ajy, 22, 1); // Tamanho configurável aqui
	}

	public String get_nome() {
		return this.nome;
	}

	public Color get_cor() {
		return this.cor;
	}
	
	public Color get_border() {
		return this.borda;
	}

	public Army get_exercito_2d() {
		return this.exercito;
	}

	public Path2D get_polygon() {
		return this.terra;
	}

	public void set_color(Color cor_atual) {
		this.cor = cor_atual;
	}

	public boolean clicou(int x, int y) {
		if (this.terra.contains(x, y) == true) {
			return true;
		}
		return false;
	}

	/**
	 * @brief retorna o centro do polígono
	 * @return Point
	 */
	private Point get_centro() {
		return new Point((int) this.terra.getBounds().getCenterX(), (int) this.terra.getBounds().getCenterY());
	}

	/**
	 * @brief retorna o centro de massa do polígono
	 *        Para ser utilizado com o Exercito2D
	 * @param path
	 * @param xs
	 * @param ys
	 * @return
	 */
	public Point get_centroDeMassa(Path2D path, int[] xs, int[] ys) {

		double area = 0;
		double cx = 0;
		double cy = 0;

		for (int i = 0; i < xs.length; i++) {
			double xi = xs[i];
			double yi = ys[i];
			double xi1 = xs[(i + 1) % xs.length];
			double yi1 = ys[(i + 1) % ys.length];

			double a = xi * yi1 - xi1 * yi;
			area += a;
			cx += (xi + xi1) * a;
			cy += (yi + yi1) * a;
		}

		area /= 2.0;
		cx /= (6.0 * area);
		cy /= (6.0 * area);

		// pequeno ajuste manual

		cx -= 8;
		cy -= 8;

		return new Point((int) cx, (int) cy);
	}
}
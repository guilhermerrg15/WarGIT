package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class DiceView {

	Image[] dadoAtacante = new Image[7];

	Image[] dadoDefensor = new Image[7];
	Image ataque1;
	Image ataque2;
	Image ataque3;
	Image defesa1;
	Image defesa2;
	Image defesa3;
	Graphics2D g2d;

	public DiceView(){
		try {
			dadoAtacante[0] = (ImageIO.read(new File("resources/imagens/dado_desativado.png")));
			dadoAtacante[1] =(ImageIO.read(new File("resources/imagens/dado_ataque_1.png")));
			dadoAtacante[2] =(ImageIO.read(new File("resources/imagens/dado_ataque_2.png")));
			dadoAtacante[3] =(ImageIO.read(new File("resources/imagens/dado_ataque_3.png")));
			dadoAtacante[4] =(ImageIO.read(new File("resources/imagens/dado_ataque_4.png")));
			dadoAtacante[5] =(ImageIO.read(new File("resources/imagens/dado_ataque_5.png")));
			dadoAtacante[6] =(ImageIO.read(new File("resources/imagens/dado_ataque_6.png")));

			dadoDefensor[0] = (ImageIO.read(new File("resources/imagens/dado_desativado.png")));
			dadoDefensor[1] =(ImageIO.read(new File("resources/imagens/dado_defesa_1.png")));
			dadoDefensor[2] =(ImageIO.read(new File("resources/imagens/dado_defesa_2.png")));
			dadoDefensor[3] =(ImageIO.read(new File("resources/imagens/dado_defesa_3.png")));
			dadoDefensor[4] =(ImageIO.read(new File("resources/imagens/dado_defesa_4.png")));
			dadoDefensor[5] =(ImageIO.read(new File("resources/imagens/dado_defesa_5.png")));
			dadoDefensor[6] =(ImageIO.read(new File("resources/imagens/dado_defesa_6.png")));

		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem dos dados");

		}
	}

	public void drawDices(Graphics g) {
		g2d = (Graphics2D) g;

		int dimension = 50;
		int diceSpacing = 10;

		g2d.drawImage(ataque1,20,60,dimension,dimension,null);
		g2d.drawImage(ataque2,20 + dimension + diceSpacing,60,dimension,dimension,null);
		g2d.drawImage(ataque3,20 + (dimension + diceSpacing)*2,60,dimension,dimension,null);
		g2d.drawImage(defesa1,20,60+dimension+diceSpacing,dimension,dimension,null);
		g2d.drawImage(defesa2,20 + dimension + diceSpacing,60+dimension+diceSpacing,dimension,dimension,null);
		g2d.drawImage(defesa3,20 + (dimension + diceSpacing)*2,60+dimension+diceSpacing,dimension,dimension,null);
	}

	public void showDices(int[] dadosAtaque, int[] dadosDefesa) {
		ataque1 = dadoAtacante[dadosAtaque[0]];
		ataque2 = dadoAtacante[dadosAtaque[1]];
		ataque3 = dadoAtacante[dadosAtaque[2]];
		defesa1 = dadoDefensor[dadosDefesa[0]];
		defesa2 = dadoDefensor[dadosDefesa[1]];
		defesa3 = dadoDefensor[dadosDefesa[2]];
	}

	public void clearDices() {
		ataque1 = null;
		ataque2 = null;
		ataque3 = null;
		defesa1 = null;
		defesa2 = null;
		defesa3 = null;
	}
}

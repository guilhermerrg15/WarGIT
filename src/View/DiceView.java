package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class DiceView extends JPanel{
    
    Image[] dadoAtacante = new Image[7];

	//Array de imagens dos dados de defesa
	Image[] dadoDefensor = new Image[7];
	Image ataque1;
	Image ataque2;
	Image ataque3;
	Image defesa1;
	Image defesa2;
	Image defesa3;

	//Componente gráfico
	Graphics2D g2d;

	//Construtor
	public DiceView(){
		
		//Carrega as imagens dos dados
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
		
		//Caso não consiga carregar as imagens, mostra uma mensagem de erro
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem dos dados");
				
		}
	}
	
	//Desenha as imagens dos dados
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(ataque1,0,10,50,50,null);
		g2d.drawImage(ataque2,0,60,50,50,null);
		g2d.drawImage(ataque3,0,110,50,50,null);
		g2d.drawImage(defesa1,50,10,50,50,null);
		g2d.drawImage(defesa2,50,60,50,50,null);
		g2d.drawImage(defesa3,50,110,50,50,null);
	}

	//Mostra os dados de ataque e defesa na tela
	public void showDices(int[] dadosAtaque, int[] dadosDefesa) {
		ataque1 = dadoAtacante[dadosAtaque[0]];
		ataque2 = dadoAtacante[dadosAtaque[1]];
		ataque3 = dadoAtacante[dadosAtaque[2]];
		defesa1 = dadoDefensor[dadosDefesa[0]];
		defesa2 = dadoDefensor[dadosDefesa[1]];
		defesa3 = dadoDefensor[dadosDefesa[2]];
	}
}

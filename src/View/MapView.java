package View;
import javax.swing.*;

import Controller.APIController;
import Model.API;
import Model.PlayerColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

public class MapView extends JPanel implements Observer{
    public static MapView MapView = null;

    JButton checkObjectivesButton = new JButton("Ver Carta de Objetivo");
    JButton checkCardsButton = new JButton("Ver Cartas de Território");
    JButton addArmy = new JButton("Adicionar Exército");
    JButton attackButton = new JButton("Atacar");
    JButton moveTroopsButton = new JButton("Realocar Tropas");
    JButton finishButton = new JButton("Finalizar Jogada");
    Image backgroundImage;
    Image territoriesImage;

    Graphics2D g;

    //Jogador da vez e cor do jogador
	String jogadorDaVez;
	// Color corDoJogador;
	String descricaoObjetivo;
	JLabel jogadorDaVezLabel = new JLabel();

    private PlayerColor corDoJogadorEscolhida;

    APIController controller = APIController.getInstance();
    API game = API.getInstance();

    //Array list de exércitos 
	ArrayList<ArmyView> armyList = new ArrayList<ArmyView>();

    //Boolean para saber se os exércitos já foram criados
	Boolean ExercitosNaoCriados = true;

    //Lista de territórios no jogo
	String[] territorios;

    public MapView() {
        // setLayout(new BorderLayout());
        setLayout(null);
    
        try {
            backgroundImage = ImageIO.read(new File("resources/imagens/imagemFundo.png"));
            // g.drawImage(backgroundImage, 0, 0, 1200, 700, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        //Cria e adiciona o label do jogador da vez
        jogadorDaVezLabel.setFont(new Font("Arial", Font.BOLD, 50));
        jogadorDaVezLabel.setForeground(Color.WHITE);
        // jogadorDaVezLabel.setBounds(640,660,200,30);
        add(jogadorDaVezLabel);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkObjectivesButton);
        
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkCardsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(addArmy);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(attackButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(moveTroopsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(finishButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // buttonPanel.add(cancelButton);

        add(buttonPanel);

        
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        this.g = (Graphics2D) graphic;

        this.g.drawImage(backgroundImage, 0, 0, 1440, 900, null);
        jogadorDaVezLabel.setText(jogadorDaVez);
        if(ExercitosNaoCriados) {
			criaExercitos(g);
			ExercitosNaoCriados = false;
		}
		desenhaExercitos(this.g);
    }



    //singleton
    public static MapView getMapView() {
        if (MapView == null) {
            MapView = new MapView();
        }
        return MapView;
    }

    // Atualiza view no início da rodada de posicionamento para determinar o primeiro jogador
	public void determinaPrimeiroJogador(String jogadorDaVez, PlayerColor corDoJogador){
		// Adicionar frase "Primeiro jogador: NOME - COR"
		// Adicionar descrição do objetivo em cima da carta
		// NÃO IMPRIMIR AINDA, SÓ ADD PARA IMPRIMIR AO CHAMAR O DRAWCOMPONENT DO MAINFRAME
		this.jogadorDaVez = jogadorDaVez;
        this.corDoJogadorEscolhida = corDoJogador;

        // Adiciona o quadrado de cor ao jogadorDaVezLabel
        adicionarQuadradoCor();
	}

    private void adicionarQuadradoCor() {
        JLabel corLabel = new JLabel();
        corLabel.setOpaque(true);
        corLabel.setBackground(getColorFromPlayerColor(corDoJogadorEscolhida));
        corLabel.setPreferredSize(new Dimension(20, 20));
        add(corLabel);
    }

    // Desenha cada bolinha nos territórios
	void desenhaExercitos(Graphics2D graphic) {
		for (ArmyView army : armyList) {
			army.drawPlayer(graphic);
		}
	}

    // Instancia os objetos dos exércitos
	void criaExercitos(Graphics2D g2d) {
		//Pega a lista de territórios
		this.territorios = controller.getTerritoriosLista();
		ArmyView exercitos;
		
		//Verifica qual o território e desenha o exército na posição correta
		for (String t: territorios) { 
			switch(t){
				//América do Sul
				case "Brasil":
					exercitos = new ArmyView(425,540,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Argentina":
					exercitos = new ArmyView(405,600,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Peru":
					exercitos = new ArmyView(345,580,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Venezuela":
					exercitos = new ArmyView(272,500,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;

				// //América do Norte
				case "Nova York":
					exercitos = new ArmyView(282,308,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "México":
					exercitos = new ArmyView(188,401,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Califórnia":
					exercitos = new ArmyView(145,306,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Groelândia":
					exercitos = new ArmyView(425,135,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Alasca":
					exercitos = new ArmyView(124,165,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Vancouver":
					exercitos = new ArmyView(184,221,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Calgary":
					exercitos = new ArmyView(216,165,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Quebec":
					exercitos = new ArmyView(425,195,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Texas":
					exercitos = new ArmyView(245,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
					
				// //Europa
				case "Polônia":
					exercitos = new ArmyView(796,239,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "França":
					exercitos = new ArmyView(670,292,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Suécia":
					exercitos = new ArmyView(745,162,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Espanha":
					exercitos = new ArmyView(621,315,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Reino Unido":
					exercitos = new ArmyView(645,215,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Romênia":
					exercitos = new ArmyView(805,298,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Ucrânia":
					exercitos = new ArmyView(835,284,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Itália":
					exercitos = new ArmyView(732,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				
				// //África
				case "Egito":
					exercitos = new ArmyView(800,426,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Argélia":
					exercitos = new ArmyView(629,432,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Nigéria":
					exercitos = new ArmyView(670,489,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Somália":
					exercitos = new ArmyView(831,554,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Angola":
					exercitos = new ArmyView(732,600,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Africa do Sul":
					exercitos = new ArmyView(679,590,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				
				// //Ásia
				// case "Estonia":
				// 	exercitos = new ArmyView(784,150,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Letonia":
				// 	exercitos = new ArmyView(770,199,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Russia":
				// 	exercitos = new ArmyView(910,164,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Siberia":
				// 	exercitos = new ArmyView(1032,157,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Turquia":
				// 	exercitos = new ArmyView(860,255,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Cazaquistao":
				// 	exercitos = new ArmyView(982,229,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Japao":
				// 	exercitos = new ArmyView(1105,286,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Siria":
				// 	exercitos = new ArmyView(776,298,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Paquistao":
				// 	exercitos = new ArmyView(879,339,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "China":
				// 	exercitos = new ArmyView(931,311,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Mongolia":
				// 	exercitos = new ArmyView(1014,262,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Coreia do Norte":
				// 	exercitos = new ArmyView(1012,315,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Coreia do Sul":
				// 	exercitos = new ArmyView(1006,340,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Jordania":
				// 	exercitos = new ArmyView(729,363,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Iraque":
				// 	exercitos = new ArmyView(790,360,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Ira":
				// 	exercitos = new ArmyView(846,358,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "India":
				// 	exercitos = new ArmyView(936,401,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Bangladesh":
				// 	exercitos = new ArmyView(984,392,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Tailandia":
				// 	exercitos = new ArmyView(1048,386,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Arabia Saudita":
				// 	exercitos = new ArmyView(796,426,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				
				// //Oceania
				// case "Australia":
				// 	exercitos = new ArmyView(1034,629,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Indonesia":
				// 	exercitos = new ArmyView(1053,520,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Perth":
				// 	exercitos = new ArmyView(951,616,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
				// case "Nova Zelandia":
				// 	exercitos = new ArmyView(1087,672,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
				// 	break;
					
				default:
					exercitos = new ArmyView(0,0,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
			
			}
			
			//Desenha o território
            exercitos.drawPlayer(g2d);
			//Adiciona exército na lista
			armyList.add(exercitos);
		}
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

    @Override
    public void notify(Observed o){
    }
}

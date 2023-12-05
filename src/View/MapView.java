package View;
import javax.swing.*;

import Controller.APIController;
import Model.API;
import Model.PlayerColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;


import javax.imageio.ImageIO;

public class MapView extends JPanel implements Observer{
    public static MapView MapView = null;

    JButton checkObjectivesButton = new JButton("Ver Carta de Objetivo");
    JButton checkCardsButton = new JButton("Ver Cartas de Território");
	JButton placeArmyButton  = new JButton("Posicionar Tropas");
	JButton continuarButton = new JButton("Continuar");
	JButton saveButton = new JButton("Salvar Jogo");
	JButton playDicesButton = new JButton( "Atacar");
    Image backgroundImage;
    Image territoriesImage;
	Image objectiveCard;
	private Map<Ellipse2D, String> territoryMapping = new HashMap<>();
	private boolean modoAddTropas = false;
    Graphics2D g;
	JLabel corLabel = new JLabel();
	int somaAtualExercitos = 0;
	int somaExInicio = 0;
	private boolean firstRound = true;

    //Jogador da vez e cor do jogador
	String jogadorDaVez;
	// Color corDoJogador;
	String descricaoObjetivo;
	JLabel jogadorDaVezLabel = new JLabel();
	JLabel objective = new JLabel();

    private PlayerColor corDoJogadorEscolhida;
	private boolean showObjectiveCard = false;

    APIController controller = APIController.getInstance();
    API game = API.getInstance();

    //Array list de exércitos 
	ArrayList<ArmyView> armyList = new ArrayList<ArmyView>();

    //Boolean para saber se os exércitos já foram criados
	Boolean ExercitosNaoCriados = true;

    //Lista de territórios no jogo
	String[] territorios;

	JComboBox<String> territoriosAtacante;
	JComboBox<String> territoriosDefesa;

    public MapView() {
        // setLayout(new BorderLayout());
        setLayout(null);
    
      	continuarButton.setBounds(1250,535,200,30);
		add(continuarButton);

		territoriosAtacante = new JComboBox<String>();
		territoriosDefesa = new JComboBox<String>();

		// Configurar a posição e tamanho dos JComboBox
        territoriosAtacante.setBounds(1250, 200, 200, 30);
        territoriosDefesa.setBounds(1250, 250, 200, 30);

        // Adicionar JComboBox ao painel
        add(territoriosAtacante);
        add(territoriosDefesa);

		playDicesButton.setBounds(1250,200,200,30);
		add(playDicesButton);

        JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        setLayout(new FlowLayout(FlowLayout.LEFT));  
		
		
        
		buttonPanel.add(saveButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(placeArmyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonPanel.add(checkObjectivesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkCardsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // buttonPanel.add(cancelButton);


		add(Box.createHorizontalStrut(20));
        add(buttonPanel);
		add(Box.createHorizontalStrut(50));
        //Cria e adiciona o label do jogador da vez
        jogadorDaVezLabel.setFont(new Font("Arial", Font.BOLD, 25));
        jogadorDaVezLabel.setForeground(Color.BLACK);
        add(jogadorDaVezLabel);

		
		

		placeArmyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trocar o estado do modo de alocação de tropas ao clicar no botão
                modoAddTropas = true;
            }
        });


		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBolinhaClick(e.getX(), e.getY());
            }
        });

		continuarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modoAddTropas = false;
				firstRound = controller.clicouContinuar();
				somaAtualExercitos = 0;
				
			}
		});

		// Adiciona ação ao selecionar um atacante
		territoriosAtacante.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.selecionouAtacante((String) territoriosAtacante.getSelectedItem());
	        }
		}); 

		

		checkObjectivesButton.addActionListener(new ActionListener() {
            // Adicionar ação do botão de ver carta de objetivo do jogador da vez
            @Override
            public void actionPerformed(ActionEvent e) {
				descricaoObjetivo = game.playerObjective(jogadorDaVez);

				// Pegar imagem da carta de objetivo de acordo com o jogador da vez
				try {
                    objectiveCard = ImageIO.read(new File("resources/imagens/war_carta_" + descricaoObjetivo + ".png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Handle the exception appropriately (e.g., load a default image)
                }

				// Toggle da carta de objetivo
				showObjectiveCard = !showObjectiveCard;
				repaint();
            }
        });


		try {
			backgroundImage = ImageIO.read(new File("resources/imagens/imagemFundo.png"));
            // g.drawImage(backgroundImage, 0, 0, 1200, 700, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		
        
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

		if (modoAddTropas) {
			// Se o modoAddTropas for verdadeiro, mostra o botão "Continuar"
			continuarButton.setVisible(true);
		} else {
			// Se o modoAddTropas for falso, esconde o botão "Continuar"
			continuarButton.setVisible(false);
		}

		if(firstRound) {
			territoriosAtacante.setVisible(false);
			territoriosDefesa.setVisible(false);
			playDicesButton.setVisible(false);
		} else {
			territoriosAtacante.setVisible(true);
			territoriosDefesa.setVisible(true);
			playDicesButton.setVisible(true);
		}

		if(showObjectiveCard) {
			// Alterar tamanho da carta
			int cardWidth = 350;
			int cardHeight = 500;

			// Posicionar carta de objetivo no centro da tela
			int centerX = (getWidth() - cardWidth) / 2;
        	int centerY = (getHeight() - cardHeight) / 2;
			this.g.drawImage(objectiveCard, centerX, centerY, 350, 500, null);
		}
    }






    // Singleton
    public static MapView getMapView() {
        if (MapView == null) {
            MapView = new MapView();
        }
        return MapView;
    }

    // Atualiza view no início da rodada de posicionamento para determinar o primeiro jogador
	public void determinaPrimeiroJogador(String jogadorDaVez, PlayerColor corDoJogador){
		this.jogadorDaVez = jogadorDaVez;
        this.corDoJogadorEscolhida = corDoJogador;
        adicionarQuadradoCor();
	}

	// Muda o jogador da vez na view
	public void mudaJogador(String nome, PlayerColor cor){
		this.jogadorDaVez = nome;
		this.corDoJogadorEscolhida = cor;
		
		adicionarQuadradoCor();
		repaint();
	}


	// Atualiza view no início da rodada de posicionamento para atualizar os jogadores atacantes
	public void atualizaAtacantes(String[] atacantes){
		// Esvazia a comboBox de atacantes e adiciona os novos territórios
		territoriosAtacante.removeAllItems();
	

		for (String s: atacantes){
			territoriosAtacante.addItem(s);
		}
	}
	
	// Atualiza os territórios a serem atacados a partir do território selecionado
	public void atualizaDefensores(String[] defensores){
		// Esvazia a comboBox de defensores e adiciona os novos territórios
		territoriosDefesa.removeAllItems();

		for (String s: defensores){
			territoriosDefesa.addItem(s);
		}
	}


	private void adicionarQuadradoCor() {
        
        corLabel.setOpaque(true);
        corLabel.setBackground(getColorFromPlayerColor(corDoJogadorEscolhida));
        corLabel.setPreferredSize(new Dimension(20, 20));
    
        // Adiciona o quadrado de cor à esquerda do jogadorDaVezLabel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(corLabel, BorderLayout.WEST);
        panel.add(jogadorDaVezLabel, BorderLayout.CENTER);
    
        // Define a posição e tamanho do painel combinado
        panel.setBounds(10, 660, 300, 30); // Ajuste as coordenadas conforme necessário
    
        add(panel);
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
					exercitos = new ArmyView(800,446,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Argélia":
					exercitos = new ArmyView(629,432,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Nigéria":
					exercitos = new ArmyView(670,489,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Somália":
					exercitos = new ArmyView(871,554,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Angola":
					exercitos = new ArmyView(772,600,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "África do Sul":
					exercitos = new ArmyView(809,660,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				
				// //Ásia
				case "Estônia":
					exercitos = new ArmyView(944,150,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Letônia":
					exercitos = new ArmyView(970,209,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Rússia":
					exercitos = new ArmyView(1100,184,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Sibéria":
					exercitos = new ArmyView(1252,157,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Turquia":
					exercitos = new ArmyView(1000,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Cazaquistão":
					exercitos = new ArmyView(1150,249,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Japão":
					exercitos = new ArmyView(1315,306,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Síria":
					exercitos = new ArmyView(906,328,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Paquistão":
					exercitos = new ArmyView(1049,379,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "China":
					exercitos = new ArmyView(1131,341,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Mongólia":
					exercitos = new ArmyView(1234,292,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Coreia do Norte":
					exercitos = new ArmyView(1182,353,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Coreia do Sul":
					exercitos = new ArmyView(1256,380,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Jordânia":
					exercitos = new ArmyView(869,403,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Iraque":
					exercitos = new ArmyView(945,410,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Irã":
					exercitos = new ArmyView(1006,398,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Índia":
					exercitos = new ArmyView(1106,411,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Bangladesh":
					exercitos = new ArmyView(1194,412,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Tailândia":
					exercitos = new ArmyView(1225,466,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Arábia Saudita":
					exercitos = new ArmyView(946,486,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				
				// //Oceania
				case "Austrália":
					exercitos = new ArmyView(1234,669,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Indonésia":
					exercitos = new ArmyView(1283,590,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Perth":
					exercitos = new ArmyView(1141,666,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
				case "Nova Zelândia":
					exercitos = new ArmyView(1287,752,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
					break;
					
				default:
					exercitos = new ArmyView(0,0,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString());
			
				
			}
			
			//Desenha o território
            exercitos.drawPlayer(g2d);
			//Adiciona exército na lista
			armyList.add(exercitos);
			territoryMapping.put(new Ellipse2D.Float(exercitos.getPosX(), exercitos.getPosY(), 22, 22), t);
		}
	}



    // Retorna quantidade de exércitos que tem em um território
    public Integer getQntExTotal(ArrayList<ArmyView> armyList) {
		int somaTotalEx = 0;
		for (ArmyView army : armyList) {
			if (controller.getCorTerritorio(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == corDoJogadorEscolhida) {
				somaTotalEx += Integer.parseInt(army.getQntExercitos());
			}
		}
		return somaTotalEx;
    }

	// fazer uma logica aqui para se o numero de exercitos chegar no máximo, trocar de jogador
	private void handleBolinhaClick(int mouseX, int mouseY) {
		if (modoAddTropas) {
			// Restaurar quantidade original no início do loop
			int quantidadeOriginal = 0;

			// Atualizar o número total de exercítos antes do incremento da rodada
			if (somaAtualExercitos==0) {
				//vai ter que zerar somaAtualExercitos quando mudar para proxima rodada
				somaExInicio = getQntExTotal(armyList);
			}
			int somaExAtual = getQntExTotal(armyList);

			somaAtualExercitos = somaExAtual - somaExInicio;

			// Calcular a quantidade máxima de exércitos permitidos
			int quantidadeMaximaExercitos = controller.getQuantidadeTerritoriosJogador(corDoJogadorEscolhida) / 2;
			
			// Verificar se a soma atual não excede a quantidade máxima permitida
			if (somaAtualExercitos <  quantidadeMaximaExercitos) {
				for (ArmyView army : armyList) {
					Ellipse2D bolinha = new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22);
					if (bolinha.contains(mouseX, mouseY)) {
						String territorioNome = territoryMapping.get(bolinha);
						PlayerColor corDoTerritorio = controller.getCorTerritorio(territorioNome);

						// Verificar se a cor do território é igual à cor do jogador
						if (corDoTerritorio == corDoJogadorEscolhida) {
							// Salvar a quantidade original antes de incrementar
							quantidadeOriginal = Integer.parseInt(army.getQntExercitos());

							// Incrementar o número de exércitos no território ao clicar na bolinha
							int novoQtdExercitos = quantidadeOriginal + 1;
							controller.incrementarExercitos(territorioNome, 1);

							// Atualizar a quantidade de exércitos na bolinha
							army.setQntExercitos(String.valueOf(novoQtdExercitos));

							// Atualizar a exibição
							repaint();
							break;
						} else {
							JOptionPane.showMessageDialog(this, "Você só pode adicionar exércitos em territórios da sua cor.");
							break;
						}
					}
				}
				somaAtualExercitos++;
			} else {
				for (ArmyView army : armyList) {
					Ellipse2D bolinha = new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22);
					if (bolinha.contains(mouseX, mouseY)) {
						army.setQntExercitos(String.valueOf(quantidadeOriginal));
						break;
					}
				}
				// somaAtualExercitos=0;
			}

			
			// // Imprimir a quantidade total de exércitos após adicionar em diferentes territórios
			// int quantidadeTotalExercitos = 0;
			// for (ArmyView army : armyList) {
			// 	if (controller.getCorTerritorio(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == corDoJogadorEscolhida) {
			// 		quantidadeTotalExercitos += Integer.parseInt(army.getQntExercitos());
			// 	}
			// }
			// System.out.println("Quantidade total de exércitos: " + quantidadeTotalExercitos);
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

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
	JButton continueButton = new JButton("Continuar");
	JButton saveButton = new JButton("Salvar Jogo");
	JButton playDicesButton = new JButton( "Atacar");
    Image backgroundImage;
    Image territoriesImage;
	Image objectiveCard;
	private Map<Ellipse2D, String> territoryMapping = new HashMap<>();
	private boolean addTroopsMode = false;
    Graphics2D g;
	JLabel labelColor = new JLabel();
	int currentArmySum = 0;
	int firstArmySum = 0;
	private boolean firstRound = true;
	API api = API.getInstance();

    //Jogador da vez e cor do jogador
	String currentPlayer;
	// Color corDoJogador;
	String objectiveDescription;
	JLabel currentPlayerLabel = new JLabel();
	JLabel objective = new JLabel();

    private PlayerColor playerSelectedColor;
	private boolean showObjectiveCard = false;

    APIController controller = APIController.getInstance();
    API game = API.getInstance();

    //Array list de exércitos
	ArrayList<ArmyView> armyList = new ArrayList<ArmyView>();
	ArmyView armie;

    //Boolean para saber se os exércitos já foram criados
	Boolean armyCreation = true;

    //Lista de territórios no jogo
	String[] territories;

	//Painel dos dados
	DiceView diceView = new DiceView();

	JComboBox<String> attackingTerritories;
	JComboBox<String> defendingTerritories;

    public MapView() {
        // setLayout(new BorderLayout());
        setLayout(null);

      	continueButton.setBounds(1250,535,200,30);
		add(continueButton);

		attackingTerritories = new JComboBox<String>();
		defendingTerritories = new JComboBox<String>();

		// Configurar a posição e tamanho dos JComboBox
        attackingTerritories.setBounds(1250, 200, 200, 30);
        defendingTerritories.setBounds(1250, 250, 200, 30);


        // Adicionar JComboBox ao painel
        add(attackingTerritories);
        add(defendingTerritories);

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
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        currentPlayerLabel.setForeground(Color.BLACK);
        add(currentPlayerLabel);


		//Cria e adiciona o painel dos dados
		diceView.setBounds(1250,350,200,200);
		add(diceView);


		placeArmyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trocar o estado do modo de alocação de tropas ao clicar no botão
                addTroopsMode = true;
            }
        });


		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBallClick(e.getX(), e.getY());
            }
        });

		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modoAddTropas = false;
				firstRound = controller.clicouContinuar();
				somaAtualExercitos = 0;
				salvaArmyAntigo();
			}
		});

		// Adiciona ação ao selecionar um atacante
		attackingTerritories.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.selecionouAtacante((String) attackingTerritories.getSelectedItem());
	        }
		});

		//Adiciona ação ao clicar no botão de jogar os dados
		playDicesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] dicesAttack = new int [3];
				int [] dicesDefense = new int [3];

				//Chama a função de jogar os dados
				int[] diceValues= api.makeAttack(attackingTerritories.getSelectedItem().toString(), defendingTerritories.getSelectedItem().toString());
				dicesAttack[0] = diceValues[0];
				dicesAttack[1] = diceValues[1];
				dicesAttack[2] = diceValues[2];
				dicesDefense[0] = diceValues[3];
				dicesDefense[1] = diceValues[4];
				dicesDefense[2] = diceValues[5];

				//Mostra os dados na tela
				diceView.showDices(dicesAttack, dicesDefense);
			}
		});



		checkObjectivesButton.addActionListener(new ActionListener() {
            // Adicionar ação do botão de ver carta de objetivo do jogador da vez
            @Override
            public void actionPerformed(ActionEvent e) {
				objectiveDescription = game.playerObjective(currentPlayer);

				// Pegar imagem da carta de objetivo de acordo com o jogador da vez
				try {
                    objectiveCard = ImageIO.read(new File("resources/imagens/war_carta_" + objectiveDescription + ".png"));
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
        currentPlayerLabel.setText(currentPlayer);
        if(armyCreation) {
			createArmies(g);
			armyCreation = false;
		}
		drawArmies(this.g);

		if (addTroopsMode) {
			// Se o modoAddTropas for verdadeiro, mostra o botão "Continuar"
			continueButton.setVisible(true);
		} else {
			// Se o modoAddTropas for falso, esconde o botão "Continuar"
			continueButton.setVisible(false);
		}

		if(firstRound) {
			attackingTerritories.setVisible(false);
			defendingTerritories.setVisible(false);
			playDicesButton.setVisible(false);
		} else {
			attackingTerritories.setVisible(true);
			defendingTerritories.setVisible(true);
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



	public void salvaArmyAntigo(){
		for (ArmyView army : armyList) {
			if (controller.getCorTerritorio(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == corDoJogadorEscolhida) {
				army.setQntExercitosAntigo(army.getQntExercitos());
			}

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
	public void determineFirstPlayer(String currentPlayer, PlayerColor playerColor){
		this.currentPlayer = currentPlayer;
        this.playerSelectedColor = playerColor;
        addSquareColor();
	}

	// Muda o jogador da vez na view
	public void changePlayer(String name, PlayerColor color){
		this.currentPlayer = name;
		this.playerSelectedColor = color;

		addSquareColor();
		repaint();
	}


	// Atualiza view no início da rodada de posicionamento para atualizar os jogadores atacantes
	public void updateAttackers(String[] attackers){
		// Esvazia a comboBox de atacantes e adiciona os novos territórios
		attackingTerritories.removeAllItems();

		for (String s: attackers){
			attackingTerritories.addItem(s);
		}
	}

	// Atualiza os territórios a serem atacados a partir do território selecionado
	public void updateDefenders(String[] defenders){
		// Esvazia a comboBox de defensores e adiciona os novos territórios
		defendingTerritories.removeAllItems();

		for (String s: defenders){
			defendingTerritories.addItem(s);
		}
	}


	private void addSquareColor() {

        labelColor.setOpaque(true);
        labelColor.setBackground(getColorFromPlayerColor(playerSelectedColor));
        labelColor.setPreferredSize(new Dimension(20, 20));

        // Adiciona o quadrado de cor à esquerda do jogadorDaVezLabel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(labelColor, BorderLayout.WEST);
        panel.add(currentPlayerLabel, BorderLayout.CENTER);

        // Define a posição e tamanho do painel combinado
        panel.setBounds(10, 660, 300, 30); // Ajuste as coordenadas conforme necessário

        add(panel);
    }

    // Desenha cada bolinha nos territórios
	void drawArmies(Graphics2D graphic) {
		for (ArmyView army : armyList) {
			army.drawPlayer(graphic);
		}
	}

    // Instancia os objetos dos exércitos
	void createArmies(Graphics2D g2d) {
		//Pega a lista de territórios
		this.territories = controller.getTerritoriesList();
		ArmyView armies;

		//Verifica qual o território e desenha o exército na posição correta
		for (String t: territories) {
			switch(t){
				//América do Sul
				case "Brasil":
					exercitos = new ArmyView(425,540,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Argentina":
					exercitos = new ArmyView(405,600,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Peru":
					exercitos = new ArmyView(345,580,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Venezuela":
					exercitos = new ArmyView(272,500,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				// //América do Norte
				case "Nova York":
					exercitos = new ArmyView(282,308,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "México":
					exercitos = new ArmyView(188,401,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Califórnia":
					exercitos = new ArmyView(145,306,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Groelândia":
					exercitos = new ArmyView(425,135,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Alasca":
					exercitos = new ArmyView(124,165,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Vancouver":
					exercitos = new ArmyView(184,221,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Calgary":
					exercitos = new ArmyView(216,165,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Quebec":
					exercitos = new ArmyView(425,195,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Texas":
					exercitos = new ArmyView(245,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				// //Europa
				case "Polônia":
					exercitos = new ArmyView(796,239,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "França":
					exercitos = new ArmyView(670,292,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Suécia":
					exercitos = new ArmyView(745,162,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Espanha":
					exercitos = new ArmyView(621,315,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Reino Unido":
					exercitos = new ArmyView(645,215,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Romênia":
					exercitos = new ArmyView(805,298,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Ucrânia":
					exercitos = new ArmyView(835,284,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Itália":
					exercitos = new ArmyView(732,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				// //África
				case "Egito":
					exercitos = new ArmyView(800,446,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Argélia":
					exercitos = new ArmyView(629,432,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Nigéria":
					exercitos = new ArmyView(670,489,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Somália":
					exercitos = new ArmyView(871,554,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Angola":
					exercitos = new ArmyView(772,600,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "África do Sul":
					exercitos = new ArmyView(809,660,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				// //Ásia
				case "Estônia":
					exercitos = new ArmyView(944,150,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Letônia":
					exercitos = new ArmyView(970,209,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Rússia":
					exercitos = new ArmyView(1100,184,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Sibéria":
					exercitos = new ArmyView(1252,157,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Turquia":
					exercitos = new ArmyView(1000,285,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Cazaquistão":
					exercitos = new ArmyView(1150,249,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Japão":
					exercitos = new ArmyView(1315,306,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Síria":
					exercitos = new ArmyView(906,328,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Paquistão":
					exercitos = new ArmyView(1049,379,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "China":
					exercitos = new ArmyView(1131,341,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Mongólia":
					exercitos = new ArmyView(1234,292,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Coreia do Norte":
					exercitos = new ArmyView(1182,353,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Coreia do Sul":
					exercitos = new ArmyView(1256,380,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Jordânia":
					exercitos = new ArmyView(869,403,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Iraque":
					exercitos = new ArmyView(945,410,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Irã":
					exercitos = new ArmyView(1006,398,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Índia":
					exercitos = new ArmyView(1106,411,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Bangladesh":
					exercitos = new ArmyView(1194,412,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Tailândia":
					exercitos = new ArmyView(1225,466,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Arábia Saudita":
					exercitos = new ArmyView(946,486,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				// //Oceania
				case "Austrália":
					exercitos = new ArmyView(1234,669,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Indonésia":
					exercitos = new ArmyView(1283,590,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Perth":
					exercitos = new ArmyView(1141,666,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;
				case "Nova Zelândia":
					exercitos = new ArmyView(1287,752,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());
					break;

				default:
					exercitos = new ArmyView(0,0,controller.getCorTerritorio(t), controller.getQtdExercitos(t).toString(), controller.getQtdExercitosAntigos(t).toString());


			}

			//Desenha o território
            armies.drawPlayer(g2d);
			//Adiciona exército na lista
			armyList.add(armies);
			territoryMapping.put(new Ellipse2D.Float(armies.getPosX(), armies.getPosY(), 22, 22), t);
		}
	}



    // Retorna quantidade de exércitos que tem em um território
    public Integer getNumArmiesTotal(ArrayList<ArmyView> armyList) {
		int sumArmies = 0;
		for (ArmyView army : armyList) {
			if (controller.getTerritoryColor(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == playerSelectedColor) {
				sumArmies += Integer.parseInt(army.getNumArmies());
			}
		}
		return sumArmies;
    }

	// fazer uma logica aqui para se o numero de exercitos chegar no máximo, trocar de jogador
	private void handleBallClick(int mouseX, int mouseY) {
		if (addTroopsMode) {
			// Restaurar quantidade original no início do loop

			// Atualizar o número total de exercítos antes do incremento da rodada
			if (somaAtualExercitos==0) {
				somaExInicio = getQntExTotal(armyList); //17
			}
			int somaExAtual = getQntExTotal(armyList);//17

			somaAtualExercitos = somaExAtual - somaExInicio;//0

			// Calcular a quantidade máxima de exércitos permitidos
			int quantidadeMaximaExercitos = controller.getQuantidadeTerritoriosJogador(corDoJogadorEscolhida) / 2;

			// Verificar se a soma atual não excede a quantidade máxima permitida
			for (ArmyView army : armyList) {
				Ellipse2D bolinha = new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22);
						// Salvar a quantidade original antes de incrementar
				int quantidadeOriginal = Integer.parseInt(army.getQntExercitosAntigo());
				int quantidadeAtualizada = Integer.parseInt(army.getQntExercitos());

				if (bolinha.contains(mouseX, mouseY)) {
					if (somaAtualExercitos <  quantidadeMaximaExercitos) {

						String territorioNome = territoryMapping.get(bolinha);
						PlayerColor corDoTerritorio = controller.getCorTerritorio(territorioNome);

						// Verificar se a cor do território é igual à cor do jogador
						if (corDoTerritorio == corDoJogadorEscolhida) {

							somaAtualExercitos++;

							// Incrementar o número de exércitos no território ao clicar na bolinha
							int novoQtdExercitos = quantidadeAtualizada + 1;
							System.out.println("quant atual " + quantidadeAtualizada);

							controller.incrementarExercitos(territorioNome, 1);

							// Atualizar a quantidade de exércitos na bolinha
							army.setNumArmies(String.valueOf(newArmies));

							// Atualizar a exibição
							repaint();
							break;
						} else {
							JOptionPane.showMessageDialog(this, "Você só pode adicionar exércitos em territórios da sua cor.");
							break;
						}
					}
					else {
						army.setQntExercitos(String.valueOf(quantidadeOriginal));
						System.out.println("quantidade original" + quantidadeOriginal);
						repaint();
						break;

					}
			}

		}


		}
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

	//precisa ser feito
    @Override
    public void notify(Observed o){
    }
}

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
	JButton endAtackButton = new JButton("Terminar Ataque");
	JButton reposicionarButton = new JButton("Reposicionar");
	JButton changePlayer = new JButton("Finalizar");
	JButton cardsTradeButton = new JButton("Trocar Cartas");

	String dicaValues[] = {"0","1","2","3","4","5","6"};
	JComboBox attackDices = new JComboBox(dicaValues);
	JComboBox defenseDices = new JComboBox(dicaValues);
	
    Image backgroundImage;
	Image objectiveCard;

	private Map<Ellipse2D, String> territoryMapping = new HashMap<>();
	private boolean addTroopsMode = false;
	private boolean endPosic = false;
	private boolean endAtack = false;

    Graphics2D g;
	JLabel labelColor = new JLabel();

	int currentArmySum = 0;
	int firstArmySum = 0;
	int bonusTradeSum = 0;

	private boolean firstRound = true;
	API api = API.getInstance();

	String currentPlayer;

	String objectiveDescription;
	JLabel currentPlayerLabel = new JLabel();
	JLabel objective = new JLabel();

    private PlayerColor playerSelectedColor;
	private boolean showObjectiveCard = false;

    APIController controller = APIController.getInstance();
    API game = API.getInstance();

	ArrayList<ArmyView> armyList = new ArrayList<ArmyView>();
	ArmyView army;

	Boolean armyCreation = true;

	Boolean showTerritoryCards = false;
	String[] territories;
	
	DiceView diceView = new DiceView();

	JComboBox<String> attackingTerritories;
	JComboBox<String> defendingTerritories;
	JComboBox<String> originTerritories;
	JComboBox<String> destinyTerritories;
	JComboBox<Integer> numReplacementBox;

    public MapView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

		//Cria e adiciona o label do jogador da vez
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        currentPlayerLabel.setForeground(Color.BLACK);
        add(currentPlayerLabel);
        JPanel panel = new JPanel();
        panel.add(labelColor);
        panel.add(currentPlayerLabel);
        add(panel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(placeArmyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonPanel.add(checkObjectivesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkCardsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);

        
		add(continueButton);
		add(cardsTradeButton);


		attackingTerritories = new JComboBox<String>();
		defendingTerritories = new JComboBox<String>();

		add(attackingTerritories);
		add(attackDices);
        add(defendingTerritories);
		add(defenseDices);

		add(playDicesButton);

		add(endAtackButton);

		originTerritories = new JComboBox<String>();
		destinyTerritories = new JComboBox<String>();
		numReplacementBox = new JComboBox<Integer>();

		add(originTerritories);
		add(numReplacementBox);
		add(destinyTerritories);

		add(reposicionarButton);
		add(changePlayer);

		originTerritories.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.selectedOrigin((String) originTerritories.getSelectedItem());
			}
		});

		reposicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(originTerritories.getSelectedItem() != null && destinyTerritories.getSelectedItem() != null){
					controller.clickedReplace(originTerritories.getSelectedItem().toString(), destinyTerritories.getSelectedItem().toString(), (Integer) numReplacementBox.getSelectedItem());
				}
				
			}
		});

		cardsTradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.clickedTrade();
			}
		});

		changePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endAtack = false;
				controller.clickedChangePlayer();
			}
		});

		placeArmyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (!addTroopsMode){
				controller.clickedPlaceArmy();
				}
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
				addTroopsMode = false;
				firstRound = controller.clickedContinue();
				currentArmySum = 0;
				salvaArmyAntigo();
				endPosic = true;
				bonusTradeSum = 0;
			}
		});

		endAtackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clickedEndAtack();
				endAtack = true;
				endPosic =  false;
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clickedSave();
			}
		});

		attackingTerritories.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.attackerSelected((String) attackingTerritories.getSelectedItem());
	        }
		});

		playDicesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] dicesAttack = new int [3];
				int [] dicesDefense = new int [3];

				int[] diceValues= api.makeAttack(attackingTerritories.getSelectedItem().toString(), defendingTerritories.getSelectedItem().toString(), Integer.valueOf((String)attackDices.getSelectedItem()), Integer.valueOf((String)defenseDices.getSelectedItem()));
				dicesAttack[0] = diceValues[0];
				dicesAttack[1] = diceValues[1];
				dicesAttack[2] = diceValues[2];
				dicesDefense[0] = diceValues[3];
				dicesDefense[1] = diceValues[4];
				dicesDefense[2] = diceValues[5];

				diceView.showDices(dicesAttack, dicesDefense);
				controller.clickedAttack();
				repaint();
			}
		});

		checkObjectivesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				objectiveDescription = game.playerObjective(currentPlayer);

				try {
                    objectiveCard = ImageIO.read(new File("resources/imagens/war_carta_" + objectiveDescription + ".png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

				// Toggle da carta de objetivo
				showObjectiveCard = !showObjectiveCard;
				repaint();
            }
        });

		checkCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				showTerritoryCards = !showTerritoryCards;
				repaint();
            }
        });

		try {
			backgroundImage = ImageIO.read(new File("resources/imagens/imagemFundo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        this.g = (Graphics2D) graphic;

        this.g.drawImage(backgroundImage, 0, 0, 1440, 900, null);

        currentPlayerLabel.setText(currentPlayer);

		diceView.drawDices(graphic);

        if(armyCreation) {
			createArmies(g);
			armyCreation = false;
		}
		drawArmies(this.g);

		if (addTroopsMode) {
			Integer qnt = controller.getNumTerritoryPlayer(playerSelectedColor) / 2;
			if (currentArmySum == qnt + bonusTradeSum){
				// Se o modoAddTropas for verdadeiro, mostra o botão "Continuar"
				continueButton.setVisible(true);
			} else {
				if (controller.canTradeCards()){
			cardsTradeButton.setVisible(true);
		}else{
			cardsTradeButton.setVisible(false);
		}
				continueButton.setVisible(false);
			}
		} else {
			continueButton.setVisible(false);
		}

		if(firstRound) {
			attackingTerritories.setVisible(false);
			attackDices.setVisible(false);
			defendingTerritories.setVisible(false);
			defenseDices.setVisible(false);
			originTerritories.setVisible(false);
			numReplacementBox.setVisible(false);
			destinyTerritories.setVisible(false);
			playDicesButton.setVisible(false);
			reposicionarButton.setVisible(false);
			changePlayer.setVisible(false);
			endAtackButton.setVisible(false);
			cardsTradeButton.setVisible(false);
			// Retirar dados da tela
			diceView.clearDices();
			repaint();

		} else {
			if (endPosic) {
				attackingTerritories.setVisible(true);
				attackDices.setVisible(true);
				defendingTerritories.setVisible(true);
				defenseDices.setVisible(true);
				playDicesButton.setVisible(true);
				endAtackButton.setVisible(true);
				cardsTradeButton.setVisible(false);
				
			} else{
				attackingTerritories.setVisible(false);
				attackDices.setVisible(false);
				defendingTerritories.setVisible(false);
				defenseDices.setVisible(false);
				playDicesButton.setVisible(false);
				endAtackButton.setVisible(false);
			}
			if (endAtack) {
				originTerritories.setVisible(true);
				numReplacementBox.setVisible(true);
				destinyTerritories.setVisible(true);
				reposicionarButton.setVisible(true);
				changePlayer.setVisible(true);
				cardsTradeButton.setVisible(false);

				// Retirar dados da tela
				diceView.clearDices();
				repaint();
			} else{
				originTerritories.setVisible(false);
				numReplacementBox.setVisible(false);
				destinyTerritories.setVisible(false);
				reposicionarButton.setVisible(false);
				changePlayer.setVisible(false);
			}
			
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

		
		if(showTerritoryCards){
			TerritoryCardView.drawTerritoryCard(g, controller.getTerritoryCards());
		}
    }

	public void setFirstRound(boolean isFirst){
		this.firstRound = true;
	}
	public void salvaArmyAntigo(){
		for (ArmyView army : armyList) {
			if (controller.getTerritoryColor(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == playerSelectedColor) {
				army.setQntExercitosAntigo(army.getNumArmies());
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

	public void determineFirstPlayer(String currentPlayer, PlayerColor playerColor){
		this.currentPlayer = currentPlayer;
        this.playerSelectedColor = playerColor;
        addSquareColor(); 
	}

	public void changePlayer(String name, PlayerColor color){
		this.currentPlayer = name;
		this.playerSelectedColor = color;

		addSquareColor();
		repaint();
	}

	public void updateAttackers(String[] attackers){
		attackingTerritories.removeAllItems();

		for (String s: attackers){
			attackingTerritories.addItem(s);
		}
	}

	public void updateDefenders(String[] defenders){
		defendingTerritories.removeAllItems();

		for (String s: defenders){
			defendingTerritories.addItem(s);
		}
	}

	private void addSquareColor() {

        labelColor.setOpaque(true);
        labelColor.setBackground(getColorFromPlayerColor(playerSelectedColor));
        labelColor.setPreferredSize(new Dimension(20, 20));

    }

	public void updateReplacement(String[] territories) {
		originTerritories.removeAllItems();

		originTerritories.addItem(null);

		for (String s: territories){
			originTerritories.addItem(s);
		}
	}

	public void updateNumReplacement(Integer qtd){
		numReplacementBox.removeAllItems();
		for (Integer i = 0; i <= qtd; i++){
			numReplacementBox.addItem(i);
		}
	}

	public void updateDestiny(String[] destinies){
		destinyTerritories.removeAllItems();
		for (String s: destinies){
			destinyTerritories.addItem(s);
		}
	}

    // Desenha cada bolinha nos territórios
	void drawArmies(Graphics2D graphic) {
		for (ArmyView army : armyList) {
			army.drawPlayer(graphic);
		}
	}

	public void clearDices(){
		diceView.clearDices();
	}

	void createArmies(Graphics2D g2d) {
		this.territories = controller.getTerritoriesList();
		ArmyView armies;

		for (String t: territories) {
			switch(t){

				// //África
				case "Egito":
					armies = new ArmyView(800,446,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Argélia":
					armies = new ArmyView(629,432,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Nigéria":
					armies = new ArmyView(670,489,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Somália":
					armies = new ArmyView(871,554,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Angola":
					armies = new ArmyView(772,600,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "África do Sul":
					armies = new ArmyView(809,660,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

				//América do Sul
				case "Brasil":
					armies = new ArmyView(425,540,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Argentina":
					armies = new ArmyView(405,600,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Peru":
					armies = new ArmyView(345,580,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Venezuela":
					armies = new ArmyView(272,500,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

				// //América do Norte
				case "Nova York":
					armies = new ArmyView(282,308,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "México":
					armies = new ArmyView(188,401,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Califórnia":
					armies = new ArmyView(145,306,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Groelândia":
					armies = new ArmyView(425,135,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Alasca":
					armies = new ArmyView(124,165,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Vancouver":
					armies = new ArmyView(184,221,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Calgary":
					armies = new ArmyView(216,165,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Quebec":
					armies = new ArmyView(425,195,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Texas":
					armies = new ArmyView(245,285,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

			// //Ásia
				case "Estônia":
					armies = new ArmyView(944,150,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Letônia":
					armies = new ArmyView(970,209,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Rússia":
					armies = new ArmyView(1100,184,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Sibéria":
					armies = new ArmyView(1252,157,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Turquia":
					armies = new ArmyView(1000,285,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Cazaquistão":
					armies = new ArmyView(1150,249,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Japão":
					armies = new ArmyView(1315,306,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Síria":
					armies = new ArmyView(906,328,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Paquistão":
					armies = new ArmyView(1049,379,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "China":
					armies = new ArmyView(1131,341,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Mongólia":
					armies = new ArmyView(1234,292,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Coreia do Norte":
					armies = new ArmyView(1182,353,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Coreia do Sul":
					armies = new ArmyView(1256,380,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Jordânia":
					armies = new ArmyView(869,403,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Iraque":
					armies = new ArmyView(945,410,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Irã":
					armies = new ArmyView(1006,398,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Índia":
					armies = new ArmyView(1106,411,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Bangladesh":
					armies = new ArmyView(1194,412,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Tailândia":
					armies = new ArmyView(1225,466,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Arábia Saudita":
					armies = new ArmyView(946,486,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

				// //Europa
				case "Polônia":
					armies = new ArmyView(796,239,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "França":
					armies = new ArmyView(670,292,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Suécia":
					armies = new ArmyView(745,162,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Espanha":
					armies = new ArmyView(621,315,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Reino Unido":
					armies = new ArmyView(645,215,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Romênia":
					armies = new ArmyView(805,298,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Ucrânia":
					armies = new ArmyView(835,284,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Itália":
					armies = new ArmyView(732,285,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

				// //Oceania
				case "Austrália":
					armies = new ArmyView(1234,669,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Indonésia":
					armies = new ArmyView(1283,590,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Perth":
					armies = new ArmyView(1141,666,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;
				case "Nova Zelândia":
					armies = new ArmyView(1287,752,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());
					break;

				default:
					armies = new ArmyView(0,0,controller.getTerritoryColor(t), controller.getNumArmiesTerritory(t).toString(), controller.getNumOldArmies(t).toString());


			}

            armies.drawPlayer(g2d);
			armyList.add(armies);
			territoryMapping.put(new Ellipse2D.Float(armies.getPosX(), armies.getPosY(), 22, 22), t);
		}
	}

	public Integer updateBonusTrade (int bonus){
		this.bonusTradeSum = bonus;
		return bonusTradeSum;
	}

    public Integer getNumArmiesTotal(ArrayList<ArmyView> armyList) {
		int sumArmies = 0;
		for (ArmyView army : armyList) {
			if (controller.getTerritoryColor(territoryMapping.get(new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22))) == playerSelectedColor) {
				sumArmies += Integer.parseInt(army.getNumArmies());
			}
		}
		return sumArmies;
    }

	private void handleBallClick(int mouseX, int mouseY) {
		if (addTroopsMode) {
			
			// Calcular a quantidade máxima de exércitos permitidos
			int quantidadeMaximaExercitos = controller.getNumTerritoryPlayer(playerSelectedColor) / 2; 
			

			// Atualizar o número total de exercítos antes do incremento da rodada
			if (currentArmySum==0) {

				firstArmySum = getNumArmiesTotal(armyList); 
			}
			int somaExAtual = getNumArmiesTotal(armyList);

			currentArmySum = somaExAtual - firstArmySum;

			quantidadeMaximaExercitos = quantidadeMaximaExercitos + bonusTradeSum;

			// Verificar se a soma atual não excede a quantidade máxima permitida
			for (ArmyView army : armyList) {
				Ellipse2D bolinha = new Ellipse2D.Float(army.getPosX(), army.getPosY(), 22, 22);
						// Salvar a quantidade original antes de incrementar
				int quantidadeOriginal = Integer.parseInt(army.getQntExercitosAntigo());
				int quantidadeAtualizada = Integer.parseInt(army.getNumArmies());

				if (bolinha.contains(mouseX, mouseY)) {
					if (currentArmySum <  quantidadeMaximaExercitos) {

						String territorioNome = territoryMapping.get(bolinha);
						PlayerColor corDoTerritorio = controller.getTerritoryColor(territorioNome);

						// Verificar se a cor do território é igual à cor do jogador
						if (corDoTerritorio == playerSelectedColor) {

							currentArmySum++;

							// Incrementar o número de exércitos no território ao clicar na bolinha
							int newArmies = quantidadeAtualizada + 1;

							controller.setNumArmiesTerritory(territorioNome, newArmies);

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
						army.setNumArmies(String.valueOf(quantidadeOriginal));
						controller.setNumArmiesTerritory(territoryMapping.get(bolinha), quantidadeOriginal);
						repaint();
						break;

					}
			}

		}


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

	Object[] infos = (Object[]) o.get();

		if (infos[0] instanceof ArrayList<?> && infos[1] instanceof ArrayList<?> && infos[2] instanceof Integer && infos[3] instanceof Integer){
			ArrayList<String> qtds = (ArrayList<String>) infos[0];
			ArrayList<PlayerColor> cores = (ArrayList<PlayerColor>) infos[1];
			Integer mod1 = (Integer) infos[2];
			Integer mod2 = (Integer) infos[3];

			if (mod1 == -1 && mod2 == -1){
				int cont = 0;
				for(ArmyView e: armyList){
					e.setNumArmies(qtds.get(cont));
					e.setCor(getColorFromPlayerColor(cores.get(cont)));
					cont++;
					e.repaint();
					e.drawPlayer(g);
					e.repaint();
				}
			}
			else{
				ArmyView e = armyList.get(mod1);
				e.setNumArmies(qtds.get(mod1));
				e.setCor(getColorFromPlayerColor(cores.get(mod1)));
				e.repaint();
				e.drawPlayer(g);
				repaint();

				if (mod2 != -1){
					e = armyList.get(mod2);
					e.setNumArmies(qtds.get(mod2));
					e.setCor(getColorFromPlayerColor(cores.get(mod2)));
					e.repaint();
					e.drawPlayer(g);
				}
			}
		}

    }
}

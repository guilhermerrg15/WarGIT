package View;

import javax.imageio.ImageIO;
import javax.swing.*;
// import Controller.TratadorSelecao;
// import Model.Game;
// import Model.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerConfig extends JPanel {
    // private String imagePath = "resources/imagens/war_tabuleiro_fundo.png";
    public static PlayerConfig playerConfig = null;
    private int numPlayers;

    // Botão de iniciar jogo
    JButton startGameButton = new JButton("Iniciar o Jogo");
        
    //Guarda os componentes de seleção de personagem
	private ArrayList <Selection> selectionComponents = new ArrayList<Selection>();

    //Guarda os nomes e cores dos jogadores em um array
	private ArrayList <String> nomesJogadores = new ArrayList<String>();
    private ArrayList <Color> coresJogadores = new ArrayList<Color>();

    // Adicionar fundo da tela inicial
	Image background;

    // Componente gráfico da tela inicial
	Graphics2D graphic;


    // Construtor da tela de configuração de jogadores
    public PlayerConfig() {
        // Carregar a imagem de fundo
        // ImageIcon background = new ImageIcon(imagePath);
        // JLabel backgroundLabel = new JLabel(background);
        // setLayout(new BorderLayout());
        // add(backgroundLabel, BorderLayout.CENTER);

        // JPanel playersPanel = new JPanel();
        // playersPanel.setLayout(new GridLayout(2, 3));
        // playersPanel.setOpaque(false);
        // add(playersPanel, BorderLayout.SOUTH);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        

        startGameButton.setAlignmentX(CENTER_ALIGNMENT);

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Selection selection : selectionComponents) {
                    String playerName = selection.getNome();
                    Color playerColor = selection.getCor();
                    nomesJogadores.add(playerName);
                    coresJogadores.add(playerColor);
                }
                if(verifyConfigErrors(nomesJogadores, coresJogadores)) {
                    MapView.getMapView();
                    Window.getWindow().configureMap();  
                } else {
					nomesJogadores.clear();
					coresJogadores.clear();
					JOptionPane.showMessageDialog(null, "Nomes ou cores inválidos ou repetidos", "Erro", JOptionPane.ERROR_MESSAGE);
				}
            }
        });

        startGameButton.setBounds(0,5,200,100);

        // Adicionar layout e botão de iniciar jogo
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        
    }

    // Desenha a imagem de fundo
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		this.graphic = (Graphics2D) graphic;
		this.graphic.drawImage(background, 0, 0, 1440, 900, null);

	}

    // Verificar casos de erro de configuração de jogadores
    public boolean verifyConfigErrors(ArrayList<String> nomes, ArrayList<Color> cores) {
        
        // Conjuntos para adicionar cores e nomes únicos
        Set<String> uniqueNames = new HashSet<>();
        Set<Color> uniqueColors = new HashSet<>();

        for(int i = 0; i < nomes.size(); i++) {
            String nome = nomes.get(i);
            Color cor = cores.get(i);

            // Verificar se há um nome igual a null
            if(nome == null || nome.equals("")) {
                return false;
            }

            // Verificar nomes ou cores repetidos
            if (!uniqueNames.add(nome) || !uniqueColors.add(cor)) {
                return false;
            }
        }

        return true;
    }

    // Desenha jogadores
	public void drawPlayers() {
        add(Box.createVerticalStrut(150));
		for(int i = 0;i < numPlayers;i++) {
			Selection selection = new Selection();
			add(selection);
			selectionComponents.add(selection);
		}
        add(startGameButton);
        add(Box.createVerticalGlue()); 
        add(Box.createVerticalStrut(250));

        try {
			background = ImageIO.read(new File ("resources/imagens/war_tabuleiro_fundo.png"));
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo\n");
		}
	}

    // Altera número de jogadores
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

    // Singleton
    public static PlayerConfig getPlayerConfig() {
        if(playerConfig == null) {
            playerConfig = new PlayerConfig();
        } 

        return playerConfig;
    }
}
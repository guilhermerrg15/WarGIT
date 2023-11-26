package View;

import javax.swing.*;
// import Controller.TratadorSelecao;
// import Model.Game;
// import Model.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Selection selection : selectionComponents) {
                    String playerName = selection.getNome();
                    Color playerColor = selection.getCor();
                    nomesJogadores.add(playerName);
                    coresJogadores.add(playerColor);
                }
                if(verifyConfigErrors(nomesJogadores, coresJogadores)) {
                    Map.getMap();
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
        add(startGameButton);
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
		for(int i = 0;i < numPlayers;i++) {
			Selection selection = new Selection();
			add(selection);
			selectionComponents.add(selection);
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
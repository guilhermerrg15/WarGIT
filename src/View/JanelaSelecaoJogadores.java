package View;

import javax.swing.*;

import Controller.TratadorSelecao;
import Model.Game;
import Model.Player;
import Model.PlayerColor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaSelecaoJogadores extends JFrame {
    private String imagePath = "resources/imagens/war_tabuleiro_fundo.png";
    private ArrayList<Player> players = new ArrayList<>();

    public JanelaSelecaoJogadores() {

        setTitle("War Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Carregar a imagem de fundo
        ImageIcon background = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(background);
        setContentPane(backgroundLabel);

        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(2, 3));
        playersPanel.setOpaque(false);
        add(playersPanel);
        setLayout(new GridBagLayout());
        
        JButton startGameButton = new JButton("Iniciar o Jogo");
        startGameButton.addActionListener(new TratadorSelecao(this));
        
        
        // startGameButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         createPlayers(); // Método para criar jogadores com base nas informações coletadas
        //         startGame(); // Método para iniciar o jogo com os jogadores criados
        //     }
        // });
        startGameButton.setBounds(50, 130, 500, 60);
        
        playersPanel.add(startGameButton);
        
        PlayerColor[] availableColors = PlayerColor.values();

        for (int i = 0; i < 6; i++) {
            JPanel playerPanel = new JPanel(new GridBagLayout());
            playerPanel.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel nameLabel = new JLabel("Jogador " + (i + 1));
            playerPanel.add(nameLabel, gbc);

            gbc.gridy = 1;
            JTextField nameField = new JTextField(10);
            playerPanel.add(nameField, gbc);

            gbc.gridy = 2;
            JComboBox<PlayerColor> colorComboBox = new JComboBox<>(availableColors);
            playerPanel.add(colorComboBox, gbc);

            playersPanel.add(playerPanel);
        }

        // Centraliza o painel de jogadores na janela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = background.getIconWidth();
        int height = background.getIconHeight();
        int panelWidth = playersPanel.getPreferredSize().width;
        int panelHeight = playersPanel.getPreferredSize().height;

        int x = (width - panelWidth) / 2;
        int y = (height - panelHeight) / 2;

        setLayout(null);
        playersPanel.setBounds(x, y, panelWidth, panelHeight);
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null); // Centraliza a janela principal
        setResizable(false); // Impede que a janela seja redimensionada
        setVisible(true);
        
        
    }
    private void createPlayers() {
        // Coletar nomes e cores selecionadas para cada jogador e criar instâncias de Player
        // Adicionar esses jogadores à lista de players
        // Exemplo:
        for (int i = 0; i < 6; i++) {
            // Obtenção dos nomes e cores selecionadas pelos jogadores
            String name = "Jogador " + (i + 1);
            PlayerColor color = PlayerColor.BLUE; // Suponha que você defina a cor padrão como azul

            // Player player = new Player(name, color);
            Player player = new Player(name);
            players.add(player);
        }
    }

    private void startGame() {
        // Criar uma instância de Game ou Match e fornecer a lista de jogadores para começar o jogo
        Game game = new Game();
        for (Player player : players) {
            game.addPlayer(player);
            System.out.println(player.getName());
        }
        game.startMatch();
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         new JanelaSelecaoJogadores();
    //     });
    // }
}


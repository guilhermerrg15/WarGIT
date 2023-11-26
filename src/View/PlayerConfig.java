package View;

import javax.swing.*;
import Controller.TratadorSelecao;
// import Model.Game;
// import Model.Player;
// import Model.PlayerColor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.ArrayList;

public class PlayerConfig extends JPanel {
    private String imagePath = "resources/imagens/war_tabuleiro_fundo.png";
    public static PlayerConfig playerConfig = null;
    // private ArrayList<Player> players = new ArrayList<>();
    JButton startGameButton = new JButton("Iniciar o Jogo");

    // Construtor da tela de configuração de jogadores
    public PlayerConfig() {
        // Carregar a imagem de fundo
        ImageIcon background = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(background);
        setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(2, 3));
        playersPanel.setOpaque(false);
        add(playersPanel, BorderLayout.SOUTH);

        
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map.getMap();
                Window.getWindow().configureMap();
            }
        });

        playersPanel.add(startGameButton);

        // PlayerColor[] availableColors = PlayerColor.values();

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
            // JComboBox<PlayerColor> colorComboBox = new JComboBox<>(availableColors);
            // playerPanel.add(colorComboBox, gbc);

            playersPanel.add(playerPanel);
        }
    }

    public static PlayerConfig getPlayerConfig() {
        if(playerConfig == null) {
            playerConfig = new PlayerConfig();
        } 

        return playerConfig;
    }
}
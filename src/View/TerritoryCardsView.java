package View;

import javax.swing.*;

import Model.API;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class TerritoryCardsView extends JFrame {
    private JLabel[] cardLabels;

    public TerritoryCardsView() {
        setTitle("Cartas de Conquista de Território");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLabels = new JLabel[API.getCartasTerritorio().length]; // Considerando o número de cartas no deck

        setLayout(new GridLayout(4, 11)); // 4 linhas e 11 colunas para exibir as cartas

        for (int i = 0; i < cardLabels.length; i++) {
            String carta = API.getCartasTerritorio()[i].getName(); // Obtém o nome da carta

            // Carrega a imagem da carta correspondente (supondo que você tenha as imagens de cada carta)
            BufferedImage cardImage = null;
            try {
                cardImage = ImageIO.read(new File("caminho/para/imagem/" + carta + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                // Trate a exceção ou apresente uma imagem padrão para indicar erro
            }

            cardLabels[i] = new JLabel(new ImageIcon(cardImage));
            add(cardLabels[i]);
        }

        pack();
        setLocationRelativeTo(null); // Centraliza a janela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TerritoryCardsView view = new TerritoryCardsView();
            view.setVisible(true);
        });
    }
}

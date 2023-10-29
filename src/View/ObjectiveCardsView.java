package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.ObjectiveCard;
import Model.ObjectiveCardDeck;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ObjectiveCardsView extends JPanel {
    private BufferedImage cardImage; // A imagem da carta
    private String objectiveText;
    private static final int MAX_CHARACTERS_PER_LINE = 250;

    public ObjectiveCardsView(String imagePath) {
        loadCardImage(imagePath);
        setRandomObjective();
    }

    private void loadCardImage(String imagePath) {
        try {
            File file = new File(imagePath);
            cardImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setRandomObjective() {
        ObjectiveCardDeck objectiveDeck = ObjectiveCardDeck.getInstance();
        String[] descriptions = objectiveDeck.getDescricao(); // Obtém as descrições dos objetivos
        Random random = new Random();
        int randomIndex = random.nextInt(descriptions.length); // Escolhe um índice aleatório
        objectiveText = descriptions[randomIndex]; // Define o texto do objetivo aleatório
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar a imagem da carta, se disponível
        if (cardImage != null) {
            g.drawImage(cardImage, 0, 0, this);
        }

        // Desenhar o texto do objetivo
        if (objectiveText != null && !objectiveText.isEmpty()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 40)); // Definir a fonte e o tamanho do texto
            
            
            int textX = 50; // Coordenada x do texto
            int textY = 200; // Coordenada y do texto - ajuste o valor para deslocar o texto verticalmente
            drawStringMultiLine(g, objectiveText, textX, textY, MAX_CHARACTERS_PER_LINE);
        }
    }
    
    
    private void drawStringMultiLine(Graphics g, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();
        int curY = y;
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (metrics.stringWidth(line.toString() + word) < maxWidth) {
                line.append(word).append(" ");
            } else {
                g.drawString(line.toString(), x, curY);
                curY += lineHeight;
                line = new StringBuilder(word + " ");
            }
        }
        g.drawString(line.toString(), x, curY);
    }

    // Método para atualizar a imagem da carta e o texto do objetivo
//    public void updateObjective(String imagePath, ObjectiveCard[] objectiveCards) {
//        loadCardImage(imagePath);
//        this.objectiveCards = objectiveCards;
//        repaint(); // Repintar a tela para exibir a nova imagem e texto
//    }

    public static void main(String[] args) {
        // Caminho para a imagem da carta
        String imagePath = "resources/imagens/war_carta_objetivo_grande.png";

        JFrame frame = new JFrame("Visualização de Objetivo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 602);

        ObjectiveCardsView objectiveView = new ObjectiveCardsView(imagePath);

        frame.add(objectiveView);
        frame.setVisible(true);
    }
}




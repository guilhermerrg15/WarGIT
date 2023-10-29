package View;

import javax.swing.*;

import Model.API;
import Model.ObjectiveCard;
import Model.ObjectiveCardDeck;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ObjectiveCardsView extends JFrame {
    private BufferedImage[] cardImages;
    private ObjectiveCard[] objectiveCards;
    ArrayList<String> descricoesCartas;
    
    
    

    public ObjectiveCardsView() {
        setTitle("Cartas de Objetivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        objectiveCards = new ObjectiveCard[8]; // 8 cartas conforme o seu modelo
        carregarCartasObjetivo();

        setPreferredSize(new Dimension(1200, 700)); // Tamanho da janela
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        
        descricoesCartas = API.getDescricoesCartas();

        if (descricoesCartas != null && !descricoesCartas.isEmpty()) {
            objectiveCards = new ObjectiveCard[descricoesCartas.size()];
            carregarCartasObjetivo();
        } else {
            // Tratamento caso a lista esteja vazia ou nula
            System.out.println("Lista de descrições de cartas está vazia ou nula.");
            // Pode ser útil exibir uma mensagem para o usuário informando que não há descrições de cartas disponíveis.
        }
        
        
    }
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        int x = 50; // Posição inicial x para desenhar as cartas
        int y = 50; // Posição inicial y para desenhar as cartas

        

        for (int i = 0; i < descricoesCartas.size(); i++) {
            // Desenhar a imagem da carta
            g2d.drawImage(cardImages[i], x, y, null);

            // Desenhar o texto do objetivo usando as informações do modelo via API
            g2d.setColor(Color.BLACK);
            g2d.drawString(descricoesCartas.get(i), x + 20, y + 200);

            x += 150; // Ajuste a posição x para a próxima carta
        }
    }

    private void carregarCartasObjetivo() {
        cardImages = new BufferedImage[8]; // 8 imagens de cartas conforme seu modelo

        // Carregar as imagens das cartas de objetivo
        carregarImagensCartasObjetivo();

        // Criar cartas de objetivo com descrições do modelo ObjectiveCardDeck
        for (int i = 0; i < descricoesCartas.size(); i++) {
            String descricao = descricoesCartas.get(i);
            objectiveCards[i] = new ObjectiveCard(descricao, "Alvo " + i);
        }
    }

//    private void carregarImagensCartasObjetivo() {
//        // Carregar imagens de cartas de objetivo (exemplo com caminho "caminho/para/imagem.jpg")
//        // Para cada carta, carregue a imagem correspondente
//        for (int i = 0; i < cardImages.length; i++) {
//            try {
//            	String imagePath = "../resources/war_carta_objetivo_grande.png"; 
//                cardImages[i] = ImageIO.read(new File(imagePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Trate a exceção ou apresente uma imagem padrão para indicar erro
//            }
//        }
//    }
    
    private void carregarImagensCartasObjetivo() {
        for (int i = 0; i < cardImages.length; i++) {
            try {
                String imagePath = "resources/imagens/war_carta_objetivo_grande.png";
                File file = new File(imagePath);
                if (file.exists()) {
                    cardImages[i] = ImageIO.read(file);
                } else {
                    System.out.println("O arquivo de imagem não foi encontrado: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao carregar a imagem da carta: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ObjectiveCardsView view = new ObjectiveCardsView();
            view.setVisible(true);
        });
    }
}



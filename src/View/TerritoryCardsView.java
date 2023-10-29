package View;

import javax.swing.*;

import Model.API;
import Model.Continent;
import Model.Territory;
import Model.TerritoryCard;
import Model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TerritoryCardsView extends JFrame {
    private JPanel cardsPanel;

    public TerritoryCardsView() {
        setTitle("Cartas de Território");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(cardsPanel);

        displayCards(); // Função para exibir as cartas recebidas
    }

    public void displayCards() {
        TerritoryCard[] territoryCards = API.getCartasTerritorio();
        Map map = Map.mapGenerator();

        for (TerritoryCard card : territoryCards) {
            if (card != null) { // Verifica se o card não é nulo
                Territory territory = map.findTerritory(card.getName());

                if (territory != null) {
                    Continent continent = map.findContinentFromTerritoryName(card.getName());
                    if (continent != null) {
                        String continentCode = getContinentCode(continent.getName());
                        String imagePath = String.format("../resources/imagens/war_carta_%s_%s.png", continentCode, card.getName());
                        ImageIcon cardImage = new ImageIcon(imagePath);

                        JLabel cardLabel = new JLabel();
                        cardLabel.setIcon(cardImage);
                        cardsPanel.add(cardLabel);
                    }
                }
            }
        }
    }

    private String getContinentCode(String continentName) {
        switch (continentName) {
            case "Africa":
                return "af";
            case "America do Norte":
                return "an";
            case "Asia":
                return "as";
            case "America do Sul":
                return "asl";
            case "Europa":
                return "eu";
            case "Oceania":
                return "oc";
            default:
                return ""; // Código vazio para um continente desconhecido (adapte conforme necessário)
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	TerritoryCardsView cardsDisplay = new TerritoryCardsView();
            cardsDisplay.setVisible(true);
        });
    }
}
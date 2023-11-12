package View;

import javax.swing.*;

import Model.API;
import Model.Continent;
import Model.TerritoryCard;
import Model.Map;
import Model.Territory;

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
            if (card != null) {
                String cardName = card.getName(); // Obtém o nome da carta

                Continent continent = map.findContinentFromTerritoryName(cardName);

                if (continent != null) {
                    String continentCode = getContinentCode(continent.getName());
                    String imagePath = String.format("../resources/imagens/war_carta_%s_%s.png", continentCode, cardName);

                    ImageIcon cardImage = new ImageIcon(imagePath);

                    JLabel cardLabel = new JLabel();
                    cardLabel.setIcon(cardImage);
                    cardsPanel.add(cardLabel);
                }
            }
        }
    }

    // Método para mapear os nomes dos continentes para códigos correspondentes
    private String getContinentCode(String continentName) {
        switch (continentName) {
            case "África":
                return "africa"; // Atualizado para refletir os nomes dos continentes
            case "América do Norte":
                return "america_norte";
            case "Ásia":
                return "asia";
            case "América do Sul":
                return "america_sul";
            case "Europa":
                return "europa";
            case "Oceania":
                return "oceania";
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

package View;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map extends JFrame {

    public Map() {
        // setTitle("Tabuleiro de War");
        super("War");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                try {
                    Image backgroundImage = ImageIO.read(new File("resources/imagens/war_tabuleiro_fundo.png"));
                    g.drawImage(backgroundImage, 0, 0, 1200, 700, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Image territoriesImage = ImageIO.read(new File("resources/imagens/war_tabuleiro_mapa copy.png"));
                    g.drawImage(territoriesImage, 0, 0, 1060, 675, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(400,500));
		add(panel);
		setLocationRelativeTo(null);
		setBounds(0,0,1200,700);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Adicione os botões ao painel de botões
        JButton checkObjectivesButton = new JButton("Ver Objetivos");
        JButton checkCardsButton = new JButton("Ver Cartas");
        JButton placeGlobalArmyButton = new JButton("Posicionar Exércitos Globais");
        JButton placeContinentalArmyButton = new JButton("Posicionar Exércitos Continentais");
        JButton attackButton = new JButton("Atacar");
        JButton moveTroopsButton = new JButton("Mover Tropas");
        JButton finishButton = new JButton("Finalizar Jogada");
        JButton cancelButton = new JButton("Cancelar");
        


        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        buttonPanel.add(checkObjectivesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(checkCardsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(placeGlobalArmyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(placeContinentalArmyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(attackButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(moveTroopsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(finishButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(cancelButton);


        // Adicione o painel de botões à lateral direita
        add(buttonPanel, BorderLayout.EAST);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map tabuleiro = new Map();
            tabuleiro.setVisible(true);
        });
    }
}


// import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.awt.image.BufferedImage;
// import java.io.IOException;
// import java.util.Hashtable;
// import java.util.List;

// import javax.imageio.ImageIO;

// import Model.Coordinates;
// import Model.Game;
// import Model.PlayerColor;

// public class Map extends Frame {
//    private Game warGame;
//    private BufferedImage foregroundImage;
//    public BufferedImage backgroundImage;
//    private Hashtable<String, TerritoryMap> territoriesMaps = new Hashtable<>();
   
//    public Map(Game game) throws IOException {
//        this.warGame = game;
//        this.backgroundImage = ImageIO.read(getClass().getResource(game.getMapForegroundPath()));
//        this.foregroundImage = ImageIO.read(getClass().getResource(game.getMapForegroundPath()));
//        List<String> territoryNames = warGame.getAllTerritoryNames();
//        setupTerritoryMaps(territoryNames);
       
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Point p = e.getPoint();
//                String territoryName = getTerritoryNameInPoint(p);
//                if (territoryName != null) {
//                    System.out.println("Clicked on territory: " + territoryName);
//                }
//            }
//        });
//    }

//    public interface ITerritoryListener {
//         public void onTerritoryOwnershipChange(String territoryName, PlayerColor newOwnerColor);
//         public void onTerritorySoldierCountChange(String territoryName, int newTerritoryCount);
//     }

//     private void setupTerritoryMaps(List<String> territoryNames) {
//         for (String territoryName : territoryNames) {
//             TerritoryMap territoryMap = new TerritoryMap(territoryName, warGame.getTerritoryVertices(territoryName));
//             territoriesMaps.put(territoryName, territoryMap);
//         }
//     }
   
//    public String getTerritoryNameInPoint(Point p) {
//        for (TerritoryMap territoryMap : territoriesMaps.values()) {
//            if (territoryMap.contains(p)) {
//                return territoryMap.territoryName;
//            }
//        }
//        return null;
//    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(backgroundImage, 0, 0, this);
//        for (TerritoryMap territoryMap : territoriesMaps.values()) {
//            territoryMap.draw(g2d);
//        }
//    }

//    private class TerritoryMap extends Polygon {
//        private String territoryName;
//        private PlayerColor ownerColor;
//        private int soldierCount;

//        public TerritoryMap(String territoryName, Coordinates[] vertices) {
//            for (Coordinates vertex : vertices) {
//                addPoint((int) (vertex.x * getWidth()), (int) (vertex.y * getHeight()));
//            }
//            this.territoryName = territoryName;
//        }

//        public void updateOwnerColor(PlayerColor color) {
//            ownerColor = color;
//        }

//        public void updateSoldierCount(int newSoldierCount) {
//            soldierCount = newSoldierCount;
//        }

//        public void draw(Graphics2D g2d) {
//            g2d.setColor(ownerColor.getColor()); // isso precisaria retornar uma Color e nÃ£o uma String
//            g2d.fill(this);
//            g2d.setColor(Color.BLACK);
//            g2d.drawString(territoryName + " (" + soldierCount + ")", getBounds().x, getBounds().y);
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        Game warGame = new Game(); // You should initialize WarGame with the proper parameters
//        Map worldMap = new Map(warGame);
//        worldMap.setSize(worldMap.backgroundImage.getWidth(), worldMap.backgroundImage.getHeight());
//        worldMap.setVisible(true);
//    }

// //    @Override
//    public void onTerritoryOwnershipChange(String territoryName, PlayerColor newOwnerColor) {
//        territoriesMaps.get(territoryName).updateOwnerColor(newOwnerColor);
//        repaint();
//    }

// //    @Override
//    public void onTerritorySoldierCountChange(String territoryName, int newSoldierCount) {
//        territoriesMaps.get(territoryName).updateSoldierCount(newSoldierCount);
//        repaint();
//    }
// }
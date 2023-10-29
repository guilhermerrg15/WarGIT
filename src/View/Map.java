package View

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WorldMap extends Frame implements ITerritoryListener {
    private WarGame warGame;
    private BufferedImage foregroundImage;
    private BufferedImage backgroundImage;
    private Hashtable<String, TerritoryMap> territoriesMaps = new Hashtable<>();
    
    public WorldMap(WarGame game) throws IOException {
        this.warGame = game;
        this.backgroundImage = ImageIO.read(getClass().getResource(game.getWorldMapBackgroundPath()));
        this.foregroundImage = ImageIO.read(getClass().getResource(game.getWorldMapForegroundPath()));
        List<String> territoryNames = warGame.getAllTerritoryNames();
        setupTerritoryMaps(territoryNames);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                String territoryName = getTerritoryNameInPoint(p);
                if (territoryName != null) {
                    System.out.println("Clicked on territory: " + territoryName);
                }
            }
        });
    }

    private void setupTerritoryMaps(List<String> territoryNames) {
        for (String territoryName : territoryNames) {
            Point center = warGame.getTerritoryCenter(territoryName);
            int x = (int) (center.x * getWidth());
            int y = (int) (center.y * getHeight());
            TerritoryMap territoryMap = new TerritoryMap(territoryName, warGame.getTerritoryVertices(territoryName));
            territoryMap.setLocation(x, y);
            territoriesMaps.put(territoryName, territoryMap);
            warGame.addTerritoryListener(territoryName, this);
        }
    }
    
    public String getTerritoryNameInPoint(Point p) {
        for (TerritoryMap territoryMap : territoriesMaps.values()) {
            if (territoryMap.contains(p)) {
                return territoryMap.territoryName;
            }
        }
        return null;
    }

    @Override
    public void onTerritoryOwnershipChange(String territoryName, PlayerColor newOwnerColor) {
        territoriesMaps.get(territoryName).updateOwnerColor(newOwnerColor);
        repaint();
    }

    @Override
    public void onTerritorySoldierCountChange(String territoryName, int newSoldierCount) {
        territoriesMaps.get(territoryName).updateSoldierCount(newSoldierCount);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, this);
        for (TerritoryMap territoryMap : territoriesMaps.values()) {
            territoryMap.draw(g2d);
        }
    }

    private class TerritoryMap extends Polygon {
        private String territoryName;
        private PlayerColor ownerColor;
        private int soldierCount;

        public TerritoryMap(String territoryName, Point[] vertices) {
            for (Point vertex : vertices) {
                addPoint((int) (vertex.x * getWidth()), (int) (vertex.y * getHeight()));
            }
            this.territoryName = territoryName;
        }

        public void updateOwnerColor(PlayerColor color) {
            ownerColor = color;
        }

        public void updateSoldierCount(int newSoldierCount) {
            soldierCount = newSoldierCount;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(ownerColor.getColor());
            g2d.fill(this);
            g2d.setColor(Color.BLACK);
            g2d.drawString(territoryName + " (" + soldierCount + ")", getBounds().x, getBounds().y);
        }
    }

    public static void main(String[] args) throws IOException {
        WarGame warGame = new WarGame(); // You should initialize WarGame with the proper parameters
        WorldMap worldMap = new WorldMap(warGame);
        worldMap.setSize(worldMap.backgroundImage.getWidth(), worldMap.backgroundImage.getHeight());
        worldMap.setVisible(true);
    }
}
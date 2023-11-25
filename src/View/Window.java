package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//import model.Observer; 

class Window extends JFrame {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    private LoadScene[] scenes;
    private static int currentSceneIndex;
    private int round;
//    private List<String> routines;
//    private int currentRoutineIndex;
//    private PlayerRoutine currentPlayerRoutine = PlayerRoutine.getInstance();

    public Window() {
        initializeWindow();
        initializeScenes();
        addMouseListener(createMouseListener());
    }

    private void initializeWindow() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initializeScenes() {
        scenes = new LoadScene[2];
        scenes[0] = new JanelaInicial(this);
//        scenes[1] = new JanelaJogo(this);
    }

    private MouseAdapter createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        };
    }

//    public String getCurrentRoutine() {
//        return routines.get(currentRoutineIndex);
//    }
//
//    public void advanceToNextRoutine() {
//        if (round == 0 && currentRoutineIndex == 1) {
//            currentRoutineIndex = 4;
//        } else {
//            currentRoutineIndex++;
//            if (currentRoutineIndex > 4) {
//                currentRoutineIndex = 0;
//            }
//            currentPlayerRoutine.setRoutineIndex(currentRoutineIndex);
//            this.repaint();
//        }
//    }

    public JButton getButton(int sceneIndex, int position) {
        return scenes[sceneIndex].getButton(position);
    }

    public List<String> getPlayerNames(int sceneIndex) {
        return scenes[sceneIndex].getPlayerNames();
    }

    public List<String> getPlayerColors(int sceneIndex) {
        return scenes[sceneIndex].getPlayerColors();
    }

//    public Observer getGameObserver() {
//        return (Observer) scenes[1];
//    }

//    public int getNumberOfPlayers() {
//        return JanelaInicial.getNumberOfPlayers();
//    }

//    public DesenhaTerritorioPoligono formas_geometricas_clicada(int x, int y) {
//		return cenarios[cenario].formas_geometricas_clicada(x,y);
//	}

    public JLabel createQuantityTextField(String value) {
        JLabel label = new JLabel(value);
        label.setBounds(457, 665, 69, 69);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(new Font(font.getFontName(), Font.PLAIN, 24));
        this.getContentPane().add(label);
        return null;  // O método original sempre retorna null, pode ser ajustado conforme necessário
    }

    public JLabel createTextFieldForTerritoryName(String name, int x, int y, int width, int height) {
        JLabel territoryLabel = new JLabel(name);
        territoryLabel.setBounds(x, y, width, height);
        territoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = territoryLabel.getFont();
        territoryLabel.setFont(new Font(font.getFontName(), Font.PLAIN, 13));
        this.getContentPane().add(territoryLabel);
        return territoryLabel;
    }
    
    public void deleteTextField() {
        getContentPane().removeAll();
    }

//    private void switchToGameScene() {
//        getContentPane().removeAll();
//        Hack.getInstance(this, 1024, 75);
//        currentSceneIndex = 1;
//        this.round = 0;
//        repaint();
//    }

    public void incrementRound() {
        this.round++;
    }

    public int getCurrentRound() {
        return this.round;
    }

//    public void reset() {
//        this.round = 0;
//        this.currentRoutineIndex = 0;
//    }

    public void repaintAllComponents() {
        Component[] components = this.getContentPane().getComponents();
        for (Component component : components) {
            component.repaint();
        }
    }

//    public ClickedGeometry[] getGeometries() {
//        return scenes[currentSceneIndex].getGeometries();
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        scenes[currentSceneIndex].draw(g);
        repaintAllComponents();
//        if (currentSceneIndex == 1) {
//            currentPlayerRoutine.showLayout(g);
//        }
    }
}


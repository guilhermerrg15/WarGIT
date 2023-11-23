package View;

import javax.swing.*;
import java.awt.*;
import Controller.MouseListener;


public class GameScene extends JFrame {
    public final int xScreen = 867;
    public final int yScreen = 656;
    private JanelaInicial janelaInicial;
    private JanelaSelecaoJogadores janelaSelecaoJogadores;
    
    public GameScene() {
        gerarPainel();
    }

    public void gerarPainel() {
        this.janelaInicial = new JanelaInicial();
        this.janelaSelecaoJogadores = new JanelaSelecaoJogadores();
        janelaInicial.addMouseListener(new MouseListener());

        setTitle("War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xScreen, yScreen);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        JPanel menuPanel = janelaInicial.getMenuPanel();
        contentPane.add(janelaSelecaoJogadores);
        contentPane.add(menuPanel, BorderLayout.EAST);
        setVisible(true);
    }
    public void redraw() {
        repaint();
    }

    public JanelaSelecaoJogadores getJanelaSelecaoJogadores() {
        return this.janelaSelecaoJogadores;
    }
}
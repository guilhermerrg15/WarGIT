package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Model.Dado;
import Model.PlayerColor;
import java.util.Random;

public class JogadasView extends JFrame {
    private JPanel mainPanel;
    private JPanel dadosAtaquePanel;
    private JPanel dadosDefesaPanel;
    private JButton jogarDadosButton;
    private MeuJPanel[] dadosAtaquePanels;
    private MeuJPanel[] dadosDefesaPanels;
    private PlayerColor corAtaque;
    private PlayerColor corDefesa;
    private int contadorBotao;

    public JogadasView() {
        setTitle("War Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Configurar o botão com uma imagem
        jogarDadosButton = new JButton();
        jogarDadosButton.setContentAreaFilled(false);
        jogarDadosButton.setBorderPainted(false);

        // Definir a imagem do botão
        try {
            BufferedImage buttonImage = ImageIO.read(new File("resources/imagens/war_btnJogarDados.png"));
            ImageIcon buttonIcon = new ImageIcon(buttonImage);
            jogarDadosButton.setIcon(buttonIcon);
        } catch (IOException e) {
            System.err.println("Erro ao carregar a imagem do botão.");
        }

        dadosAtaquePanel = new JPanel();
        dadosDefesaPanel = new JPanel();
        dadosAtaquePanel.setLayout(new GridLayout(1, 3));
        dadosDefesaPanel.setLayout(new GridLayout(1, 3));

        dadosAtaquePanels = new MeuJPanel[3];
        dadosDefesaPanels = new MeuJPanel[3];

        corAtaque = escolherCorAleatoria();
        corDefesa = escolherCorAleatoriaDiferenteDe(corAtaque);

        for (int i = 0; i < 3; i++) {
            dadosAtaquePanels[i] = new MeuJPanel();
            dadosDefesaPanels[i] = new MeuJPanel();

            dadosAtaquePanel.add(dadosAtaquePanels[i]);
            dadosDefesaPanel.add(dadosDefesaPanels[i]);
        }

        mainPanel.add(jogarDadosButton);
        mainPanel.add(dadosAtaquePanel);
        mainPanel.add(dadosDefesaPanel);
        add(mainPanel);

        jogarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contadorBotao == 0) {
                    exibirDadosAtaque();
                    contadorBotao++;
                } else if (contadorBotao == 1) {
                    exibirDadosDefesa();
                    jogarDadosButton.setEnabled(false);
                }
            }
        });
    }

    private void exibirDadosAtaque() {
        for (int i = 0; i < 3; i++) {
            Dado.jogaDado();
            int valorAtaque = Dado.getDado();
            String imagePathAtaque = "resources/imagens/dado_ataque_" + valorAtaque + ".png";
            dadosAtaquePanels[i].setImagem(imagePathAtaque);
            dadosAtaquePanels[i].setCorFundo(corAtaque.getColor());
        }
    }

    private void exibirDadosDefesa() {
        for (int i = 0; i < 3; i++) {
            Dado.jogaDado();
            int valorDefesa = Dado.getDado();
            String imagePathDefesa = "resources/imagens/dado_defesa_" + valorDefesa + ".png";
            dadosDefesaPanels[i].setImagem(imagePathDefesa);
            dadosDefesaPanels[i].setCorFundo(corDefesa.getColor());
        }
    }

    private PlayerColor escolherCorAleatoria() {
        PlayerColor[] cores = PlayerColor.values();
        Random rand = new Random();
        return cores[rand.nextInt(cores.length)];
    }

    private PlayerColor escolherCorAleatoriaDiferenteDe(PlayerColor cor) {
        PlayerColor[] cores = PlayerColor.values();
        PlayerColor corAleatoria;

        do {
            corAleatoria = escolherCorAleatoria();
        } while (corAleatoria == cor);

        return corAleatoria;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JogadasView warView = new JogadasView();
            warView.setVisible(true);
        });
    }
}

class MeuJPanel extends JPanel {
    private JLabel label;

    public MeuJPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }

    public void setImagem(String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(img);
            label.setIcon(icon);
        } catch (IOException e) {
            System.err.println("Erro ao carregar a imagem: " + imagePath);
        }
    }

    public void setCorFundo(Color cor) {
        setBackground(cor);
    }
}







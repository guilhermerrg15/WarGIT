//package View;
//
//import Model.Dado;
//import Model.Player;
//import Model.PlayerColor;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class JogadasView extends JFrame {
//    private JPanel jogadorDaVez;
//    private JPanel resultadoDados;
//    private JButton botaoJogarDado;
//
//    private ImageIcon[] imagensDosDados; // Array de imagens dos dados de 1 a 6
//
//    public JogadasView() {
//        // Inicializar a interface gráfica
//        // Configurar o layout, painéis e botões
//
//
//        jogadorDaVez = new JPanel();
//        jogadorDaVez.setPreferredSize(new Dimension(200, 50));
//
//        resultadoDados = new JPanel();
//        resultadoDados.setLayout(new GridLayout(1, 2)); // Para exibir 2 dados
//
//        botaoJogarDado = new JButton("Jogar Dado");
//        botaoJogarDado.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                simularJogada(); // Chamada à função para simular a jogada dos dados
//            }
//        });
//
//        this.add(jogadorDaVez, BorderLayout.NORTH);
//        this.add(resultadoDados, BorderLayout.CENTER);
//        this.add(botaoJogarDado, BorderLayout.SOUTH);
//    }
//    
//    private Color getColorFromPlayerColor(PlayerColor playerColor) {
//        switch (playerColor) {
//            case BLUE:
//                return Color.BLUE;
//            case YELLOW:
//                return Color.YELLOW;
//            case RED:
//                return Color.RED;
//            case BLACK:
//                return Color.BLACK;
//            case WHITE:
//                return Color.WHITE;
//            case GREEN:
//                return Color.GREEN;
//            default:
//                return Color.BLACK; // Retorna preto por padrão
//        }
//    }
//
// // Método para simular o lançamento dos dados e atualizar a interface
//    public void simularJogada() {
//        Dado.jogaDado(); // Simulação do lançamento do dado
//
//        int resultadoDado = Dado.getDado(); // Obtém o resultado do dado
//
//        // Exibe a imagem correspondente ao resultado do dado na interface
//        resultadoDados.removeAll();
//        resultadoDados.add(new JLabel(imagensDosDados[resultadoDado - 1]));
//
//        // Atualiza a cor do painel do jogador da vez para indicar o próximo jogador (utilizando a lógica do modelo)
//      
//        Player jogadorAtual = // Obtém o jogador da vez do modelo
//        jogadorDaVez.setBackground(getColorFromPlayerColor(jogadorAtual.getColor()));
//    }
//}
//

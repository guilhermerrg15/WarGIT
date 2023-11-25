package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class JanelaInicial extends LoadScene {
    protected static List<JButton> button = new ArrayList<>();
    protected static List<String> playerName = new ArrayList<>();
    protected static List<String> colors = new ArrayList<>(Arrays.asList("azul", "verde", "vermelho", "branco", "preto", "amarelo"));
	protected static List<String> playerColor = new ArrayList<>();
	protected static JComboBox<String> opCoresComboBox;
	protected static JTextField nm;
	protected static int num_jogadores;
	protected static boolean escolhendo_cores;

    public JanelaInicial(Window tela) {
        this.images = new ImageDrawer[1];
        imageLoaded(new ImageDrawer("war_janelaInicial.png", 0, -200, 1200, 1000));
        Container c = tela.getContentPane();
        c.setLayout(null);

        JButton b1 = new JButton("Novo Jogo");
        b1.setBounds(550, 500, 100, 30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setImageCount(0);
                removeComponents(tela);
                escolhe_qtd_jogadores(tela);
            }
        });

        JButton bskip = new JButton("Continuar");
        bskip.setBounds(550, 550, 100, 30);

        JButton b3 = new JButton("3"); 
		b3.setBounds(530, 250, 100, 30);
		b3.addActionListener(new ActionListener() {
            int valor = 3; 

            @Override
            public void actionPerformed(ActionEvent e) {
                valor++; 
                if (valor > 6) {
                    valor = 3; 
                }
                b3.setText(Integer.toString(valor)); 
            }
        });
		
		JButton b2 = new JButton("Confirmar");
        b2.setBounds(530, 300, 100, 30);
        b2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JanelaInicial.set_num_jogadores(b3.getText());
                removeComponents(tela);
                JanelaInicial.set_escolhendo_cores(true);
                escolhe_nome_jogadores_cor(tela);
                tela.repaint();
            }
        });
        
        JButton inc = new JButton("Confirmar");
        inc.setBounds(195+350, 270+100, 165, 25);
        
        button.add(bskip);
		button.add(b1);
		button.add(b2);
		button.add(b3);
		button.add(inc);		
		c.add(b1); 
		c.add(bskip);
		c.repaint();
    }

    private static void removeComponents(Window tela) {
        tela.getContentPane().removeAll();
    }

    private static void escolhe_qtd_jogadores(Window tela) {
		Container c = tela.getContentPane();
        c.add(button.get(2));
        c.add(button.get(3));
        c.repaint(); 
	}
    
    public static ActionListener salva_jogadores(Window tela) {
		return new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        boolean algumSelecionado = opCoresComboBox.getSelectedIndex() != -1;

		        if (algumSelecionado && !nm.getText().isEmpty()) {
		        	int selectedColorIndex = opCoresComboBox.getSelectedIndex();
		        	playerName.add(nm.getText());
		        	playerColor.add(colors.get(selectedColorIndex));
		            removeComponents(tela);
		            if (JanelaInicial.get_num_jogadores() > 1) {
		                // Verifique qual opção foi selecionada no JComboBox
		                if (selectedColorIndex != -1) {
		                    colors.remove(selectedColorIndex);
		                }
		                JanelaInicial.set_num_jogadores(JanelaInicial.get_num_jogadores() - 1);
		                escolhe_nome_jogadores_cor(tela);
		            } else {
		            	JanelaInicial.set_num_jogadores(JanelaInicial.get_num_jogadores() - 1);
//		                tela.trocarParaJanelaJogo();
		            
		            }
		            Container c = tela.getContentPane();
		            c.repaint();
		        }
		    }
		};
	}
    
	private static void escolhe_nome_jogadores_cor(Window tela) {
		int dx = 350;
		int dy = 100;
		Container c = tela.getContentPane();
		JLabel ln = new JLabel("Nome");
		nm = new JTextField();
		
		ln.setBounds(40+dx, 83+dy, 65, 25);
		nm.setBounds(110+dx, 80+dy, 250, 25);
				

		// Substituir JRadioButton e ButtonGroup por JComboBox
		opCoresComboBox = new JComboBox<>();
		opCoresComboBox.setBounds(50+dx, 120+dy, 100, 30);

		for (String cor : colors) {
		    opCoresComboBox.addItem(cor);
		}

		c.add(ln);
		c.add(nm);
		
		c.add(button.get(4));
		
		c.add(opCoresComboBox);
		c.repaint();
	}
    
	public List<String> get_jogares_color(){
		return playerColor;
	}
    
	public static int get_num_jogadores() {
		return num_jogadores;
	}
	
	public static void set_num_jogadores(String number) {
		num_jogadores = Integer.parseInt(number);
	}
	public static void set_num_jogadores(int number) {
		num_jogadores = number;
	}
	public static void set_escolhendo_cores(boolean status) {
		escolhendo_cores = status;
	}

    public JButton get_button(int pos) {
        return button.get(pos);
    }

    public List<String> getPlayersName(){
		return playerName;
	}
    
    public void desenha(Graphics g) {
        for (int i = 0; i < this.imageCount; i++) {
            if (images[i] != null) {
                g.drawImage(images[i].getImage(), images[i].getX(), images[i].getY(), images[i].getW(), images[i].getH(), null);
            }
        }
    }

}
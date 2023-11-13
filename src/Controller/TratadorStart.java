package Controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.*;
import View.*;


public class TratadorStart implements ActionListener {
    Component c;
    
    public TratadorStart(Component x) {
        c = x;
    }
    public void actionPerformed(ActionEvent e) {
        JFrame window = new JanelaSelecaoJogadores();
        
    }
}

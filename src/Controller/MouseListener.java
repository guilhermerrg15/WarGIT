package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.API;

public class MouseListener extends MouseAdapter {
    private API api = API.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Clicked at: ("+x+","+y+")");

    }

}

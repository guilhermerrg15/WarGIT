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

    }
    int getPosition(int x, int y){
		if(x>=0 && x<=240 && y>=0 && y<=240){
            return -1;
        }
        else if(x>=0 && x<=240 && y>=360 && y<=600){
            return -1;
        }
        else if(x>=360 && x<=600 && y>=0 && y<=240){
            return -1;
        }
        else if(x>=360 && x<=600 && y>=360 && y<=600){
            return -1;
        }
        else if (x>=320 && x<=360 && y>=40 && y<= 240){
            return -1 + (y/40);
        }

        return 9999;
    }
}

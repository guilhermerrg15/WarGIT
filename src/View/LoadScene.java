package View;

import java.util.ArrayList;
//import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.util.List;
//import Model.Observado;

import javax.swing.JButton;

abstract class LoadScene {
    protected ImageDrawer[] images;
    protected OutlineTerritory[] geometric_shapes;
    protected int imageCount;
//    protected int shapeCount;

//    public OutlineTerritory[] getShapes() {
//        return geometric_shapes;
//    }

    public void setImageCount(int count) {
        this.imageCount = count;
    }
	// public void notify(Observado o) {
	
	// }

    public void imageLoaded(ImageDrawer image) {
        imageCount++;
        this.images[imageCount - 1] = image;
    }

//    public void shapeLoaded(OutlineTerritory shape) {
//        shapeCount++;
//        this.geometric_shapes[shapeCount - 1] = shape;
//    }

    public void draw(Graphics g) {
        for (int i = 0; i < this.imageCount; i++) {
            if (images[i] != null) {
                g.drawImage(images[i].getImage(), images[i].getX(), images[i].getY(), images[i].getW(),
                        images[i].getH(), null);
            }
        }

//        Graphics2D g2d = (Graphics2D) g;
//        for (int i = 0; i < this.shapeCount; i++) {
//            if (geometric_shapes[i] != null) {
//                g2d.setColor(geometric_shapes[i].getColor());
//                g2d.fill(geometric_shapes[i].getPolygon());
//                g2d.setColor(Color.BLACK);
//                g2d.draw(geometric_shapes[i].getPolygon());
//            }
//        }
    }

//    public OutlineTerritory clickedShape(int x, int y) {
//        for (int i = 0; i < this.shapeCount; i++) {
//            if (geometric_shapes[i].isClicked(x, y)) {
//                return geometric_shapes[i];
//            }
//        }
//        return null;
//    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(); // Implementação padrão, pode ser substituída nas subclasses
    }

    public List<String> getPlayerColors() {
        return new ArrayList<>(); // Implementação padrão, pode ser substituída nas subclasses
    }

    public JButton getButton(int position) {
        return new JButton(); // Implementação padrão, pode ser substituída nas subclasses
    }
}

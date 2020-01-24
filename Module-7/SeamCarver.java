import java.awt.Color;

/**
 * Seam carver class to implment the concept of seam carving.
 * In this partocular module, we are going to clacuate energy of a particular pixel. 
 * @author K. Ranjith Kumar
 * */
public class SeamCarver {
    int width;
    int height;
    Picture pic;

    /**
     * Constructor when an object is created.Takes parameter as pic of class picture.
     */
    public SeamCarver(Picture pic) {
        if(pic == null) {
            throw new IllegalArgumentException("pic is null");
        }

        width = pic.width();
        height = pic.height();
        this.pic = pic;
    }
 
    /**
     * pic method to return pic
     */
    public Picture pic() {
        return pic;
    }
 
    /**
     * width method to return width of the picture. In this the width is 3
     */
    public int width() {
        return width;
    }
 
    /**
     * height method returns the height of a picture. In this one, the height is 7.
     */
    public int height() {
        return height;
    }
 
    /**
     * energy method to calcualte the energy os particular pixel at column x and row y.
     * @param x and y
     */
    public double energy(int x, int y) {
        if(x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException();
        }
        if(x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }

        int deltaX = del_energyCal(x + 1, y, x - 1, y);
        int deltaY = del_energyCal(x - 1, y, x - 1, y);
        
        double energy = Math.sqrt(deltaX + deltaY);
        return energy;
    }

    /**
     * energy method to calcualte the energy of particular pixel at column x and row y.
     * @oaram x1, y1, x2, y2
     */
    private int del_energyCal(int x1, int y1, int x2, int y2) {
        Color a = pic.get(x1, y1);
        Color b = pic.get(x2, y2);

        int Red = a.getRed() - b.getRed();
        int Green = a.getGreen() - b.getGreen();
        int Blue = a.getBlue() - b.getBlue();

        int RedGreenBlue = (Red * Red) + (Green * Green) + (Blue * Blue);
        return RedGreenBlue;

    } 
}


 
import java.awt.Color;

/**
 * Seam carver class to implment the concept of seam carving.
 * In this partocular module, we are going to clacuate energy of a particular pixel. 
 * @author K. Ranjith Kumar
 * @reference Github
 * @reference Bob sedgewick
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

    /**
     * this method is used for finding the vertical seam of a picture havinf low energies.
     * @return returns an array of seam of energies.
     */
    public int[] findVerticalSeam() {

        double[][] energyMatrix = new double[width()][height()];
        int[][] matrixTo = new int[width()][height()];

        for(int x =0; x< width(); x++) {
            for(int y=0; y< height(); y++) {
                energyMatrix[x][y] = Double.POSITIVE_INFINITY;
            }
        }
        for(int x =0; x < width(); x++) {
            energyMatrix[x][0] = 1000;
        }
        
        for(int j=0; j< height() - 1; j++) {
            for(int i=0; i < width(); i++ ) {
                if(i>0) {
                    if(energyMatrix[i-1][j+1] > energyMatrix[i][j] + energy(i-1,j+1)) {
                        energyMatrix[i-1][j+1] = energyMatrix[i][j] + energy(i-1,j+1);
                        matrixTo[i-1][j+1] = i;
                    }

                }

                if(energyMatrix[i][j+1] > energyMatrix[i][j] + energy(i,j+1)) {
                    energyMatrix[i][j+1] = energyMatrix[i][j] + energy(i,j+1);
                    matrixTo[i][j+1] = i;
                }
                

                if(i < width()-1) {
                    if(energyMatrix[i+1][j+1] > energyMatrix[i][j] + energy(i+1,j+1)) {
                        energyMatrix[i+1][j+1] = energyMatrix[i][j] + energy(i+1,j+1);
                        matrixTo[i+1][j+1] = i;
                    }
                }
            }
        }
       
        double minEnergy = Double.POSITIVE_INFINITY;
        int minEnergyX = -1;
        for(int w =0; w < width() ; w++) {
            if(energyMatrix[w][height()-1] < minEnergy) {
                minEnergyX = w;
                minEnergy = energyMatrix[w][height() - 1];
            }
        }

        int[] seam = new int[height()];
        seam[height() -1] = minEnergyX;
        int prevX = matrixTo[minEnergyX][height() - 1];

        for(int h = height() - 2; h >= 0 ; h--) {
            seam[h] = prevX;
            prevX = matrixTo[prevX][h];
        }
    return seam;
    
    }

     /**
      * method to find the horizontal seam of the picture.
      @return returns an array containing pixel energies having low energies horizonatally.
      */
    public int[] findHorizontalSeam() {
        int[][] matrixTo = new int[height()][width()];
        double[][] energyMatrix = new double[height()][width()];
        //reset(energyMatrix);

        for(int i = 0; i < energyMatrix.length; i++) {
            for(int j = 0; j < energyMatrix[i].length; j++) {
                energyMatrix[i][j] = Double.MAX_VALUE;
            }
        }

        for (int row = 0; row < height(); row++) {
            energyMatrix[row][0] = 1000;
        }
        // this is for relaxation.
        for (int col = 0; col < width() - 1; col++) {
            for (int row = 0; row < height(); row++) {
                //relaxH(row, col, matrixTo, energyMatrix);
                int nextcol = col + 1;
                for (int i = -1; i <= 1; i++) {
                    int nextrow = row + i;
                    if (nextrow < 0 || nextrow >= height()) continue;
                    if(i == 0) {
                        if(energyMatrix[nextrow][nextcol] >= energyMatrix[row][col]  + energy(nextcol, nextrow)) {
                            energyMatrix[nextrow][nextcol] = energyMatrix[row][col]  + energy(nextcol, nextrow);
                            matrixTo[nextrow][nextcol] = i;
                        }
                    }
                    if (energyMatrix[nextrow][nextcol] > energyMatrix[row][col]  + energy(nextcol, nextrow)) {
                        energyMatrix[nextrow][nextcol] = energyMatrix[row][col]  + energy(nextcol, nextrow);
                        matrixTo[nextrow][nextcol] = i;
                    }
                }

            }
        }

        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height(); row++) {
            if (minDist > energyMatrix[row][width() - 1]) {
                minDist = energyMatrix[row][width() - 1];
                minRow = row;
            }
        }

        int[] indices = new int[width()];
        for (int col = width() - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= matrixTo[row][col];
        }
        return indices;
    }
    
    /**
     * Method to remove the vertical seam obtained by finidng methods above.
     * @param array of seam to be removed obtaied in find method.
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }

        if (seam.length != height()) {
            throw new IllegalArgumentException();
        }

        Picture original = this.pic;
        Picture carvedPic = new Picture(original.width() - 1, original.height());

        for (int h = 0; h < carvedPic.height(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                carvedPic.set(w, h, original.get(w, h));
            }
            for (int w = seam[h]; w < carvedPic.width(); w++) {
                carvedPic.set(w, h, original.get(w + 1, h));
            }
        }

        this.pic = carvedPic;
        
        width = pic.width();
        height = pic.height();
    }

    /**
     * method to implement removing horizontal seams in a picture.
     * @param seam yet to be removed passed as parameter.
     */
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }

        if (seam.length != width()) {
            throw new IllegalArgumentException();
        }

        Picture original = this.pic;
        Picture carvedPic = new Picture(original.width() , original.height()-1);

        for (int h = 0; h < carvedPic.width(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                carvedPic.set(h,w, original.get(h,w));
            }
            for (int w = seam[h]; w < carvedPic.height()-1; w++) {
                carvedPic.set(h,w, original.get(h,w+1));
            }
        }

        this.pic = carvedPic;
        
        width = pic.width();
        height = pic.height();
    }
}
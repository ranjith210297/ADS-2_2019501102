
import java.awt.Color;
import edu.princeton.cs.algs4.Picture;


/**
 * Seam carver class to implment the concept of seam carving.
 * In this partocular module, we are going to clacuate energy of a particular pixel. 
 * @author K. Ranjith Kumar
 * 
 * @reference Github
 * @reference Bob sedgewick

 * AFTER SUBMISSION ON COURSERA - SCORE IS-- 80%
 * SCREENSHOT IS PRODUCED IN GITHUB MODULE-10 AND LMS MODULE-10
 * PROJECT FILE IS ALSO SUBMITTED IN BOTH MODULE-10 AND PROJECT REPORT IN LMS.
 * */
public class SeamCarver {
    private int width;
    private int height;
    private Picture picture;

    /**
     * Constructor when an object is created.Takes parameter as picture of class picture.
     */
    public SeamCarver(Picture picture) {
        if(picture == null) {
            throw new IllegalArgumentException("picture is null");
        }

        width = picture.width();
        height = picture.height();
        this.picture = picture;
    }
 
    /**
     * picture method to return picture
     */
    public Picture picture() {
        return picture;
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
            throw new IllegalArgumentException();
        }
        if(x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }

        int deltaX = del_energyCal(x + 1, y, x - 1, y);
        int deltaY = del_energyCal(x, y+1, x , y-1);
        
        double energy = Math.sqrt(deltaX + deltaY);
        return energy;
    }

    /**
     * energy method to calcualte the energy of particular pixel at column x and row y.
     * @Param x1, y1, x2, y2
     */
    private int del_energyCal(int x1, int y1, int x2, int y2) {
        Color a = picture.get(x1, y1);
        Color b = picture.get(x2, y2);

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
    private double[][] transposeMatrix() {
        Picture transPic = new Picture(picture.height(), picture.width());
        double[][] newEnergy = new double[picture.height()][picture.width()];
        double[][] energy = new double[picture.width()][picture.height()];
        for (int i = 0; i < picture.width(); i++) {
            for (int k = 0; k < picture.height(); k++) {
                transPic.set(k, i, picture.get(i, k));
                newEnergy[k][i] = energy[i][k];
            }
        }
        
        picture = transPic;
        width = picture.width();
        height = picture.height();
        return newEnergy;
    }

    /**
    Method for finding the horizontal seam of the picture given as argument.
    */
    public int[] findHorizontalSeam() {
        transposeMatrix();
        int[] horizontal_seam = findVerticalSeam();
        transposeMatrix();
        return horizontal_seam;
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

        Picture original = this.picture;
        Picture carvedPic = new Picture(original.width() - 1, original.height());

        for (int h = 0; h < carvedPic.height(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                carvedPic.set(w, h, original.get(w, h));
            }
            for (int w = seam[h]; w < carvedPic.width(); w++) {
                carvedPic.set(w, h, original.get(w + 1, h));
            }
        }

        this.picture = carvedPic;
        
        width = picture.width();
        height = picture.height();
    }


    /**
     * method to implement removing horizontal seams in a picture.
     * @param seam array of seam or energies to be removed passed as parameter.
     */
   public void removeHorizontalSeam(int[] seam) {
      if (width() <= 1 || height() <= 1 || seam.length >width() )
         throw new IllegalArgumentException();

      Picture carvedPicture = new Picture(width(), height() - 1);

      for (int col = 0; col < width(); col++)
         for (int row = 0; row < height() - 1; row++) {

            if (row < seam[col])
               carvedPicture.set(col, row, picture.get(col, row));
            else
               carvedPicture.set(col, row, picture.get(col, row + 1));

         }

      height = height - 1;
      picture = new Picture(carvedPicture);
   }

}
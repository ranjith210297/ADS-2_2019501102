
import java.util.Arrays;
public class ShowEnergy {

    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        StdOut.printf("image is %d columns by %d rows\n", picture.width(), picture.height());
        picture.show();        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Displaying energy calculated for each pixel.\n");
        SCUtility.showEnergy(sc);
        int[] p = sc.findVerticalSeam();
        System.out.println(p.length);
        System.out.println(Arrays.toString(p));

    }

}

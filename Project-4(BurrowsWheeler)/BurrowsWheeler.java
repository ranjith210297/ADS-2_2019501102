import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;

/**
 * class to implement the burrowswheeler transforming using circularArr suffix array class.
 * @author k. Ranjith kumar
 * @reference Bob sedgewick.
 */
public class BurrowsWheeler {
    private static final int R = 256;

    /**
     * method to transform the inout and output.
     */
    public static void transform() {
        String str = BinaryStdIn.readString();
        

        CircularSuffixArray circularArr = new CircularSuffixArray(str);

        int len = str.length();
        for (int i = 0; i < len; i++)
            if (circularArr.index(i) == 0)
                BinaryStdOut.write(i);
        for (int i = 0; i < len; i++)
            BinaryStdOut.write(str.charAt((circularArr.index(i) + len - 1) % len), 8);

        BinaryStdOut.close();
    }

    /**
     * Transforming the string to inverse using circularArr suffix array.
     */
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String str = BinaryStdIn.readString();

        int len = str.length();
        char[] t = str.toCharArray();
        char[] sorted = new char[len];
        int[] count = new int[R + 1];
        for (int i = 0; i < len; i++)
            count[t[i] + 1]++;
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];
        for (int i = 0; i < len; i++)
            sorted[count[t[i]]++] = t[i];

        count = new int[R + 1];
        for (int i = 0; i < len; i++)
            count[sorted[i] + 1]++;
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        int[] next = new int[len];
        for (int j = 0; j < len; j++) {
            int i = count[t[j]]++;
            next[i] = j;
        }

        for (int i = 0, j = first; i < len; i++) {
            BinaryStdOut.write(sorted[j]);
            j = next[j];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) return;

        if ("-".equals(args[0]))
            transform();

        else if ("+".equals(args[0]))
            inverseTransform();
    }
}
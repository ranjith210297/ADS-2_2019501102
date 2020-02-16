
/**
 * class that implements move to front .
 * @author k. Ranjith kumar 
 * @reference Bob Sedgewick
 */
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.BinaryStdIn;


public class MoveToFront {
    private static final int R = 256;

    /**
     * method for applying encoding inputArr and output.
     * 
     */
    public static void encode() {
        char[] sequenceArr = new char[R];
        for (int i = 0; i <= R; i++) {
            sequenceArr[i] = (char) i;
        }

        String s = BinaryStdIn.readString();
        char[] inputArr = s.toCharArray();

        for (int i = 0; i < inputArr.length; i++) {
            char in = inputArr[i];

            int out;
            for (out = 0; out < sequenceArr.length; out++) {
                if (sequenceArr[out] == in) {
                    break;
                }
            }
            assert out < sequenceArr.length;

            BinaryStdOut.write((char) out);

            System.arraycopy(sequenceArr, 0, sequenceArr, 1, out);
            sequenceArr[0] = in;
        }

        BinaryStdOut.close();
    }


    /**
     * method for applying decoding iput and output.
     */
    public static void decode() {
        char[] sequenceArr = new char[R];
        for (int i = 0; i < R; i++) {
            sequenceArr[i] = (char) i;
        }

        String s = BinaryStdIn.readString();
        char[] inputArr = s.toCharArray();

        for (int i = 0; i < inputArr.length; i++) {
            int in = inputArr[i];
            char ch = sequenceArr[in];

            BinaryStdOut.write(ch);

            System.arraycopy(sequenceArr, 0, sequenceArr, 1, in);
            sequenceArr[0] = ch;
        }

        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        //else if (args[0].equals("+")) decode();
    }
}
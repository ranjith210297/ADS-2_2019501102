import java.util.Arrays;

/**
 * class which implements circular suffix array,which creates an array consisting of sufffixex.
 * @author k. ranjith kumar
 * @reference Bobsedgewick
 */
public class CircularSuffixArray {
    private class Node implements Comparable<Node> {
        private String k;
        private int val;

        public Node(String key, int value)
        {
            k = key;
            val = value;
        }

        /**
         * method to compare the characters based on their ascii values.
         */
        public int compareTo(Node that) {
            int len = k.length();
            for (int i = 0; i < len; i++) {
                char a = k.charAt((i + this.val) % len);
                char b = k.charAt((i + that.val) % len);
                if (a > b) return 1;
                else if (a < b) return -1;
                else continue;
            }
            return 0;
        }
        
        /**
         * for getting value
         * @return  int value
         */
        public int getValue() {
            return val;
        }
    }
    
    private int []indexs;
    private int length;
    
    /**
     * method to get the circular array of suffixes fron input.
     * @param string s
     */
    public CircularSuffixArray(String s) {
        String []arrays;
        length = s.length();
        indexs = new int[length];

        Node []substrings = new Node[length];
        for (int i = 0; i < length; i++)
            substrings[i] = new Node(s, i);

        Arrays.sort(substrings);

        for (int i = 0; i < substrings.length; i++) {
            indexs[i] = substrings[i].getValue();
            //stdOut.printf("%d %d\n", i, indexs[i]);
        }
    }

    /**
     * for finfing length of string.
     */
    public int length() {
        return length;
    }


    /**
     * for finding index of char .
     */
    public int index(int i) {
        return indexs[i];
    }

    public static void main(String[] args) {
        if (args.length < 2)
             new CircularSuffixArray(args[0]);
    }
}
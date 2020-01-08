import java.io.*;
import java.util.*;
/**
 * Digraph for counting numbe rof edges in the digraph.
 * It uses array list to store the vertices and edges.
 * @author K. Ranjith Kumar
 */
public class Digraph_EdgeCount{
    static ArrayList<String> hal = new ArrayList<String>();
    static ArrayList<String[]> sal = new ArrayList<String[]>();

    /**
     * This method is for counting and returning the number of vertices in the given digraph.
     * Takes hypernyms as parameters.
     */
    public static void parseHypernyms(String hyper) throws Exception {
        String strLine = "";
        int w=0;
        /**
         * Filereader method to read input file.
         */
        LineNumberReader hreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\hypernyms.txt"), "UTF-8"));
        while (((strLine = hreader.readLine()) != null)) {
            String[] str = strLine.split(",",2);
            if(str.length > 1) {
                hal.add(str[0]);
                sal.add(str[1].split(","));
            } else{
                hal.add(str[0]);
                sal.add(null);
            }
            w++;
    }
    System.out.println("Number of Vertices are ---" +w);
}

/**
 * Main method to launch the program.
 */
public static void main(String[] args) throws Exception {
    parseHypernyms("hypernyms");
    Digraph g = new Digraph(hal.size());  // Object for digraph class to access addedge method.
    int edge_count = 0;
    for(int i=0;i<hal.size();i++){
        if(sal.get(i) != null){
            for(int j=0;j<sal.get(i).length;j++){
                int s = Integer.parseInt(hal.get(i));
                int r = Integer.parseInt(sal.get(i)[j]);
                g.addEdge(s,r);
                edge_count++;
            }
        }
    }
    System.out.println("Number of edges are-------"+ edge_count);
}
}
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import java.lang.IllegalArgumentException;


/**
 * SAP: Shortest Ancestor Path. Class to implement path of edges and the graph.
 * In this we also implement the path from two vertices.
 * Implement to find the ancestor of two different vertices.
 * 
 */

public final class SAP {
    private final Digraph dg;

    /**
     * Construcor to initialize the breadth first dearch variables.
     */
    public  SAP(Digraph G) {
        this.dg = G;
    }

    /**
     * to find the shortest ancestral path from v to w. 
     * @param v vertice
     * @param w edge
     * @return path length if have. -1 if no path.
     */
    public  int length(final int v, final int w) {
        
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(dg,w);

         int leng = Integer.MAX_VALUE;
        for(int i = 0; i < dg.V(); i++) {
            if(bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
                int len = bfdp1.distTo(i) + bfdp2.distTo(i);
                if(leng > len) { 
                    leng = len; 
                }
            }
        }
        bfdp1 = null;
        bfdp2 =null;
        if( leng == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return leng;
        }
        
    }

    /**
     * Method to find the common ancestor of v and w in the found shortest ancestral path.
     * @param v vertice 
     * @param w edge
     * @return returns the common anvestor, -1 if no path found.
     */
    public int ancestor(final int v, final int w) {
        
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(dg,v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(dg,w);

        int leng = Integer.MAX_VALUE;
         int anstor = -1;
        for(int i = 0;i < dg.V(); i++) {
            if(bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
                int len = bfdp1.distTo(i) + bfdp2.distTo(i);
                if(leng > len) { 
                    leng = len;
                    anstor = i;
                }
            }
        }
        bfdp1 = null;
        bfdp2 =null;
        return anstor;
    }


    /**
     * Iterable method to find the shortest ancestral path fron v to w.
     * @param v iterable vertice.
     * @param w iterable edge
     * @return returns length, -1 if no such path found.
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w)  {
        for(Integer ver : v) {
            if (ver == null || ver < 0) {
            throw new IllegalArgumentException("Invalid inputs");
        }
    }
        for(Integer ed : w) {
            if (ed == null || ed < 0) {
            throw new IllegalArgumentException("Invalid inputs");
        }
    }
         int leng = Integer.MAX_VALUE;
        for(int i : v) {
            for(int j : w) {
                int lengthh = length(i, j);
                if( lengthh != -1 && leng > lengthh) {
                    leng = lengthh;
                }
            }
        }
        if( leng != Integer.MAX_VALUE) {
            return leng;
        }
        else {
            return -1;
        }
    }
            

    /**
     * Iterable Method to find the commom ancestor.
     * @param vertice v iterable
     * @param edge w iterable
     * @return ancestor -1 if no ancestor found.
     */
    public  int ancestor(final Iterable<Integer> v, final Iterable<Integer> w)  {
        for(Integer ver2 : v) {
            if (ver2 == null || ver2 < 0) {
            throw new IllegalArgumentException("Invalid inputs");
        }
    }
        for(Integer ed2 : w) {
            if (ed2 == null || ed2 < 0) {
            throw new IllegalArgumentException("Invalid inputs");
        }
    }
         int leng = Integer.MAX_VALUE;
        int anstor = -1;
        for(int i : v) {
            for(int j : w) {
                int length = length(i,j);
                if( length != -1 && leng > length) {
                    leng = length;
                    anstor = ancestor(i,j);
                }
            }
        }
        assert leng != -1;
        return anstor;
    }
        
}

import java.util.*;
import java.lang.*;

/**
 * SAP: Shortest Ancestor Path. Class to implement path of edges and the graph.
 * In this we also implement the path from two vertices.
 * Implement to find the ancestor of two different vertices.
 * Input graph- 13           Iutput-Vertices: |  Ouput:
 *              11                 3 11       |   length = 4, ancestor = 1       
 *              7  3               9 12       |   length = 3, ancestor = 5
 *              8  3               7 2        |   length = 4, ancestor = 0
 *              3  1               1 6        |   lwngth = -1. ancestor = -1
 *              4  1
 *              5  1
 *              9  5
 *              10 5
 *              11 10
 *              12 10
 *              1  0
 *              2  0
 * 
 */

public class SAP {
    public  Digraph dg;
    public BreadthFirstDirectedPaths[] bfs;

    /**
     * Construcot to initialize the breadth first dearch variables.
     */
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.dg = G;
        bfs = new BreadthFirstDirectedPaths[this.dg.vertices()];
        for( int i=0;i<dg.vertices();i++) {
            bfs[i] = new BreadthFirstDirectedPaths(dg, i);
        }
    }

    /**
     * to find the shortest ancestral path from v to w. 
     * @param v vertice
     * @param w edge
     * @return path length if have. -1 if no path.
     */
    public int length(int v, int w) {
        if(((v<0)|| v>dg.vertices()) && (w<0 || w > dg.vertices())) {
            throw new IllegalArgumentException();
        }
        int leng = Integer.MAX_VALUE;
        for(int i = 0;i<dg.vertices();i++) {
            if(bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int len = bfs[v].distTo(i) + bfs[w].distTo(i);
                if(leng > len) { 
                    leng = len; 
                }
            }
        }
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
    public int ancestor(int v, int w) {
        if(((v<0)|| v>dg.vertices()) && (w<0 || w > dg.vertices())) {
            throw new IllegalArgumentException();
        }
        int leng = Integer.MAX_VALUE;
        int anstor = -1;
        for(int i = 0;i<dg.vertices();i++) {
            if(bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int len = bfs[v].distTo(i) + bfs[w].distTo(i);
                if(leng > len) { 
                    leng = len;
                    anstor = i;
                }
            }
        }
        return anstor;
    }


    /**
     * Iterable method to find the shortest ancestral path fron v to w.
     * @param v iterable vertice.
     * @param w iterable edge
     * @return returns length, -1 if no such path found.
     */
    public int length(Iterable<Integer> v,Iterable<Integer> w)  {
                
        int leng = Integer.MAX_VALUE;
        for(int i : v) {
            for(int j : w) {
                int lengthh = length(i,j);
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
    public int ancestor(Iterable<Integer> v,Iterable<Integer> w)  {
                     
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
        return anstor;
    }
        
}

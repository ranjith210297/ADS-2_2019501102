
import java.util.NoSuchElementException;

/**
 * Digraph class to implement api.
 * @author K. Ranjith Kumar
 */
    public class Digraph {
        private final int V;           // number of vertices in this digraph
        private int E;                 // number of edges in this digraph
        public Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
        private int[] indegree; 
    
    //WordNet Ver = new WordNet();
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }   
    }

    /**
     * Adds the directed edge v→w to this digraph.
     * @param  v the tail vertex
     * @param  w the head vertex
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

   
}
    
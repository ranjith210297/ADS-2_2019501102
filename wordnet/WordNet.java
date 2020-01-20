
import java.util.ArrayList;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.In;

/**
 * WordNet class to implement the wordnet api project.
 * It consisits of various methods including a constructor.
 * @author K. Ranjith Kumar
 * @reference Prof. Robert Sedgewick.
 */
public class WordNet {
   private   SAP sap;
   private final  Digraph graph;
   private LinearProbingHashST<String, ArrayList<Integer>> hash;
   private ArrayList<String> values = new ArrayList<>();

    /**
     * Wordnet constructor to initialize the values of the methods.
     * In this we use synset and hypernym sets files to take input.
     */
    public WordNet (String synsetsFile, String hypernymsFile) {
       hash = new LinearProbingHashST<String, ArrayList<Integer>>();
       int syn = parseSynsets(synsetsFile);
       graph = parseHypernyms(hypernymsFile, syn);
       sap = new SAP(graph);
      
    }


    /**
     * This method for splitting the id's in the synsets according to the synset id's.
     * @param filename synsets text file
     */
    private int parseSynsets(String filename)  {
        In input = new In(filename); 
        String str1;
        int count = 0;     
        while (input.hasNextLine()) {
            str1 = input.readLine();
            String[] x = str1.split(",");
            int k = Integer.parseInt(x[0]);
            values.add(k,x[1]);   
            String [] st = x[1].split(" ");
            count++;
            for(int j = 0; j < st.length; j++) {
                String n = st[j].trim();
                if(!hash.contains(n)) {
                    ArrayList<Integer> id = new ArrayList<Integer>();
                    id.add(k);
                    hash.put(n, id);
                } else {
                    hash.get(n).add(Integer.parseInt(x[0]));
                }
            }
     
        }
    return count;
    }

    /**
     * This method is for splitting the id's in hypernym file and stored in the array.
     * @param filename synset text file.
     * @param syn each synset noun.
     */
    private Digraph parseHypernyms(String filename, int syn)  {
   
        In br = new In(filename);  
        Digraph graph =  new Digraph(syn);
        while (br.hasNextLine()) {
            String [] Str2 = br.readLine().split(",");    
            for(int i = 1; i < Str2.length; i++) {
                int x = Integer.parseInt(Str2[0]);
                int y = Integer.parseInt(Str2[i]);
                graph.addEdge(x,y);
                                
            }
  
        }
        return graph;

    }


    /**
     * nouns() method to iterate over the keys in the hash table.
     */
    public Iterable<String> nouns(){
        return hash.keys();
    
    }
    
    /**
     * Method to find whether a string is noun or not.
     * @param word noun word
     */
    public boolean isNoun(String word) {
        return hash.contains(word);
 
    }
    
    /**
     * distance method to find the distance between the nounA and nounB.
     * @param nounA noun word
     * @param nounB noun word
     */
    public int distance(String nounA, String nounB){
    
        if(isNoun(nounA) && isNoun(nounB) ){
      
        int  len = sap.length(hash.get(nounA) , hash.get(nounB));
            return len ;
        }
    
        return -1;
    }
    
    /**
     * sap method to find the shortest ancestral path of ancestor from the two nodes.
     * @param nounA noun-word
     * @param nounB noun-word
     */
    public String sap(String nounA, String nounB) {
    
        if(isNoun(nounA) && isNoun(nounB) == true) {
      
            int ans =  sap.ancestor(hash.get(nounA) , hash.get(nounB));
            if (ans !=  -1) {    
                 return values.get(ans);
            }
            else {
                 
                return "there is no common ancestor";
            }
        }
       return null;
    }
 
 
 }
 
 
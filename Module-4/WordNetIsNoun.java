import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;

/**
 * This class is used for unit testing the distance and isNoun functions.
 * It divides the constructor cretaed earlier in the first day of the project.
 * @author K. Ranjith Kumar
 */
public class WordNetIsNoun {
    private ArrayList<String> synsetList;
    private Map<String, ArrayList<Integer>> synsetMap;
    private Digraph G;
    private SAP sap;

    /**
     * This method is for passing the synsets and hypernets and reading the nouns from the 
     * text files.
     * @param synsets synset text file.
     * @param hypernym hypernyms text file.
     */
    public WordNetIsNoun(String synsets, String hypernyms) throws IOException{
        synsetList = new ArrayList<String>();
        synsetMap = new HashMap<String, ArrayList<Integer>>();

        //Line reader for reading a text file line by line.
        LineNumberReader synsetsIn = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\synsets.txt"), "UTF-8"));
        String strLine = "";
        while (((strLine = synsetsIn.readLine()) != null)) {
            //String strLine = synsetsIn.readLine();
            String[] tokens = strLine.split(",");
            int id = Integer.parseInt(tokens[0]);
            if (synsetList.size() < id) {
                synsetList.ensureCapacity(id + 1);
            }
            synsetList.add(tokens[1]);
            String[] nouns = tokens[1].split(" ");
            for (String noun : nouns) {
                if (!synsetMap.containsKey(noun)) {
                    synsetMap.put(noun, new ArrayList<Integer>());
                }
                synsetMap.get(noun).add(id);
            }
        }
        synsetsIn.close();

        G = new Digraph(synsetList.size());
        int[] outputs = new int[synsetList.size()];

        //In hypernymsIn = new In(hypernyms);
        LineNumberReader hypernymsIn = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\hypernyms.txt"), "UTF-8"));
        //String strLine = "";

        while (((strLine = hypernymsIn.readLine()) != null)) {
            String[] tokens = strLine.split(",");
            int v = Integer.parseInt(tokens[0]);
            outputs[v] += tokens.length - 1;

            for (int i = 1; i < tokens.length; ++i) {
                int w = Integer.parseInt(tokens[i]);
                G.addEdge(v, w);
            }
        }
        hypernymsIn.close();

      
    }

    

    /**
     * Iterable method to map the keys by iterating over all the  keys.
     * @param no params.
     */
    public Iterable<String> nouns() {
        return synsetMap.keySet();
    }

    /**
     * isNoun method to check whether the string is noun or not.
     * @param noun word passed to the method.
     */
    public boolean isNoun(String word) {
        return synsetMap.containsKey(word);
    }


    /**
     * distance method to find distncae between two nouns in the synsets file.
     * @param first noun to be passed to the distance method.
     * @param second noun to be passed to the distance method.
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        return sap.length(synsetMap.get(nounA), synsetMap.get(nounB));
    }


    /**
     * sap method to find shortest ancestral path from nounA to nounB.
     * @param nounA
     * @param nounB
     */
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        return synsetList.get(sap.ancestor(synsetMap.get(nounA),
                    synsetMap.get(nounB)));
    }


    /**
     * main method to initialize object and call methods.
     */
    public static void main(String[] args) throws IOException { 
        WordNetIsNoun obj = new WordNetIsNoun("synsets","hypernyms"); 
        //System.out.println(obj.isNoun("sakdl"));


      }
}
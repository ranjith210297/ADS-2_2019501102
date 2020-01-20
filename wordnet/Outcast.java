
/**
 * Outcast class to implement outcasting using wordnet.
 * consists of methods and constructor.
 * @author K. Ranjith Kumar
 */
public class Outcast {
    private WordNet wordnet;

    /**
     * Constructor for initialization.
     */
    public Outcast(final WordNet wordnet)  {
        this.wordnet = wordnet;
    }

    /**
     * Method to outcast the odd word from set of words.
     * @param nouns array of nouns from input.
     */
    public String outcast(String[] nouns)  {
        int distance = 0;
        String outcast = null;

        for (String i : nouns) {
            int d = 0;
            for (String j : nouns) {
                int dist = wordnet.distance(i, j);
                d += dist;
            }
            if (d > distance) {
                distance = d;
                outcast = i;
            }
        }
        
        return outcast;
    }
    
}
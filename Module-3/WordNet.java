import java.io.*; 

/**
 * WordNet class to implement wordnet of synsets and hypernyms.
 *It consists of two methods parseSysnets and parsehypernets
 * and a constructor - parameterized.
 * Syntax Reference - @references -  geeksfor geeks
 * @author K. Ranjith Kumar
 */

     class WordNet2 { 
        WordNet2(String synsets,String hypernyms) throws IOException {
            parseSynsets(synsets);
            parseHypernyms(hypernyms);
        }


    /**
     * ParseSynsets method to split the comma separated values to print 
     * the first character in the lines of the synsets file.
     * @param synsets consists of set of synsets.
     */
    private int parseSynsets(String synsets) throws IOException {
        String strLine = "";
        int v=0;
        LineNumberReader sreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\synsets.txt"), "UTF-8"));
        while (((strLine = sreader.readLine()) != null)) {
        String[] str = strLine.split(",");
        //System.out.println(str[0]);
        v++;
        }
    sreader.close();
    return v;
    }

     

    /**
     * ParseHypernyms method to split the comma separated values to print 
     * the first character in the lines of the synsets file.
     * @param hypernyms 
     */
    private int parseHypernyms(String hypernyms) throws IOException {
        String strLine = "";
        int w=0;
        LineNumberReader hreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\hypernyms.txt"), "UTF-8"));
        while (((strLine = hreader.readLine()) != null)) {
        String[] str = strLine.split(",");
        w++;
        }
        hreader.close();
    return w;
    }
  }

  public class WordNet {
    public static void main(String[] args) throws IOException { 
      WordNet2 obj = new WordNet2("synsets","hypernyms"); 
        In inputFile = new In("D:\\MSIT\\GIT_REPOS\\wordnet\\digraph1.txt");
        Digraph dg = new Digraph(inputFile);
        SAP sapp = new SAP(dg);

        System.out.println("length = "+ sapp.length(3,11) + "  Ancestor = " + sapp.ancestor(3,11));

        System.out.println("length = "+ sapp.length(9,12) + "  Ancestor = " + sapp.ancestor(9,12));

        System.out.println("length = "+ sapp.length(7,2) +  "  Ancestor = " + sapp.ancestor(7,2));

        System.out.println("length = "+ sapp.length(1,6) +  "  Ancestor = " + sapp.ancestor(1,6));


    }
  }


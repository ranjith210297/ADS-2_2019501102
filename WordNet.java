import java.io.*; 

/**
 * WordNet class to implement wordnet of synsets and hypernyms.
 *It consists of two methods parseSysnets and parsehypernets
 * and a constructor - parameterized.
 * Syntax Referenc - @references -  geeksfor geeks
 * @author K. Ranjith Kumar
 */

    public class WordNet { 
        WordNet(String synsets,String hypernyms) throws IOException{
            parseSynsets(synsets);
            parseHypernyms(hypernyms);
        }

    /**
     * ParseSynsets method to split the comma separated values to print 
     * the first character in the lines of the synsets file.
     * @param synsets consists of set of synsets.
     */
    private void parseSynsets(String synsets) throws IOException{
        String strLine = "";
        LineNumberReader sreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\synsets.txt"), "UTF-8"));
        while (((strLine = sreader.readLine()) != null)) {
        String[] str = strLine.split(",");
        System.out.println(str[0]);
        }
    sreader.close();
    }

    
    /**
     * ParseHypernyms method to split the comma separated values to print 
     * the first character in the lines of the synsets file.
     * @param hypernyms 
     */
    private void parseHypernyms(String hypernyms) throws IOException{
        String strLine = "";
        LineNumberReader hreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\GIT_REPOS\\wordnet\\hypernyms.txt"), "UTF-8"));
        while (((strLine = hreader.readLine()) != null)) {
        String[] str = strLine.split(",");
        System.out.println(str[0]);
        }
    hreader.close();
    }

    /**
     * main method to create the object and call the functions using the constructor.
     * constructor @param synsets , @hypernyms hypernyms.
     */
  public static void main(String[] args) throws IOException { 
    WordNet obj = new WordNet("synsets","hypernyms"); 
  }
}



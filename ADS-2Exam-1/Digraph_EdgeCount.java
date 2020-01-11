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

    public static void parseEmails(String emails) throws IOException {
        String strLine = "";
        LineNumberReader sreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\ADS-2(EXAMS)\\ADS-2Exam-1\\ADS-2Exam-1\\emails.txt"), "UTF-8"));
        int line = 0;
        while(((strLine = sreader.readLine()) != null) && line < 10) {
             String[] str = strLine.split(";");
            System.out.println("Number of famous emails are "+ str[0]+","+str[1]);
            line++;
        }
        sreader.close();
    }
    /**
     * This method is for counting and returning the number of vertices in the given digraph.
     * Takes hypernyms as parameters.
     */
    public static void parseEmailLogs(String EmailLogs) throws Exception {
        String strLine = "";
        int w=0;
        /**
         * Filereader method to read input file.
         */
        LineNumberReader hreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\ADS-2(EXAMS)\\ADS-2Exam-1\\ADS-2Exam-1\\email-logs.txt"), "UTF-8"));
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
    System.out.println("Number of EDGES are ---" +w);
}

/**
 * Main method to launch the program.
 */
public static void main(String[] args) throws Exception {
    parseEmailLogs("EmailLogs");
    parseEmails("emails");
    Digraph g = new Digraph(hal.size());  // Object for digraph class to access addedge method.
    //Digraph g = new Digraph(hal.size());  // Object for digraph class to access addedge method.
    // int edge_count = 0;
    // for(int i=0;i<hal.size();i++){
    //     if(sal.get(i) != null){
    //         for(int j=0;j<sal.get(i).length;j++){
    //             int s = Integer.parseInt(hal.get(i));
    //             int r = Integer.parseInt(sal.get(i)[j]);
    //             g.addEdge(s,r);
    //             edge_count++;
    //         }
    //     }
    // }
    // System.out.println("Number of edgescounts are-------"+ edge_count);
    
    // }
    // int s;
    // int r;
    // for(int i=0;i<=hal.size();i++){
    //     if(hal.get(i) != null){
    //         for(int j=0;j<sal.get(i).length;j++){
    //             String[] k = sal.get(i).split(" ");
    //             s = Integer.parseInt(hal.get(k));
    //             r = Integer.parseInt(sal.get(k)[j]);
    //             g.addEdge(s,r);
    //             edge_count++;
    //         }
    //     }
    
    // }
    // for(int i=0;i<hal.size();i++){
    //System.out.println("Number of Famous vertices are-------"+edge_count);
    // // }
    // for(int i=0;i<hal.size();i++){
    //     System.out.println("Number of edges are-------"+s+"-->" +r);
    //     }
}

}
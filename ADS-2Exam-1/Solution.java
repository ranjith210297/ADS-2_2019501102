import java.io.*;


class WordNet2 {
    WordNet2(String synsets, String hypernyms) throws IOException {
        parseSynsets(synsets);
        parseHypernyms(hypernyms);
    }

    private void parseSynsets(String synsets) throws IOException {
        String strLine = "";
        LineNumberReader sreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\ADS-2(EXAMS)\\ADS-2Exam-1\\ADS-2Exam-1\\emails.txt"), "UTF-8"));
        int line = 0;
        while(((strLine = sreader.readLine()) != null) && line < 4) {
             String[] str = strLine.split(";");
            System.out.println(str[0]+","+str[1]);
            line++;
        }
        sreader.close();
    }

    String[] str2;
    String[] str3;
    private void parseHypernyms(String hypernyms) throws IOException {
        String strLine = "";
        LineNumberReader hreader = new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\MSIT\\ADS-2(EXAMS)\\ADS-2Exam-1\\ADS-2Exam-1\\email-logs.txt"), "UTF-8"));
        int line =0;
        while(((strLine = hreader.readLine()) != null) && line < 4) {
            String[] str = strLine.split(",");
             str2 = str[0].split(" ");
             str3 = str[1].split(" ");

            System.out.println(str2[1]+","+str3[2]);
            line++;


       }
        hreader.close();
    }

//     private int sol() {
//         int count = 0;
//         for(int i=0;i<str2.length;i++){
//         if(Integer.pasreInt(str2[1][i]) == Integer.parseInt(Str2[1][i+1])) {
//             count++;
//         }
//     }
//     System.out.println(Integer.parseInt(str2[1][0])+","+count);
// }

}

// public class Solution {
//     public static void main(String[] args) throws IOException {
//         WordNet2 obj = new WordNet2("synsets","hypernyms");    }
// }

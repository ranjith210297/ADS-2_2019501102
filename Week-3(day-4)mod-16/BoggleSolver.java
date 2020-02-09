import java.util.Set;
import java.util.HashSet;


/**
 * boggle solver class. for implementing backtracking algorithm using hasprefix method suing trieset
 * In this class we mainly implement the game of boggle consisiting of 4x4 game board.
 * In this, we  ifnd valid words across the board. In this we write the method get valid words.
 * 
 * @author K. Ranjith Kumar
 * @reference Bob Sedgewick
 */
public class BoggleSolver {

  private TrieSET wordDict; // Object for the trieset tree.
   
  /**
   * Constructor to initialize the fields and object is created.
   * @param dictionary consisting of all the words.
   */
  public BoggleSolver(String[] dictionary) {
    wordDict = new TrieSET();

    for (String s : dictionary) {
      wordDict.add(s);
    }
  }

  /**
   * this method to find all the valid words from the dictionary we find with the alphabets on the board.
   * @param board pf type boggleboard.
   */
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    Set<String> validWords = new HashSet<String>();
    

    int row=0;
    while(row < board.rows()){
      int col=0;
      while(col < board.cols()){
        boolean[][] marked = new boolean[board.rows()][board.cols()];
        collect(board,row,col,marked,"",validWords);
        col++;
      }
      row++;
    }
    return validWords;
  }

  /**
   * colect method to get the valid words from the dictionary and mark them such that each node or letter 
   * is visited only once, just like Depth first search.
   * @param board of type boggleboard
   * @param row
   * @param col
   * @param marked an array consisting of marked words from collect method.
   * @param prefix, set
   * 
   */
  private void collect(BoggleBoard board, int row, int col, boolean[][] marked, String prefix, Set<String> set) {
    if (marked[row][col]) {
      return;
    }

    char letter = board.getLetter(row, col);
    String word = prefix;

    if (letter == 'Q') {
      word += "QU";
    } else {
      word += letter;
    }


    if (!wordDict.hasPrefix(word)) {
      return;
    }


    if (word.length() > 2 && wordDict.contains(word)) {
      set.add(word);
    }

    marked[row][col] = true;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }

        
        if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
          collect(board, row + i, col + j, marked, word, set);
        }
      }
    }

    marked[row][col] = false;
  }


  /**
   * THIS METHOD TO GIVE THE GAME SCORE TO EACH WORD FOUND ON THE BOARD.
   * @param WORD.
   */
  public int scoreOf(String word) {
    if (wordDict.contains(word)) {
      switch (word.length()) {
      case 0:
      case 1:
      case 2:
        return 0;
      case 3:
      case 4:
        return 1;
      case 5:
        return 2;
      case 6:
        return 3;
      case 7:
        return 5;
      default:
        return 11;
      }
    } else {
      return 0;
    }
  }
}
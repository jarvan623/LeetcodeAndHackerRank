import java.util.ArrayList;
import java.util.List;

public class Q51 {
  /*The n-queens puzzle is the problem of placing n queens on an n x n chessboard
  such that no two queens attack each other.
  Given an integer n, return all distinct solutions to the n-queens puzzle.
  You may return the answer in any order.
  Each solution contains a distinct board configuration
  of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
  https://leetcode.cn/problems/n-queens*/

  public static void main(String[] args) {
    List<List<String>> lists = solveNQueens(4);
    for (List<String> list : lists) {
      System.out.println(list.toString());
    }
  }

  static List<List<String>> answer = new ArrayList<>();

  public static List<List<String>> solveNQueens(int n) {
    List<String> track = new ArrayList<>();
    backTrack(track, n);
    return answer;
  }

  private static void backTrack(List<String> track, int n) {
    if (track.size() == n) {
      answer.add(new ArrayList<String>(track));
    }
    for (int i = 0; i < n; i++) {
      if (canPlace(track, i)) {
        // if can place,build a string and add it to track for further permutation
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
          if (j == i) {
            sb.append("Q");
            continue;
          }
          sb.append(".");
        }
        track.add(sb.toString());
        backTrack(track, n);
        track.remove(track.size() - 1);
      } else {
        continue;
      }
    }
  }

  private static boolean canPlace(List<String> track, int n) {
    int size = track.size();
    if (size == 0) {
      return true;
    }
    for (int i = 0; i < size; i++) {
      String s = track.get(i);
      if (s.charAt(n) == 'Q') {
        // check vertically
        return false;
      }
      // check diagonal
      int index = s.indexOf("Q");
      if (n - index == size - i || index - n == size - i) {
        return false;
      }
    }
    return true;
  }
}

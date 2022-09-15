public class Q1905 {
  /*You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land).
  An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

  An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

  Return the number of islands in grid2 that are considered sub-islands.

  https://leetcode.cn/problems/count-sub-islands*/
  public static void main(String[] args) {
    int[][] grid1 =
        new int[][] {
          {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}
        };
    int[][] grid2 =
        new int[][] {
          {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}
        };
    System.out.println(countSubIslands(grid1, grid2));
  }

  public static int countSubIslands1(int[][] grid1, int[][] grid2) {
    int answer = 0;
    for (int i = 0; i < grid2.length; i++) {
      for (int j = 0; j < grid2[0].length; j++) {
        if (grid2[i][j] == 1) {
          if (floodIsland1(grid1, grid2, i, j)) {
            answer++;
          }
        }
      }
    }
    return answer;
  }

  public static boolean floodIsland1(int[][] grid1, int[][] grid2, int i, int j) {
    if (i >= grid2.length || j >= grid2[0].length || i < 0 || j < 0) {
      return true;
    }
    if (grid2[i][j] == 0) {
      return true;
    }
    grid2[i][j] = 0;
    // actually, this kind of boolean recursion will not ensure all connect islands flooded
    // because when it encounters FALSE it will return and abandon remaining recursion
    // thus there would be bugs for some test cases
    /*return floodIsland1(grid1, grid2, i + 1, j)
    && floodIsland1(grid1, grid2, i - 1, j)
    && floodIsland1(grid1, grid2, i, j + 1)
    && floodIsland1(grid1, grid2, i, j - 1)
    && grid1[i][j] == 1;*/
    // change to this kind of boolean recursion, it will ensure every connect islands to be blooded
    boolean a = floodIsland1(grid1, grid2, i + 1, j);
    boolean b = floodIsland1(grid1, grid2, i - 1, j);
    boolean c = floodIsland1(grid1, grid2, i, j + 1);
    boolean d = floodIsland1(grid1, grid2, i, j - 1);
    return a && b && c && d && grid1[i][j] == 1;
  }

  public static int countSubIslands(int[][] grid1, int[][] grid2) {
    for (int i = 0; i < grid2.length; i++) {
      for (int j = 0; j < grid2[0].length; j++) {
        // flood grid2's unique island and grid will only remain common islands
        if (grid2[i][j] == 1 && grid1[i][j] == 0) {
          floodIsland(grid2, i, j);
        }
      }
    }
    int answer = 0;
    for (int i = 0; i < grid2.length; i++) {
      for (int j = 0; j < grid2[0].length; j++) {
        // remaining islands would all be covered by grid1, so flood them and count it
        if (grid2[i][j] == 1) {
          floodIsland(grid2, i, j);
          answer++;
        }
      }
    }
    return answer;
  }

  public static void floodIsland(int[][] grid2, int i, int j) {
    if (i >= grid2.length || j >= grid2[0].length || i < 0 || j < 0 || grid2[i][j] == 0) {
      return;
    }
    grid2[i][j] = 0;
    floodIsland(grid2, i + 1, j);
    floodIsland(grid2, i - 1, j);
    floodIsland(grid2, i, j + 1);
    floodIsland(grid2, i, j - 1);
    return;
  }
}

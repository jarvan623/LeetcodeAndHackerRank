public class Q1020 {
  /*You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

  A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

  Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

  https://leetcode.cn/problems/number-of-enclaves*/
  public static void main(String[] args) {
    int[][] grid =
        new int[][] {
          {0, 0, 0, 0},
          {1, 0, 1, 0},
          {0, 1, 1, 0},
          {0, 0, 0, 0},
        };
    System.out.println(numEnclaves(grid));
  }

  static int m;
  static int n;
  static int[][] islands;

  public static int numEnclaves(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    islands = grid;
    // firstly flood the island located in out circle because they can walk off the map
    for (int i = 0; i < n; i++) {
      if (grid[0][i] == 1) {
        floodIsland(0, i);
      }
      if (grid[m - 1][i] == 1) {
        floodIsland(m - 1, i);
      }
    }
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 1) {
        floodIsland(i, 0);
      }
      if (grid[i][n - 1] == 1) {
        floodIsland(i, n - 1);
      }
    }
    int answer = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (islands[i][j] == 1) {
          answer++;
        }
      }
    }
    return answer;
  }

  public static void floodIsland(int i, int j) {
    if (i >= m || j >= n || i < 0 || j < 0) {
      return;
    }
    if (islands[i][j] == 1) {
      islands[i][j] = 0;
      floodIsland(i, j + 1);
      floodIsland(i + 1, j);
      floodIsland(i - 1, j);
      floodIsland(i, j - 1);
    }
  }
}

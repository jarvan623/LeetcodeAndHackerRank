public class Q1254 {
  /*Given a 2D grid consists of 0s (land) and 1s (water).
  An island is a maximal 4-directionally connected group of 0s and
  a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

  Return the number of closed islands.

  https://leetcode.cn/problems/number-of-closed-islands*/
  public static void main(String[] args) {
    int[][] grid =
        new int[][] {
          {1, 1, 1, 1, 1, 1, 1, 0},
          {1, 0, 0, 0, 0, 1, 1, 0},
          {1, 0, 1, 0, 1, 1, 1, 0},
          {1, 0, 0, 0, 0, 1, 0, 1},
          {1, 1, 1, 1, 1, 1, 1, 0}
        };
    System.out.println(closedIsland(grid));
  }

  static int m;
  static int n;
  static int[][] islands;

  public static int closedIsland(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    islands = grid;
    // firstly flood the island located in out circle because they are not closed island
    for (int i = 0; i < n; i++) {
      if (grid[0][i] == 0) {
        floodIsland(0, i);
      }
      if (grid[m - 1][i] == 0) {
        floodIsland(m - 1, i);
      }
    }
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 0) {
        floodIsland(i, 0);
      }
      if (grid[i][n - 1] == 0) {
        floodIsland(i, n - 1);
      }
    }
    // then count the inner closed island
    int answer = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          answer++;
          floodIsland(i, j);
        }
      }
    }
    return answer;
  }

  public static void floodIsland(int i, int j) {
    if (i >= m || j >= n || i < 0 || j < 0) {
      return;
    }
    if (islands[i][j] == 0) {
      islands[i][j] = 1;
      floodIsland(i, j + 1);
      floodIsland(i + 1, j);
      floodIsland(i - 1, j);
      floodIsland(i, j - 1);
    }
  }
}

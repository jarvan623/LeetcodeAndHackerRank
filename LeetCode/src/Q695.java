public class Q695 {
  /*You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally
  You may assume all four edges of the grid are surrounded by water.

  The area of an island is the number of cells with a value 1 in the island.

  Return the maximum area of an island in grid. If there is no island, return 0.

  https://leetcode.cn/problems/max-area-of-island*/
  public static void main(String[] args) {
    int[][] grid =
        new int[][] {
          {1, 1, 1, 1, 1, 1, 1, 0},
          {1, 0, 0, 0, 0, 1, 1, 0},
          {1, 0, 1, 0, 1, 1, 1, 0},
          {1, 0, 0, 0, 0, 1, 0, 1},
          {1, 1, 1, 1, 1, 1, 1, 0}
        };
    System.out.println(maxAreaOfIsland(grid));
  }

  static int[][] islands;
  static int m;
  static int n;

  public static int maxAreaOfIsland(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    islands = grid;
    int answer = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (islands[i][j] == 1) {
          answer = Math.max(answer, floodIsland(i, j));
        }
      }
    }
    return answer;
  }

  public static int floodIsland(int i, int j) {
    if (i >= m || j >= n || i < 0 || j < 0 || islands[i][j] == 0) {
      return 0;
    }
    islands[i][j] = 0;
    return 1
        + floodIsland(i + 1, j)
        + floodIsland(i - 1, j)
        + floodIsland(i, j + 1)
        + floodIsland(i, j - 1);
  }
}

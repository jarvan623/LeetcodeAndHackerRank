public class Q200 {
  /*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
  return the number of islands.

  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
  You may assume all four edges of the grid are all surrounded by water.

  https://leetcode.cn/problems/number-of-islands*/
  public static void main(String[] args) {
    char[][] grid =
        new char[][] {
          {'1', '1', '0', '0', '0'},
          {'1', '1', '0', '0', '0'},
          {'0', '0', '1', '0', '0'},
          {'0', '0', '0', '1', '1'}
        };
    System.out.println(numIslands(grid));
  }

  static char[][] islands;

  public static int numIslands(char[][] grid) {
    int answer = 0;
    islands = grid;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (islands[i][j] == '1') {
          // increase the answer and all islands linked with current island would be water
          answer++;
          floodIsland(i, j);
        }
      }
    }
    return answer;
  }

  public static void floodIsland(int i, int j) {
    if (i >= islands.length || j >= islands[0].length || i < 0 || j < 0) {
      return;
    }
    if (islands[i][j] == '1') {
      // make current island and linked islands to water
      islands[i][j] = '0';
      floodIsland(i + 1, j);
      floodIsland(i, j + 1);
      floodIsland(i - 1, j);
      floodIsland(i, j - 1);
    }
  }
}

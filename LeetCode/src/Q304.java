public class Q304 {
  public static void main(String[] args) {
    int[][] matrix =
        new int[][] {
          {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}
        };
    NumMatrix numMatrix = new NumMatrix(matrix);
    System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
    System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
  }
  /**
   * Your NumMatrix object will be instantiated and called as such: NumMatrix obj = new
   * NumMatrix(matrix); int param_1 = obj.sumRegion(row1,col1,row2,col2);
   */
  static class NumMatrix {
    int[][] sums;

    public NumMatrix(int[][] matrix) {
      int rows = matrix.length;
      int cols = matrix[0].length;
      sums = new int[rows + 1][cols + 1];
      for (int i = 1; i < rows + 1; i++) {
        for (int j = 1; j < cols + 1; j++) {
          sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      return sums[row2 + 1][col2 + 1]
          - sums[row2 + 1][col1]
          - sums[row1][col2 + 1]
          + sums[row1][col1];
    }
  }
}

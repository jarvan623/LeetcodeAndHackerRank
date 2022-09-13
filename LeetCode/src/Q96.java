public class Q96 {
  /*Given an integer n, return the number of structurally unique BST's (binary search trees)
  which has exactly n nodes of unique values from 1 to n.
  https://leetcode.cn/problems/unique-binary-search-trees*/
  public static void main(String[] args) {
    System.out.println(numTrees(16));
  }

  public static int numTrees(int n) {
    if (n == 1 || n == 0) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    int count = 0;
    for (int i = 1; i <= n / 2; i++) {
      // left count*right count and double the product because it is symmetrical
      count += 2 * numTrees(n - i) * numTrees(i - 1);
    }
    // for example, if n==5, the traverse above will not add n(2)*n(2)condition
    // which 3 is the root
    if (n % 2 == 1) {
      count += numTrees(n / 2) * numTrees(n / 2);
    }
    return count;
  }
}

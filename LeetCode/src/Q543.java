public class Q543 {
  public static void main(String[] args) {
    int[] vals = new int[] {1, 2, 3, 4, 5, -1, -1};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(diameterOfBinaryTree(root));
  }

  static int answer;
  // answer records the count of nodes in longest path
  public static int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    answer = 0;
    longestSubTree(root);
    return answer - 1;
  }

  public static int longestSubTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = longestSubTree(root.left);
    int right = longestSubTree(root.right);
    // update answer for bigger
    answer = Math.max(answer, 1 + left + right);
    // return the max count of nodes current node could provide
    // Either left or right plus root it self
    return 1 + Math.max(left, right);
  }
}

public class Q124 {
  /*A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
  A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

  The path sum of a path is the sum of the node's values in the path.

  Given the root of a binary tree, return the maximum path sum of any non-empty path.

  https://leetcode.cn/problems/binary-tree-maximum-path-sum*/
  public static void main(String[] args) {
    int[] vals = new int[] {-10, 9, 20, -1, -1, 15, -7};
    System.out.println(maxPathSum(TreeNode.makeTree(vals)));
  }

  static int answer;

  public static int maxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // answer begins with min_value
    // because this question is asking for at least one node tree and its max value
    // thus, if answer begins with 0 and encounter all negative tree
    // the answer would be 0 rather than max negative which is incorrect
    answer = Integer.MIN_VALUE;
    maxProvided(root);
    return answer;
  }

  public static int maxProvided(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxProvided(root.left);
    int right = maxProvided(root.right);
    // update answer for every node as root
    answer = Math.max(answer, root.val + left + right);
    if (root.val + Math.max(left, right) <= 0) {
      // if this node can only provide negative, then leave it as 0
      // the final answer would not take this node in
      return 0;
    }
    // return the max value current node could provide
    return root.val + Math.max(left, right);
  }
}

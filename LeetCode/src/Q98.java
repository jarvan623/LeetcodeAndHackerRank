public class Q98 {
  /*Given the root of a binary tree, determine if it is a valid binary search tree (BST).

  A valid BST is defined as follows:

  The left subtree of a node contains only nodes with keys less than the node's key.
  The right subtree of a node contains only nodes with keys greater than the node's key.
  Both the left and right subtrees must also be binary search trees.
  https://leetcode.cn/problems/validate-binary-search-tree*/
  public static void main(String[] args) {
    int[] vals1 = new int[] {2, 1, 3};
    int[] vals2 = new int[] {5, 1, 4, -1, -1, 3, 6};
    TreeNode root1 = TreeNode.makeTree(vals1);
    TreeNode root2 = TreeNode.makeTree(vals2);
    System.out.println(isValidBST(root1));
    System.out.println(isValidBST(root2));
  }

  public static boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    return verifyBST(root.left, root.val, Long.MIN_VALUE)
        && verifyBST(root.right, Long.MAX_VALUE, root.val);
  }

  public static boolean verifyBST(TreeNode root, long max, long min) {
    if (root == null) {
      return true;
    }
    if (root.val >= max || root.val <= min) {
      return false;
    }
    return verifyBST(root.left, root.val, min) && verifyBST(root.right, max, root.val);
  }
}

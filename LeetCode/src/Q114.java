public class Q114 {
  /*The "linked list" should use the same TreeNode class
  where the right child pointer points to the next node in the list and the left child pointer is always null.
  The "linked list" should be in the same order as a pre-order traversal of the binary tree.
  https://leetcode.cn/problems/flatten-binary-tree-to-linked-list*/
  public static void main(String[] args) {
    int[] nodes = new int[] {1, 2, 5, 3, 4, -1, 6};
    TreeNode root = TreeNode.makeTree(nodes);
    System.out.println(TreeNode.toString(root));
    flatten(root);
    System.out.println(TreeNode.toString(root));
  }

  public static void flatten(TreeNode root) {
    // from bottom to top. recursively flat the tree
    // or do pre-travel and rebuild the tree
    if (root == null) {
      return;
    }
    flatten(root.left);
    flatten(root.right);
    TreeNode temp = root.right;
    root.right = root.left;
    // very important here, invert left node to right and remember to cut original left node
    root.left = null;
    while (root.right != null) {
      root = root.right;
    }
    root.right = temp;
  }
}

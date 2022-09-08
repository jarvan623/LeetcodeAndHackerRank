public class Q226 {
  public static void main(String[] args) {
    // Given the root of a binary tree, invert the tree, and return its root.
    int[] nodes = new int[] {4, 2, 7, 1, 3, 6, 9};
    TreeNode root = TreeNode.makeTree(nodes);
    System.out.println(TreeNode.toString(root));
    TreeNode answer = invertTree(root);
    System.out.println(TreeNode.toString(answer));
  }

  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    invertTree(root.left);
    invertTree(root.right);
    return root;
  }
}

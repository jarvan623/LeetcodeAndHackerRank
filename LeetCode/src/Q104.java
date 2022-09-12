public class Q104 {
  public static void main(String[] args) {
    int[] vals = new int[] {3, 9, 20, -1, -1, 15, 7};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(maxDepth(root));
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }
}

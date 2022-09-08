public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  public static TreeNode makeTree(int[] nodes) {
    TreeNode[] tree = new TreeNode[nodes.length];
    for (int i = nodes.length - 1; i >= 0; i--) {
      tree[i] = new TreeNode(nodes[i]);
      if (i * 2 + 1 < nodes.length) {
        tree[i].left = tree[i * 2 + 1];
        tree[i].right = tree[i * 2 + 2];
      }
    }
    return tree[0];
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public static String toString(TreeNode root) {
    if (root == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    sb.append(root.val);
    sb.append(toString(root.left));
    sb.append(toString(root.right));
    return sb.toString();
  }
}

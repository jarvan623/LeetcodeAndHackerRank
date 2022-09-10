public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  public static TreeNode makeTree(int[] nodes) {
    // input has to be level-traversal of a tree and each level has 2^(n-1) element
    // including every null nodes. Even in level 4, there could be 7 null(-1) and only one not null
    TreeNode[] tree = new TreeNode[nodes.length];
    for (int i = nodes.length - 1; i >= 0; i--) {
      if (nodes[i] == -1) {
        tree[i] = null;
        continue;
      }
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
      return " -1";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    sb.append(root.val);
    sb.append(toString(root.left));
    sb.append(toString(root.right));
    return sb.toString();
  }
}

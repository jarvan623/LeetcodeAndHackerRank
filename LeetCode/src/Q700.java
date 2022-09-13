public class Q700 {
  /*You are given the root of a binary search tree (BST) and an integer val.
  Find the node in the BST that the node's value equals val and
  return the subtree rooted with that node. If such a node does not exist, return null.
  https://leetcode.cn/problems/search-in-a-binary-search-tree*/
  public static void main(String[] args) {
    int[] vals = new int[] {4, 2, 7, 1, 3, -1, -1};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(TreeNode.toString(searchBST(root, 2)));
  }

  public static TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    if (root.val == val) {
      return root;
    }
    if (root.val > val) {
      return searchBST(root.left, val);
    }
    return searchBST(root.right, val);
  }
}

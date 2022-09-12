public class Q669 {
  /*Given the root of a binary search tree and the lowest and highest boundaries as low and high,
  trim the tree so that all its elements lies in [low, high].
  Trimming the tree should not change the relative structure of the elements that will remain in the tree
  (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
  Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
  https://leetcode.cn/problems/trim-a-binary-search-tree*/

  public static void main(String[] args) {
    int[] vals = new int[] {3, 0, 4, -1, 2, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1};
    TreeNode root = TreeNode.makeTree(vals);
    int low = 1;
    int high = 3;
    System.out.println(TreeNode.toString(trimBST(root, low, high)));
  }

  public static TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) {
      // no need to trim null nodes
      return null;
    }
    if (root.val < low) {
      // if root val is smaller than low means there is no nodes in left tree will meet the range
      // go for seeking in right tree
      return trimBST(root.right, low, high);
    }
    if (root.val > high) {
      return trimBST(root.left, low, high);
    }
    // if root is in the range, trim its subtrees
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    return root;
  }
}

public class Q701 {
  /*You are given the root node of a binary search tree (BST) and a value to insert into the tree.
  Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST
  Notice that there may exist multiple valid ways for the insertion
  as long as the tree remains a BST after insertion. You can return any of them
  https://leetcode.cn/problems/insert-into-a-binary-search-tree*/
  public static void main(String[] args) {
    int[] vals = new int[] {4, 2, 7, 1, 3, -1, -1};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(TreeNode.toString(insertIntoBST(root, 5)));
  }

  public static TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      // if null, means it could suit for any val so insert and return
      return new TreeNode(val);
    }
    if (root.val > val) {
      // insert into root's left tree
      root.left = insertIntoBST(root.left, val);
      return root;
    }
    // insert into root's right tree
    root.right = insertIntoBST(root.right, val);
    return root;
  }
}

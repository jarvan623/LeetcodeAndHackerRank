public class Q450 {
  /*Given a root node reference of a BST and a key, delete the node with the given key in the BST.
  Return the root node reference (possibly updated) of the BST.
  Basically, the deletion can be divided into two stages:
  Search for a node to remove.
  If the node is found, delete the node.

  https://leetcode.cn/problems/delete-node-in-a-bst*/
  public static void main(String[] args) {
    int[] vals = new int[] {5, 3, 6, 2, 4, -1, 7};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(TreeNode.toString(deleteNode(root, 3)));
  }

  public static TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val == key) {
      // if current node is key
      // try to find the smallest node in its right tree
      TreeNode temp = root;
      temp = temp.right;
      if (temp == null) {
        // if it has no right tree, just return its left tree
        return root.left;
      }
      if (temp.left == null) {
        // right node has no left node thus this node replaces root and take over root's left tree
        temp.left = root.left;
        return temp;
      }
      TreeNode before = new TreeNode();
      while (temp.left != null) {
        // traverse till its leftest node and before store its prev node
        before = temp;
        temp = temp.left;
      }
      root.val = temp.val;
      before.left = temp.right;
      return root;
    }
    if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else {
      root.right = deleteNode(root.right, key);
    }
    return root;
  }
}

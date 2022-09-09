import java.util.Arrays;

public class Q106 {
  /*Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
  and postorder is the postorder traversal of the same tree, construct and return the binary tree.
  https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal*/
  public static void main(String[] args) {
    int[] preorder = new int[] {3, 9, 20, 15, 7};
    int[] inorder = new int[] {9, 3, 15, 20, 7};
    System.out.println(TreeNode.toString(buildTree(preorder, inorder)));
  }

  public static TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder == null || inorder.length == 0) {
      return null;
    }
    int n = inorder.length;
    //root of current tree is the last element in postorder
    TreeNode root = new TreeNode(postorder[n - 1]);
    for (int i = 0; i < n; i++) {
      if (inorder[i] == root.val) {
        root.left =
            buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0, i));
        root.right =
            buildTree(
                Arrays.copyOfRange(inorder, i + 1, n), Arrays.copyOfRange(postorder, i, n - 1));
        break;
      }
    }
    return root;
  }
}

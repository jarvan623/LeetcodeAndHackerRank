import java.util.Arrays;

public class Q105 {
  /*Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
  and inorder is the inorder traversal of the same tree, construct and return the binary tree.
  https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal*/

  public static void main(String[] args) {
    int[] preorder = new int[] {3, 9, 20, 15, 7};
    int[] inorder = new int[] {9, 3, 15, 20, 7};
    System.out.println(TreeNode.toString(buildTree(preorder, inorder)));
  }

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || preorder.length == 0) {
      return null;
    }
    int rootVal = preorder[0];
    int n = inorder.length;
    TreeNode root = new TreeNode(rootVal);
    for (int i = 0; i < n; i++) {
      if (inorder[i] == rootVal) {
        root.left =
            buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
        root.right =
            buildTree(
                Arrays.copyOfRange(preorder, i + 1, n), Arrays.copyOfRange(inorder, i + 1, n));
        break;
      }
    }
    return root;
  }
}

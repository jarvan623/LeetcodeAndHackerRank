import java.util.Arrays;

public class Q889 {
  /*Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree
  of distinct values and postorder is the postorder traversal of the same tree,reconstruct and return the binary tree.
  If there exist multiple answers, you can return any of them.
  https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal*/
  public static void main(String[] args) {
    int[] preorder = new int[] {1, 2, 4, 5, 3, 6, 7};
    int[] postorder = new int[] {4, 5, 2, 6, 7, 3, 1};
    System.out.println(TreeNode.toString(constructFromPrePost(preorder, postorder)));
  }

  public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
    int n = preorder.length;
    if (n == 0) {
      return null;
    }
    if (n == 1) {
      return new TreeNode(preorder[0]);
    }
    TreeNode root = new TreeNode(preorder[0]);
    // when have pre and post order, cannot divide according to root
    // because root is the head in pre and the tail in post, has no help to divide
    // we use the left node which is the 2nd element in pre
    // or use the right node which is the penultimate element in postorder
    // anyway rebuild the tree is easy when you mind the INDEX for copyofrange
    int left = preorder[1];
    for (int i = 0; i < n; i++) {
      if (postorder[i] == left) {
        root.left =
            constructFromPrePost(
                Arrays.copyOfRange(preorder, 1, i + 2), Arrays.copyOfRange(postorder, 0, i + 1));
        root.right =
            constructFromPrePost(
                Arrays.copyOfRange(preorder, i + 2, n),
                Arrays.copyOfRange(postorder, i + 1, n - 1));
        break;
      }
    }
    return root;
  }
}

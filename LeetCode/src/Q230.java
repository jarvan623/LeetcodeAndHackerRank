import java.util.ArrayList;
import java.util.List;

public class Q230 {
  /*Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed)
  of all the values of the nodes in the tree.
  https://leetcode.cn/problems/kth-smallest-element-in-a-bst*/
  public static void main(String[] args) {
    int[] vals = new int[] {3, 1, 4, -1, 2, -1, -1};
    System.out.println(kthSmallest(TreeNode.makeTree(vals), 1));
  }

  public static int kthSmallest(TreeNode root, int k) {
    // BST could be ranked by inorder traverse
    List<Integer> ans = inorder(root);
    return ans.get(k - 1);
  }

  public static List<Integer> inorder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<Integer> answer = new ArrayList<>();
    answer.addAll(inorder(root.left));
    answer.add(root.val);
    answer.addAll(inorder(root.right));
    return answer;
  }
}

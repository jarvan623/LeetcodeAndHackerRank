public class Q1373 {
  /*Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

  Assume a BST is defined as follows:

  The left subtree of a node contains only nodes with keys less than the node's key.
  The right subtree of a node contains only nodes with keys greater than the node's key.
  Both the left and right subtrees must also be binary search trees.
  https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree*/
  public static void main(String[] args) {
    int[] vals = new int[] {1, 4, 3, 2, 4, 2, 5, -1, -1, -1, -1, -1, -1, 4, 6};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(Solution1.maxSumBST1(root));
    System.out.println(Solution2.maxSumBST(root));
  }

  static class Solution1 {
    /*this solution is easy to understand
    but this solution will traverse 3 times for every node(in worst condition)
    time consumption will exceed the question limitation
    thus need to get double or triple information in one traverse
    for example, we can decide whether bst and the total val in one single traverse
    for specific,go solution2*/
    private static int max = 0;

    public static int maxSumBST1(TreeNode root) {
      if (root == null) {
        return 0;
      }
      if (isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE)) {
        max = Math.max(total(root), max);
      }
      maxSumBST1(root.left);
      maxSumBST1(root.right);
      return max;
    }

    public static boolean isBST(TreeNode root, int max, int min) {
      if (root == null) {
        return true;
      }
      if (root.val < max && root.val > min) {
        return isBST(root.left, Math.min(max, root.val), min)
            && isBST(root.right, max, Math.max(root.val, min));
      }
      return false;
    }

    public static int total(TreeNode root) {
      if (root == null) {
        return 0;
      }
      return root.val + total(root.left) + total(root.right);
    }
  }

  static class Solution2 {
    static int answer = Integer.MIN_VALUE;

    public static int maxSumBST(TreeNode root) {
      nodeCondition(root);
      return Math.max(answer, 0);
    }

    public static int[] nodeCondition(TreeNode root) {
      if (root == null) {
        // if node is null
        // it will not decide whether its father is BST
        // [0]represents 0-no bst 1-is bst
        // [1]current tree's total val,null node is 0
        // [2]biggest value in current tree. cause null node cannot influence father thus MIN_VALUE
        // [3]smallest value in current tree
        return new int[] {1, 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
      }
      int[] temp = new int[4];
      int[] left = nodeCondition(root.left);
      int[] right = nodeCondition(root.right);
      // if left or right son is not bst then father is not bst
      // if root.val <= maxvalue in left subtree then root is not bst
      // if root.val >= minvalue in right subtree then root is not bst
      if (left[0] == 0 || right[0] == 0 || root.val <= left[2] || root.val >= right[3]) {
        return new int[] {0, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
      }
      // till here root is bst
      temp[0] = 1;
      // root's total value would be itself and its left and right subtrees
      temp[1] = left[1] + right[1] + root.val;
      // when left or right node is null. the Math compare will work
      // for example temp = Math.max(Integer.MIN_VALUE, root.val)
      // max value in root tree would be root's value
      // apart from above situation, temp[2]or[3] would not be root.val because root is bst
      // thus, root tree's max value would always be root's right subtree's max
      temp[2] = Math.max(right[2], root.val);
      temp[3] = Math.min(root.val, left[3]);
      answer = Math.max(answer, temp[1]);
      return temp;
    }
  }
}

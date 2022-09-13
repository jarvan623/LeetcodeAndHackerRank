import java.util.ArrayList;
import java.util.List;

public class Q95 {
  /*Given an integer n, return all the structurally unique BST's (binary search trees),
  which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

  https://leetcode.cn/problems/unique-binary-search-trees-ii*/
  public static void main(String[] args) {
    List<TreeNode> treeNodes = generateTrees(5);
    for (TreeNode node : treeNodes) {
      System.out.println(TreeNode.toString(node));
    }
  }

  public static List<TreeNode> generateTrees(int n) {
    return makeTrees(1, n);
  }

  public static List<TreeNode> makeTrees(int start, int end) {
    List<TreeNode> answer = new ArrayList<>();
    if (end < start) {
      // base case for creating null node for root
      answer.add(null);
      return answer;
    }
    if (end - start == 0) {
      // equals mean only one node and one permutation
      answer.add(new TreeNode(start));
      return answer;
    }
    if (end - start == 1) {
      // two nodes condition has two permutation
      answer.add(new TreeNode(end, new TreeNode(start), null));
      answer.add(new TreeNode(start, null, new TreeNode(end)));
      return answer;
    }
    // i starts from start not 1
    for (int i = start; i <= end; i++) {
      List<TreeNode> leftNodes = makeTrees(start, i - 1);
      List<TreeNode> rightNodes = makeTrees(i + 1, end);
      for (TreeNode leftNode : leftNodes) {
        for (TreeNode rightNode : rightNodes) {
          TreeNode root = new TreeNode(i);
          root.left = leftNode;
          root.right = rightNode;
          answer.add(root);
        }
      }
    }
    return answer;
  }
}

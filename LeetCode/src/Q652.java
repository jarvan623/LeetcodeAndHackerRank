import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q652 {
  /*Given the root of a binary tree, return all duplicate subtrees.

  For each kind of duplicate subtrees, you only need to return the root node of any one of them.

  Two trees are duplicate if they have the same structure with the same node values.
  https://leetcode.cn/problems/find-duplicate-subtrees*/
  public static void main(String[] args) {
    int[] nodeVals = new int[] {1, 2, 3, 4, -1, 2, 4, -1, -1, -1, -1, 4, -1, -1, -1};
    TreeNode root = TreeNode.makeTree(nodeVals);
    System.out.println(TreeNode.toString(root));
    List<TreeNode> duplicateSubtrees = findDuplicateSubtrees(root);
    for (TreeNode node : duplicateSubtrees) {
      System.out.println(node.val);
    }
  }

  static HashMap<String, Integer> map = new HashMap<>();
  static List<TreeNode> answer = new ArrayList<>();

  public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    if (root == null) {
      return null;
    }
    String preorder = preorder(root);
    map.put(preorder, map.getOrDefault(preorder, 0) + 1);
    if (map.get(preorder) == 2 && !preorder.equals("#")) {
      // only 2 would be added to answer 3 4 5 6..will not
      answer.add(root);
    }
    findDuplicateSubtrees(root.left);
    findDuplicateSubtrees(root.right);
    return answer;
  }

  public static String preorder(TreeNode root) {
    if (root == null) {
      return "#";
    }
    String s = Integer.toString(root.val);
    // every element should be sperated by "," to avoid 1 11 and 11 1
    s += ",";
    s += preorder(root.left);
    s += ",";
    s += preorder(root.right);
    return s;
  }
}

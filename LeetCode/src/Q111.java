import java.util.ArrayList;
import java.util.List;

public class Q111 {
  /*Given a binary tree, find its minimum depth.

  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

  Note: A leaf is a node with no children.

  https://leetcode.cn/problems/minimum-depth-of-binary-tree*/
  public static void main(String[] args) {
    int[] vals = new int[] {3, 9, 20, -1, -1, 15, 7};
    TreeNode root = TreeNode.makeTree(vals);
    System.out.println(minDepth1(root));
  }

  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int right = minDepth(root.right);
    int left = minDepth(root.left);
    // if one side is null and the other side is not
    // this node is not leaf node so need to add none-null side length
    if (right == 0 && left != 0) {
      return 1 + left;
    }
    if (right != 0 && left == 0) {
      return 1 + right;
    }
    return 1 + Math.min(right, left);
  }

  public static int minDepth1(TreeNode root) {
    // level traverse when encounter a node with two null nodes that would be minimum depth
    List<TreeNode> list = new ArrayList<>();
    if (root == null) {
      return 0;
    }
    // answer will record the level of tree
    int answer = 1;
    list.add(root);
    while (list.size() > 0) {
      // fix the size to avoid size dynamic increasing
      // we only traverse nodes in same level in ONE-FOR-LOOP
      int size = list.size();
      for (int i = 0; i < size; i++) {
        TreeNode temp = list.remove(0);
        if (temp.left == null && temp.right == null) {
          // if current node has two null nodes, means it is a leaf
          // and it has to be the shortest leaf because it is level traverse
          return answer;
        }
        if (temp.left != null) {
          list.add(temp.left);
        }
        if (temp.right != null) {
          list.add(temp.right);
        }
      }
      // after all nodes in same level are traversed, add the level count
      answer++;
    }
    return answer;
  }
}

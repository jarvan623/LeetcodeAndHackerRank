import java.util.ArrayList;
import java.util.Arrays;

public class Q297 {
  /*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
  in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

  Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
  You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure
  Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
  You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
  https://leetcode.cn/problems/serialize-and-deserialize-binary-tree*/
  public static void main(String[] args) {
    System.out.println("".length());
    int[] vals = new int[] {1, 2, -1};
    TreeNode root = TreeNode.makeTree(vals);
    String serialize = Codec2.serialize(root);
    System.out.println(serialize);
    TreeNode temp = Codec2.deserialize(serialize);
    System.out.println(TreeNode.toString(temp));
  }

  public static class Codec1 {

    // Encodes a tree to a single string.
    // can reuse the method to rebuild a tree by preorder - inorder
    // however, this solution only applicable for unique val tree
    // because the preorder or inorder did not store any information for null nodes
    // thus there would be conflict when root val is same as one son val
    // for perfect solution view class Codec2
    public static String serialize(TreeNode root) {
      if (root == null) {
        return "";
      }
      String preOrder = preOrder(root);
      String inorder = inOrder(root);
      return preOrder + "," + inorder;
    }

    public static String preOrder(TreeNode root) {
      if (root == null) {
        return null;
      }
      String s = Integer.toString(root.val);
      String left = preOrder(root.left);
      String right = preOrder(root.right);
      if (left != null) {
        s += "," + left;
      }
      if (right != null) {
        s += "," + right;
      }
      return s;
    }

    public static String inOrder(TreeNode root) {
      if (root == null) {
        return null;
      }
      String left = inOrder(root.left);
      String s = Integer.toString(root.val);
      String right = inOrder(root.right);
      if (left != null) {
        s = left + "," + s;
      }
      if (right != null) {
        s += "," + right;
      }
      return s;
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

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
      int n = data.length();
      if (data == null || n == 0) {
        return null;
      }
      String[] split = data.split(",");
      n = split.length;
      int[] preOrder = new int[n / 2];
      int[] inOrder = new int[n / 2];
      for (int i = 0; i < n / 2; i++) {
        preOrder[i] = Integer.valueOf(split[i]);
      }
      for (int i = n / 2; i < n; i++) {
        inOrder[i - n / 2] = Integer.valueOf(split[i]);
      }
      return buildTree(preOrder, inOrder);
    }
  }

  public static class Codec2 {

    // Encodes a tree to a single string.
    // you have to provide at least two of pre,in,post to rebuild a tree
    // however, if you store additional information for null nodes
    // then you can rebuild a tree just based on single one traverse
    public static String serialize(TreeNode root) {
      if (root == null) {
        return "#";
      }
      String s = Integer.toString(root.val);
      s += "," + serialize(root.left) + "," + serialize(root.right);
      return s;
    }

    private static ArrayList<String> nodes;

    public static TreeNode deserialize(String data) {
      if (data == null || data.length() == 0) {
        return null;
      }
      nodes = new ArrayList<>();
      for (String s : data.split(",")) {
        nodes.add(s);
      }
      return dePreOrder(nodes);
    }

    private static TreeNode dePreOrder(ArrayList<String> nodes) {
      if (nodes.isEmpty()) {
        return null;
      }
      if (nodes.get(0).equals("#")) {
        nodes.remove(0);
        return null;
      }
      TreeNode root = new TreeNode(Integer.valueOf(nodes.get(0)));
      nodes.remove(0);
      root.left = dePreOrder(nodes);
      // all left nodes has been removed already
      root.right = dePreOrder(nodes);
      return root;
    }

    // Decodes your encoded data to tree.
    private static String vals;

    public static TreeNode deserialize1(String data) {
      if (data.length() == 0) {
        return null;
      }
      if (data.charAt(0) == '#') {
        if (data.length() == 1) {
          vals = "";
          return null;
        }
        vals = data.substring(2, data.length());
        return null;
      }
      TreeNode root;
      // the first element could be tricky if use string
      // for example -17 this elements takes 3 positions in string
      // but 3 or -3 only take 1 or 2 position
      // thus it is stupid to use string here
      // arraylist is better than string or array
      // could be modified and also could isolate numbers like arrays no matter how many digits
      // right answer plz see deserialize method
      if (data.charAt(0) == '-') {
        // will fail for example when -17
        root = new TreeNode(-data.charAt(1) - '0');
        vals = data.substring(3, data.length());
      } else {
        root = new TreeNode(data.charAt(1) - '0');
        vals = data.substring(2, data.length());
      }
      root.left = deserialize1(vals);
      root.right = deserialize1(vals);
      return root;
    }
  }
}

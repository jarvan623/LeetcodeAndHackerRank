public class Q116 {
  /*You are given a perfect binary tree where all leaves are on the same level
  and every parent has two children. The binary tree has the following definition:
  struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
  }
  Populate each next pointer to point to its next right node. If there is no next right node,
  the next pointer should be set to NULL.

  Initially, all next pointers are set to NULL
  https://leetcode.cn/problems/populating-next-right-pointers-in-each-node*/
  public static void main(String[] args) {
    Node root = new Node(1);
    Node left = new Node(2);
    Node right = new Node(3);
    root.left = left;
    root.right = right;
    connect1(root);
  }

  public static Node connect1(Node root) {
    if (root == null || root.left == null) {
      return root;
    }
    Node left = root.left;
    Node right = root.right;
    left.next = right;
    while (left.right != null) {
      // connect gap between two sub tree for every node
      left = left.right;
      right = right.left;
      left.next = right;
    }
    connect1(root.left);
    connect1(root.right);
    return root;
  }

  public Node connect2(Node root) {
    // it is easier to understand with a helper function
    if (root == null || root.left == null) {
      return root;
    }
    realConnect(root.left, root.right);
    return root;
  }

  public void realConnect(Node left, Node right) {
    if (left == null) {
      return;
    }
    left.next = right;
    realConnect(left.left, left.right);
    realConnect(right.left, right.right);
    realConnect(left.right, right.left);
  }

  static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }
  ;
}

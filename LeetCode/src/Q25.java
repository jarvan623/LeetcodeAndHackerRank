import java.util.Stack;

public class Q25 {
  /*Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

  k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

  You may not alter the values in the list's nodes, only nodes themselves may be changed.

  https://leetcode.cn/problems/reverse-nodes-in-k-group*/
  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    ListNode answer = reverseKGroup1(head, 2);
    while (answer != null) {
      System.out.println(answer.val);
      answer = answer.next;
    }
  }

  public static ListNode reverseKGroup1(ListNode head, int k) {
    // base case is to reverse the first K nodes and put the rest to the reverse again and again
    if (k == 1) {
      return head;
    }
    ListNode groupHead = head;
    int count = 0;
    while (head != null) {
      count++;
      head = head.next;
    }
    if (count < k) {
      return groupHead;
    }
    ListNode curr = groupHead.next;
    ListNode pre = groupHead;
    for (int i = 0; i < k - 1; i++) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    groupHead.next = reverseKGroup1(curr, k);
    return pre;
  }

  public static ListNode reverseKGroup2(ListNode head, int k) {
    // stack solution
    if (k == 1) {
      return head;
    }
    ListNode dummy = new ListNode(-1, head);
    ListNode temp = dummy;
    Stack<ListNode> stack = new Stack<>();
    while (head != null) {
      for (int i = 0; i < k; i++) {
        if (head == null) {
          // if less than k, just return
          // no worry about the stack because the rest of less than K nodes are added at the end
          return dummy.next;
        }
        stack.add(new ListNode(head.val));
        head = head.next;
      }
      while (!stack.isEmpty()) {
        temp.next = stack.pop();
        temp = temp.next;
      }
      // ensure the rest nodes less than K would be connected at the end of list
      temp.next = head;
    }
    return dummy.next;
  }
}

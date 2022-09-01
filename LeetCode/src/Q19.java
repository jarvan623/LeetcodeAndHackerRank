public class Q19 {
  /*Given the head of a linked list, remove the nth node from the end of the list and return its head.
  https://leetcode.cn/problems/remove-nth-node-from-end-of-list*/
  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    System.out.println(removeNthFromEnd(head, 4).val);
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(-1, head);
    ListNode fast = dummy;
    ListNode slow = dummy;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
  }
}

public class Q876 {
  /*Given the head of a singly linked list, return the middle node of the linked list.

  If there are two middle nodes, return the second middle node.

  https://leetcode.cn/problems/middle-of-the-linked-list*/
  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    System.out.println(middleNode(head).val);
  }

  public static ListNode middleNode(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    // if fast.next == null, it means that there are odd nodes and there is only one middle
    if (fast.next == null) {
      return slow;
    }
    // it means even nodes and there are two middles and return the second middle
    return slow.next;
  }
}

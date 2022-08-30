public class Q141 {
  /*Given head, the head of a linked list, determine if the linked list has a cycle in it.

  There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

  Return true if there is a cycle in the linked list. Otherwise, return false.

  https://leetcode.cn/problems/linked-list-cycle*/
  public static void main(String[] args) {
    ListNode head = new ListNode(3);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(0);
    ListNode head4 = new ListNode(-4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    head4.next = head2;
    System.out.println(hasCycle(head));
  }

  public static boolean hasCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (slow != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        // fast goes faster than slow, when faces again, there is a circle
        return true;
      }
    }
    // if the while loop breaks, means there is an end in the LinkedList
    // Thus, this LinkedList has no circle because node only have one NEXT
    // which means there could not be a circle in the middle of the LinkedList
    return false;
  }
}

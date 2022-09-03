public class Q92 {
  /*Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
  https://leetcode.cn/problems/reverse-linked-list-ii*/
  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    ListNode head5 = new ListNode(5);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    head4.next = head5;
    ListNode answer = reverseBetween2(head, 3, 4);
    while (answer != null) {
      System.out.println(answer.val);
      answer = answer.next;
    }
  }

  public static ListNode reverseBetween1(ListNode head, int left, int right) {
    // iteration
    // left and right are the INDEX of list nodes rather than val of nodes
    if (left == right) {
      // no need to reverse, just return
      return head;
    }
    ListNode dummy = new ListNode(-1, head);
    ListNode dummyLeft = dummy;
    ListNode curr;
    ListNode pre;
    ListNode groupHead;
    int count = 1;
    while (head != null) {
      while (count < left) {
        // before reach left, head moves and user dummyleft to record the node before left
        dummyLeft = head;
        head = head.next;
        count++;
      }
      // head currently is LEFT nodes
      // groupHead records the LEFT but it would become the last node of [left,right]range
      groupHead = head;
      pre = head;
      curr = head.next;
      count++;
      while (count <= right) {
        // in the range,do reverse
        ListNode temp = curr.next;
        curr.next = pre;
        pre = curr;
        curr = temp;
        count++;
      }
      // the node before left connects to the pre
      dummyLeft.next = pre;
      // the LEFT node becomes the last node in range and connects to the rest nodes
      groupHead.next = curr;
      break;
    }
    return dummy.next;
  }

  public static ListNode reverseBetween2(ListNode head, int left, int right) {
    if (left == right) {
      return head;
    }
    // convert this question to reverse the firstK nodes reverse
    if (left > 1) {
      // if left>1,means there are still nodes before target range, so continues to recurse
      head.next = reverseBetween2(head.next, --left, --right);
      return head;
    }
    ListNode dummy = new ListNode(-1, head);
    ListNode curr = head;
    ListNode pre = dummy;
    for (int i = 0; i < right; i++) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    // head pointer never changes, it is the start of range and needs to point to the rest nodes
    head.next = curr;
    // pre is the last node in range, but no becomes the first node in whole list
    return pre;
  }
}

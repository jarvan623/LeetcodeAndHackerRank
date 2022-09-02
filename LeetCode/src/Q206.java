public class Q206 {
  /*Given the head of a singly linked list, reverse the list, and return the reversed list.
  https://leetcode.cn/problems/reverse-linked-list*/
  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    ListNode answer = reverseList2(head);
    while (answer != null) {
      System.out.println(answer.val);
      answer = answer.next;
    }
  }

  public static ListNode reverseList1(ListNode head) {
    // Iteration solution is easier to understand and cost less memory
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    return pre;
  }

  public static ListNode reverseList2(ListNode head) {
    // recursion is cool but consumes extra stack space.
    // if reach end, return end
    if (head == null || head.next == null) {
      return head;
    }
    // firtly, get the end node: last
    ListNode last = reverseList2(head.next);
    // reverse head and head.next by building new pointer FROM head.next TO head
    head.next.next = head;
    // cut the pointer FROM head TO head.next
    head.next = null;
    return last;
  }
}

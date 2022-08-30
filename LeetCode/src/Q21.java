public class Q21 {
  /* You are given the heads of two sorted linked lists list1 and list2.

  Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

  Return the head of the merged linked list.

  https://leetcode.cn/problems/merge-two-sorted-lists*/
  public static void main(String[] args) {
    int[] l1 = new int[] {1, 2, 4};
    int[] l2 = new int[] {1, 3, 4};
    ListNode list1 = new ListNode(l1[0]);
    ListNode list2 = new ListNode(l2[0]);
    ListNode dummy1 = new ListNode(-1, list1);
    ListNode dummy2 = new ListNode(-1, list2);
    for (int i = 1; i < l1.length; i++) {
      list1.next = new ListNode(l1[i]);
      list1 = list1.next;
    }
    for (int i = 1; i < l2.length; i++) {
      list2.next = new ListNode(l2[i]);
      list2 = list2.next;
    }
    ListNode answer = mergeTwoLists(dummy1.next, dummy2.next);
    while (answer != null) {
      System.out.println(answer.val);
      answer = answer.next;
    }
  }

  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    ListNode dummy = new ListNode(-1);
    ListNode head = dummy;
    while (list1 != null & list2 != null) {
      if (list1.val > list2.val) {
        head.next = list2;
        list2 = list2.next;
        head = head.next;
      } else {
        head.next = list1;
        list1 = list1.next;
        head = head.next;
      }
    }
    if (list1 == null) {
      head.next = list2;
    } else {
      head.next = list1;
    }
    return dummy.next;
  }
}

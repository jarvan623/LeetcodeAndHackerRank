import java.util.PriorityQueue;

public class Q23 {
  /*You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

  Merge all the linked-lists into one sorted linked-list and return it

  https://leetcode.cn/problems/merge-k-sorted-lists*/
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
    ListNode[] lists = new ListNode[] {dummy1.next, dummy2.next};
    ListNode answer = mergeKLists(lists);
    while (answer != null) {
      System.out.println(answer.val);
      answer = answer.next;
    }
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (ListNode node : lists) {
      while (node != null) {
        pq.offer(node.val);
        node = node.next;
      }
    }
    ListNode head = new ListNode(-1);
    ListNode dummy = head;
    while (!pq.isEmpty()) {
      head.next = new ListNode(pq.poll());
      head = head.next;
    }
    return dummy.next;
  }
}

public class Q83 {
  /*Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
  https://leetcode.cn/problems/remove-duplicates-from-sorted-list*/
  public static void main(String[] args) {
    int[] nums = new int[] {1, 1, 2, 3, 3};
    ListNode dummy = new ListNode(-1);
    ListNode head = new ListNode(nums[0]);
    dummy.next = head;
    for (int i = 1; i < nums.length; i++) {
      head.next = new ListNode(nums[i]);
      head = head.next;
    }
    ListNode temp = deleteDuplicates(dummy.next);
    while (temp != null) {
      System.out.println(temp.val);
      temp = temp.next;
    }
  }

  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null) {
      if (slow.val != fast.val) {
        slow.next.val = fast.val;
        slow = slow.next;
      }
      fast = fast.next;
    }
    // finally, cut elements behind slow
    slow.next = null;
    return dummy.next;
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

import java.util.HashSet;
import java.util.Set;

public class Q160 {
  /*Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

  https://leetcode.cn/problems/intersection-of-two-linked-lists*/
  public static void main(String[] args) {
    // this question has 3+ solutions
    ListNode headA = new ListNode(1);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(3);
    ListNode head4 = new ListNode(4);
    headA.next = head2;
    head2.next = head3;
    head3.next = head4;
    ListNode headB = new ListNode(5);
    headB.next = head3;
    System.out.println(getIntersectionNode1(headA, headB).val);
    System.out.println(getIntersectionNode2(headA, headB).val);
    System.out.println(getIntersectionNode3(headA, headB).val);
  }

  public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
    // count the length of two list and try to compare
    int lengthA = 0;
    ListNode dummyA = new ListNode(-1, headA);
    while (headA != null) {
      lengthA++;
      headA = headA.next;
    }
    ListNode dummyB = new ListNode(-1, headB);
    int lengthB = 0;
    while (headB != null) {
      lengthB++;
      headB = headB.next;
    }
    headA = dummyA.next;
    headB = dummyB.next;
    if (lengthA > lengthB) {
      for (int i = 0; i < lengthA - lengthB; i++) {
        headA = headA.next;
      }
      while (headA != null) {
        if (headA == headB) {
          return headA;
        }
        headA = headA.next;
        headB = headB.next;
      }
    } else {
      for (int i = 0; i < lengthB - lengthA; i++) {
        headB = headB.next;
      }
      while (headA != null) {
        if (headA == headB) {
          return headA;
        }
        headA = headA.next;
        headB = headB.next;
      }
    }
    return null;
  }

  public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    ListNode dummyA = new ListNode(-1, headA);
    while (headA.next != null) {
      headA = headA.next;
    }
    ListNode temp = headA;
    headA.next = dummyA.next;
    // connect headA's tail to headA and this question would be converted to circle of list
    ListNode fast = headB;
    ListNode slow = headB;
    ListNode dummyB = new ListNode(-1, headB);
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        fast = dummyB.next;
        while (fast != slow) {
          slow = slow.next;
          fast = fast.next;
        }
        // break the circle created to avoid intersect validation
        temp.next = null;
        return fast;
      }
    }
    temp.next = null;
    return null;
  }

  public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
    // use set to detect first repeated node
    Set<ListNode> set = new HashSet<>();
    while (headA != null) {
      set.add(headA);
      headA = headA.next;
    }
    while (headB != null) {
      if (set.contains(headB)) {
        return headB;
      } else {
        headB = headB.next;
      }
    }
    return null;
  }
}

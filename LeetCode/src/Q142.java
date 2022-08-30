public class Q142 {
  /*Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

  There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

  Do not modify the linked list.

  https://leetcode.cn/problems/linked-list-cycle-ii*/
  public static void main(String[] args) {
    ListNode head = new ListNode(3);
    ListNode head2 = new ListNode(2);
    ListNode head3 = new ListNode(0);
    ListNode head4 = new ListNode(-4);
    head.next = head2;
    head2.next = head3;
    head3.next = head4;
    head4.next = head2;
    System.out.println(detectCycle(head).val);
  }

  public static ListNode detectCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (slow != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        /*fast goes faster than slow, when faces again, there is a circle
        2(y+x)=y+2x+z
        y represents the distance between list's start to circle's start
        x represents the distance between circle's start to current node
        z represents the distance between current node to circle's start
        finally prove y = z
        let a new node begin from the list's start and slow node keep moving
        when faces, the node would be the circle's start*/
        while (slow != head) {
          head = head.next;
          slow = slow.next;
        }
        return head;
      }
    }
    // if the while loop breaks, means there is an end in the LinkedList
    // Thus, this LinkedList has no circle because node only have one NEXT
    // which means there could not be a circle in the middle of the LinkedList
    return null;
  }
}

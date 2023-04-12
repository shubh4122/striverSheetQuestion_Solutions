package striversSheet.easy;

//https://leetcode.com/problems/linked-list-cycle/description/
public class ll_findCycle {

    //Floyd's cycle detection algo
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){//NOTE == compare if 2 references are identical or not,
                // which here will be correct, as both point to exact same location
                return true;
            }
        }
        return false;
    }


    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}

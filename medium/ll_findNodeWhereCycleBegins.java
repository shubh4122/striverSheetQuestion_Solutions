package striversSheet.medium;

//https://leetcode.com/problems/linked-list-cycle-ii/description/
public class ll_findNodeWhereCycleBegins {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        //only if loop broke due to break condition, we do below steps.
        //also, we put condition on fast, as for the case [1] and no loop, this would work well
        if (fast != null && fast.next != null) {
            slow = head;

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
        return null;
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

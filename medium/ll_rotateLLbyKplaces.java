package striversSheet.medium;

import striversSheet.medium.ll_findNodeWhereCycleBegins.ListNode;

//https://leetcode.com/problems/rotate-list/description/
public class ll_rotateLLbyKplaces {

    public ListNode rotateRight(ListNode head, int k) {
        //BC
        if (head == null)
            return head;

        //1. Find len of LL
        ListNode ptr = head;
        int len = 0;
        while(ptr != null) {
            len++;
            ptr = ptr.next;
        }

        //2. Find actual k.
        k = k%len;

        if (k == 0)//i.e initial k = len or multiple of it.
            return head;

        //3. Find k+1 th node from end
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;//at last fast points to k+1th node from front
        }

        //4. Make slow point to k+1th node from end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        //5. Now start changing LINKS.
        //slow points to k+1th node, and fast points to last node.
        ptr = slow.next;//kth node broken from LL
        fast.next = head;//the broken LL added to front of LL
        head = ptr;//new head is the kth node.

        //break link of slow pointer to kth node
        slow.next = null;

        return head;
    }

    /*
        OR[ Striver's ]
        as ptr already points to TAIL.
        do:
        ptr.next = head

        and move len - k times from head.
        now, at this point, break the link and make it new head.
     */
}

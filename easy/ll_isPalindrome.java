package striversSheet.easy;

import striversSheet.easy.ll_findCycle.ListNode;

//https://leetcode.com/problems/palindrome-linked-list/
public class ll_isPalindrome {

    public static boolean isPalindrome(ListNode head) {
        ListNode ptr1 = head, ptr2 = head;
        int l = lenOfLL(head);

        //bring pointer just before the node, from which list has to be reversed. i.e. to mid node
        for (int i = 1; i < (l+1)/2; i++) {
            ptr2 = ptr2.next;
        }

        ptr2 = reverseLLfromMid(ptr2.next);

        //now ptr2 points at second half of LL
        while (ptr2 != null) {
            if (ptr1.val != ptr2.val)
                return false;
            ptr2 = ptr2.next;
            ptr1 = ptr1.next;
        }
        return true;
    }

    public static ListNode reverseLLfromMid(ListNode head) {
        if (head != null && head.next != null) {
            ListNode tailReversedLL = head.next;
            ListNode reversedLLHead = reverseLLfromMid(head.next);
            tailReversedLL.next = head;

            head.next = null;//imp to delete old, non-reversed link

            return reversedLLHead;
        }
        return head;
    }

    public static int lenOfLL(ListNode head) {
        int l = 0;
        while (head != null) {
            l++;
            head = head.next;
        }
        return l;
    }
}

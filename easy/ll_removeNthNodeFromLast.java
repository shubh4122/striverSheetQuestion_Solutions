package striversSheet.easy;

import striversSheet.easy.ll_reverseLL.Node;

//https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/

public class ll_removeNthNodeFromLast {


    public static Node removeNthFromEnd(Node head, int n) {
        Node first = head, second = head;

        for (int i = 1; i < n; i++) {
            first = first.next;//first finally should point to nth node from start
            if (first == null)//i.e. n < size of ll, return original list
                return head;
        }

        //check if first is on the last node already -> n = size and we need to remove 1st node
        if (first.next == null) {
            head = head.next;
            return head;
        }

        while (first.next.next != null) {
            first = first.next;
            second = second.next;
        }
        //now second points to node, just before the node that has to be removed.
        //1-2-3-4-5-6. n=3. by now, first = 5. second = 3. i.e 4 has to be removed

        Node removedNode = second.next;
        second.next = second.next.next;//removed the node
        removedNode.next = null;

        return head;
    }
}

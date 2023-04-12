package striversSheet.hard;

//https://leetcode.com/problems/reverse-nodes-in-k-group/description/
public class ll_reverseNodesInKGroups {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);

        int k = 5;

        ListNode rr = root;
        while (rr != null) {
            System.out.print(rr.val +" -> ");
            rr = rr.next;
        }

        System.out.println();
        ListNode head = reverseKGroup(root, k);
        while (head != null) {
            System.out.print(head.val +" -> ");
            head = head.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = null, tailTemp = head, headTemp = null;
        for (int i = 1; i < k; i++) {//runs k-1 times
            tailTemp = tailTemp.next;
        }
        newHead = tailTemp;

        while (tailTemp != null) {
            headTemp = tailTemp.next;//Store the pointer which sublist points to
            tailTemp.next = null;//disconnect sublist from LL

            //reversal
            reverseLL(head, tailTemp);

            //updation
            //now, again make tailtemp point to the new Temp head, which marks the
            //start of new sublist
            //copy is used, when TT gets to null, and yet K elem not there
            //in sublist. So in that case, we will just point TT back to its original
            //position which is preserved by ttCopy.
            tailTemp = headTemp;
            ListNode tailTempCopy = tailTemp;
            int i = 1;
            //and move tailtemp to end of this sublist
            for (; i < k; i++) {//runs k-1 times
                if (tailTemp == null || tailTemp.next == null) {
                    tailTemp = tailTempCopy;
                    break;
                }

                tailTemp = tailTemp.next;
            }

            //pointing the reversed sublist to the node, which will be
            //the head of the next reversed sublist.
            //and that next head is pointed by tailtemp, after it is updated
            //in the above for loop.
            head.next = tailTemp;
            head = headTemp;//making head point to the new sublist, waiting to be reversed

            //this runs, when we no more have sublist long enough to be reversed
            if (i != k)
                break;
        }

        return newHead;
    }

    public static void reverseLL(ListNode head, ListNode tail) {
        if (head != tail) {
            ListNode tailReversedLL = head.next;
            reverseLL(head.next, tail);
            tailReversedLL.next = head;
            //VV imp, remove the front -> back links
            head.next = null;
        }
    }



    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}

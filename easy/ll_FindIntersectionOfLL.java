package striversSheet.easy;

public class ll_FindIntersectionOfLL {

    //https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //Step 1 : Find len of both LL
        ListNode currA = headA, currB = headB;
        int lenA = 0, lenB = 0;

        while(currA != null) {
            lenA++;
            currA = currA.next;
        }
        while(currB != null) {
            lenB++;
            currB = currB.next;
        }

        currA = headA;
        currB = headB;

        //Step 2: CutOff extra path, and begin both pointers from same length
        int cutoff = Math.abs(lenA - lenB);
        //ALWAYS make currA point to larger and currB to smaller
        if (lenA < lenB){
            ListNode temp = currA;
            currA = currB;
            currB = temp;
        }

        //Step 3 : Make currA come to equal len as currB
        for (int i = 0; i < cutoff; i++) {
            currA = currA.next;
        }

        while (currA != null) {
            if (currA == currB)
                return currA;

            currA = currA.next;
            currB = currB.next;
        }
        return null;
    }


    //Image showing DRY RUN
    //https://lh3.googleusercontent.com/lQGGtwWBXL3Kvl15qC71jpZwvbokF4h963ahFBTd1fAathQjnPSbpxWbCaXv8c3OjJSaWJRot_Ug9WL85_SEPy9ShJxNNCLUFHTWsjS6pQKWGbGoK4Jhpe4Ebgr4VfbCWfOQ0uHC
    //https://leetcode.com/problems/intersection-of-two-linked-lists/solutions/49785/java-solution-without-knowing-the-difference-in-len/?orderBy=most_votes

    //MOST OPTIMISED APPROACH!
    public static ListNode getIntersectionNode_JugglinePointers(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;

            /*
                Juggling Pointers? - I coined this term :')
                The pointers (a, b) traverse through the LL, and when they reach end(null)
                They JUGGLE OVER to the other LL's head.

                Reason why its working:
                You can prove that: say A length = a + c, B length = b + c,
                after switching pointer, pointer A will move another b + c steps,
                pointer B will move a + c more steps,
                since a + c + b + c = b + c + a + c, it does not matter what value c is.
                Pointer A and B must meet after a + c + b (b + c + a) steps.
                If c == 0, they meet at NULL.

                Therefore, it'll find intersection in 2nd Traversal for sure!!
             */
        }

        return a;
    }



    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}




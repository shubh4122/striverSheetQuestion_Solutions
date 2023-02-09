package striversSheet.easy;
import striversSheet.easy.ll_reverseLL.Node;

//https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/
public class ll_mergeSortedLL {
    public static void main(String[] args) {

    }

    public static Node mergeTwoLL(Node n1, Node n2) {
        if (n1 == null && n2 == null)
            return null;
        else if (n1 == null)    return n2;
        else if (n2 == null)    return n1;

        Node newHead = (n1.data <= n2.data) ? n1 : n2;
        //i.e c1 will always point to start of merged list
        Node curr1 = newHead, curr2 = (n1.data > n2.data) ? n1 : n2;

        //now node n1 and n2 can be used as temp pointers, also n1 doesnt necessarily point to LL1
        while(curr1.next != null) {
            if (curr1.next.data <= curr2.data) {
                curr1 = curr1.next;
            }
            else {
                n1 = curr1.next;
                curr1.next = curr2;
                curr1 = curr2;
                //Basically curr1 always points to the end of the connected list by far.
                //now we use curr2 to point to the disconnected node of ll.
                curr2 = n1;
            }
        }
        //at a point when 1 list is over, c1 will be at its last node. now simply attach the other remaining list to c1.
        curr1.next = curr2;
        return newHead;
    }
}

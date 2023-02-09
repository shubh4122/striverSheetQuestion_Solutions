package striversSheet.easy;

public class ll_reverseLL {
    public static void main(String[] args) {

    }

    public static Node reversedLL_Recursive(Node head) {
        //BC
        //NOTE: Always before checking node.next = null, always check node == null first!!
        if (head == null || head.next == null)
            return head;

        //Hypothesis : fun(h) returns head of reversed LL, whose tail is h
        Node revLL_Head = reversedLL_Recursive(head.next);
        //Induction - finding ans for nTH item, after getting ans for n-1TH using recursion
        Node tailRevLL = head.next;
        tailRevLL.next = head;
        head.next = null;

        return revLL_Head;
    }

    public static Node reversedLL(Node head) {
        Node prev = null, curr = head, next;
        while (curr != null) {
            //reversing links
            next = curr.next;
            curr.next = prev;
            //moving ahead
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }


    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}

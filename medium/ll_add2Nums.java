package striversSheet.medium;

import striversSheet.easy.ll_reverseLL;
import striversSheet.easy.ll_reverseLL.Node;

//https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/
public class ll_add2Nums {

    public static Node add2Numbers(Node n1, Node n2) {
        Node ans = new Node(0);//this is just a dummy node. we start storing from its next node
        Node tempHead = ans;

        int carry = 0;
        while (n1 != null && n2 != null) {
            ans.next = new Node(0);//creating new node
            ans = ans.next;

            int add = n1.data + n2.data +carry;
            ans.data = (add)%10 ;
            carry = add/10;

            n1 = n1.next;
            n2 = n2.next;
        }

        //putting in the remaining nodes addition, when 1 linked list is done
        Node restNodes = (n1 == null) ? n2 : n1;
        while (restNodes != null) {
            ans.next = new Node(0);//creating new node
            ans = ans.next;

            int add = carry + restNodes.data;
            ans.data = add%10;
            carry = add/10;

            restNodes = restNodes.next;
        }
        if (carry != 0)
            ans.next = new Node(carry);

        return tempHead.next;//because tempHead for now points to our dummy node. so dummy.next is our starting of ans
    }
}

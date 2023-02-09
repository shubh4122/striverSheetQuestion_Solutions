package striversSheet.medium;

import striversSheet.easy.ll_reverseLL.Node;

//https://takeuforward.org/data-structure/delete-given-node-in-a-linked-list-o1-approach/
public class ll_deleteNodeFromLL {

    public static void deleteNode(Node del) {
        del.data = del.next.data;
        del.next = del.next.next;
    }
}

package striversSheet.medium;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
public class bst_PopulatingNextRightPtrs {

    //DFS - See if there is INTUITIVE solution. The ones i found weren't understandable.
//    public Node connect(Node root) {
//
//    }

    //BFS
    public Node connectBFS(Node root) {
        //level order traversal
        if (root == null)
            return root;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node pop = q.remove();

            if (pop == null) {
                if(!q.isEmpty())
                    q.add(null);
            }
            else{
                if (pop.left != null)
                    q.add(pop.left);
                if (pop.right != null)
                    q.add(pop.right);

                //creating next connection
                pop.next = q.peek();
            }
        }
        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

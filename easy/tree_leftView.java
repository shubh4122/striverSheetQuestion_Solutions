package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
public class tree_leftView {

    //Recursive Level order traversal - O(n) TC, O(H) SC -- Better

    //O(n) TC, O(n) SC
    public static ArrayList<Integer> leftview(Node root) {
        //use Level order traversal, line by line.
        //put elem just after null in queue to leftview list
        ArrayList<Integer> lv = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();//linkedlist used, coz we need to handle null too

        //TREE EMPTY
        if (root == null)
            return lv;

        q.add(root);
        q.add(null);
        lv.add(root.data);

        while (!q.isEmpty()) {
            Node popped = q.remove();
            if (popped == null) {//elem just after null is the start of level, hence the left view
                if (!q.isEmpty()) {
                    lv.add(q.peek().data);
                    q.add(null);//insert null when null popped(only when stack empty). coz it marks end of level
                }
            }
            else {
                if (popped.left != null)
                    q.add(popped.left);
                if (popped.right != null)
                    q.add(popped.right);
            }
        }
        return lv;
    }
}

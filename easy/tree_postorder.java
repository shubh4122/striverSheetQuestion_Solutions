package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.*;

//https://takeuforward.org/data-structure/post-order-traversal-of-binary-tree/
public class tree_postorder {

    public static List<Integer> postorderIterative2Stacks(Node root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<Node> s1 = new Stack<>();//temporary holds all nodes. root-left-right -> push order
        Stack<Node> s2 = new Stack<>();//contains the postorder finally

        if (root == null)
            return postorder;

        s1.push(root);
        while (!s1.isEmpty()) {
            s2.push(s1.pop());

            //push left and right child if any of s2's top
            Node topS2 = s2.peek();
            if (topS2.left != null)
                s1.push(topS2.left);
            if (topS2.right != null)
                s1.push(topS2.right);
        }

        //now we have postorder in stack 2.
        while (!s2.isEmpty())
            postorder.add(s2.pop().data);

        return postorder;
    }


    //1Stack
    public static List<Integer> postorderIterative(Node root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        Node popped;

        //empty tree
        if (root == null)
            return postorder;

        s.push(root);

        //this method similar to preorder simpler code.
        //PostOrder --> L R Root.............
        //Order of dealing postOrder --> Root R L (this is what list stores), that is reverse of Postorder. But later we will reverse List, so it becomes postorder
        //Order of dealing node --> Root L R (just swap of above order)( because stack pops last pushed elem, and hence it'll be R 1st popped and then L. Hence original postorder preserved
        while (!s.isEmpty()) {
            //Root
            popped = s.pop();
            postorder.add(popped.data);

            //L
            if (popped.left != null)
                s.push(popped.left);
            if (popped.right != null)
                s.push(popped.right);
        }

        //Now the list we have stores Order -> Root R L (reverse of postorder)
        Collections.reverse(postorder);
        return postorder;
    }

    public static List<Integer> postorderRec(Node root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(postorderRec(root.left));
            list.addAll(postorderRec(root.right));
            list.add(root.data);
        }
        return list;
    }
}

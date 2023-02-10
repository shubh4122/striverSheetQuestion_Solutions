package striversSheet.easy;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/
public class tree_inorderTraversal {

    public static List<Integer> inorderIterative(Node root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        Node popped;

        //return empty list if tree is empty
        if (root == null)
            return inorder;

        s.push(root);

        while (!s.isEmpty()){
            //put all left nodes until its null
            while (root.left != null) {
                s.push(root.left);
                root = root.left;
            }

            //pop node now
            popped = s.pop();
            inorder.add(popped.data);

            //push the right of popped node if it's not null
            if (popped.right != null) {
                root = popped.right;
                s.push(root);
            }
        }
        return inorder;
    }


    public static List<Integer> inorderRecursive(Node root){
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(inorderRecursive(root.left));
            list.add(root.data);
            list.addAll(inorderRecursive(root.right));
        }
        return list;
    }




    //Every thing public coz i want all of it to be accessible across packages
    public class Node {
        public int data;
        public Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }
}

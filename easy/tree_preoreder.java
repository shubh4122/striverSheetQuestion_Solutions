package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/
public class tree_preoreder {


    public static List<Integer> preorderIterative(Node root) {
        List<Integer> preorder = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        Node popped;

        //empty tree
        if (root == null)
            return preorder;

        s.push(root);
        preorder.add(root.data);

        while (!s.isEmpty()) {
            //adding and printing every node(left) we visit, until null
            while (root.left != null) {
                preorder.add(root.left.data);
                s.push(root.left);
                root = root.left;
            }

            //when no more left nodes, explore the right nodes, of the popped nodes
            popped = s.pop();

            //right of popped
            if (popped.right != null) {
                s.push(popped.right);
                preorder.add(popped.right.data);
                root = popped.right;
            }
        }
        return preorder;
    }


    public static List<Integer> preorderRecursion(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.data);
            list.addAll(preorderRecursion(root.left));
            list.addAll(preorderRecursion(root.right));
        }
        return list;
    }
}

package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal;
import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://takeuforward.org/data-structure/check-for-children-sum-property-in-a-binary-tree/
public class tree_childrenSumProperty {

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(35);
        root.right = new Node(10);
        root.left.left = new Node(2);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(2);

        System.out.println(tree_levelOrderTraversalLbL.lblLevelOrder(root));

        modifyTreeCSP_DFS(root);
        System.out.println(tree_levelOrderTraversalLbL.lblLevelOrder(root));
    }

    //almost same as   **preorder**   recursive traversal
    public static void modifyTreeCSP_DFS(Node root) {//void because we just need to travel to each node. no need to return anything
        /*
            Simple Recursive DFS approach
            1. if root > l + r, l = r = root
            2. if root < l + r, root = l + r

            Do 1, 2 while moving down, But do only 2 when moving up.(coz val in tree will have arranged in that way where only 2 is req)
         */

        if (root != null) {

            //Step 1 : Rewrite CHILD values when moving top to down
            rewriteChildValues(root);


            //Step 2: Traverse
            modifyTreeCSP_DFS(root.left);//move down left.
            modifyTreeCSP_DFS(root.right);//move down right


            //Step 3: Rewrite PARENT values when moving bottom to up (Update parent val as per new child values)
            rewriteParentValues(root);
        }
    }

    private static void rewriteParentValues(Node root) {
        Node left = root.left;
        Node right = root.right;

        if (left != null && right != null) {
            if (root.data < left.data + right.data)
                root.data = left.data + right.data;//only parent/root val changed
        }
        else {
            Node nonNullNode = (left == null) ? right : left;
            if (nonNullNode == null)//happens when both left and right are null
                return;

            if (root.data < nonNullNode.data)
                root.data = nonNullNode.data;
        }
    }

    private static void rewriteChildValues(Node root) {
        Node left = root.left;
        Node right = root.right;

        if (left != null && right != null) {
            if (root.data > left.data + right.data)
                left.data = right.data = root.data;//only child val changed
        }
        else {
            Node nonNullNode = (left == null) ? right : left;
            if (nonNullNode == null)//happens when both left and right are null
                return;

            if (root.data > nonNullNode.data)
                nonNullNode.data = root.data;
        }
    }






    //Easy - just check if tree follows csp
    public static int csp(Node root) {
        //following level order traversal
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){
            Node popped = q.remove();
            int left = 0, right = 0;

            if (popped.left != null) {
                q.add(popped.left);
                left = popped.left.data;
            }
            if (popped.right != null) {
                q.add(popped.right);
                right = popped.right.data;
            }

            //this means popped node is leaf node. so no check for it
            if (left == 0 && right == 0)
                continue;
            //check csp
            if (left + right != popped.data)
                return 0;
        }

        return 1;
    }
}

package striversSheet.medium;

import striversSheet.medium.tree_flattenTreeToLL.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/binary-tree-preorder-traversal/
public class tree_MorrisTraversalPreorder {

    //https://www.youtube.com/watch?v=2BdY9fixMrM
    //https://youtu.be/80Zug6D1_r4
    //above resources to learn about it.
    public List<Integer> morrisTraversalInorder(TreeNode root) {
        /*
            curr - is used for traversing
            prev - is the rightmost/last node of a subtree
            which points to curr, in order to facilitate returning back of control
            or the curr pointer once the traversal of that subtree is done.
         */
        List<Integer> preorder = new ArrayList<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null) {
            //Case 1: curr has no left.
            if (curr.left == null) {
                preorder.add(curr.val);
                curr = curr.right;
            }

            //Case 2: curr has left subtree.
            else {
                prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                //i.e loop broke coz of prev.right being NULL
                if(prev.right == null) {
                    prev.right = curr;
                    //just this line change! i.e print when we create link. coz order is, Root L R - so root printed first.
                    preorder.add(curr.val);
                    curr = curr.left;
                }
                //Loop broke due to prev.right = curr
                else{
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return preorder;
    }
}

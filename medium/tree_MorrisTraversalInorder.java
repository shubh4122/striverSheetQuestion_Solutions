package striversSheet.medium;

import striversSheet.medium.tree_flattenTreeToLL.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/binary-tree-inorder-traversal/
public class tree_MorrisTraversalInorder {

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
        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null) {
            //Case 1: curr has no left.
            if (curr.left == null) {
                inorder.add(curr.val);
                curr = curr.right;
            }

            //Case 2: curr has left subtree.
            else {
                prev = curr.left;
                /*
                    if, prev.right is NULL, we will connect it to curr
                    so, we can traverse back to parent of this SUBTREE.[backtrack]

                    but, if prev points to curr.
                    This happens when we actually backtrack using this threaded link
                    then curr is the parent and the last node of subtree points it.
                    when we reach it, this time.
                    We need to break this link. In order to UNDO changes done to
                    tree for backtracking purpose
                 */
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                //i.e loop broke coz of prev.right being NULL
                if(prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                }
                //Loop broke due to prev.right = curr
                else{
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return inorder;
    }
}

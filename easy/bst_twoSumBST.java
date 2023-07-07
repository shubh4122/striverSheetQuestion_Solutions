package striversSheet.easy;

import java.util.ArrayList;
import java.util.List;
import striversSheet.medium.bst_IteratorBST;
import striversSheet.medium.bst_IteratorBST.TreeNode;

//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
public class bst_twoSumBST {

    //BST Iterator - O(n)TC, O(2h) SC -> BETTER [1 stack for next, 1 for before]
    /*
        Similarity with 2 pointers.
        we can use BEFORE function in iterator to mimic the END ptr in 2 ptr method
        and Next -> start pointer.
     */
    public boolean findTargetBetter(TreeNode root, int k) {
        //Using the already created Iterator class.
        bst_IteratorBST iterator = new bst_IteratorBST(root);

        //unlike 2 ptr method, here start and end HOLD, ACTUAL values and not their indices.
        int start = iterator.next();
        int end = iterator.before();
        while (end > start) {
            int sum = start+end;
            if (sum < k)
                start = iterator.next();
            else if (sum > k)
                end = iterator.before();
            else//equal
                return true;
        }
        return false;
    }


    //2 pointer method - O(2n) TC, O(n) SC
    ArrayList<Integer> inorder = new ArrayList<>();
    public boolean findTarget(TreeNode root, int k) {
        inorderTrav(root);

        int start = 0, end = inorder.size()-1;
        while (start < end) {
            int sum = inorder.get(start) + inorder.get(end);
            if (sum < k)
                start++;
            else if (sum > k)
                end--;
            else
                return true;
        }
        return false;
    }

    private void inorderTrav(TreeNode root) {
        if (root == null)
            return;

        inorderTrav(root.left);
        inorder.add(root.val);
        inorderTrav(root.right);
    }



}

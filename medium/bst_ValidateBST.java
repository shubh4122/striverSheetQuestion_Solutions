package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal;
import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.List;

//https://leetcode.com/problems/validate-binary-search-tree/
public class bst_ValidateBST {

    //Intuition - Get a range for every NODE. for root - range: INTMIN to INTMAX
    public boolean isValidBST(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node root, long minValue, long maxValue) {
        if (root != null) {
            if (root.data >= minValue && root.data <= maxValue)
                return isBST(root.left, minValue, (long)root.data-1)
                        && isBST(root.right, (long)root.data+1, maxValue);

            else
                return false;
        }
        //if root/node -> NULL, so it is perfectly valid BST
        return true;
    }


    //BRUTE FORCE ----- NOTE - Inorder trav if ASCENDING, then TREE will def be BST
    public boolean isValidBST_Brute(Node root) {
        List<Integer> inorder = tree_inorderTraversal.inorderRecursive(root);

        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i) <= inorder.get(i-1))
                return false;
        }
        return true;
    }

}

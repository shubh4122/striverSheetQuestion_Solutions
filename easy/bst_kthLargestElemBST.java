package striversSheet.easy;

import static striversSheet.easy.tree_inorderTraversal.*;

public class bst_kthLargestElemBST {
    int counter = 0;
    // return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(Node root, int k)
    {
        if (root == null)
            return -1;

        //JUST recursive calls of right and left interchanged from kthSmall.
        int ans = kthLargest(root.right, k);
        //i.e. if we havent still found the kTH element, only then we run below code
        if (ans == -1) {
            counter++;
            if (k == counter)
                return root.data;

            ans = kthLargest(root.left, k);
        }

        return ans;
    }
}

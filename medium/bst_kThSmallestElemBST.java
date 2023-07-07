package striversSheet.medium;

public class bst_kThSmallestElemBST {

    int counter = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;

        int ans = kthSmallest(root.left, k);
        //i.e. if we havent still found the kTH element, only then we run below code
        if (ans == -1) {
            counter++;
            if (k == counter)
                return root.val;

            ans = kthSmallest(root.right, k);
        }

        return ans;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

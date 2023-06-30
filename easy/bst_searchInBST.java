package striversSheet.easy;

public class bst_searchInBST {

    //Recursive
    public TreeNode searchBST(TreeNode root, int val) {
        if (root!=null) {
            if (root.val == val)
                return root;
            else if (root.val < val)
                return searchBST(root.right, val);
            else
                return searchBST(root.left, val);
        }
        return null;
    }

    //Iterative
    public TreeNode searchBST_Iterative(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val)
                break;

            else if (root.val < val)
                root = root.right;

            else
                root = root.left;
        }
        return root;
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

package striversSheet.medium;

public class bst_lcaBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root!= null){
            if (p.val <= root.val && root.val <= q.val)
                return root;
            if (q.val <= root.val && root.val <= p.val)
                return root;

            //both p & q are smaller than root, hence LCA will only lie in LEFT subtree
            if (p.val < root.val && q.val < root.val)
                return lowestCommonAncestor(root.left, p, q);
            //both p,q greater than root. so LCA lies in right SUBTREE
            if (p.val > root.val && q.val > root.val)
                return lowestCommonAncestor(root.right, p, q);
        }
        return null;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

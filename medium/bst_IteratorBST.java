package striversSheet.medium;

import java.util.Stack;

//https://leetcode.com/problems/binary-search-tree-iterator/
public class bst_IteratorBST {

    /*
        Firstly, why use this DS?
        Just to BACKTRACK to previous nodes!!

        I used list, but i made it function just like stack.
        By accessing only the first char and inserting at
        first position only.
        On LC:
        List - performed well in terms of percentage both in time and space
        Stack - performed miserably low in terms of percentage in both
     */
    TreeNode root, ptr;
//    List<TreeNode> partialInorder;
    Stack<TreeNode> partialInorder;

    public bst_IteratorBST(TreeNode root) {
        this.root = root;
        ptr = new TreeNode(-1);
//        partialInorder = new ArrayList<>();
        partialInorder = new Stack<>();
    }

    public int next() {
        if (ptr.val == -1) {
            ptr = root;
            pushAllLeftToStack();
        }
//        TreeNode ans = partialInorder.remove(0);
        TreeNode ans = partialInorder.pop();

        if (ans.right!=null){
            ptr = ans.right;
            pushAllLeftToStack();
        }
//        ptr = partialInorder.isEmpty()? null:partialInorder.get(0);
        ptr = partialInorder.isEmpty()? null:partialInorder.peek();
        return ans.val;
    }

    private void pushAllLeftToStack() {
        while (ptr!=null) {
//                partialInorder.add(0, ptr);
            partialInorder.push(ptr);
            ptr = ptr.left;
        }
    }

    public boolean hasNext() {
        if (ptr==null)
            return false;

        return true;//for ptr==-1 or ptr = some val.
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

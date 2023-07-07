package striversSheet.medium;

import java.util.Stack;

//https://leetcode.com/problems/binary-search-tree-iterator/
public class bst_IteratorBST {

    /**
     * NOTE: INTERVIEW TIP!!
     * For even better code, we can use a var, isReverse and if its true, we do BEFORE, else NEXT
     *
     * Also, then we wont write 2 stacks and 2 diff functions.
     * Just use a simple if/else, for isRev and see if it must be ptr.left or ptr.right.
     *
     * Make 2 objects, one for next other for before in 2 sum and work accordingly..
     *
     * More Info:
     * https://youtu.be/ssL3sHwPeb4?t=737
     */



    /*
        Firstly, why use this DS?
        Just to BACKTRACK to previous nodes!!

        NOTE: this current Stack Modified and Cleaned code performs equivalent to list code.

        I used list, but i made it function just like stack.
        By accessing only the first char and inserting at
        first position only.
        On LC:
        List - performed well in terms of percentage both in time and space
        Stack - performed miserably low in terms of percentage in both
     */
    Stack<TreeNode> partialInorderNext, partialInorderBefore;

    public bst_IteratorBST(TreeNode root) {
        partialInorderNext = new Stack<>();
        partialInorderBefore = new Stack<>();

        pushAllLeftToStack(root);//making ptr point to the smallest elem
        pushAllRightToStack(root);
    }

    //gives out elem in ascending order
    public int next() {
        TreeNode ans = partialInorderNext.pop();
        if (ans.right!=null)
            pushAllLeftToStack(ans.right);

        return ans.val;
    }

    //gives out elem in descending order
    public int before(){
        TreeNode ans = partialInorderBefore.pop();
        if (ans.left!=null)
            pushAllRightToStack(ans.left);

        return ans.val;
    }

    public boolean hasNext() {
        return !partialInorderNext.isEmpty();//if stack empty, means no HasNext;
    }


    private void pushAllLeftToStack(TreeNode ptr) {
        while (ptr!=null) {
            partialInorderNext.push(ptr);
            ptr = ptr.left;
        }
    }

    private void pushAllRightToStack(TreeNode ptr) {
        while (ptr!=null) {
            partialInorderBefore.push(ptr);
            ptr = ptr.right;
        }
    }


    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

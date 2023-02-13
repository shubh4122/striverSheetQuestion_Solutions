package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;

//https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
public class tree_checkBalancedTree {

    public static void main(String[] args) {
        Node root = new Node(2);

        //WE can also use a global variable to retain changes
        boolean[] isBal = {true};//similar technique as used in diameter. use arr to retain val across recursive calls

        heightForBalanced(root, isBal);

        System.out.println(isBal[0]);
    }

    public static int heightForBalanced(Node root, boolean[] isBalanced) {
        if (!isBalanced[0])//see does it even stops any further recursion? is it of any use?
            return -1;
        if (root == null)
            return 0;

        int lHt = heightForBalanced(root.left, isBalanced);
        int rHt = heightForBalanced(root.right, isBalanced);

        if (Math.abs(lHt - rHt) > 1)
            isBalanced[0] = false;

        //return ht
        return 1 + Math.max(lHt, rHt);
    }
}

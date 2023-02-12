package striversSheet.easy;


import striversSheet.easy.tree_inorderTraversal.Node;

//https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/
public class tree_htOfBinaryTree {

    public static int height(Node root) {
        if (root == null)
            return 0;

        int lHt = height(root.left);//height of left subtree.
        int rHt = height(root.right);

        return 1 + Math.max(lHt, rHt);//1+ .. -> 1 is including the root itself
    }
}

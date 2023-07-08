package striversSheet.easy;

import striversSheet.medium.bst_Floor.TreeNode;

//https://www.codingninjas.com/studio/problems/ceil-from-bst_920464
public class bst_Ceil {

    public  static int findCeil(TreeNode<Integer> node, int x) {
        int ceil = Integer.MAX_VALUE;

        while (node != null) {
            if (x < node.data) {
                ceil = Math.min(ceil, node.data);
                node = node.left;
            }
            else if (x > node.data)
                node = node.right;
            else
                return node.data;
        }
        return ceil==Integer.MAX_VALUE ? -1:ceil;
    }
}

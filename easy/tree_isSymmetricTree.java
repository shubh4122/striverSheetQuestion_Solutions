package striversSheet.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/symmetric-tree/description/
public class tree_isSymmetricTree {
/*
    NOTE: inorder won't work as it just checks value of nodes. what if val were same
    but there orientation different. eg:
            1
          2    2
         2    2
     this tree will fail our inorder approach.
 */


    public boolean isSymmetricRECURSIVE(TreeNode p, TreeNode q) {
        //BC1 -- either both must be null, or none
        if (p == null || q == null)
            return p == null && q == null;

        //BC2
        if (p.val != q.val)
            return false;

        return isSymmetricRECURSIVE(p.left, q.right) && isSymmetricRECURSIVE(p.right, q.left);
    }


    public boolean isSymmetric(TreeNode root) {
        if (root.left == null && root.right == null)
            return true;

        Queue<TreeNode> qLeft = new LinkedList<>();
        Queue<TreeNode> qRight = new LinkedList<>();

        qLeft.add(root.left);
        qRight.add(root.right);

        while (!qLeft.isEmpty() && !qRight.isEmpty()) {
            TreeNode popLeft = qLeft.remove();
            TreeNode popRight = qRight.remove();

            try{
                if (popRight.val != popLeft.val)
                    return false;

                if (popLeft.left != null || popRight.right != null)
                    if (popLeft.left.val != popRight.right.val)
                        return false;
                if (popLeft.right != null || popRight.left != null)
                    if (popLeft.right.val != popRight.left.val)
                        return false;
            }
            catch (Exception e) {
                return false;
            }

            //For left subtree
            if (popLeft.left != null)
                qLeft.add(popLeft.left);
            if (popLeft.right != null)
                qLeft.add(popLeft.right);

            //For Right subtree. We input nodes in reverse fashion in queue here
            if (popRight.right != null)
                qRight.add(popRight.right);
            if (popRight.left != null)
                qRight.add(popRight.left);
        }
        return true;
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

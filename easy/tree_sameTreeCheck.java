package striversSheet.easy;

import java.util.Stack;

//https://leetcode.com/problems/same-tree/description/
public class tree_sameTreeCheck {


    //Recursive - EASY and short code than iterative
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        //BC1
        //runs only if anyone is null
        if (p == null || q == null)
            return p == null && q == null;//if both null, it returns true, else false;

        //BC2
        if (p.val != q.val)
            return false;

//        boolean isSameInLeft = isSameTreeRecursive(p.left, q.left);
//        if (! isSameInLeft)
//            return false;
//
//        boolean isSameInRight = isSameTreeRecursive(p.right, q.right);
//        if (! isSameInRight)
//            return false;
//
//        return true;

        //the above commented code can be written in short as
        return isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
    }

    //Iterative Preorder type traversal
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //iterative preorder traversal - Root L R
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        //if both null tree
        if (p == null && q == null)
            return true;

        //if any one is null tree
        if (p == null || q == null)
            return false;

        //if none is null tree
        if (p.val != q.val)
            return false;

        s1.push(p);
        s2.push(q);
        while (!s1.isEmpty()) {
            TreeNode pop1 = s1.pop();
            TreeNode pop2 = s2.pop();

            try {
                //Right
                if(pop1.right != null || pop2.right != null) {
                    if (pop1.right.val != pop2.right.val)
                        return false;
                    s1.push(pop1.right);
                    s2.push(pop2.right);
                }
                //Left
                if(pop1.left != null || pop2.left != null) {
                    if (pop1.left.val != pop2.left.val)
                        return false;
                    s1.push(pop1.left);
                    s2.push(pop2.left);
                }
            }
            catch (Exception e) {
                //coz I check null checks for only tree 1, but traverse for both trees
                //so if 2nd tree misses a node, which tree 1 has, it will throw error
                //while traversing and when caught in catch, it returns both trees aren't same
                return false;
            }
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

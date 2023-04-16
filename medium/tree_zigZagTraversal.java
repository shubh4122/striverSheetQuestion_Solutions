package striversSheet.medium;

import java.util.*;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class tree_zigZagTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left= new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        for (List<Integer> s: zigzagLevelOrder(root)){
            System.out.println(s);
        }
    }

    //M2 - better and less complicated
    /*
        https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/solutions/33815/my-accepted-java-solution/comments/32401
        above link inspires below solution

        while traversing we just need to print/store the nodes differently.
        when level is even, store them from left to right, as usual
        when level is odd, store them from right to left, that is opposite

        so simply same these nodes in this fashion and SIMPLY traverse line by line as is usually done
     */
    public static List<List<Integer>> zigzagLevelOrderSIMPLE(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> order = new ArrayList<>();

        if (root == null)
            return order;

        q.add(root);
        q.add(null);
        int level = 0;
        order.add(new ArrayList<>());

        while (!q.isEmpty()) {
            TreeNode pop = q.remove();

            if (pop == null) {
                level++;
                if (!q.isEmpty()) {
                    order.add(new ArrayList<>());
                    q.add(null);
                }
            }

            else{
                if (level % 2 == 0)
                    order.get(order.size() - 1).add(pop.val);
                else
                    order.get(order.size() - 1).add(0, pop.val);

                if (pop.left != null)
                    q.add(pop.left);
                if (pop.right != null)
                    q.add(pop.right);
            }
        }
        return order;
    }

    //M1
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> order = new ArrayList<>();

        if (root == null) //empty tree
            return order;

        Deque<TreeNode> dq = new LinkedList<>();
        dq.addFirst(root);
        dq.addFirst(null);
        order.add(new ArrayList<>());

        /*
            Technique:
            for even level.
                pop this level nodes from back.
                And insert its next level from front.
                first left child and then right.

            for odd level
                just opposite
         */
        int level = 0;
        while (! dq.isEmpty()) {
            TreeNode pop;
            if (level % 2 == 0)
                pop = dq.removeLast();
            else
                pop = dq.removeFirst();

            if (pop == null){
                level++;
                if (! dq.isEmpty()) {
                    order.add(new ArrayList<>());
                    if (level %2 == 0)
                        dq.addFirst(null);
                    else
                        dq.addLast(null);

                }
            }
            else{
                order.get(order.size() - 1).add(pop.val);
                if (level % 2 == 0) {
                    if (pop.left != null)
                        dq.addFirst(pop.left);
                    if (pop.right != null)
                        dq.addFirst(pop.right);
                }
                else{
                    if (pop.right != null)
                        dq.addLast(pop.right);
                    if (pop.left != null)
                        dq.addLast(pop.left);
                }
            }
        }
        return order;
    }


    static class TreeNode {
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

package striversSheet.medium;

import java.util.Stack;

//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
public class tree_flattenTreeToLL {
    TreeNode prev = null;

    //M1: Recursive - O(n), O(n)
    //https://youtu.be/sWf7k1x9XR4?t=277   -- Refer this dry run. VV Helpful
    public void flatten(TreeNode root) {
        if (root != null) {
            //First flatten right then left, like reverse Preorder
            flatten(root.right);
            flatten(root.left);

            //prev points at the straight LL created so far. i.e. prev
            //is head of that LL.
            root.right = prev;//make root's right point to LL created so far
            root.left = null;
            prev = root;//update LL's head by including the current node(root)
        }
    }


    //M2: Iterative - O(n), O(n)
    public void flattenM2(TreeNode root) {
        /*
            Similarities with M1:
            - stack used here compensates for recursion
            - the recursive calls there are equivalent to if conditions where we push nodes to stack
                first right and then left(just like M1)
            - curr.right = st.peek() is same as assigning root's right to prev
            - and At the beginning, curr = st.pop() is similar to prev = node
         */
        if (root == null)
            return;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (! st.isEmpty()) {
            //
            TreeNode curr = st.pop();

            if (curr.right != null)
                st.push(curr.right);
            if (curr.left != null)
                st.push(curr.left);

            if (!st.isEmpty()) {
                curr.right = st.peek();
            }
            curr.left = null;
        }
    }


    //M3: BEST - Morris Traversal - time O(n), space O(1)
    public void flattenMorrisMethod(TreeNode root) {
        /*
            Here, like in Morris trav, we used to connect to curr.
            But, as we need to connect left subtree's last node to the right of curr.
            we do, prev.right = curr.right.

            Also, we dont undo the links, as these links only form our linked list
         */

        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null) {
            if (curr.left != null) {
                prev = curr.left;

                while (prev.right != null)
                    prev = prev.right;

                //This, attaches the last most node of left subtree, to 1st node of right subtree, hence linked list formation
                prev.right = curr.right;//new link between left's last node and right's first node
                curr.right = curr.left;//change of link, making curr's right point to its left
                curr.left = null;//while left is made to point at null, as asked in ques for LL
            }
            //now curr's right points to its left child(subtree) so we move ahead to left part to continue process
            curr = curr.right;
        }
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

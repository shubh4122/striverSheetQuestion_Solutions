package striversSheet.OtherGoodQuestions;

import java.util.*;

//https://leetcode.com/problems/unique-binary-search-trees-ii/description/
public class recursion_dp_uniqueBST2 {

    //M2: Recursive[NOT BACKTRACKING!!] - take ith elem as root, elements lesser than it in left subtree and more than it as right subtree. Make combinations
    //I took help in this optimised solution! -> https://youtu.be/SXU--MPoUe8

    public List<TreeNode> generateTrees(int n) {
        return getBSTsFrom(1, n);
    }

    private List<TreeNode> getBSTsFrom(int start, int end) {
        //BC
        if (start > end) {
            List<TreeNode> t = new ArrayList<>();
            t.add(null);
            return t;//when extremes are the root, one of the subtree will be null. so, just fill NULL in the LIST
        }

        if (start == end) {//only 1 value there
            List<TreeNode> t = new ArrayList<>();
            t.add(new TreeNode(start));
            return t;
        }

        List<TreeNode> ans = new ArrayList<>();

        //each val from 1 to n is given a chance to be the root, this for loop takes care of it.
        //So, vals before root Will lie in left side of root and val more than root in right side.
        for(int rootVal = start; rootVal <= end; rootVal++){
            //These lists store all the BSTs that occur in LeftSubtree and RightSubtree of ROOT.
            List<TreeNode> leftBSTs = getBSTsFrom(start, rootVal-1);
            List<TreeNode> rightBSTs = getBSTsFrom(rootVal+1, end);

            //Now there will be different combinations of left subtrees with the right subtrees[BSTs]. Making combinations:

            for (TreeNode leftSubtree : leftBSTs) {
                for (TreeNode rightSubtree : rightBSTs) {
                    //Making a tree[BST] combination using 1 left and 1 right subBST
                    TreeNode root = new TreeNode(rootVal);
                    root.left = leftSubtree;
                    root.right = rightSubtree;

                    //Store this combination
                    ans.add(root);
                }
            }
        }
        return ans;
    }













    //M1 - My - Backtracking. VV MESSY Solution!

    boolean[] vis;
    List<TreeNode> ans;
    TreeNode bst;
    HashSet<ArrayList<Integer>> set = new HashSet<>();
    public List<TreeNode> generateTrees1(int n) {
        vis = new boolean[n+1];
        ans = new ArrayList<>();
        bst = new TreeNode(-1);//DUMMY NODE

        generateBST(n, 1);

        return ans;
    }

    private void generateBST(int n, int idx) {
        if (idx == n+1){
            //This is used to avoid duplicates
            ArrayList<Integer> order = convertToArr(bst.right);
            if (set.contains(order)) return;

            //if not duplicate, store the bst
            set.add(order);
            TreeNode deepcopiedBST = deepCopyBST(bst.right);
            ans.add(deepcopiedBST);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (vis[i]) continue;

            vis[i] = true;
            bstInsert(bst, i);
            generateBST(n, idx+1);
            bstDelete(bst, i);//tho the functin returns root, but our bst wont  change its root
            vis[i] = false;
        }
    }

    private ArrayList<Integer> convertToArr(TreeNode ptr) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(ptr);
        ArrayList<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            TreeNode pop = q.remove();
            if (pop.val == -1)
                ans.add(-1);
            else {
                ans.add(pop.val);
                q.add(pop.left==null?new TreeNode(-1): pop.left);
                q.add(pop.right==null?new TreeNode(-1): pop.right);
            }
        }
        return ans;
    }

    private TreeNode deepCopyBST(TreeNode bst) {

        if (bst == null)
        {
            return null;
        }
        TreeNode newNode = new TreeNode( bst.val, null, null);
        newNode.left= deepCopyBST(bst.left);
        newNode.right= deepCopyBST(bst.right);
        return newNode;
    }

    private TreeNode bstDelete(TreeNode bst, int val) {
        TreeNode ptr = bst;
        /*
            3 cases of BST DELETE:
            1. node to be deleted is the leaf node. -> Direct delete
            2. node to be deleted has 1 child -> Copy child's val to node and delete child
            3. node to be deleted has 2 child ->
                a. Find Inorder successor of the node
                b. copy the successor's val to node
                c. Delete that inorder successor node
         */

        if (bst == null)
            return bst;

        // Recursive calls for ancestors of
        // node to be deleted
        if (bst.val > val) {
            bst.left = bstDelete(bst.left, val);
            return bst;
        } else if (bst.val < val) {
            bst.right = bstDelete(bst.right, val);
            return bst;
        }

        // We reach here when bst is the node
        // to be deleted.

        // If one of the children is empty
        if (bst.left == null) {
            TreeNode temp = bst.right;
            return temp;
        } else if (bst.right == null) {
            TreeNode temp = bst.left;
            return temp;
        }

        // If both children exist
        else {

            TreeNode succParent = bst;

            // Find successor
            TreeNode succ = bst.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Delete successor.  Since successor
            // is always left child of its parent
            // we can safely make successor's right
            // right child as left of its parent.
            // If there is no succ, then assign
            // succ.right to succParent.right
            if (succParent != bst)
                succParent.left = succ.right;
            else
                succParent.right = succ.right;

            // Copy Successor Data to bst
            bst.val = succ.val;

            // Delete Successor and return bst
            return bst;
        }
    }

    private void bstInsert(TreeNode bst, int val) {
        TreeNode ptr = bst;

        while(ptr != null){
            if (ptr.val < val) {
                if (ptr.right != null)
                    ptr = ptr.right;
                else{
                    ptr.right = new TreeNode(val);
                    break;
                }
            }

            else if (ptr.val > val) {
                if (ptr.left != null)
                    ptr = ptr.left;
                else {
                    ptr.left = new TreeNode(val);
                    break;
                }
            }
        }
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

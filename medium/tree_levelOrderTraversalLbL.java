package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal;
import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://takeuforward.org/data-structure/level-order-traversal-of-a-binary-tree/
public class tree_levelOrderTraversalLbL {

    public static List<List<Integer>> lblLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        //empty tree
        if (root == null)
            return ans;

        ans.add(new ArrayList<>());//added 1 internal arraylist to start with

        int i = 0;
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node popped = q.remove();

            if (popped == null) {
                i++;//change level in arraylist with tree's level change
                if (!q.isEmpty()) {
                    q.add(null);
                    ans.add(new ArrayList<>());//also add a new level in outer arraylist, only if there's further level
                }
            }
            else {
                //if popped not null, add it to list
                ans.get(i).add(popped.data);

                if (popped.left != null)
                    q.add(popped.left);
                if (popped.right != null)
                    q.add(popped.right);
            }
        }
        return ans;
    }
}

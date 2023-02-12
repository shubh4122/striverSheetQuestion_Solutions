package striversSheet.medium;

import com.sun.source.tree.Tree;
import striversSheet.easy.tree_inorderTraversal.Node;
import striversSheet.medium.tree_bottomView.Pair;

import java.util.*;

//https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
public class tree_topView {

    //Same as bottom view, only diff is that here we take the 1st node of a given dist
    public static ArrayList<Integer> topview(Node root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();//key:dist, val:node
        Queue<Pair> q = new LinkedList<>();
        ArrayList<Integer> tv = new ArrayList<>();
        int currDist = 0, parentDist = -1;

        map.put(currDist, root.data);
        q.add(new Pair(root, currDist));
        while (!q.isEmpty()) {
            Pair popped = q.remove();
            Node poppedNode = (Node) popped.first;
            parentDist = (int)popped.second;

            if (poppedNode.left != null) {
                currDist = parentDist - 1;
                q.add(new Pair(poppedNode.left, currDist));
                if (!map.containsKey(currDist))
                    map.put(currDist, poppedNode.left.data);
            }
            if (poppedNode.right != null) {
                currDist = parentDist + 1;
                q.add(new Pair(poppedNode.right, currDist));
                if (!map.containsKey(currDist))
                    map.put(currDist, poppedNode.right.data);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            tv.add(entry.getValue());
        }

        return tv;
    }
}

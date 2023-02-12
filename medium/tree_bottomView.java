package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal.Node;

import java.util.*;

//https://takeuforward.org/data-structure/bottom-view-of-a-binary-tree/
public class tree_bottomView {

    public static ArrayList<Integer> bottomViewTreeMap(Node root) {
        //To keep track of ""latest"" nodes with a given horizontal dist from root.
        //treemap By default, is sorted in ascending order of keys!!
        TreeMap<Integer, Integer> map = new TreeMap<>();//dist, node.data
        ArrayList<Integer> bv = new ArrayList<>();
        Queue<Pair<Node, Integer>> q = new LinkedList<>();//to do level order traversal
        int currDist = 0, parentDist = -1;//horizontal dist

        map.put(0, root.data);
        q.add(new Pair<>(root, currDist));

        while (!q.isEmpty()) {
            Pair popped = q.remove();
            Node poppedNode = (Node) popped.first;
            parentDist = (int) popped.second;

            if (poppedNode.left != null) {
                currDist = parentDist - 1;
                q.add(new Pair<>(poppedNode.left, currDist));
                map.put(currDist, poppedNode.left.data); // left child have dist - 1;
            }
            if (poppedNode.right != null) {
                currDist = parentDist + 1;
                q.add(new Pair<>(poppedNode.right, currDist));
                map.put(currDist, poppedNode.right.data); // left child have dist - 1;
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            bv.add(entry.getValue());
        }
        return bv;
    }

    public static ArrayList<Integer> bottomView(Node root) {
        //To keep track of ""latest"" nodes with a given horizontal dist from root.
        HashMap<Integer, Integer> map = new HashMap<>();//dist, node.data
        ArrayList<Integer> bv = new ArrayList<>();
        Queue<Pair<Node, Integer>> q = new LinkedList<>();//to do level order traversal
        int maxDist = 0, minDist = 0, currDist = 0, parentDist = -1;//horizontal dist

        map.put(0, root.data);
        q.add(new Pair<>(root, currDist));

        while (!q.isEmpty()) {
            Pair popped = q.remove();
            Node poppedNode = (Node) popped.first;
            parentDist = (int) popped.second;

            if (poppedNode.left != null) {
                currDist = parentDist - 1;
                q.add(new Pair<>(poppedNode.left, currDist));
                map.put(currDist, poppedNode.left.data); // left child have dist - 1;

                //calc range min
                minDist = Math.min(minDist, currDist);
            }
            if (poppedNode.right != null) {
                currDist = parentDist + 1;
                q.add(new Pair<>(poppedNode.right, currDist));
                map.put(currDist, poppedNode.right.data); // left child have dist - 1;

                //calc range max
                maxDist = Math.max(maxDist, parentDist + 1);
            }
        }

        for (int i = minDist; i <= maxDist; i++) {
            if (map.containsKey(i))
                bv.add(map.get(i));
        }
        return bv;
    }

    public static class Pair<T, S> {
        public T first;
        public S second;

        public Pair(T first, S second) {
            this.first = first;
            this.second = second;
        }
    }
}

package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;
import java.util.ArrayList;

//https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/
public class tree_pathToGivenNode {



    public static ArrayList<Integer> pathToNode(Node root, int b) {
        ArrayList<Integer> path = new ArrayList<>();

        if (root == null)
            return path;

        if (root.data == b) {
            path.add(b);
            return path;
        }

        //left traversal
        path.addAll(pathToNode(root.left, b));
        //if we got 'b' in left subtree, add its parent, i.e. root to list and return
        if (!path.isEmpty()){//NO NEED OF RIGHT TRAVERSAL
            path.add(0, root.data);//always add in front, so we dont get reversed sequence
            return path;
        }

        //if not found in left subtree, right traversal
        path.addAll(pathToNode(root.right, b));
        if (!path.isEmpty()){
            path.add(0, root.data);//always add in front, so we dont get reversed sequence
        }

        return path;
    }
}

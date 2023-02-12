package striversSheet.easy;

import striversSheet.easy.tree_inorderTraversal.Node;

//https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree/
public class tree_diameterOfTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        //make a tree;
        int[] diameter = new int[1];
        //just call it to populate diam[]. dont use value it returns. it is height
        htForDiameter(root, diameter);
        System.out.println(diameter[0] - 1);//ques counted edges for diameter. hence -1 done. diam[] stores nodes count as diam by def
    }

    //similar to ht of tree
    public static int htForDiameter(Node root, int[] maxDiameter) {
        if (root == null)
            return 0;

        int lHt = htForDiameter(root.left, maxDiameter);//height of left subtree.
        int rHt = htForDiameter(root.right, maxDiameter);

        //calc diam. passed array so we can use its property similar to pass by ref.
        //where changes made to it are sustained through recursive calls.
        maxDiameter[0] = Math.max(maxDiameter[0], lHt + rHt + 1);
        return Math.max(lHt, rHt);//returning height
    }
}

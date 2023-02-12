package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal.Node;
import striversSheet.medium.tree_bottomView.Pair;

import java.util.LinkedList;
import java.util.Queue;

//https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
public class tree_widthOfTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(2);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.right.right = new Node(9);

        System.out.println(maxWidth(root));
    }

    public static int maxWidth(Node root) {
        //Strategy, Indexing nodes. in level order traversal
        Queue<Pair> q= new LinkedList<>();
        q.add(new Pair(root, 1));//node, index
        q.add(new Pair(null, -1));
        int maxWidth = 0, first = 1, last = 0;//f=1 because initially it'll be 1

        while (!q.isEmpty()) {
            Pair popped = q.remove();
            Node popNode = (Node) popped.first;
            int index = (int) popped.second;

            if (!q.isEmpty() && q.peek().first == null) {
                //means the popped node is the end node of a level
                last = index;
                maxWidth = Math.max(maxWidth, last - first + 1);
            }
            if (popNode == null) {
                if (!q.isEmpty()) {
                    q.add(new Pair(null, -1));
                    first = (int) q.peek().second;
                }
            }
            else {
                if (popNode.left != null)
                    q.add(new Pair(popNode.left, index*2));//the left index is double of its parent
                if (popNode.right != null)
                    q.add(new Pair(popNode.right, (2*index) + 1));//for right child, index is 2i+1
            }
        }
        return maxWidth;
    }

    //Coz this method changes whole tree, it distorts tree. HENCE INCORRECT OUTPUT
    //level order traversal Line by Line used
//    public static int maxWidthofTree(Node root) {
//        Queue<Node> q = new LinkedList<>();
//        int maxWidth = 0, width = 0;
//
//        int height = height(root);
//        int level = 0;
//
//        q.add(root);
//        q.add(null);//marks end of 1st line
//
//        while (!q.isEmpty()) {
//            Node popped = q.remove();
//            width++;
//
//            //for line by line
//            if (popped == null) {
//                level++;
//
//
//                maxWidth = Math.max(maxWidth, width - 1);//w - 1 coz, popping of null was also counted
//                width = 0;
//
//                if (level == height)
//                    break;
//
//                if (!q.isEmpty())//this if cant be merged with above if. It'll cause NULLPOINTEREXCEPTION in else part
//                    q.add(null);
//            }
//            else if (popped == IMAGINARY_NODE) {
////                System.out.println(popped.data);
//                //add 2 imaginary nodes in queue if popped node is img.
//                q.add(IMAGINARY_NODE);//left child
//                q.add(IMAGINARY_NODE);//right child
//            }
//            else {
////                System.out.println(popped.data);
//                if (popped.left == null) {//if null, see possibility of entering img node
//                    //if level = height, it is the last level, so dont add imaginary nodes to its nodes.
//                    if (level < height && (q.peek() != null || popped.right != null))//if popped node has a right child, means we can add img child
//                        q.add(IMAGINARY_NODE);
//                }
//                else//if p.l isn't null, put the child in queue.
//                    q.add(popped.left);
//
//                if (popped.right == null) {
//                    if (level < height && q.peek() != null)//if popped node is the last node in level, we dont need to add an imaginary child
//                        q.add(IMAGINARY_NODE);
//                }
//                else
//                    q.add(popped.right);
//            }
//        }
//
//        return maxWidth;
//    }
//
//    public static int height(Node root) {
//        if (root == null)
//            return 0;
//
//        int hLeftSubtree = height(root.left) + 1;
//        int hRightSubtree = height(root.right) + 1;
//
//        return Math.max(hLeftSubtree, hRightSubtree);
//    }
}

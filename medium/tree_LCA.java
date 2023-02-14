package striversSheet.medium;

import striversSheet.easy.tree_inorderTraversal.Node;

//https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
public class tree_LCA {//Lowest common ancestor

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        Node p = new Node(5);
        Node q = new Node(4);

        System.out.println(lca(root, p, q).data);
    }


    //Method 1: Using Path between nodes. WORST. 2 Traversals. both having:
    //TC - O(n) | SC - O(n)

    //DO when path ques done.


    //Below 2 methods based on assumptions that both p,q exist. and keys are unique - as said in ques
    //Method 2: Better and short.
    //TC - O(n) | SC - O(n)
    public static Node lcaBetter(Node root, Node p, Node q) {
        //We only return 4 types of vals. -> null, p, q, Final LCA
        if (root == null || root == p || root == q)
            return root;

        //l,r tells if p/q exists in l/r subtree. if no, l, r = null. if yes, l,r=p/q
        Node left = lcaBetter(root.left, p, q);
        Node right = lcaBetter(root.right, p, q);

        //if both are non-null, means both left and right subtree contains p and q each
        if (left != null && right != null)
            return root;//in this case return root, which will be the first common ancestor encountered, HENCE ITS LCA

        //even if both are null, this will run, hence will return null
        else if (left == null)
            return right;

        else// if (right == null)
            return left;
    }


    //TC - O(n) | SC - O(n)
    //Method 1 : -------------------------MY-------------------------
    public static Node lca(Node root, Node p, Node q) {
        Node[] lcaNode = {new Node(Integer.MAX_VALUE)};
        hasNode(root, p, q, lcaNode);
        return lcaNode[0];
    }

    public static boolean hasNode(Node root, Node p, Node q, Node[] lcaNode) {//used to find if theres a node in a subtree/tree
        if (root != null){
            boolean leftSubTreeHasNode = hasNode(root.left, p, q, lcaNode);
            boolean rightSubTreeHasNode = hasNode(root.right, p, q, lcaNode);

            //if (root.equals(p) || root.equals(q)) {//NOTE - .equals doesn't work for objects.
            if (root.data == p.data || root.data == q.data) {
                if (leftSubTreeHasNode || rightSubTreeHasNode)
                    lcaNode[0] = root;

                //if p, q is root itself
                return true;//return true coz we found either p or q.
            } else {
                if (leftSubTreeHasNode && rightSubTreeHasNode)
                    lcaNode[0] = root;

                //if p, q lies in any of left/right subtree of root.
                //this is very imp. this helps us return found status of its child subtrees.
                return leftSubTreeHasNode || rightSubTreeHasNode;
            }
        }

        //if root null, return false
        return false;
    }
}

package striversSheet.medium;

//https://www.codingninjas.com/studio/problems/floor-from-bst_920457
public class bst_Floor {
    public static int floorInBST(TreeNode<Integer> root, int X) {
        int floor = -1;

        while (root!=null) {
            if (X < root.data)
                root = root.left;

            else if (X > root.data) {
                floor = Math.max(floor, root.data);
                root = root.right;
            }
            else
                return root.data;
        }
        return floor;
    }



    public class TreeNode<T> {

        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        TreeNode(T data)
        {
            this.data = data;
            left = null;
            right = null;
        }
    };
}

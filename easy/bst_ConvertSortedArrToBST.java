package striversSheet.easy;


import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

//DO this similar question
//https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
public class bst_ConvertSortedArrToBST {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9}; //{55,60,65,70,75,80,100};
        bfsEfficientLineByLine(sortedArrayToBST(nums));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return makeBST(nums, 0, nums.length-1);
    }
    
    private static TreeNode makeBST(int[] nums, int start, int end) {
        int mid = (end+start)/2;
        //BC
        if (start == end)
            return (new TreeNode(nums[mid]));
        if (end < start)
            return null;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = makeBST(nums, start, mid-1);
        root.right = makeBST(nums, mid+1, end);

        return root;
    }

    //UTILS
    public static void bfsEfficientLineByLine(TreeNode root) {
//        TC : O(n)

//        Queue<TreeNode> q = new ArrayDeque<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

//          Here we need to use poll and offer, coz add and remove will through Exception for null
            if (temp == null) {
                if (!q.isEmpty())
                    q.offer(null);
                System.out.println();
            }

            else {
                System.out.print(temp.val + " ");

                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);

            }

        }
    }

    public static class TreeNode {
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

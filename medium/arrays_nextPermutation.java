package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;

//https://leetcode.com/problems/next-permutation/description/
public class arrays_nextPermutation {

    public static void main(String[] args) {
        int[] a = {2,3,1,3,3};//{1,3,2};//{2,1,4,5,3};//{2,1,4,3,5};//{3,2,1};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }


    public static void nextPermutation(int[] nums) {

        //Step 1: Travel from back, and find point where a[i-1] < a[i]
        //Upto this point array will be in descending order from back


        for (int i = nums.length - 1; i >= 1 ; i--) {
            if (nums[i-1] < nums[i]) {

                //Step2 : further Steps


                helper(nums, i);
                //terminate loop when this point found and necessary action taken.
                return;
            }
        }

        //Step 3: if this statement is reached, it means its the case of complete array being
        //in descending order. hence simply reverse whole array


        reverse(nums, 0, nums.length - 1);
    }

    private static void helper(int[] nums, int i) {
        //Step 2.1 : From this point, traverse in usual order(front to back)
        //and find just bigger num than nums[i-1]


        int justBigInd = i;
        for (int j = i; j < nums.length; j++) {
            //NOTE: <= IS VERY important for case like {2,3,1,3,3}. We want justBig num which occurs
            //At the last if it has multiple occurrences
            if (nums[j] <= nums[justBigInd] && nums[j] > nums[i-1])
                justBigInd = j;
        }

        //Step 2.2 : Swap i-1 and justbig
        swap(nums, i-1, justBigInd);



        //Step 2.3 : Reverse array from i to end
        reverse(nums, i, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        for (int k = 0; k <= (end-start)/2; k++) {
            swap(nums, start + k, end - k);
        }
    }

    private static void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }
}

package striversSheet.medium;

//https://leetcode.com/problems/search-in-rotated-sorted-array/description/
public class binarySearch_SearchInRotatedSortedArray {


    public int search(int[] nums, int target) {
        /*
            MODIFIED BINARY SEARCH
            We do some extra checks for this kind of rotated sorted array
            as, arr is sorted and then rotated from pivot K, we can imagine it as 2 sorted arrays
            Eg: [5,6,7,0,1,2,3,4] --> [5,6,7] 1st arr, [0,1,2,3,4] 2nd arr;

            1. Before checking for target, CHECK: which part of array does MID lies!!!
            2. Now for every mid, check if mid >/< target and, check target with start pointer also!!
               This ensures if target lies between start<target<mid or not
               If it does, TARGET is in first part of array, ELSE in 2nd part.
               Move pointers accordingly now
         */
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;

            if (nums[mid] == target)
                return mid;

            //1st check: Check which part does mid lie!
            if (nums[start] <= nums[mid]) {

                //2nd Check: if target lies in 1st part
                if (nums[start] <= target && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}

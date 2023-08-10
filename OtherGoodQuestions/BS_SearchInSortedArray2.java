package striversSheet.OtherGoodQuestions;

//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
public class BS_SearchInSortedArray2 {

    //TC: Worst : O(n) - when all same characters and target isnt in array
    //    Best  : O(logN) - when array has no duplicates

    //Code is almost same as Search in sorted array 1. Just 1 extra if condition
    public boolean search(int[] nums, int target) {
        int s = 0, e = nums.length - 1;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] == target)
                return true;

            if (nums[mid] == nums[s] && nums[mid] == nums[e]) {//Extra condition - truncate same values as they are redundant
                s++;
                e--;
            }
            //same code from here
            else if (nums[mid] <= nums[e]) {
                if (nums[mid] <= target && target <= nums[e])
                    s = mid + 1;
                else
                    e = mid - 1;
            }
            else {
                if (nums[s] <= target && target <= nums[mid])
                    e = mid - 1;
                else
                    s = mid + 1;
            }
        }
        return false;
    }
}

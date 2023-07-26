package striversSheet.OtherGoodQuestions;


//https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
public class SlidingWin_LongestSubarrayOf1DeletingOneElem {

    /*
     *   General Approach of such SLIDING WINDOW PROBLEM :-
     *   Resource: https://youtu.be/HI9A-pvwIQ0
     *
     *   0. (IMP)If we have Question like this, where we just need the LONGEST consecutive string of same chars,
     *      irrespective of the fact what that char is. (i.e here it can either be the Longest sequence of F or T)
     *      Then, run the Code for both F and T. And Return the MAX of them
     *
     *   1. INITIALIZE WINDOW : Have 2 pointers, `start` and `end` -> indicating start and end of the window
     *   2. INCREASE WINDOW SIZE : Till, our condition is TRUE, [i.e. like here we keep getting our desired character(T when MainChar = T)]
     *      we move end++ -> i.e. keep increasing window size
     *   3. DECREASE WINDOW SIZE : Now at a point when the curr char is not our Desired char. Do start++.
     *       BUT until when?  -> suppose we have `k` as the limit of undesired chars that can happen in our sequence
     *       So, At a Point when counUnDesired > k,
     *           - Start to DECREASE win size by doing start++
     *           - Whenever find unDESIRED elem, do count--;
     *           - Then when count==k, STOP DECREASING size, and store current window size.
     * */
    public int longestSubarray(int[] nums) {
        //Step 1 : Initialise window
        int start = 0, end = 0, countZeroes = 0, maxLen = 0;

        while (end < nums.length) {
            if (nums[end]==1)
                end++;
            else{
                countZeroes++;
                end++;
            }

            while (countZeroes > 1) {
                if (nums[start] == 0)
                    countZeroes--;

                start++;
            }
            //why -1? coz we need to delete 1 elem in window
            maxLen = Math.max(maxLen, end-start-1);
        }
        return maxLen;
    }
}

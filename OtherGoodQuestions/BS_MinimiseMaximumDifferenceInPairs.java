package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

//https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/
public class BS_MinimiseMaximumDifferenceInPairs {

    
    /*  NOTE: Threshold = values that MAX DIFFERENCE can have.
        Steps.
        1. Sort Array. Coz then the min diff pairs will be ADJACENT.Exploring any other pairs will be WASTE of TIME
        2. Do a BS on the Max Difference. -> It finds the MINIMUM threshold!
        
            > That is, take limits of Max difference as 0 and n[n-1] - n[0]
            > Now BS on this limit. mid -> THRESHOLD [it is the values of MAX DIFFERENCE of a pair]
            > We need to find that are there AT-LEAST 'p' pairs in nums, whose difference is less than equal to THRESHOLD
            > If for a given THRESHOLD, there are at-least 'p' pairs with diff <= T. Then, check for (start+T)/2. -> THIS GETS US THE MIN of MAX DIFF!
            > If not, check for a bigger Threshold.
        3. While checking in array, the 'p' pairs with diff<= T. Do this by GREEDY APPROACH.
            > if [i+1]-[i] <= T  -> count it -> do i+=2;
            > else -> do i++

        Why Greedy works:
            > See NOTION!
            > LC Editorial -> See Just Before Algorithm, they explain it.
            > Look Here -> https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/solutions/3395952/in-case-you-are-wondering-why-greedy-works-for-this-problem/
     */

    public int minimizeMax(int[] nums, int p) {
        if (p==0)
            return 0;

//-------------------------------------------Step1-------------------------------------------
        Arrays.sort(nums);
        
//-------------------------------------------Step2-------------------------------------------
        int s = 0, e = nums[nums.length - 1] - nums[0], ans = 0;
        while (s <= e) {
            int threshold = s + (e-s)/2;//this is MID
            
            if (found_pPairs(nums, p, threshold)) {
                e = threshold - 1;
                ans = threshold;//Store the value of Min (Maximum difference) obtained so far
            }

            else
                s = threshold + 1;
        }
        return ans;
    }


//-------------------------------------------Step3-------------------------------------------
    //this method returns if there are atLeast 'p' pairs with difference <= threshold
    private boolean found_pPairs(int[] nums, int p, int threshold) {

        int countPairs = 0, i = 0;
        while (i < nums.length-1) {
            if (nums[i+1] - nums[i] <= threshold){
                countPairs++;
                i+=2;//i+2 coz we will skip the i+1th elem as it is included in pair now.
            }
            else i++;
        }

        return countPairs >= p;
    }
}

package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/longest-increasing-subsequence/
public class dp_LIS {


    //Binary Search. - Why use this?
    //TC: O(NlogN),  SC: O(N) -> in case whole array is LIS
    /*
        On some OJs the constraint for size of nums is 10^5.
        And in DP, we need the table of size N*N.
        10^5 * 10^5 = 10^10 -> OUT of INT range, hence such a table cant be created
     */
    public int lengthOfLIS(int[] nums) {
        //intuition in notes. ->  https://youtu.be/on2hvxBXJH4
        ArrayList<Integer> temp = new ArrayList<>();
        for (int num : nums) {
            if (temp.isEmpty() || temp.get(temp.size() - 1) < num)
                temp.add(num);
            else {
                //binary search. - coz the array is sorted by default!
                int insertAt = binarySearch(temp, num);
                temp.set(insertAt, num);
            }
        }
        return temp.size();
    }

    private int binarySearch(ArrayList<Integer> temp, int num) {
        int s = 0, e = temp.size()-1;

        while (s <= e){
            int mid = s + (e-s)/2;

            if (temp.get(mid) == num)
                return mid;

            else if (temp.get(mid) < num)
                s = mid + 1;

            else
                e = mid - 1;
        }
        return e+1;
    }

//----------------------------------------------------------------------------------------------------------------------------------


    //TC: O(N*N),  SC: O(N*N)+O(N)
    int dp[][];
    public int lengthOfLISMem(int[] nums) {
        dp = new int[nums.length+1][nums.length+1];
        for (int[] row :dp)
            Arrays.fill(row, -1);

        return calcLISlen(nums, -1, 0);
    }

    /*
        NOTE: imp
        I originally used lisTop elem, and hence I had to use it as index of dp, as it
        was changing. but lisTop can be -ve too. so, instead:

        USE index of the elem that was last added to our LIS. -> lisTopIdx

        IMP NOTE 2:
        invalid lisInd = -1. i.e. no elem is there in lis as of now
        But, dp[][lisIdx] would not work. as it will throw error on negative index
        so, for dp we do """"""""""""""dp[][lisIdx + 1]""""""""""""""
     */

    //lisTopIdx - is the index last/greatest elem of LIS
    private int calcLISlen(int[] nums, int lisTopIdx, int i) {
        if (i== nums.length)
            return 0;

        //Memo
        if (dp[i][lisTopIdx+1]!=-1)
            return dp[i][lisTopIdx+1];

        //main code
        if (lisTopIdx == -1 || nums[lisTopIdx] < nums[i]){
            //len of LIS when we take/notTake ith elem in LIS
            int take = 1 + calcLISlen(nums, i, i+1);
            int notTake = calcLISlen(nums, lisTopIdx, i+1);

            return dp[i][lisTopIdx+1] = Math.max(take, notTake);
        }
        else
            return dp[i][lisTopIdx+1] = calcLISlen(nums, lisTopIdx, i+1);
    }
}

package striversSheet.OtherGoodQuestions;


import java.util.Arrays;

//LC contest q3
//https://leetcode.com/problems/visit-array-positions-to-maximize-score/description/

public class DP_VisitArrayPositionsMAXscore {

    long dp[][];
    public long maxScore(int[] nums, int x) {
        dp = new long[nums.length+1][3];//[idx][oddReq]
        //why 3 -> it can either be True[1] False[0]. so 2+1
        for (long[] arr : dp) {
            Arrays.fill(arr, -1L);
        }

        //n[0] has to be included for sure
//        return nums[0] + helperDP(nums, x, 1, nums[0]%2);
        return nums[0] + helperDP2(nums, x, 1, nums[0]%2);
    }

    //M2: 2 cases, eithr parity will change or will remain same.
    private long helperDP2(int[]nums, int x, int idx, int oddParity) {
        //BC
        if (idx == nums.length)
            return 0;

        //memoization
        if (dp[idx][oddParity]!=-1)
            return dp[idx][oddParity];


        //case 1: no parity change when we take [idx] elem. TAKE
        if (nums[idx]%2 == oddParity) {
            return dp[idx][oddParity] = nums[idx]+helperDP2(nums, x, idx+1, oddParity);
        }
        //case 2: parity changes. Hence 2 options: Take/Nt elem [idx]
        else{
            //Max(take, not take)
            int reversedParity = oddParity==0? 1:0;
            return dp[idx][oddParity] = Math.max(nums[idx] - x + helperDP2(nums, x, idx+1, reversedParity),
                                        helperDP2(nums, x, idx+1, oddParity));

        }
    }

    //M1: Take/NT an index
    /*
        Hypothesis: helper() would return the maxScore from index 'idx' to nums.length-1
        Video Explanation - https://youtu.be/Smkmk0ZKWFM?t=1579
     */
    private long helperDP(int[]nums, int x, int idx, int oddParity) {
        //BC
        if (idx == nums.length)
            return 0L;

        //Memoization
        if (dp[idx][oddParity] != -1L)
            return dp[idx][oddParity];

        long score=0;
        int newParity=oddParity;
        //Visit
        if (oddParity != nums[idx]%2) {
            score -= x;
            newParity = nums[idx]%2;
        }
        score += nums[idx] + helperDP(nums, x, idx+1, newParity);

        dp[idx][oddParity] = score;

        //notVISIT
        long score1 = helperDP(nums, x, idx+1, oddParity);


        return dp[idx][oddParity] = Math.max(dp[idx][oddParity],score1);
    }
}

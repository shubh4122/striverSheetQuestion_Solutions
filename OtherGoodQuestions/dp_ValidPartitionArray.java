package striversSheet.OtherGoodQuestions;

import java.util.Arrays;


//https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/description/
public class dp_ValidPartitionArray {


    //------------- 1D dp, O(n) TC. -------------
    Boolean[] dp;
    public boolean validPartition(int[] nums){
        dp = new Boolean[nums.length+1];

        return checkValidPartition(nums, 0);
    }


    //Function Hypothesis: This function returns if we can Partition nums starting from
    //index 'i' upto the end of arr. Using given 3 rules. Array before index 'i' is already PARTITIONED
    private boolean checkValidPartition(int[] nums, int i){
        //BC
        int n = nums.length;
        if (i >= n)
            return true;//Coz, we reached end, and hence put the last Partition here. If there were cases where we cant partition, our code will not let the control reach the end of arr

        if (dp[i] != null)
            return dp[i];

        //NOTE: dp[i] = partitionPossible?
        //We need to check all 3 RULES, so we use if. NO ELSE

        //Rule 1
        if (i+1 < n && nums[i] == nums[i+1]) {
            dp[i] = checkValidPartition(nums, i + 2);//coz, till i+1 we have partitioned. see after i+1, i.e. from i+2

            if (dp[i])
                return true;//coz, if partition is POSSIBLE following RULE1, why do we need to check other RULES?
        }

        //Rule 2
        if (i+2< n && (nums[i]==nums[i+1] && nums[i+1]==nums[i+2])){
            dp[i] = checkValidPartition(nums, i+3);

            if (dp[i])
                return true;
        }
        //RULE 3.
        if (i+2< n && (nums[i]+1==nums[i+1] && nums[i+1]+1==nums[i+2])) {
            dp[i] = checkValidPartition(nums, i + 3);

            if (dp[i])
                return true;
        }

        //If an array(or its subarray) matches NONE of the RULES, return false;
        return dp[i] = false;
    }


//------------------------------------This 2D DP gives MLE, due to high Constraints------------------------------------

    Boolean[][] dp2D;//Boolean used instead of boolean, coz, we also need a 3rd val -> NULL
    public boolean validPartition2Ddp(int[] nums) {
        dp2D = new Boolean[nums.length+1][nums.length+1];
        for (Boolean[] row : dp2D) {
            Arrays.fill(row, null);
        }
        return checkValidPartition(nums, 0, 0);
    }

    private boolean checkValidPartition(int[] nums, int s, int e) {
        //BC
        if (s==e && s == nums.length)
            return true;
        if (e == nums.length)
            return false;

        //memo
        if (dp2D[s][e] != null)
            return dp2D[s][e];

        //Case1: Partition with size 2
        if (e-s+1 == 2 && allSame(nums, s, e)){
            boolean doPartition = checkValidPartition(nums, e+1, e+1);
            boolean noPartition = checkValidPartition(nums, s, e+1);

            return dp2D[s][e] = doPartition || noPartition;
        }
        //Case2.a, 2.b: Partition with size 3 with 2 sub-cases
        else if (e-s+1 == 3){
            if (allSame(nums, s, e) || consecutiveIncreasingElems(nums, s, e))
                return dp2D[s][e] = checkValidPartition(nums, e+1, e+1);
            else //no further partition possible
                return dp2D[s][e] = false;
        }
        else //no partition. Keep checking
            return dp2D[s][e] = checkValidPartition(nums, s, e+1);
    }

    private boolean consecutiveIncreasingElems(int[] nums, int s, int e) {
        for (int i = s+1; i <= e ; i++) {
            if (nums[i-1] + 1 != nums[i])
                return false;
        }
        return true;
    }

    //checks if all elem in the subarray are same
    private boolean allSame(int[] nums, int s, int e) {
        for (int i = s+1; i <= e; i++) {
            if (nums[i-1] != nums[i])
                return false;
        }
        return true;
    }
}

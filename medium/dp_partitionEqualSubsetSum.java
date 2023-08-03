package striversSheet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/partition-equal-subset-sum/submissions/
public class dp_partitionEqualSubsetSum {

    /*
        Let partition/subset 1 sum = s1, partition2 sum = s2
        s1 = s2 as per ques.
        and s1 +s2 = sum of array.
        i.e. s1 = s2 = sum/2
     */

    //Tabulation space optimised
    public boolean canPartition(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum%2!=0)//odd sum, cant partition
            return false;

        //here we can take boolean dp
        int n = nums.length;
        sum = sum/2;
        boolean[] prev = new boolean[sum+1];
        boolean[] curr = new boolean[sum+1];

        //Initialize base cases
        //BC1 - when sum == 0 -> True
        //BC2 - when n == 0 and sum != 0 -> false
        Arrays.fill(prev, false);
        prev[0] = true;
        curr[0] = true;

        //main code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (j - nums[i-1] >= 0)
                    curr[j] = prev[j-nums[i-1]] || prev[j];
                else
                    curr[j] = prev[j];
            }
            //VERY IMP. """prev = curr""" will be WRONG.
            prev = curr.clone();
        }
        return curr[sum];
    }



    //Tabulation
    public boolean canPartitionTab(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum%2!=0)//odd sum, cant partition
            return false;

        //here we can take boolean dp
        int n = nums.length;
        sum = sum/2;
        boolean[][] dp = new boolean[n+1][sum+1];

        //Initialize base cases
        //BC1 - when sum == 0 -> True
        //BC2 - when n == 0 and sum != 0 -> false
        Arrays.fill(dp[0], false);
        for (boolean[] row: dp) {
            row[0] = true;
        }

        //main code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (j - nums[i-1] >= 0)
                    dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][sum];
    }


    //Memoization
    int[][] dp;
    public boolean canPartitionMem(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum%2!=0)//odd sum, cant partition
            return false;

        //NOTE: cant take boolean dp for Memoization, coz it cant be
        //initialized with some val like -1 to mark the NOT visited dp states
        dp = new int[nums.length+1][(sum/2)+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return isEqualPartition(nums, nums.length, sum/2);
    }

    public boolean isEqualPartition(int[] nums, int n, int sum){
        if (sum == 0)
            return true;

        if (n == 0)//&& sum != 0 - obvious
            return false;

        //Memoization
        if (dp[n][sum]!=-1)
            return dp[n][sum]==1;

        if (sum - nums[n-1] >= 0){
            boolean take = isEqualPartition(nums, n-1, sum - nums[n-1]);
            boolean notTake = isEqualPartition(nums, n-1, sum);

            dp[n][sum] = (take || notTake) ? 1:0;
        }
        else
            dp[n][sum] =  isEqualPartition(nums, n-1, sum)?1:0;//not take

        return dp[n][sum]==1;
    }
}

package striversSheet.medium;

import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
public class dp_MaxSumIncreasingSubsequence {

    //KindOf LIS. but here instead of keeping record of length, record the SUM
    int[][] dp;

    public int maxSumIS(int arr[], int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row: dp) {
            Arrays.fill(row, -1);
        }
        return getMaxSumIS(arr, n, -1, 0);
    }

    //returns max Sum
    //IMP NOTE: Use index of Last elem of IS! as taking elem is not feasible for DP
    public int getMaxSumIS(int[] arr, int n, int lastElemISIdx, int i) {
        if (i == n)
            return 0;//if no elem. then 0 sum

        //memo
        if (dp[lastElemISIdx+1][i] != -1)
            return dp[lastElemISIdx+1][i];

        if (lastElemISIdx == -1 || arr[lastElemISIdx] < arr[i]) {
            //Take/NT
            int sumTake = arr[i] + getMaxSumIS(arr, n, i, i + 1);
            int sumNT = getMaxSumIS(arr, n, lastElemISIdx, i + 1);

            return dp[lastElemISIdx+1][i] = Math.max(sumTake, sumNT);
        } else {
            return dp[lastElemISIdx+1][i] = getMaxSumIS(arr, n, lastElemISIdx, i + 1);
        }
    }
}

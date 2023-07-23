package striversSheet.OtherGoodQuestions;


import java.util.Arrays;

//https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/description/
public class DP_WaysToRepresentIntegerAsSumOfPowers {

    int mod = (int) (1e9+7);
    public int numberOfWays(int n, int x) {
        int t = n;
        int[][] dp = new int[n+1][t+1];

        for (int[] temp : dp) {
            Arrays.fill(temp, -1);
        }

        return countWays(n, x, t, dp);
    }


    private int countWays(int n, int x, int t, int[][] dp) {
        if (n<=1)
            return 1;
        if (t==1)
            return 0;

        if (dp[n][t]!=-1)
            return dp[n][t];

        //We take an integer t only if
        if (n >= Math.pow(t, x)){
            //here we have option to take or not take. We sum both T/NT ways for total ways
            int take = countWays((int) (n-Math.pow(t,x)), x, t-1, dp)%mod;
            int nTake= countWays(n, x, t-1, dp)%mod;

            return dp[n][t] = (take+nTake)%mod;
        }
        else{
            //if the current int doesnt fit the limit, leave it
            return dp[n][t] = countWays(n, x, t-1, dp)%mod;
        }
    }
}

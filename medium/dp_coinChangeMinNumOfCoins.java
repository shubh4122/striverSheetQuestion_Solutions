package striversSheet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/coin-change/
public class dp_coinChangeMinNumOfCoins {

    int[][] dp;
    public int coinChange(int[] coins, int amount) {
        dp = new int[coins.length+1][amount+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = getMinCoins(coins, coins.length, amount);
        if (ans >= (int) 1e9)
            return -1;

        return ans;
    }

    public int getMinCoins(int[] coins, int i, int amount) {
        //BC
        if (amount == 0)
            return 0;//a = 0, so we need no coin

        //we dont have any coin and amount != 0, so OBVIOUSLY NO WAY to reach the amount using coins
        if (i == 0)
            return (int) 1e9;//hence return HUGE NUMBER, so this is discarded by Math.min


        //m3mo
        if (dp[i][amount] != -1)
            return dp[i][amount];

        //main code - choice diag
        if (amount-coins[i-1] >= 0){
            int take = 1 + getMinCoins(coins, i, amount-coins[i-1]);//coins required if we take (i-1)th coin
            int notTake = getMinCoins(coins, i-1, amount);

            return dp[i][amount] = Math.min(take, notTake);
        }
        else
            return dp[i][amount] = getMinCoins(coins, i-1, amount);
    }

}

package striversSheet.medium;


import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
public class dp_01KS {

    //NOTE: Space optimisation can be done ONLY IN TABULATION!!

    //NOTE: For space optimised Tabulation, KEEP the loops as -> i=n, j=w. Otherwise it gives ERRORS
    //space optimised Tabulation
    static int knapSackTabOptim(int w, int wt[], int val[], int n) {
        int[] prev = new int[w+1];
        int[] curr = new int[w+1];
        //BC -> fill w=0 row, and n=0 col with 0. Not req here as its default val

        // i -> n, j -> w
        //prev = dp[j-1]
        //curr = dp[j]
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < w+1; j++) {//with <n val[n-1] wont be used!
                if (j-wt[i-1] >= 0)
                    curr[j] = Math.max(val[i - 1] + prev[j - wt[i - 1]], prev[j]);
                else
                    curr[j] = prev[j];
            }
            prev = curr.clone();
        }
        return prev[w];
    }


    //Tabulation
    static int knapSackTab(int w, int wt[], int val[], int n) {
        int[][] dp = new int[w+1][n+1];//make it always of size var+1. to avoid confusions

        //BC -> fill w=0 row, and n=0 col with 0. Not req here as its default val

        // i -> w, j -> n
        for (int i = 1; i < w+1; i++) {
            for (int j = 1; j < n+1; j++) {//with <n val[n-1] wont be used!
                if (i-wt[j-1] >= 0)
                    dp[i][j] = Math.max(val[j - 1] + dp[i - wt[j - 1]][j - 1], dp[i][j - 1]);
                else
                    dp[i][j] = dp[i][j-1];
            }
        }
        return dp[w][n];
    }

 /*
        -------------------------------------------------NOTE-------------------------------------------------
        Why did i reduce params in RECURSIVE function. and made them global?

        Reason: Because, sometimes some question may require too many calls, and there might be chances of filling up the entire
        Recursive call stack.
        So if i Remove CONSTANT params from recursive function, and make them Global for access. I can save on the space in recursive
        stack that was taken unnecessary by constants

        IDEA: https://qr.ae/pySVXu
  */

    //Memoization with actual TOP DOWN trend in params
    static int[][] dp;
    static int[] weight, value;
    static int knapSack(int w, int wt[], int val[], int n) {
        //dp[n][w+1]
        dp = new int[n+1][w+1];
        weight = wt;
        value = val;

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return ks(n, w);
    }

    private static int ks(int n, int w) {
        if (n ==0 || w==0)
            return 0;

        //Memoization
        if (dp[n][w] != -1)
            return dp[n][w];

        if (w- weight[n-1] >= 0){
            int take = value[n-1] + ks(n-1, w- weight[n-1]);
            int notTake = ks(n-1, w);

            return dp[n][w] = Math.max(take, notTake);
        }
        else
            return dp[n][w] = ks(n-1, w);
    }



    /*
        -------------------------------------------------NOTE-------------------------------------------------
        Below code is Memoization dp. that is TOP DOWN dp.
        BUT i coded it somewhat bottom up. coz i start with finding ans for index = 0, and then at last for index = n-1

        Though this doesnt cause problem with recursive/memoization code. But If we need to convert it to TABULATION [Bottom UP]
        IT causes too much confusion.

        So, KEEP THIS IN MIND: Always write the Top down memo code as TRUE TOP DOWN.
        i.e. start with : fun(n) -> fun(n-1) ->...............->fun(0)[BC]
     */


    //Memoization
    static int knapSack1(int w, int wt[], int val[], int n) {
        //dp[w+1][n]
        int[][] dp = new int[w+1][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return ks1(w, wt, val, n, 0, dp);
    }

    private static int ks1(int w, int[] wt, int[] val, int n, int idx, int[][] dp) {
        if (w==0 || idx == n)
            return 0;//0 val can be obtained from ks

        //Memoization
        if (dp[w][idx]!=-1)
            return dp[w][idx];

        if (w-wt[idx] >= 0){
            int take = val[idx] + ks1(w-wt[idx], wt, val, n, idx+1, dp);
            int notTake = ks1(w, wt, val, n, idx+1, dp);
            return dp[w][idx] = Math.max(take, notTake);
        }
        else{//not take coz the wt of curr elem doesnt fit KS capacity
            return dp[w][idx] = ks1(w, wt, val, n, idx+1, dp);
        }
    }
}

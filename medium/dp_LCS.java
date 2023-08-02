package striversSheet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/longest-common-subsequence/description/
public class dp_LCS {


    //Space-Optimised dp.
    //dp[i] -> curr.  dp[i-1] -> prev
    public int longestCommonSubsequenceTabOptimised(String text1, String text2){
        int[] prev = new int[text2.length()+1];
        int[] curr = new int[text2.length()+1];

        lcsTabOptimised(text1, text2, text1.length(), text2.length(), prev, curr);
        return curr[text2.length()];
    }

    private void lcsTabOptimised(String text1, String text2, int l1, int l2, int[] prev, int[] curr) {
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1))
                    curr[j] = 1 + prev[j-1];
                else
                    curr[j] = Math.max(prev[j], curr[j-1]);
            }
            //store now curr, to now prev. and empty curr
            prev = curr.clone();//NOTE: .clone() does deep copy for primitive types only!! o/w shallow copy
        }
    }



    //Tabulation
    public int longestCommonSubsequenceTabulation(String text1, String text2){
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        lcsTab(text1, text2, text1.length(), text2.length(), dp);
        return dp[text1.length()][text2.length()];
    }

    private void lcsTab(String text1, String text2, int l1, int l2, int[][] dp) {
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }

    //Memoization
    public int longestCommonSubsequenceMem(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int[] temp : dp)
            Arrays.fill(temp, -1);

        return lcsMem(text1, text2, 0, 0, dp);
    }

    public int lcsMem(String a, String b, int i, int j, int[][] dp) {
        //BC
        if (i==a.length() || j == b.length())
            return 0;

        //Memoization
        if (dp[i][j] != -1)
            return dp[i][j];

        int lcsLen = 0;
        if (a.charAt(i) == b.charAt(j)){
            //TAKE this char in LCS
            lcsLen = 1+ lcsMem(a, b, i+1, j+1, dp);
        }
        else {
            int case1 = lcsMem(a, b, i+1, j, dp);
            int case2 = lcsMem(a, b, i, j+1, dp);
            lcsLen = Math.max(case1, case2);
        }
        return dp[i][j] = lcsLen;
    }
}

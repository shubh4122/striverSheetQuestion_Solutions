package striversSheet.OtherGoodQuestions;

import java.util.*;

//https://leetcode.com/problems/word-break/description/
public class dp_WordBreak {

    int[][] dp;
    public boolean wordBreak(String s, List<String> wordDict) {

        HashSet<String> dict = new HashSet<>(wordDict);
        dp = new int[s.length()+1][s.length()+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return isSubstringInDict(s, dict, 1);
    }

    /*
        ----------------------------NOTE----------------------------
        For boolean dp, use Boolean. so, we can put null. and then do the memoize check
     */
    private boolean isSubstringInDict(String s, HashSet<String> dict, int i) {
        if (s.isEmpty())
            return true;

        if (i==s.length()+1)
            return false;

        //Memoization
        if (dp[s.length()][i]!=-1)
            return dp[s.length()][i]==1;

        if (dict.contains(s.substring(0, i))){
            //T || NT
            boolean take = isSubstringInDict(s.substring(i), dict, 1);
            boolean notTake = isSubstringInDict(s, dict, i+1);

            dp[s.length()][i] = take || notTake ? 1 :0;
        }
        else {
            dp[s.length()][i] = isSubstringInDict(s, dict, i+1) ? 1 :0;
        }

        return dp[s.length()][i]==1;
    }

}

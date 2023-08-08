package striversSheet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/edit-distance/description/
//Watch strivers video if doubt
public class dp_EditDistance {

    int[][] dp;
    public int minDistance(String word1, String word2) {
        dp = new int[word1.length()+1][word2.length()+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return getMinOperations(word1, word2, word1.length()-1, word2.length()-1);
    }

    private int getMinOperations(String word1, String word2, int i, int j) {
        //BC - 2 : either i or j reaches -1 -> i.e. invalid index
        if (i == -1)//as word1 has been finished, but if still word2 hasnt formed completely, we will need j+1 ops to form it using w1. [SEE BY TRYING OUT]
            return j+1; //hence w1 will need j+1 insertions to convert to w2.
        if (j==-1)
            return i+1;//same as above reason. w1 will need i+1 deletions to convert to w2

        //memo
        if (dp[i][j] != -1)
            return dp[i][j];


        //if the characters match -> move both pointers
        if (word1.charAt(i) == word2.charAt(j))
            return getMinOperations(word1, word2, i-1, j-1);


        //else, Try all 3 ops, and find the min operations taken by them.
        /*
            1. in insert, we insert the char w2[j] in w1. so now there's a match.
               So, move the j ptr, only and match it with the ith ptr if it matches.

            2. in delete, we delete the char w1[i] from w1, and hence i must naturally move -> i-1.
               while j remains at its position as its match hasnt been found yet

            3. in replace, we replace char w1[i] from w2[j]. now as ith char has been changed to match w2[j],
               there's a match. Hence move both i and j.

               we do-> 1+fun(). here 1 means the count of current operation. and then for the rest of the string, count = fun()
         */
        int insert = 1 + getMinOperations(word1, word2, i, j-1);
        int delete = 1 + getMinOperations(word1, word2, i-1, j);
        int replace = 1 + getMinOperations(word1, word2, i-1, j-1);

        int minOps = Math.min(insert, Math.min(delete, replace));

        return dp[i][j] = minOps;
    }


}

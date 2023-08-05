package striversSheet.OtherGoodQuestions;

import java.util.Arrays;

//https://leetcode.com/problems/unique-binary-search-trees/
public class dp_UniqueBST1 {
/*
    NOTE: this question is easily done by recursive approach along with observation
    BUT, to make it more efficient, use DP, to remember the results
 */

    //M1 -> https://youtu.be/qOItdXuTZGo?t=406
    int[] dp;
    public int numTrees(int n) {
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        return calcTrees(n);
    }

    public int calcTrees(int n) {
        if (n==0)
            return 1;//if a subtree has no node, so just return 1.
            //returning 0 would make all further calcs as 0!!
        if (n==1 || n == 2)
            return n;

        //memo
        if (dp[n] != -1)
            return dp[n];

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            //i -> root
            //elem before i, taken in left subtree
            //elem after i taken in right subtree
            int leftSubtreeCount = calcTrees(i-1);
            int rightSubtreeCount = calcTrees(n-i);

            ans+= leftSubtreeCount*rightSubtreeCount;
        }
        return dp[n] = ans;
    }

    //M2: - Catalan Number/series. May see if you wish
    //https://leetcode.com/problems/unique-binary-search-trees/solutions/1565543/c-python-5-easy-solutions-w-explanation-optimization-from-brute-force-to-dp-to-catalan-o-n/
}

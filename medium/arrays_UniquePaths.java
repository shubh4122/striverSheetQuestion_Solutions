package striversSheet.medium;

//https://leetcode.com/problems/unique-paths/description/
import java.util.Arrays;

//ASKED IN GOOGLE MULTIPLE TIMES, HENCE THE EXTRA OPTIMISATION, COMBINATORICS



public class arrays_UniquePaths {

    //BEST: TC - O(n-1) or  O(m-1) depending on the formula we are using.
    //SC - O(1).
    public int uniquePathsCOMBINATORICS(int m, int n) {
        /*
            we have m-1 moves to move down, and n-1 moves to move right
            in order to reach the last cell

            so Total moves: m+n-2
            So we basically need to Find NUMBER of WAYS we can choose
            m-1 moves out of total moves. OR n-1 out of total

            that is,  (m+n-2)C(m-1) or (m+n-2)C(n-1) -> this is our answer
            Why any of them can be used.
            Its reason is kinda PnC thing.
            https://youtu.be/t_f0nwwdg5o?t=918  -- Watch it here

            10C3 = (10*9*8)/3! = 10!/(10-3)!.3!
         */
        double numerator = 1, denominator = 1;
        for (int i = 0; i < m-1; i++) {
            numerator *= (m+n-2-i);
            denominator *= (i+1);//calculates factorial
        }
        return (int) Math.round(numerator/denominator);
    }




    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return uniquePathsMem(m, n, dp);
    }

    //as this recursion gives TLE, use DP mem
    //Recursion: EXPONENTIAL TC, SC
    //MEM : O(m*n) both
    public int uniquePathsMem(int m, int n, int[][] dp) {
        if (m == 1 || n == 1)
            return 1;

        //MEMOIZATION
        if (dp[m][n] != -1)
            return dp[m][n];

        //it gives number of ways/paths in a matrix of size: m * n-1
        int moveRight = uniquePathsMem(m, n - 1, dp);
        //it gives number of ways/paths in a matrix of size: m-1 * n
        int moveDown = uniquePathsMem(m - 1, n, dp);

        return dp[m][n] = moveRight + moveDown;
    }
}

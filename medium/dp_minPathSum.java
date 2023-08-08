package striversSheet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-path-sum/description/
public class dp_minPathSum {

    int[][] dp;
    public int minPathSum(int[][] grid) {
        dp = new int[grid.length][grid[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return getPathSum(grid, 0, 0);
    }

    public int getPathSum(int[][] grid, int i, int j) {
        //BC
        if (i == grid.length-1 && j == grid[0].length-1)
            return grid[i][j];//when on the last block return its cell val

        //when path reaches to a point where we can no more take right move
        //then return INFINITY so the right movement is rejected by Math.min()
        //denoting no more right moves and only down moves possible
        //Same for down, when j == g[0].length
        if (i == grid.length || j == grid[0].length)
            return (int) 1e9;

        //Memo
        if (dp[i][j] != -1)
            return dp[i][j];

        int rightMovesSum = grid[i][j] +getPathSum(grid, i, j+1);
        int downMovesSum = grid[i][j] + getPathSum(grid, i+1, j);

        return dp[i][j] = Math.min(rightMovesSum, downMovesSum);
    }
}

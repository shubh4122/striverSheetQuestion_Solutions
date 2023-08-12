package striversSheet.OtherGoodQuestions;

import java.util.Arrays;

//https://leetcode.com/problems/unique-paths-ii/description/
public class dp_UniquePaths2 {

    int[][] dp;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return getUniquePaths(obstacleGrid, 0, 0);
    }

    private int getUniquePaths(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length)
            return 0;//when no new ways/paths

        if (grid[i][j] == 1)
            return 0;//when obstacle appears, no path here.

        if (i == grid.length - 1 && j == grid[0].length - 1)
            return 1;//if we reached our destination, this is 1 VALID path


        //memo
        if (dp[i][j] != -1)
            return dp[i][j];
        int moveRight = getUniquePaths(grid, i, j + 1);
        int moveDown = getUniquePaths(grid, i + 1, j);

        return dp[i][j] = moveRight + moveDown;//total paths
    }
}

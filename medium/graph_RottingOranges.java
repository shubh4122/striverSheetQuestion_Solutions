package striversSheet.medium;

import striversSheet.medium.tree_bottomView.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class graph_RottingOranges {

    public static void main(String[] args) {
        int[][] grid = {{0,1}};//{{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int count = 0;
        int[] count1 = new int[1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2)
                    q.add(new Pair<>(i, j));
                else if (grid[i][j] == 1)
                    count1[0]++;
            }
        }

        Pair<Integer, Integer> SEPARATOR = new Pair<>(-1, -1);
        q.add(SEPARATOR);

        while (!q.isEmpty()) {
            Pair pop = q.remove();
            int i = (int) pop.first;
            int j = (int) pop.second;

            if ((int) pop.first != -1) {
                //no separator encountered
                perform4DirectionalOps(i-1, j, q, grid, grid.length, grid[i].length, count1);
                perform4DirectionalOps(i+1, j, q, grid, grid.length, grid[i].length, count1);
                perform4DirectionalOps(i, j-1, q, grid, grid.length, grid[i].length, count1);
                perform4DirectionalOps(i, j+1, q, grid, grid.length, grid[i].length, count1);
            }
            else {
                //SEPARATOR
                if (!q.isEmpty()) {
                    count++;
                    q.add(SEPARATOR);
                }
            }
        }

        if (count1[0] != 0)
            count = -1;
        return count;
    }

    private static void perform4DirectionalOps(int i, int j, Queue<Pair<Integer, Integer>> q, int[][] grid, int m, int n, int[] count1) {
        if(i >= 0 && j >= 0 && i <= m - 1 && j <= n - 1 && grid[i][j] == 1) {
            grid[i][j] = 2;
            q.add(new Pair<>(i, j));
            count1[0]--;
        }
    }
}

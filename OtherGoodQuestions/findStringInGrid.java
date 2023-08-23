package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;

//https://practice.geeksforgeeks.org/problems/find-the-string-in-grid0111/1
public class findStringInGrid {

    public int[][] searchWord(char[][] grid, String word) {
        ArrayList<int[]> list = new ArrayList<>();

        //for each cell, if we can find the given word starting from this very cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (check(grid, i, j, word))//if yes, store this starting coordinate
                    list.add(new int[]{i,j});
            }
        }

        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }


    //This function checks in all the 8 directions if we can find the given word
    private boolean check(char[][] grid, int i, int j, String word) {
        //top, bottom, left, right, dTopLeft, dTopRight, dBottomLeft, dBottomRight
        int[][] directions = {{-1,0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int d = 0; d < 8; d++) {
            if (move(grid, i, j, word, directions[d][0], directions[d][1]))
                return true;
        }

        return false;//when none of the 8 directions could yield a true!
    }

    //This functin moves in 1 given direction from given index (i,j) and finds if the word can be found
    private boolean move(char[][] grid, int i, int j, String word, int changeI, int changeJ) {

        int newI = i, newJ = j;
        for (int idx = 0; idx < word.length(); idx++) {
            if(validIdx(grid, newI, newJ)) {
                if (word.charAt(idx) == grid[newI][newJ]) {
                    newI = newI + changeI;//update indices
                    newJ = newJ + changeJ;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    //simply checks if the given index is within the bounds
    private boolean validIdx(char[][] grid, int nextI, int nextJ) {
        return nextI >= 0 && nextI < grid.length && nextJ >=0 && nextJ < grid[0].length;
    }
}

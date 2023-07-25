package striversSheet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/n-queens/
public class Recursion_Btcking_Nqueens {

    //NOTE - METHOD 2 is best. VERY efficient! both space and time

    List<List<String>> ans;
    //M2 specifics
    //MAPS
    boolean[] upperDiagOccupied, leftRowOccupied, lowerDiagOccupied;

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();

        //M2 - imagine a n*n matrix, each cell = i+j. For diagonals use this i+j value;
        upperDiagOccupied = new boolean[2*n-1]; // -> this will give us indices 0 to 2n-2[max val of i+j]
        leftRowOccupied = new boolean[n];
        lowerDiagOccupied = new boolean[2*n-1];

        String[][] cB = new String[n][n];
        for (String[] t : cB) {
            Arrays.fill(t, ".");
        }

//        tryAllWays(cB, 0);
        tryAllWays2(cB, 0, cB.length);
        return ans;
    }

    //M2: OPTIMISED PositionSafe CHECK - HASHING!
    private void tryAllWays2(String[][] chessBoard, int j, int n) {
        //BC
        if (j == chessBoard.length) {
            ans.add(new ArrayList<>(convertMatrixToList(chessBoard)));
            return;
        }


        for (int i = 0; i < chessBoard.length; i++) {
            if (isPositionAttackedOptimised(chessBoard, i, j, n)) continue;

            chessBoard[i][j] = "Q";//choose[i][j] to Place the Queen
            //Update Map - Mark Unsafe Positions
            upperDiagOccupied[n-1+j-i] = true;
            leftRowOccupied[i] = true;
            lowerDiagOccupied[i+j] = true;

            tryAllWays2(chessBoard, j+1,n);//explore other indices for other queens

            //Update Map - UnMark Unsafe Positions
            upperDiagOccupied[n-1+j-i] = false;
            leftRowOccupied[i] = false;
            lowerDiagOccupied[i+j] = false;
            chessBoard[i][j] = ".";//un-choose/abandon

        }
    }

    private boolean isPositionAttackedOptimised(String[][] chessBoard, int i, int j, int n) {
        return upperDiagOccupied[n-1+j-i] || leftRowOccupied[i] || lowerDiagOccupied[i+j];
    }


    //M1: Backtracking
    private void tryAllWays(String[][] chessBoard, int j) {
        //BC
        if (j == chessBoard.length) {
            ans.add(new ArrayList<>(convertMatrixToList(chessBoard)));
            return;
        }


        for (int i = 0; i < chessBoard.length; i++) {
            //before putting Queen at [i][j] -> check if the position is NOT ATTACKED by other queens
            if (isPositionAttacked(chessBoard, i, j)) continue;

            chessBoard[i][j] = "Q";//choose[i][j] to Place the Queen
            tryAllWays(chessBoard, j+1);//explore other indices for other queens
            chessBoard[i][j] = ".";//un-choose/abandon

        }
    }

    private List<String> convertMatrixToList(String[][] cB) {
        List<String> list = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < cB.length; i++) {
            for (int j = 0; j < cB.length; j++) {
                temp.append(cB[i][j]);
            }
            list.add(temp.toString());
            temp.setLength(0);
        }
        return list;
    }

    private boolean isPositionAttacked(String[][] chessBoard, int idx, int jdx) {
        //4 cases -> 4 directional attacks -> Vertical, Horizonatal and 2 diagonals
        /*
            OPTIMISATION:
            we move from left to right, i.e col =0 to col = len-1

            At any point, The only attacks we can face is from the LEFT side(with cell[i][j] being the ref.)
            as only left portion
            of the grid is populated by QUEENS.
            So no need to check right sides of grid.
         */

        //Vertical check - NOT required. as in a column we just place the Queen once. then for subsequent
        //rec calls we do j+1. And even if we do place the queen in the same J, its not in subsequent calls
        //Rather its during the iteration, where we first UNDO the placement of queen in that column and
        //then place it in the same column in some other row!!


        //Horizontal check - just left side of row we check -> hence just check upto the given col(jdx)
        for (int j = 0; j < jdx; j++) {
            if (chessBoard[idx][j].equals("Q"))
                return true;
        }

        // \diagonal check top
        int i = idx, j = jdx;
        while (i >=0 && j >=0) {
            if (chessBoard[i][j].equals("Q"))
                return true;
            i--;
            j--;
        }

        // \diagonal check bottom -> no need, as this would lie on right side of cell[i][j]

        // /diagonal check top -> again no need, it also lies on right side

        // /diagonal check bottom
        i = idx; j = jdx;
        while (i < chessBoard.length && j >=0) {
            if (chessBoard[i][j].equals("Q"))
                return true;
            i++;
            j--;
        }

        return false;
    }
}

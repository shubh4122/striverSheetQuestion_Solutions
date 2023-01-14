package striversSheet.medium;

import java.util.Arrays;

public class arrays_setMatrixZeroes {
    //There can be 3rd, most inefficient solution. where me use O(m*n) space

    //Best Approach
    public void setZeroesMostEfficient(int[][] matrix) {
        //O(1) space
        //O(2*(N*M) time
        int m = matrix.length;
        int n = matrix[0].length;

        //This method basically reduces that m+n arr space by
        //using the 1st row and 1st column of the MATRIX as marker for 0's
        boolean col0Has0 = false; //the 0,0 cell can be used as indicator for ROW_0. So we need an indicator for col0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0Has0 = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0)      //Marking the dummy row/col for 0's for all cells except 1st column
                    matrix[0][j] = matrix[i][0] = 0;
            }
        }

        //Starting to fill 0's from BACKWARDS of arr
        //REASON : if we start from front then out dummy markers will change
        for (int i = m-1; i >= 0 ; i--) {
            for (int j = n-1; j >= 1 ; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
            if (col0Has0)   matrix[i][0] = 0; // the 0th col is made 0 only after all the col cells in the given row are made 0, hence no issues.
        }
    }


    //Better Approach
    public static void setZeroes(int[][] matrix) {
        //O(m+n) Aux space
        //O(mn + m*(m+n)) time
        int m = matrix.length;
        int n = matrix[0].length;

        int[] marker = new int[n+m];
        //uptil, 0 to m-1 it marks which row has 0
        //then from m to n-1 it marks which col

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //marking where 0's are located
                    marker[i] = 1;
                    marker[m + j] = 1;
                }
            }
        }

        for (int i = 0; i < m+n; i++) {
            if (marker[i] == 1) {
                if (i < m) {//row marker
                    //fill iTH row
                    Arrays.fill(matrix[i], 0);
                }
                else {//col marker, i >=m
                    for (int j = 0; j < m; j++) {
                        matrix[j][i-m]=0;
                    }
                }
            }
        }
    }

}

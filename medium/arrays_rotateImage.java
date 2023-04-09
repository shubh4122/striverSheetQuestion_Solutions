package striversSheet.medium;

//https://leetcode.com/problems/rotate-image/description/
public class arrays_rotateImage {

    //NOTE:
    //Mirroring[say about x-axis] in code is simply - swapping of rows. Swap 0th row with n-1th. and so on. [SYMMETRIC]


    public static void main(String[] args) {
        int[][] m = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        rotateCLOCLWISE(m);
    }


    //M1 - top to down(mirror it about x-axis). Then SWAP about diagonal [CLOCKWISE]
    /*
Source : https://leetcode.com/problems/rotate-image/solutions/18872/a-common-method-to-rotate-the-image/
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * [--- 1. Mirror about y-axis. --- 2. Swap(Transpose) ---]
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7


     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * [--- 1. Mirror about x-axis. --- 2. Swap(Transpose) ---]
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
 */
    public static void rotateCLOCLWISE(int[][] matrix) {
        int n = matrix.length;

        //Step 1: Mirror about x-axis
        /*
            Original Matrix:
            [1, 2, 3]
            [4, 5, 6]
            [7, 8, 9]
         */
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }
        /*
            Above code results into this.
            [7, 8, 9]
            [4, 5, 6]
            [1, 2, 3]
         */

        //Step 2: Swapping about diagonals. that is, [i,j] -> [j, i]
        //--------- THIS IS BASICALLY TRANSPOSE OF SQUARE MATRIX ----------
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {//IMP note: j starts from 'i'. so there is no 2 swaps for same elem.
                //if we start j with 0, then we will obtain the original matrix[before transpose] as swaps happen 2 times, hence cancelling effect
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        /*
            Final Result is a 90deg rotated matrix
            [7, 4, 1]
            [8, 5, 2]
            [9, 6, 3]
         */
    }

    //M2
    /*
    Source: https://leetcode.com/problems/rotate-image/solutions/18879/ac-java-in-place-solution-with-explanation-easy-to-understand/
        Clockwise :
            1. Transpose
            2. Mirror about y-axis

        Anti-Clockwise :
            1. Transpose
            2. Mirror about x-axis
     */

    //MORE: rotation about 90, 180 and 270 deg.
    //Source: https://leetcode.com/problems/rotate-image/solutions/1449737/rotation-90-code-180-270-360-explanation-inplace-o-1-space/
 }

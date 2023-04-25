package striversSheet.medium;

public class arrays_SearchIn2DMatrix {


    //BETTER - 1 BS
    //TC: O(log n), SC: O(1)
    public boolean searchMatrixBETTER(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0, end = m*n - 1;

        while (start <= end) {
            int mid = start + (end - start)/2;

            //VV IMP - the mid elem is this. SEE HOW?
            if (matrix[mid/n][mid%n] == target)
                return true;

            else if (matrix[mid/n][mid%n] < target)
                start = mid+1;

            else
                end = mid - 1;
        }
        return false;
    }

    //2 Binary searches
    //TC: O(2*log n), SC: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0, end = m - 1;
        int midC = 0;//mid in the column

        //BS 1
        //This while loop finds the row target is in.
        //Col keeps same
        while (start <= end) {
            midC = start + (end - start)/2;

            if (start == end) {
                break;
            }

            if (matrix[midC][n - 1] == target)
                return true;
            else if (matrix[midC][n - 1] < target)
                start = midC + 1;

            else {
                //this if takes care that if, target lies in the
                //row of midC, so we must break and discontinue BS here
                if (matrix[midC][0] <= target)
                    break;
                end = midC - 1;
            }
        }

        //BS2
        //This will find the target in row - midC
        start = 0; end = n - 1;
        int midR = 0;//mid in the row

        //Row keeps same
        while (start <= end) {
            midR = start + (end - start)/2;

            if (matrix[midC][midR] == target)
                return true;

            else if (matrix[midC][midR] < target)
                start = midR+1;

            else
                end = midR-1;
        }
        return false;
    }
}

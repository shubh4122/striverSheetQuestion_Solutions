package striversSheet.OtherGoodQuestions;

import java.util.LinkedList;
import java.util.Queue;


//https://leetcode.com/problems/01-matrix/description/
public class graph_bfs_01Matrix {


    /*
        Steps: MULTISRC BFS
            1. Traverse Matrix firstly
                > Insert all the sources in the Queue beforehand[Multisource BFS](Before starting the BFS Traversal)
                > Mark all 1's as -1[=UNVISITED]. This saves us an array of memory of vis[].
            2. Begin BFS loop
                > Pop
                > Traverse 4 directionally using the FOR LOOP
                > Now, a cells value/distance can only be changed if these 3 conditions are TRUE
                    ~ If the calculated childI, childJ [4 dirc. indices] are valid and not out of bound
                    ~ If the current cell is not already visited. i.e. its current val will be UNVISITED[=-1]
                    ~ If the current cell value is not 0. BECAUSE all 0's are the source, and they have already been considered[in for loop above]
                > If all above conditions are true, DO:
                    ~ Update the child Cell dist to : parentDist + 1.
                    ~ Enter this child to Queue for its neighbours to be considered.
            3. NOTE: We don't check if the current value is More than the newDist.
               REASON: We only visit a cell once. BFS is by def. already capable to calculate the shortest dist in one Traversal only
                       Hence, we don't need to visit any cell twice to see if we have any shorted path. BFS does it all for us in just one visit.
     */
    final int UNVISITED = -1;
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair> q = new LinkedList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

//---------------------------------------------- Step1 ----------------------------------------------
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                if (mat[i][j] == 0)
                    q.add(new Pair(i, j));
                else
                    mat[i][j] = UNVISITED;//all 1's are marked UNVISITED[-1]


//---------------------------------------------- Step1 ----------------------------------------------
        while (!q.isEmpty()) {
            Pair pop = q.remove();
            int parentI = pop.i;
            int parentJ = pop.j;

            int k = 0;
            for (int idx = 0; idx < 4; idx++) {
                int childI = parentI + dx[k];
                int childJ = parentJ + dy[k];

                if (validIndex(mat, childI, childJ)
                    && unvisited(mat, childI, childJ) && notZero(mat, childI, childJ)) {
                    //deal with its dist
                    mat[childI][childJ] = mat[parentI][parentJ] + 1;
                    //add it to queue.
                    q.add(new Pair(childI, childJ));
                }
                k++;
            }
        }
        return mat;
    }



//---------------------------------------------- UTILS ----------------------------------------------
    private boolean notZero(int[][] mat, int i, int j) {
        return mat[i][j] != 0;
    }

    private boolean unvisited(int[][] mat, int i, int j) {
        return mat[i][j] == UNVISITED;
    }

    private boolean validIndex(int[][] mat, int newI, int newJ) {
        return newI >= 0 && newI < mat.length && newJ >= 0 && newJ < mat[0].length;
    }

    class Pair {
        int i;
        int j;

        Pair(int first, int second) {
            i = first;
            j = second;
        }
    }
}

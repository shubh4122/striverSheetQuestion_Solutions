package striversSheet.medium;

public class graph_floydWarshall {
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;

        //VV IMP : via loop will be outermost! and all other in same sequence
        for (int via = 0; via < n; via++) {
            for (int src = 0; src < n; src++) {
                for (int dest = 0; dest < n; dest++) {

                    if (matrix[via][dest] == -1)
                        continue;
                    else if (matrix[src][via] == -1)
                        break;

                    int newDist = matrix[src][via] + matrix[via][dest];
                    if (newDist < matrix[src][dest] || matrix[src][dest] == -1)
                        matrix[src][dest] = newDist;
                }
            }
        }
    }
}

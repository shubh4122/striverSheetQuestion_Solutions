package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class graph_bellmanFord {

    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, 100000000);//this is infinity in case of question
        dist[S] = 0;

        for (int i = 0; i < V - 1; i++) {
            //relax v-1 times over all edges
            for (int j = 0; j < edges.size(); j++) {
                int u = edges.get(j).get(0);
                int v = edges.get(j).get(1);
                int wt = edges.get(j).get(2);

                relax(dist, u, v, wt, false);
            }
        }

        //if there had been no -ve cycle the dist[] will remain unchanged in nTH iteration.
        //if dist[] changes it means there is -ve cycle

        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            int wt = edges.get(i).get(2);

            if (relax(dist, u, v, wt, true))//cycle found
                return new int[]{-1};
        }

        return dist;
    }


    public static boolean relax(int[] dist, int u, int v, int wt, boolean isNthCall) {
        //relax only if the node(u) is already visited, otherwise relaxation doesnt make sense!!
        if (dist[u] != 100000000) {
            int newDist = dist[u] + wt;
            if (newDist < dist[v]) {
                dist[v] = newDist;
                if (isNthCall)
                    return true;
            }
        }
        return false;
    }
}

package striversSheet.medium;

import striversSheet.medium.graph_dijkstra.Pair;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class graph_mstPrims {

    static int spanningTree(int n, int m, int edges[][]){
        ArrayList<ArrayList<Pair>> graph = edgeToListWeighted(edges, n, m);
        int mstWt = 0;
        PriorityQueue<Pair> q = new PriorityQueue<>(new Pair());//comparator
        boolean[] vis = new boolean[n];

        q.add(new Pair(0, 0));//any random node pushed

        while (!q.isEmpty()) {
            Pair pop = q.remove();
            int node = pop.first;
            int wt = pop.second;


            //this is because there may be multiple occurences of a node in PQ
            //so, once the smallest weight pair of that node is visited, all others ignored
            //using this if.
            if (vis[node])  continue;
            //taking the shortest edge
            mstWt += wt;
            vis[node] = true;//mark nodes visited only when they are popped and included in MST

            for (Pair adj : graph.get(node)) {
                //When nodes are entered here, it doesnt mean it is VISITED.
                //only when we pop them, they are marked visited.
                //Hence same node with different weights maybe present in PQ
                if (!vis[adj.first]) {
                    q.add(adj);
                }
            }
        }
        return mstWt;
    }

    public static ArrayList<ArrayList<Pair>> edgeToListWeighted(int[][] edges, int n, int m) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {//<=n because 1 based indexing
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            graph.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            graph.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }

        return graph;
    }
}

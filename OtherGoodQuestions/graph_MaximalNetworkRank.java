package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//https://leetcode.com/problems/maximal-network-rank/
public class graph_MaximalNetworkRank {


    //M2: just keeping count of edges connected to a node, and keeping track of immediately connected node
    public int maximalNetworkRank2(int n, int[][] roads) {
        int[] countDeg = new int[n];
        boolean[][] directConnection = new boolean[n][n];

        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0];//i did this by graph.get(u).size(); in M1
            int v = roads[i][1];
            countDeg[u]++;
            countDeg[v]++;

            directConnection[u][v] = true;//in M1, int commonRoad = graph.get(u).contains(v)?1:0; this is used
            directConnection[v][u] = true;
        }

        int maxNRank = 0;
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                maxNRank = Math.max(maxNRank, countDeg[u] + countDeg[v] - (directConnection[u][v] ? 1 : 0));
            }
        }
        return maxNRank;
    }


    //M1: Creating a complete graph and using it.
    public int maximalNetworkRank(int n, int[][] roads) {
        List<HashSet<Integer>> graph = makeGraphFromEdges(roads, n);

        int maxNetworkRank = 0;
        //look for each pair
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                //number of directly connected roads to a city is essentially its DEGREE
                int degreeU = graph.get(u).size();
                int degreeV = graph.get(v).size();

                int commonRoad = graph.get(u).contains(v) ? 1 : 0;
                maxNetworkRank = Math.max(maxNetworkRank, degreeU + degreeV - commonRoad);
            }
        }
        return maxNetworkRank;
    }

    //i took hashset so i can easily check if there is any common edge
    //n = num of nodes
    public List<HashSet<Integer>> makeGraphFromEdges(int[][] edges, int n) {
        List<HashSet<Integer>> graph = new ArrayList<>();

        //initialising all sets
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            //coz bi-directional graph edges.
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}

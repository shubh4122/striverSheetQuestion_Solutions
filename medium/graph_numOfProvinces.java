package striversSheet.medium;

import DSA.DisjointSet;

import java.util.ArrayList;

//https://leetcode.com/problems/number-of-provinces/description/
public class graph_numOfProvinces {

//    public static void main(String[] args) {
//        graph_numOfProvinces a = new graph_numOfProvinces();
//        a.findCircleNum2(new int[][]{{1,1,0},{1,1,0},{0,0,1}});
//    }
//    //using Disjt set and union find
//    public int findCircleNum2(int[][] isConnected) {
//        DisjointSet d = new DisjointSet(isConnected.length);
//
//        for (int i = 0; i < isConnected.length; i++) {
//            for (int j = 0; j < isConnected.length; j++) {
//                if (isConnected[i][j] == 1)
//                    d.unionByRank(i, j);
//            }
//        }
//
//        System.out.println(d.parent);
//        return 0;
//    }




    //By converting edge to list and DFS trav, compnent count
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> graph = edgeToList(isConnected);

        boolean vis[] = new boolean[isConnected.length];
        int count = 0;
        for(int node = 0; node < vis.length; node++) {
            if(!vis[node]) {
                count++;
                dfs(graph, vis, node);
            }
        }

        return count;
    }

    public void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] vis, int node) {

        if(!vis[node]) {
            vis[node] = true;

            for(int adj : graph.get(node)) {
                dfs(graph, vis, adj);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> edgeToList(int[][] a) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < a.length; i++) {
            graph.add(new ArrayList());
        }

        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length; j++) {
                if(a[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }
}

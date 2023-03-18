package striversSheet.medium;

import striversSheet.medium.tree_bottomView.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://www.codingninjas.com/codestudio/problems/1062670?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=0
public class graph_undirectedCycleFind {

    //VV IMP NOTE: 1 based indexing
    public static String cycleDetection(int[][] edges, int n, int m) {
        boolean[] vis = new boolean[n+1];
        ArrayList<ArrayList<Integer>> graph = edgeToList(edges, n, m);

        for (int node = 1; node <= n; node++) {
            if (!vis[node]) {
//                if (cycleDetectionBFS(graph, n, vis, node)) {
//                    return "Yes";
//                }

                if (cycleDetectionDFS(graph, vis, node, -1))
                    return "Yes";
            }
        }
        return "No";
    }

    public static boolean cycleDetectionDFS(ArrayList<ArrayList<Integer>> graph, boolean[] vis, int node, int parent) {
        if (! vis[node]) {
            vis[node] = true;
            for (int adjNode : graph.get(node)) {
                if (! vis[adjNode]) {//these brackets very imp. Their absence causes DANGLING IF condition
                    if (cycleDetectionDFS(graph, vis, adjNode, node))
                        return true;
                }
                else if (vis[adjNode] && adjNode != parent)
                    return true;

            }
        }
        return false;
    }
    
    public static boolean cycleDetectionBFS(ArrayList<ArrayList<Integer>> graph, int n, boolean[] vis, int node) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();

        //src = NODE
        //--------------- VV IMP ---------------//
        //Reason : coz every component will have different source and hence use it as passed!!
        q.add(new Pair<>(node, -1));//node, parent
        vis[node] = true;
        
        while (!q.isEmpty()) {
            Pair temp = q.remove();
            int pop = (int) temp.first;
            int popParent = (int) temp.second;

            for (int adjNode : graph.get(pop)) {
                if (vis[adjNode] && adjNode != popParent)
                    return true;
                else if (!vis[adjNode]) {
                    q.add(new Pair<>(adjNode, pop));
                    vis[adjNode] = true;
                }
            }
        }
        return false;
    }
    
    public static ArrayList<ArrayList<Integer>> edgeToList(int[][] edges, int n, int m) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {//<=n because 1 based indexing
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        return graph;
    }
}

package striversSheet.medium;

import java.util.ArrayList;

//https://www.codingninjas.com/codestudio/problems/1062626?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website
public class graph_DirectedCycleFind {


    //easy. see Graphs.java
//    public static boolean cycleDetectionBFS(int n, ArrayList < ArrayList < Integer >> edges) {
//        ArrayList<ArrayList<Integer>> graph = edgeToList(edges, n);
//
//    }

    public static boolean cycleDetection(int n, ArrayList < ArrayList < Integer >> edges) {
        boolean[] vis = new boolean[n+1];//1 indexed
        boolean[] DFSvis = new boolean[n+1];
        ArrayList<ArrayList<Integer>> graph = edgeToList(edges, n);

        for (int node = 1; node <= n; node++) {
            if (!vis[node]) {
                if (cycleDetectionDFS(graph, vis, DFSvis, node))
                    return true;
            }
        }
        return false;
    }

    public static boolean cycleDetectionDFS(ArrayList<ArrayList<Integer>> graph, boolean[] vis, boolean[] DFSvis, int node) {

        if (DFSvis[node])
            return true;

        if (!vis[node]){
            vis[node] = true;
            DFSvis[node] = true;

            for (int adjNode : graph.get(node)) {
                if (cycleDetectionDFS(graph, vis, DFSvis, adjNode))
                    return true;

                //WRONG!!!!!
                //DFSvis[node] = false;
            }
        }

        //IMP : Every node's DFSvis must be made false when its traversal ends. And its traversal ends when function ends
        //Not in the for loop. Coz there traversals of adjNodes may end, but it wont work for their parent, that is the "node"
        //especially for source nodes of graph and nodes that are passed from above function - cycleDetection(for-if)
        DFSvis[node] = false;
        return false;
    }


    public static ArrayList<ArrayList<Integer>> edgeToList(ArrayList<ArrayList<Integer>> edges, int n) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {//<=n because 1 based indexing
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            //Because edge here is Directed hence not both directions
            graph.get(edges.get(i).get(0)).add(edges.get(i).get(1));
        }

        return graph;
    }

}

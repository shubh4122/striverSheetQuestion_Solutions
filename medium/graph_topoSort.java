package striversSheet.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class graph_topoSort {

    //------------------- BFS -------------------//

    public int[] topoBFS(int n, ArrayList<ArrayList<Integer>> graph) {
        boolean vis[] = new boolean[n];
        ArrayList<Integer> topo = new ArrayList<>(n);

        //------------- NOT required, IT GIVES TLE IF USED!! ----------------
        
        //for only disconnected components
//        for (int node = 0; node < n; node++) {
//            if (!vis[node]) {

                //MAIN CODE
                //STEP 1 : calc indegree
                int[] indegree = new int[n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < graph.get(i).size(); j++) {//adj node to parent 'i'
                        indegree[graph.get(i).get(j)]++;
                    }
                }

                Queue<Integer> q = new LinkedList<>();

                //STEP 2 : insert all nodes with indeg 0 to q
                for (int i = 0; i < n; i++)
                    if (indegree[i] == 0)
                        q.add(i);

                //STEP 3 : bfs code

                while (!q.isEmpty()) {
                    int parent = q.remove();
                    topo.add(parent);

                    for (int adjNode : graph.get(parent)) {
                        indegree[adjNode]--;
                        if (indegree[adjNode] == 0)
                            q.add(adjNode);
                    }
                }
//            }
//        }

        //returning answer
        int[] topoArr = new int[n];

        for (int i = 0; i < n; i++) {
            topoArr[i] = topo.get(i);
        }

        return topoArr;
    }


    //------------------- DFS -------------------//

    public int[] toposort(int n, ArrayList<ArrayList<Integer>> graph) {
        boolean vis[] = new boolean[n];
        ArrayList<Integer> topo = new ArrayList<>(n);

        //here this is not only for Disconnected Components.
        //Here it also takes care of nodes that cant be reached from current nodes
        //hence it lets us visit all nodes
        for (int node = 0; node < n; node++) {
            if (!vis[node])
                topoDFS(graph, node, vis, topo);
        }
        int[] topoArr = new int[n];
        Collections.reverse(topo);
        for (int i = 0; i < n; i++) {
            topoArr[i] = topo.get(i);
        }

        return topoArr;
    }

    public void topoDFS(ArrayList<ArrayList<Integer>> graph, int node, boolean[] vis, ArrayList<Integer> topo) {
        if (!vis[node]) {
            vis[node] = true;
            for (int adjNode : graph.get(node)) {
                topoDFS(graph, adjNode, vis, topo);
            }
            topo.add(node);
        }
    }
}

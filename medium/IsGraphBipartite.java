package striversSheet.medium;

import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {
    public static void main(String[] args) {

    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];//similar to vis array
        //-1 : color 1
        //1  : color 2
        //0  : Not visited
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0)//i.e. not visited nodes can only enter traversal
//                if (! isBipartiteBFS(graph, color, i))
//                    return false;//else keep checking for other components
                if (! isBipartiteDFS(graph, color, i, 1))//and hence source will always be colored -1.
                    return false;
        }
        return true;
    }

    public boolean isBipartiteDFS(int[][] graph, int[] color, int node, int parentColor){
        if (color[node] == 0) {//uncolored
            color[node] = parentColor * -1;

            for (int adjNode : graph[node]) {
                if (! isBipartiteDFS(graph, color, adjNode, color[node]))
                    return false;
            }
        }
        else {
            if (color[node] == parentColor)
                return false;
        }

        //run when if runs and false is never returned in for loop!!
        return true;
    }

    public boolean isBipartiteBFS(int[][] graph, int[] color, int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = -1;

        while (!q.isEmpty()) {
            int pop = q.remove();//parent here

            for (int adjNodes : graph[pop]) {
                if(color[adjNodes] == 0) {//unvisited
                    q.add(adjNodes);
                    color[adjNodes] = color[pop] * -1;
                }
                else {//colored
                    if (color[adjNodes] == color[pop])
                        return false;
                }
            }
        }
        return true;
    }
}

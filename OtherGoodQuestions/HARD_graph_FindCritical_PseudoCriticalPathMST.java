package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/
public class HARD_graph_FindCritical_PseudoCriticalPathMST {

    /*
        Steps:
            1. Firstly, As we need to apply Kruskal, the edges must be SORTED.
               But, we need to keep track of their indices AS We need to return indices of EDGES in ANSWER!
            2. Find the MST weight using (MODIFIED)KRUSKAL(See why only KRUSKAL?)
            3. Now, iterate over each EDGE.
                > To find the CRITICAL EDGE: Try to Skip it and call KRUSKAL.
                    ~ If, thisMSTwt > mstWT  -> means, the current skipped edge is CRITICAL.
                    ~ Also, if graph gets disconnected after removing this edge, it is also CRITICAL
                > To find the P.Critical Edge:
                    ~ Take iTH edge NECESSARILY in the mst, and see if "newMSTwt == mstWt" then it is PCritical edge

            4. Write a slightly modified Kruskal to do this task
     */
    int nodes;

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

//------------------------------------------------------- Step 1 -------------------------------------------------------
        nodes = n;
        int[][] sortedEdges = new int[edges.length][4];
        for (int i = 0; i < edges.length; i++) {
            sortedEdges[i][0] = edges[i][0];//u
            sortedEdges[i][1] = edges[i][1];//v
            sortedEdges[i][2] = edges[i][2];//wt
            sortedEdges[i][3] = i;//original index
        }
        Arrays.sort(sortedEdges, Comparator.comparing(o -> o[2]));//sort wrt wt.

//------------------------------------------------------- Step 2 -------------------------------------------------------
        int mstWt = kruskalModified(sortedEdges, -1, -1);

//------------------------------------------------------- Step 3 -------------------------------------------------------
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        int criticalMstWt, pCriticalMstWt;
        for (int i = 0; i < sortedEdges.length; i++) {
            int[] edge = sortedEdges[i];
            //Critical Edge: Check by SKIPPING current edge
            criticalMstWt = kruskalModified(sortedEdges, i, -1);
            if (criticalMstWt > mstWt) {
                critical.add(edge[3]);
                continue;//if an edge is critical It CANT BE PSEUDO_CRITICAL
            }

            //Pseudo-Critical Edge: Compulsorily add this edge
            pCriticalMstWt = kruskalModified(sortedEdges, -1, i);
            if (pCriticalMstWt == mstWt)
                pseudoCritical.add(edge[3]);
        }

//------------------------------------------------------- Returning answer -------------------------------------------------------
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(critical);//Just doing SHALLOW COPY, as these inner lists don't change at this point. so no need of deep copy
        ans.add(pseudoCritical);

        return ans;
    }

//------------------------------------------------------- Step 4 -------------------------------------------------------
    private int kruskalModified(int[][] sortedEdges, int skipEdge, int addEdge) {
        DSU dsu = new DSU(nodes);
        int mstWt = 0;

        //modification1 : for pseudo critical edges.
        if (addEdge != -1) {
            dsu.union(sortedEdges[addEdge][0], sortedEdges[addEdge][1]);
            mstWt += sortedEdges[addEdge][2];
        }

        for (int i = 0; i < sortedEdges.length; i++) {
            //modification2: for critical edges
            if (skipEdge == i)
                continue;

            int u = sortedEdges[i][0];
            int v = sortedEdges[i][1];
            int wt = sortedEdges[i][2];

            //if cycle forms, skip the edge
            if (dsu.findParent(u) == dsu.findParent(v))
                continue;

            //else join this edge.
            dsu.union(u, v);
            mstWt += wt;
        }

        //BROKEN GRAPH: it might happen that because of skipping an edge, we end up forming BROKEN GRAPH. so check this
        for (int i = 0; i < nodes; i++) {
            if (dsu.findParent(i) != dsu.findParent(0))//check with any random node. Every ones parent must be same
                return Integer.MAX_VALUE;//that is skipped edge was critical
        }

        return mstWt;
    }
}

class DSU {
    int[] rank, parent;

    DSU(int nodes) {
        rank = new int[nodes];
        parent = new int[nodes];
        //IMP: every node is parent of itself at the starting
        for (int i = 0; i < nodes; i++) {
            parent[i] = i;
        }
    }

    public int findParent(int node) {//this finds the ultimate parent
        if (node == parent[node])
            return node;

        int parentOfNode = findParent(parent[node]);
        return parent[node] = parentOfNode;
    }

    public void union(int u, int v) {
        //Step1 : Find parents of both
        int pu = findParent(u);
        int pv = findParent(v);

        //Step2: Connected: if both u, v have same parent already -> Means they are already connected
        if (pu == pv)
            return;

        //Step3: Disconnected: Find ranks of parents and compare
        int rankPU = rank[pu];
        int rankPV = rank[pv];

        //Step4: Find Union/Join the 2 components. Smaller rank joins the Larger rank
        if (rankPU < rankPV) {
            parent[pu] = pv;
        } else if (rankPU > rankPV)
            parent[pv] = pu;
        else {//equal
            parent[pu] = pv;
            rank[pv] = rank[pu] + 1;
        }
    }
}

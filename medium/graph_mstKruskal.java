package striversSheet.medium;

import DSA.DisjointSet;

import java.util.Arrays;
import java.util.Comparator;

public class graph_mstKruskal {

    static int spanningTree(int n, int m, int edges[][]){
        //Step1 - sort edges about wt
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];//index [i][2] is weight of corresponding edge
            }
        });

        //Step 2: use disjoint set, and make connections
        DisjointSet ds = new DisjointSet(n);
        int mstWt = 0;

        for (int[] edge : edges) {
            if (ds.findUltParent(edge[0]) != ds.findUltParent(edge[1])) {
                mstWt += edge[2];
                ds.unionByRank(edge[0], edge[1]);
            }
        }
        return mstWt;
    }
}

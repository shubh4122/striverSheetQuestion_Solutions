package striversSheet.medium;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
public class graph_dijkstra {

    //Using Set
//    static int[] dijkstraSet(int n, ArrayList<ArrayList<ArrayList<Integer>>> graph, int src) {
//        TreeSet<Pair> set = new TreeSet<>(new Pair());//this is Comparator
//        int[] dist = new int[n];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//
//        set.add(new Pair(src, 0));
//        dist[src] = 0;
//
//        while (!set.isEmpty()) {
//            Pair p = set.pollFirst();//removes smallest item in set
//            int node = p.first;
//            int wt = p.second;
//
//            for (ArrayList<Integer> adj : graph.get(node)) {
//                int adjNode = adj.get(0);
//                int adjWt = adj.get(1);
//
//                //Relaxation
//                int newDist = dist[node] + adjWt;
//                if (newDist < dist[adjNode]){
//                    //if node already exist in Set, remove it.
//                    if (dist[adjNode] != Integer.MAX_VALUE)//it'll exist only if its dist is not INFINITY
//                        set.remove(new Pair(adjNode, dist[adjNode]));
//                    dist[adjNode] = newDist;
//                    set.add(new Pair(adjNode, adjWt));
//                }
//            }
//        }
//        return dist;
//    }

    //Using PriorityQueue
    static int[] dijkstra(int n, ArrayList<ArrayList<ArrayList<Integer>>> graph, int src)  {
        PriorityQueue<Pair> q = new PriorityQueue(new Pair());//this is Comparator
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new Pair(src, 0));
        dist[src] = 0;

        while (!q.isEmpty()) {
            Pair p = q.remove();
            int node = p.first;
            int wt = p.second;

            for (ArrayList<Integer> adj : graph.get(node)) {
                int adjNode = adj.get(0);
                int adjWt = adj.get(1);

                //Relaxation
                int newDist = dist[node] + adjWt;
                if (newDist < dist[adjNode]){
                    dist[adjNode] = newDist;
                    q.add(new Pair(adjNode, adjWt));
                }
            }
        }
        return dist;
    }

    public static class Pair implements Comparator<Pair> {
        int first, second;
        public Pair(){}

        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compare(Pair p1, Pair p2) {
            return p1.second - p2.second;//ascending order
        }
    }
}

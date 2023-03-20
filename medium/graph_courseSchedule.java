package striversSheet.medium;

import java.util.ArrayList;

import static striversSheet.medium.graph_DirectedCycleFind.cycleDetectionDFS;

//https://leetcode.com/problems/course-schedule/
public class graph_courseSchedule {

    //prereq is edges arr here
    /*
        IDEA:
        [a,b] means b course should be done before a.
        Hence, b-->a.
        Now if there is a cycle formed. like:   b->a->c->b
        Then, no course can be done first as they are interdependant on each other

        THEREFORE, same as CYCLE IN DIRECTED GRAPH
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = edgeToList(prerequisites, numCourses, prerequisites.length);
        boolean[] vis = new boolean[numCourses];
        boolean[] dfsVis = new boolean[numCourses];

        for (int node = 0; node < numCourses; node++) {
            if (!vis[node])
                if (cycleDetectionDFS(graph, vis, dfsVis, node))
                    return false;//return false if cycle found. Coz then we CANT FINISH ALL COURSES
        }
        return true;//i.e. we can finish all courses
    }


    public static ArrayList<ArrayList<Integer>> edgeToList(int[][] edges, int n, int m) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {//<=n because 1 based indexing
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph.get(edges[i][1]).add(edges[i][0]); //Reversed coz, b->a and not as usual
        }

        return graph;
    }
}

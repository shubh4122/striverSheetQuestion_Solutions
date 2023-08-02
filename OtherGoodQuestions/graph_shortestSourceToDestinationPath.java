package striversSheet.OtherGoodQuestions;

import striversSheet.medium.graph_dijkstra;

import java.util.LinkedList;
import java.util.Queue;

//https://practice.geeksforgeeks.org/problems/shortest-source-to-destination-path3544/1
public class graph_shortestSourceToDestinationPath {

    class Pair{
        int first, second;
        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

//TLE

//                    vis[i][j]= true;
//                    if (i+1 < N && A[i+1][j] == 1 && !vis[i+1][j])
//                        q.add(new Pair(i+1, j));
//                    if (j+1 < M && A[i][j+1]==1 && !vis[i][j+1])
//                        q.add(new Pair(i, j+1));
//                    if (i-1>=0 && A[i-1][j]==1 && !vis[i-1][j])
//                        q.add(new Pair(i-1, j));
//                    if (j-1>=0 && A[i][j-1]==1 && !vis[i][j-1])
//                        q.add(new Pair(i, j-1));

    //BFS
    int shortestDistance(int N, int M, int A[][], int X, int Y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        q.add(new Pair(-1, -1));//this is used to separate cells of same level!

        //These are the values that must be subtracted/added to i/j to move to their 4 neighbours
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};


        int countSteps = 0;
        while (!q.isEmpty()) {
            Pair pop = q.remove();
            int i = pop.first;
            int j = pop.second;

            if (i==X && j==Y)
                return countSteps;

            if (i==-1 && j == -1){
                if (!q.isEmpty())
                    q.add(new Pair(-1, -1));

                countSteps++;//num of separators encountered are basically no of times we take a step.
            }

            else{
                //equivalent to individually checking and doing [i-1,j][i,j+1] etc
                //NOTE: if i individually do it, it gives TLE at end cases, but this works
                for (int k = 0; k < 4; k++) {
                    int newI = i + dx[k];
                    int newJ = j + dy[k];

                    if (newI >=0 && newI < N && newJ >=0 && newJ < M && A[newI][newJ]==1){
                        //once we have recorded all the 4 directional cells. make them 0, so
                        //they aren't re-counted by their children!
                        q.add(new Pair(newI, newJ));
                        A[newI][newJ] = 0;//to prevent it to be recounted by its children in Queue
                    }
                }
            }

        }
        return -1;
    }


}

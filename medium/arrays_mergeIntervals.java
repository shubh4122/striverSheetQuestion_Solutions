package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/problems/merge-intervals/description/
public class arrays_mergeIntervals {

    public static void main(String[] args) {
        int[][] m = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        System.out.println(Arrays.deepToString(merge(m)));
    }
    public static int[][] merge(int[][] intervals) {
        //sort wrt start times
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]); //  O(NlogN)

        ArrayList<int[]> ans = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];

        for (int i = 0; i < intervals.length - 1; i++) {
            if (end >= intervals[i+1][0]) { //i.e. end of prev interval is >= start of next interval.
                //overlap!
                end = Math.max(end, intervals[i+1][1]); //new end will be end time of next overlapped interval
            }
            else {
                //as soon as interval with no overlap is found
                System.out.println(start + " " + end);
                ans.add(new int[]{start, end});
                start = intervals[i+1][0];
                end = intervals[i+1][1];
            }
        }
        ans.add(new int[]{start, end});
//        int[][] ansArr = new int[ans.size()][ans.size()];
//        for (int i = 0; i < ansArr.length; i++) {
//            ansArr[i] = ans.get(i);
//        }

        return ans.toArray(new int[ans.size()][]);//param - the array in which elem of list is stored and hence returned!
    }
}

package striversSheet.OtherGoodQuestions;

import java.util.List;
import java.util.TreeSet;

//https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint/
public class MinAbsDiffBetweenElemWithConstraint {


    /*
        Notes:
        1. We need a DS that is sorted and also easy to search for CEIL/FLOOR
           Treeset can be used.
        2. Start loop from x to n.size().
           > Insert (i-x)th elem in ds.
           > But look for (i)th elem's floor/ceil.
           Reason:
           coz, doing this will ensure that we always pick elem as floor/ceil which
           are at-least 'x' distance apart from the (i)th element.
     */
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> ds = new TreeSet<>();

        int min = Integer.MAX_VALUE;
        for (int i = x; i < nums.size(); i++) {
            ds.add(nums.get(i - x));
            int currNum = nums.get(i);

            Integer justLesser = ds.floor(currNum);
            Integer justGreater = ds.ceiling(currNum);

            if (justLesser != null)
                min = Math.min(min, currNum - justLesser);
            if (justGreater != null)
                min = Math.min(min, justGreater - currNum);

            if (min == 0) return min;
        }
        return min;
    }
}

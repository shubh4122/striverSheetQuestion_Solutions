package striversSheet.medium;

import java.util.HashSet;
import java.util.TreeSet;

//https://leetcode.com/problems/longest-consecutive-sequence/description/
public class arrays_LongestConsecutiveSequence {

    //See this UNION FIND method?
    //https://leetcode.com/problems/longest-consecutive-sequence/solutions/166544/union-find-thinking-process/

    //MY - Works good. In much less time than STRIVER's Optimal soln
    public int longestConsecutive(int[] nums) {
        //BC
        if (nums.length == 0)
            return 0;

        //Step 1: Push all in a SET
        HashSet<Integer> h = new HashSet<>();
        for (int n : nums) {
            h.add(n);
        }

        //Step 2: Query if set has, n-1 and n+1 elems.
        int count = 0, maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //break if all elems done
            if (h.isEmpty())
                break;

            int n = nums[i];
            boolean isPresent = h.remove(n);

            if (isPresent){
                //count the removed elem
                count++;

                int nCopy = n;
                //Check if n-1 elems present
                while (!h.isEmpty() && h.contains(n - 1)) {
                    h.remove(n - 1);
                    n = n - 1;
                    count++;
                }

                while (!h.isEmpty() && h.contains(nCopy + 1)) {
                    h.remove(nCopy + 1);
                    nCopy = nCopy + 1;
                    count++;
                }

                maxCount = Math.max(maxCount, count);
                count = 0;

            }
        }
        return maxCount;
    }
}

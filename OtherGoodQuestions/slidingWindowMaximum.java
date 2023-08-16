package striversSheet.OtherGoodQuestions;

import java.util.*;

//https://leetcode.com/problems/sliding-window-maximum/
public class slidingWindowMaximum {

    //M2: Using """MONOTONIC DEQUEUE"""
    /*
        Steps:
            1. Loop until (end < n)
                a. if window size > k,
                    > Check if the First elem in DQ(the max) was at start.
                      If yes, remove that, as window moves it moves out of win.
                    > Update start.
                b. while the top < curr, POP TOP.
                c. then insert curr.
                d. If curr window size == k -> Insert the dq.first in MAX[]
                e. Update end.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int[] max = new int[nums.length - k + 1];

        int start = 0, end = 0, i = 0;
        while (end < nums.length) {
            //when window size gets MORE THAN K.
            if (end - start + 1 > k) {
                if (dq.peekFirst() == nums[start])
                    dq.removeFirst();
                start++;
            }

            //inserting the new elem - pointed by end
            //but before pop all elem lesser than this new elem
            while (!dq.isEmpty() && dq.peekLast() < nums[end])
                dq.removeLast();

            dq.addLast(nums[end]);
            //for this window, store the MAX
            if (end - start + 1 == k)//if win size has reached k.
                max[i++] = dq.peekFirst();

            end++;
        }
        return max;
    }


    //M1: Brute Force. Gives TLE
    public int[] maxSlidingWindowBRUTE(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);

            if (pq.size() == k) {
                list.add(pq.peek());
                //remove the first elem of window
                pq.remove(nums[i - k + 1]);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}

package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-the-longest-equal-subarray/description/
//https://www.notion.so/2831-Find-the-Longest-Equal-Subarray-2bda8abdf72e408288333a5a1f7194e0?pvs=4 -> Notion
public class SlidingWindow_findLongesEqualSubarray {

    /*
        Steps:
            1. Store indices of all elements in a Hashmap
            2. Traverse the Map Values, and for each key's value:
            3. Do a Sliding Window on the Stored INDICES list.
                > Check for each element, The max subarray of an element we can get by doing 'K' deletions
            4. At the end, the max window size we get is the Max subarray we can get out of nums.


        Explanation with Example:
            nums: [1,2,3,1,2,2,2,2,3,4,2,1] and k = 4;
            1. After step 1, our map:
                    1 -> {0,3,11}
                    2 -> {1,4,5,6,7,10}
                    3 -> {2,8}
                    4 -> {9}

            2. Iterate over this map. and for each value list, Do a SLIDING WINDOW. To check the max window size(= max subarray after deletions)
                a. 1st Iteration on map
                   s = 0, e = 1, deletions = 0
                        > deletions = 2, max = 2, e =2
                        > deletions = 9, (d > k)  -> s = 1,2,3  -> and there is no more ways to move a window.
                b. 2nd Iteration on map
                   s = 0, e = 1, del = 0
                        > del = 2, max = 2, e = 2{5)
                        > del = 2, max = 3, e = 3(6)
                        > del = 2, max = 4, e = 4(7)
                        > del = 2, max = 5, e = 5(10)
                        > del = 4, max = 6, e = 6(ended)
                c. similarly.

            3. now, the max window size we found was max = 6, which is our answer.
     */
    public int longestEqualSubarray(List<Integer> nums, int k) {
        if (nums.size()==1)
            return 1;

//---------------------------------------------------- Step 1 ----------------------------------------------------
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int n = nums.get(i);
            List<Integer> temp = map.getOrDefault(n, new ArrayList<>());
            temp.add(i);
            map.put(n, temp);
        }

//---------------------------------------------------- Step 2 ----------------------------------------------------
        int maxSubarrayLen = 1;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> val = entry.getValue();

//---------------------------------------------------- Step 3 ----------------------------------------------------
            //apply sliding window here.
            int s = 0, e = 1, deletions = 0;
            while (e < val.size()) {
                deletions += val.get(e) - val.get(e-1) -1;
                while (deletions > k){//until our deletions get lesser than equal to K.
                    s++;
                    deletions -= val.get(s)-val.get(s-1)-1;
                }

                maxSubarrayLen = Math.max(maxSubarrayLen, e-s+1);
                e++;
            }
        }
        return maxSubarrayLen;
    }
}

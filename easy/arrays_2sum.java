package striversSheet.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//https://leetcode.com/problems/two-sum/
public class arrays_2sum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> h = new HashMap();
        int[] ans = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (h.containsKey(nums[i])) {
                //coz there can be duplicate elem
                //This will happen only if there is duplicate elem
                if (target - nums[i] == nums[i]){
                    ans[0] = i;
                    ans[1] = h.get(nums[i]);
                    return ans;
                }
            }
            else {
                h.put(nums[i], i);
                if (h.containsKey(target - nums[i]) && target - nums[i] != nums[i]) {
                    ans[0] = i;
                    ans[1] = h.get(target - nums[i]);
                    return ans;//we return here only so loop doesnt run further
                }
            }
        }
        return ans;
    }

}

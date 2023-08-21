package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/sorting-three-groups/
public class LIS_sorting3Groups {

    /*
        As, we need to find a seq of: 1...1 2...2 3...3  in nums.
        It is Similar to increasing sequence.
        Hence, the elem that dont follow this are the problems. We need to change them
        so they also follow this order.

        Those elements count = Nums.length - LISLen.
     */
    public int minimumOperations(List<Integer> nums) {
        int lisLen = findLISlen(nums);

        return nums.size()-lisLen;//this gives num of elem which arent included in LIS
    }

    private int findLISlen(List<Integer> nums) {
        List<Integer> lis = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i);
            if (lis.isEmpty() || lis.get(lis.size()-1) <= val)
                lis.add(val);
            else{
                int idx = binarySearch(val, lis);//gives idx of given num or val just greater than it
                lis.set(idx, val);
            }
        }
        return lis.size();
    }

    /*
        The only minor change here is that, In LIS we didn't entertain same numbers more than once
        in the LIS list. but here we need to.
        Hence we strictly need to find an index whose value is just greater than the elem. NOT EQUAL
     */
    private int binarySearch(int val, List<Integer> lis) {
        int s=0, e = lis.size()-1, idx = -1;

        while (s <= e) {
            int mid = s +(e-s)/2;

            //if a val is equal to [mid] we don't consider it. As we need to store equal vals next to each other.
            //coz we need a non-decreasing sequence
            //if the lis contains only equal chars, it will already be handled in calling function. BS won't be called for it
            if (lis.get(mid) > val){
                idx = mid;
                e = mid-1;
            }

            else
                s = mid+1;
        }
        return idx;
    }
}

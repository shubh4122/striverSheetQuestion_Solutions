package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class recursion_Permutations {

    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();
        boolean[] vis = new boolean[nums.length];

        makePermutations(nums, vis);
        return ans;
    }

    //M2:  SC: O(1) -> Making changes in NUMS itself
    private void makePermutationsBETTER(int[] nums, int idx) {
        //BC
        if (idx == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) {
                list.add(n);
            }
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            //swap i with idx, which brings n[i] to n[idx] hence array is permuted upto n[idx]
            swap(nums, i, idx);
            /*
                IMP: why idx+1 and not i+1
                suppose idx = 0, i = 2;
                so, i+1 would be 3, fun(3) means, we have permuted upto n[3] which is wrong!
                we have just permuted upto idx. so next index to be permuted be idx+1
             */
            makePermutationsBETTER(nums, idx+1);
            swap(nums, i, idx); //undo swap to restore to original arr
        }
    }

    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }


    //M1:  SC: O(n) - coz using extra vis array
    private void makePermutations(int[] nums, boolean[] vis) {
        //BC
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) continue;

            vis[i] = true;
            list.add(nums[i]);
            makePermutations(nums, vis);
            list.remove(list.size()-1);
            vis[i] = false;
        }
    }
}

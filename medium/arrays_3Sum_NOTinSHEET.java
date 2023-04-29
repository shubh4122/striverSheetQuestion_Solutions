package striversSheet.medium;

import java.util.*;

public class arrays_3Sum_NOTinSHEET {

    //OPTIMAL - Because it uses NO extra SPACE!!
    //TC - O(n logn) + O(n^2), SC - O(Just the tripelets we store. NO extra due to Data Strs)
    public List<List<Integer>> threeSumOptimal(int[] nums) {
        /*
            Approach: Use 3 Pointers. i, j, k
            1st SORT array
            Put i at starting. j just next to i. And k at end

            add n[i]+[j]+[k] = val.
            if val < target(0, here) Then do, j++;
            COZ, on increasing j, we get greater values, as array is sorted
            and hence we can reach closer to 0 with new j.

            Once val adds upto 0, put them in List, and move j++, k-- UNTIl
            j points to a number not same as its previous, and k points to the
            number not same as its previous.
            REPEAT
            And once, j and k CROSS each other. STOP.
            And then increase i, until it points to different elem from prev
         */

        List<List<Integer>> triplets = new ArrayList<>();

        //Step1 - Sort
        Arrays.sort(nums);

        //i, j, k - 3 pointers used
        for (int i = 0; i < nums.length; i++) {

            //OPTIMISATION STEP:
            //IF the 1st elem(smallest) is a +ve elem. Then NO way we can ever form a SUM = 0
            if (nums[0] > 0)
                break;

            if (i > 0 && nums[i] == nums[i-1])
                continue;

            int j = i+1;
            int k = nums.length - 1;

            //Step 2: while j and k dont cross, keep iterating
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0){
                    triplets.add(List.of(nums[i], nums[j], nums[k]));
                    //as both j and k, used now update them both.
                    j++;
                    k--;
                    //the point j crosses k, we are done. NO NEED TO CARRY ON
                    while (j < k && nums[j] == nums[j-1])
                        j++;

                    while (j < k && nums[k] == nums[k+1])
                        k--;
                }
                else if (sum < 0) {
                    //In this case, increase J.
                    j++;
                }
                else {
                    //i.e. sum > 0, so Decrease K
                    k--;
                }
            }
        }
        return triplets;
    }


    //BETTER
    //TC: O(n^2)
    //SC: O(no. of triplets) + O(n) - for temp set
    public List<List<Integer>> threeSumBetter(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        HashSet<Integer> temp = new HashSet<>();
        HashSet<List<Integer>> ans = new HashSet<>();

        /*
            Put 2 pointers. I and j.
            and the elements between these pointers should be put in
            temp set

            now, 1st elem: nums[i], 2nd elem: nums[j]
            3rd elem: -(1st + 2nd)

            Find -(1st + 2nd) in HASHSET, temp.
         */
        for (int i = 0; i < nums.length; i++) {
            int elem1 = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int elem2 = nums[j];
                int elem3 = - (elem1 + elem2);

                if (temp.contains(elem3)) {
                    List<Integer> probableTriplets = new ArrayList<>();
                    probableTriplets.add(elem1);
                    probableTriplets.add(elem2);
                    probableTriplets.add(elem3);

                    Collections.sort(probableTriplets);

                    if (! ans.contains(probableTriplets)) {
                        triplets.add(probableTriplets);
                        ans.add(probableTriplets);
                    }
                }

                temp.add(elem2);
            }

            //Clear this set, coz with every changing 1st elem,
            //we need an empty set and start the process from beginning
            temp.clear();
        }
        return triplets;
    }


    //BRUTE
    //TC: O(n^3) SC: O(no. of triplets) +O(n) -- TLE!!
    //Coz, n^3 complexity can only run for an input of size upto
    //400. here input arr size is upto 3k
    public List<List<Integer>> threeSumBrute(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> probableTriplets = new ArrayList<>();
                        probableTriplets.add(nums[i]);
                        probableTriplets.add(nums[j]);
                        probableTriplets.add(nums[k]);

                        //this is just constant time, coz only 3 elems sorted.
                        Collections.sort(probableTriplets);

                        if (! set.contains(probableTriplets)) {
                            triplets.add(probableTriplets);
                            set.add(probableTriplets);
                        }
                    }
                }
            }
        }
        return triplets;
    }
}

package striversSheet.medium;

import java.util.*;

public class arrays_majorityElem2 {


    //BETTER - Boyer Moore Voting Algo
    public List<Integer> majorityElementOptimised(int[] nums) {
        /*
            Why and how it'll work?
            Coz, if voting should be > n/3, so we can only have
            2 such elements in the list which are in majority
            So, Keep to voting counters and 2 Candidates.
            And do Voting algo as usual
         */

        int vote1 = 0, vote2 = 0, candidate1 = -1, candidate2 = -1;

        for (int n : nums) {
            if (vote1 == 0 && candidate2 != n){
                candidate1 = n;
                vote1 = 1;
            }
            else if (vote2 == 0 && candidate1 != n){
                candidate2 = n;
                vote2 = 1;
            }
            else if (candidate1 == n) {
                vote1++;
                //NOTE: THIS IS NOT TO BE DONE. 1 candidate cant cut votes of other candidate
//                vote2--;
            }
            else if (candidate2 == n) {
//                vote1--;
                vote2++;
            }
            else {
                vote1--;
                vote2--;
            }

        }

        //put only that candidate which is actually in majority
        List<Integer> ansMajCan = new ArrayList<>();
        //So we need to iterate and check whether above candidates are really MAJORITY
        //coz it isn't said both of them will be majority
        vote1 = 0; vote2 = 0;
        for (int n : nums) {
            if (n == candidate1)
                vote1++;
            else if (n == candidate2)
                vote2++;
        }

        if (vote1 > nums.length/3)
            ansMajCan.add(candidate1);

        if (vote2 > nums.length/3)
            ansMajCan.add(candidate2);


        return ansMajCan;
    }

    //O(2n), O(n) - HashMap
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> majElemList = new ArrayList<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(map.get(entry.getKey()) > nums.length/3)
                majElemList.add(entry.getKey());
        }

        return majElemList;
    }
}

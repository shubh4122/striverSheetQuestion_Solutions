package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;

//https://leetcode.com/problems/combination-sum-ii/description/
public class recursion_combinationSum2 {
    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;

        Arrays.sort(candidates);
        /*
            IMP NOTE: ans being static, only 1 copy is made of it. so in LEETCODE,
            when i use this, all test cases answer are added to ans, one after other.

            To prevent this, initialize the ans list every time at the start of prog, for every test case!

         */
        //either declare ans here itself! and pass in fun param
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();//IMP
        Arrays.sort(candidates);
        combSum2(candidates, new ArrayList<>(), target, 0, ans);
        System.out.println(ans);
    }


    //M2:  Standard Backtracking Template - STRIVER CODE.
    public static void combSum2Striver(int[] candidates, ArrayList<Integer> list, int target, int index, ArrayList<ArrayList<Integer>> ans){
        if (target == 0){
            ans.add(new ArrayList<>(list));
            return;
        }

        //Backtracking template
        for (int i = index; i < candidates.length; i++) {
            if (i!=index && candidates[i] == candidates[i-1])
                continue;

            if (target-candidates[i] >= 0){
                list.add(candidates[i]);
                combSum2Striver(candidates, list, target - candidates[i], i+1, ans);
                list.remove(list.size() - 1);
            }
        }
    }


    //M1:  T/NT method. with WHILE loop for avoiding repetition
    public static void combSum2(int[] candidates, ArrayList<Integer> list, int target, int i, ArrayList<ArrayList<Integer>> ans) {
        //BC
        if (target == 0) {
            //TC: O(k) assuming k to be avg subset len
            ans.add(new ArrayList<>(list));
            return;
        }
        if (i == candidates.length) {//i is len of candidates. i = len means arr finished
            return;
        }


        //Choice Diag - same recursive solution as Unbounded KS, DP not used tho.
        if (target - candidates[i] >= 0) {
            //take
            list.add(candidates[i]);
            combSum2(candidates, list, target - candidates[i], i+1, ans); //len changes to i+1, as limited supply
            list.remove(list.size() - 1);

//            while (i!= candidates.length-1 && candidates[i] == candidates[i+1])
//                i++;
//
//            //not take
//            combSum2(candidates, list, target, i+1, ans);
        }
//        else {

            while (i!= candidates.length-1 && candidates[i] == candidates[i+1])
                i++;

            //not take
            combSum2(candidates, list, target, i+1, ans);
//        }
    }
}

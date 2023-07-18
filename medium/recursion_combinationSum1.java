package striversSheet.medium;

import java.util.ArrayList;

//https://leetcode.com/problems/combination-sum/description/
public class recursion_combinationSum1 {
    static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    //always use NON static global var in Online compiler[multiple test cases]

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,5};//{2,3,6,7};
        int target = 8;//7;

        /*
            IMP NOTE: ans being static, only 1 copy is made of it. so in LEETCODE,
            when i use this, all test cases answer are added to ans, one after other.

            To prevent this, initialize the ans list every time at the start of prog, for every test case!

         */
        //either declare ans here itself! and pass in fun param
        ans = new ArrayList<>();//IMP
//        combSum(candidates, new ArrayList<>(), target, candidates.length);
        combSum(candidates, new ArrayList<>(), target, 0);
        System.out.println(ans);
    }


    //M2:  Just extending the Backtracking template.
    public static void combSumBACKTRACKINGtemplate(int[] candidates, ArrayList<Integer> list, int target, int index) {
        if (target == 0){
            ans.add(new ArrayList<>(list));
            return;
        }

        //Backtracking template
        for (int i = index; i < candidates.length; i++) {
            //same as combination sum2, just that SKIPPING condition is removed!!
            if (target-candidates[i] >= 0){
                list.add(candidates[i]);
                combSum(candidates, list, target - candidates[i], i);
                list.remove(list.size() - 1);
            }
        }
    }

    //M1: Intuitive Take/NotTake Method
    /*
        TC: O(2^target . k)   SC: O(k.x) hypothetically. x = no. of combinations. which we cant tell, hence hypo.

        Why not (2^n) but (2^t) (where n is the size of an array)?
        Assume that there is 1 and the target you want to reach is 10 so 10 times you can “pick or not pick” an element.
     */
    public static void combSum(int[] candidates, ArrayList<Integer> opCombn, int target, int i) {
        //Unbounded Knapsack, ques. But DP not needed!!

        //BC
        if (target == 0) {
            //TC: O(k) assuming k to be avg subset len
            ans.add(new ArrayList<>(opCombn));
            return;
        }
        if (i == candidates.length) {//i is len of candidates. i = 0 means arr finished
            return;
        }

        //Choice Diag - same recursive solution as Unbounded KS, DP not used tho.
        if (target - candidates[i] >= 0) {
            //take
            opCombn.add(candidates[i]);
            combSum(candidates, opCombn, target - candidates[i], i); //len remains i, as unlimited supply
            opCombn.remove(opCombn.size() - 1);

            //not take
            combSum(candidates, opCombn, target, i+1);
        }
        else {
            //not take
            combSum(candidates, opCombn, target, i+1);
        }
    }
}

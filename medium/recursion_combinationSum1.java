package striversSheet.medium;

import java.util.ArrayList;

public class recursion_combinationSum1 {
    static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

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
        combSum(candidates, new ArrayList<>(), target, candidates.length);
        System.out.println(ans);
    }

//    TC: O(2^target . k)   SC: O(k.x) hypothetically. x = no. of combinations. which we cant tell, hence hypo.
    public static void combSum(int[] candidates, ArrayList<Integer> opCombn, int target, int i) {
        //Unbounded Knapsack, ques. But DP not needed!!

        //BC
        if (target == 0) {
            //TC: O(k) assuming k to be avg subset len
            ans.add(new ArrayList<>(opCombn));
            return;
        }
        if (i == 0) {//i is len of candidates. i = 0 means arr finished
            return;
        }

        //Choice Diag - same recursive solution as Unbounded KS, DP not used tho.
        if (candidates[i-1] <= target) {
            //take
            opCombn.add(candidates[i-1]);
            combSum(candidates, opCombn, target - candidates[i-1], i); //len remains i, as unlimited supply
            opCombn.remove(opCombn.size() - 1);

            //not take
            combSum(candidates, opCombn, target, i - 1);
        }
        else {
            //not take
            combSum(candidates, opCombn, target, i - 1);
        }
    }
}

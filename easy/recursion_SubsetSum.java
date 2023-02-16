package striversSheet.easy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
public class recursion_SubsetSum {

    public static void main(String[] args) {
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> ip = new ArrayList<>();

        ip.add(5);
        ip.add(2);
        ip.add(1);

        //called to make changes in ans
        subsetSum(ip, 0, 0, ans);
        //changes made to ans
        System.out.println(ans);
    }

    //TC : O(2^n), SC : O(2^n) - (coz for each elem, we have 2 options, take or not. hence by permutation - 2^n)

    //To understand, might see /DSA/Recursion.java/subsetArr()  Steps fully explained there.
    public static void subsetSum(ArrayList<Integer> ip, int index, int opSum, ArrayList<Integer> ans) {
        //Ip/Op method. - Recursive tree
        //BC
        if (index == ip.size()) {//that pointer reached end. input list finished
            ans.add(opSum);
            return;
        }

        //not take
        subsetSum(ip, index + 1, opSum, ans);

        //take
        subsetSum(ip, index + 1, opSum + ip.get(index), ans);
    }
}

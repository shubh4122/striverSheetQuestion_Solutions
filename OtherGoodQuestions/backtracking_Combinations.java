package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/combinations/
public class backtracking_Combinations {

    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        makeCombinations(n, k, 1);
        return ans;
    }

    public void makeCombinations(int n, int k, int idx){
        if (k==0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        //we dont have to put a condition to restrict IDX to not exceed n.
        //COZ in the for loop we already put (i<=n) which restricts it.

        /*
            VV imp: instead of i<=n we must use i<=n-k+1!! It beats 100% solutions
            while the earlier was much slower.

            Reason: suppose, n=4, k=2
                    Now we dont have to take 4 as the first elem of list. coz no
                    combination would begin with 4 as its the LIMIT. eg [4,x] cant be there
                    so, 4-2+1=3 which is the LAST elem a loop must run to, for keeping
                    it as the first elem in the list.

                    Then, when k =1, which means 1 elem is already there in the list
                    So, now we can let our FOR loop run to n, i.e. 4 coz, the second
                    elem can obviously be 4.eg: [1,4] [2,4] [3,4]
         */
        for (int i = idx; i <= n-k+1; i++) {//NOTE THESE optimisations!! Found on LC
            list.add(i);//choose
            makeCombinations(n, k-1, i+1);//explore
            list.remove(list.size()-1);//abandon
        }
    }
}

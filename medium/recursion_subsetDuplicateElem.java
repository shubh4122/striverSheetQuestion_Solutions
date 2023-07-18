package striversSheet.medium;

import java.util.*;

/**
 *  BACKTRACKING vs RECURSION:
 *      Recursion -> It has calls to self, and for a smaller input
 *      BT -> Uses recursion,
 *              Builds one solution  [EG:  list.add(....)]
 *              Then REMOVES/UNDO the choice   [EG: list.remove(...)]
 *
 *      This is characteristic of BT!!
 */


//https://leetcode.com/problems/subsets-ii/description/
public class recursion_subsetDuplicateElem {

    public static void main(String[] args) {

        HashSet<ArrayList<Integer>> uniqAns = new HashSet<>();
        int[] nums = new int[]{1,3,2,2};//{4,1,0};//{4,4,4,1,4};


//        subsetDup(nums, 0, new ArrayList<Integer>(), uniqAns);
        //hset populated
        //IMP - copy any collection to arraylist
//        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(uniqAns);



        //optimised
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        subsetDupOptimised(nums, 0, ans, new ArrayList<>());
        System.out.println(ans);
    }


    //M3: Optimised BRUTE, Used WHILE to skip repetitive INDICES for NT.
    /*
        BEST - Because code is Similar to our usual, take/not take.
        Though same for complexity in M2.[BACKTRACKING]
        This is also BACKTRACKING.

        REASON for skipping in NT.
        Moreover, we skip NOT TAKE of repetitive elem because,
        eg: [1,2,2*]
        If we skip (2), That is we are in NT branch of the tree for (2). We now have choice to either
        Take (2*) or NT (2*). If we suppose take it, the new subset formed is: [1, 2*]
        WHICH is same as the subset of [1,2] formed when we TAKE first occuring 2.
     */
    public static void subsetDupBEST(int[] arr, int index, List<List<Integer>> ans, List<Integer> list) {
        //BC
        if (index == arr.length) {
            ans.add(new ArrayList<>(list));//deep copy - O(n) TC
            return;
        }

        //take - take of all elem(even repetitive
        list.add(arr[index]);
        subsetDupBEST(arr, index+1, ans, list);
        list.remove(list.size() - 1);

        //SKIP all similar, consecutive elem for NOT TAKE call - REASON in copy and in ABOVE COMMENT before method
        while (index != arr.length-1 && arr[index] == arr[index+1])
            index++;

        /*
            Not take - NT only for the last of the repetitive consecutive elem
            i.e. [1,2,2*,3]
            we do take for all, BUT, NT for only indices[0,2,3] i.e.[1,2*,3]
         */
        //NT
        subsetDupBEST(arr, index+1, ans, list);
    }


    //M2: -----------------------BACKTRACKING TEMPLATE as on LC[STRIVER method]-----------------------
    /*
        better, but TAKES MUCH space.
        arr -> sorted!! - so that duplicate elem occur only  at consecutive index
        Hypothesis: fun(arr, 0) puts subsets from 0 to end in list.[empty list for now]
                    fun(Arr, 3) puts subsets from 3 to end in list.[list has elem from prev
                                                                 recursive call]
     */
    public static void subsetDupOptimised(int[] arr, int index, List<List<Integer>> ans, List<Integer> list) {
        //add list to ans, all the time. each call has a subset ready.
        ans.add(new ArrayList<>(list));

        //BC is index == arr.len, but it is handled by FOR condition. so no need

        /*
            Traverse every other index after idx, i.e. from idx+1 to end.
            Every Iteration covers each elem, but list remains of same size
            Only a Recursive calls increases list by +1 size. i.e. adds an elem to it.
         */
        for (int i = index; i < arr.length; i++) {
            /*
                i.e. in a given array[limits -> index to end]
                we dont want repetitive elems.
                So we handle the consecutive repetitive elements here.
                Also, the first elem, i.e. i = index, need not be checked
                coz, in the given limit it is the first elem, and will obviously
                not have any previous elem.[as it is the starting of the limit]
             */
            if (i!=index && arr[i] == arr[i-1])
                continue;

            list.add(arr[i]);
            /*
                means, ab list me arr[i] and usse pehle ka sab to rahega,
                uske baad ke elem daalo, and make possible subsets!
                subsets from i+1 to end.

                i.e. list[e1, e2....ei] -> RECURSIVE CALL -> list[e1,...,ei, + subsetsOfArray(i+1 to end)]
             */
            subsetDupOptimised(arr, i+1, ans, list);
            /*
                Why remove?
                look at tree of recursive calls.
                At one level, we replace each elem, with next one in arr
                so size doesnt increase.
                Hence, when, 1st level[0 indexed]:
                arr[1,2,4,2]
                list.add(arr[0]) -> list[1] -> list.remove(i) -> list[]
                list.add(arr[1]) -> list[2] -> list.remove(i) -> list[]
                list.add(arr[3]) -> list[4] -> list.remove(i) -> list[]
                 arr[4] repeated hence not done

                But if we dont remove and only add, result -> list[1,2,4] which is wrong
                as per our tree structure.

             */
            list.remove(i);
        }

    }



    //M1: BRUTE
    /*
        Hypothesis:
        Basically, fun(ip, i) -> take/notTake ith element in subset
        fun(ip, i) -> gives us Subsets[unique] from the starting to index 'i'. ie. from """""[0 to i]"""""
     */
    public static void subsetDup(int[] ip, int index,  ArrayList<Integer> op, HashSet<ArrayList<Integer>> uniqSubsets) {
        //BC
        if (index == ip.length){
            //complete ip has been utilized
            //Sort op so that [1,2] and [2,1] dont pose as 2 different arrays
            //VVV IMP NOTE ⬇️
            /*
                NOTE:
                DONT EVER CHANGE AN ARR/LIST IN RECURSION.
                IF I JUST SORT THE ORIGINAL OP LIST, IT CAUSES SO MANY DISTORTIONS IN OUTPUT FOR CASE {4,1,0}

                HENCE CREATED ANOTHER LIST, AND SORTED IT AND STORED IT.!!!!!!!!!!!!!!!!!!!!!!!
                This causes issue coz we remove last elem of list. and coz when we sort, last elem changed
             */
            ArrayList<Integer> ___neverEverChangeListInRecursion___ = new ArrayList<>(op);
            Collections.sort(___neverEverChangeListInRecursion___);
            uniqSubsets.add(new ArrayList<>(___neverEverChangeListInRecursion___)); // VVVVV IMP. to add an arraylist to a COLLECTION, use only this.
            return;
        }

        //take
        op.add(ip[index]);
        subsetDup(ip, index+1, op, uniqSubsets);
        op.remove(op.size() - 1);//remove the elem i inserted, to UNDO CHANGE IN LIST

        //not take
        subsetDup(ip, index+1, op, uniqSubsets);
    }
}

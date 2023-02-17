package striversSheet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 *      Understand the optimised  code, especially what does WHILE loop do??
 *      Resources :
 *      https://leetcode.com/problems/subsets-ii/solutions/169226/java-two-way-of-recursive-thinking/?orderBy=most_votes
 *
 *      really good video explanation
 *      https://www.youtube.com/watch?v=Vn2v6ajA7U0
 */




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
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        subsetOptimised(nums, 0, new ArrayList<>(), ans);
        System.out.println(ans);
    }


    //Optimised, Here we dont generate the duplicate subsets in the first place
    public static void subsetOptimised(int[] ip, int index, ArrayList<Integer> op, ArrayList<ArrayList<Integer>> ans) {
        if (index == ip.length) {
            //doing DEEP COPY!!
            ans.add(new ArrayList<>(op));
            return;
        }

        //Take
        op.add(ip[index]);
        subsetOptimised(ip, index+1, op, ans);
        op.remove(op.size() - 1);

        //This prevents from creating duplicate subsets
        while (index+1 < ip.length && ip[index] == ip[index+1])
            index++;

        //not take
        subsetOptimised(ip, index+1, op, ans);
    }


    // DEBUG COPY OF ABOVE CODE> TO UNDERSTAND WORKING OF WHILE LOOP -- DO TRY UNDERSTANDING1!!!!!

    //Optimised, Here we dont generate the duplicate subsets in the first place
//    public static void subsetOptimised(int[] ip, int index, ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> subsetsALl) {
//        if (index == ip.length) {
//            //doing DEEP COPY!!
//            subsetsALl.add(new ArrayList<>(subset));
//                                    System.out.println("-----BC HIT -----");
//                                    System.out.println(subsetsALl);
//                                    System.out.println("----Returning-----");
//            return;
//        }
//
//        //Take
//        subset.add(ip[index]);
//                                    System.out.println("----------------------TAKE CALL------------------------");
//                                    System.out.println("Curr index " + index);
//                                    System.out.println("Adding curr (index) elem to subset -> "+subset);
//
//        subsetOptimised(ip, index+1, subset, subsetsALl);
//        subset.remove(subset.size() - 1);
//
//                                    System.out.println("subset after take call -> "+subset);
//                                    System.out.println("Removing curr (index) elem to subset -> "+subset);
//
//
//                                    System.out.println("Index " + index);
//        //This prevents from creating duplicate subsets
//        while (index+1 < ip.length && ip[index] == ip[index+1])
//            index++;
//
//
//                                        System.out.println("Index after while loop :" + index);
//
//        //not take
//
//                                    System.out.println("---------------------- NOT TAKE CALL------------------------");
//        subsetOptimised(ip, index+1, subset, subsetsALl);
//
//                                     System.out.println("subset after not take call -> "+subset);
//    }


    public static void subsetDup(int[] ip, int index, ArrayList<Integer> op, HashSet<ArrayList<Integer>> uniqSubsets) {
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
             */
            ArrayList<Integer> ___neverEverChangeListInRecursion___ = new ArrayList<>(op);
            Collections.sort(___neverEverChangeListInRecursion___);
            uniqSubsets.add(new ArrayList<>(___neverEverChangeListInRecursion___)); // VVVVV IMP. to add an arraylist to a COLLECTION, use only this.
            return;
        }

        //not take
        subsetDup(ip, index+1, op, uniqSubsets);

        //take
        op.add(ip[index]);
        subsetDup(ip, index+1, op, uniqSubsets);
        op.remove(op.size() - 1);//remove the elem i inserted, to UNDO CHANGE IN LIST
    }
}

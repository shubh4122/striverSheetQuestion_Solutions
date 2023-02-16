package striversSheet.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class recursion_subsetDuplicateElem {

    public static void main(String[] args) {

        HashSet<ArrayList<Integer>> uniqAns = new HashSet<>();
        int[] nums = new int[]{4,1,0};//{4,4,4,1,4};


        subsetDup(nums, 0, new ArrayList<Integer>(), uniqAns);
        //hset populated

        //IMP - copy any collection to arraylist
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(uniqAns);
        System.out.println(ans);
    }

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

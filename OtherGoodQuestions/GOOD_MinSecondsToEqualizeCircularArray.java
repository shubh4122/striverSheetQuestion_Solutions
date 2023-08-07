package striversSheet.OtherGoodQuestions;

import java.util.*;

//https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array/description/
//https://youtu.be/RFaCL68Zt74?t=1203
public class GOOD_MinSecondsToEqualizeCircularArray {

    /*
        This is a proper CP type question, No big concept use, just the IMPLEMENTATION there
        NOTE:
        Finding maximum[GREEDY] and replacing arr elem with it WONT WORK!!
     */
    public int minimumSeconds(List<Integer> nums) {
        //Step1. Store list of indices where an elem occurs in nums
        HashMap<Integer, List<Integer>> occurenceMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int elem = nums.get(i);
            List<Integer> temp = occurenceMap.getOrDefault(elem, new ArrayList<>());
            temp.add(i);
            occurenceMap.put(elem, temp);
        }
        
        //Step2. Iterate over all elem in maps[coz elem uniquely stored here, so kinda efficient], 
        //       and calc min time taken by an elem to Equalize
        int minTime = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : occurenceMap.entrySet()){
            int totalTime = getTotalTime(nums, entry);

            //To find the least total time taken by an elem in nums to equalize it.
            minTime = Math.min(minTime, totalTime);
        }
        return minTime;
    }

    private static int getTotalTime(List<Integer> nums, Map.Entry<Integer, List<Integer>> entry) {
        List<Integer> idxList = entry.getValue();

        int totalTime = 0, i, j;
        //find maximum time taken by an elem to equalize arr. For loop only finds time to equalize arr
        //between first and last occurence.
        for (int idx = 0; idx < idxList.size()-1; idx++) {
            i = idxList.get(idx);
            j = idxList.get(idx+1);
            totalTime = Math.max(totalTime, (j - i)/2);
        }
        //To find time taken to equalize arr beyond last occurence and before 1st occurence:
        i = idxList.get(0);//first - 0
        j = nums.size() - idxList.get(idxList.size()-1);//sizeOfNums - lastIdxOfOccurence
        totalTime = Math.max(totalTime, (j+i)/2);
        return totalTime;
    }

}

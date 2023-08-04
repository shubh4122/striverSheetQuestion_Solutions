package striversSheet.easy;

import java.util.HashMap;

//https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
public class array_largestSubarrayWith0Sum {

    int maxLen(int arr[], int n){
        HashMap<Integer, Integer> map = new HashMap<>();//{val, index}
        map.put(arr[0], 0);
        int maxSubarrayLen = 0;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1]+arr[i];//prefix sum

            if (arr[i] != 0 && map.containsKey(arr[i])){
                //dont update map with new index, as we want the least index to be there for comparison
                maxSubarrayLen = Math.max(maxSubarrayLen, Math.abs(map.get(arr[i]) - i));
            }
            else//update it when arr[i] is either 0, or not in map already
                map.put(arr[i], i);
        }
        maxSubarrayLen = Math.max(maxSubarrayLen, map.getOrDefault(0, -1)+1);
        return maxSubarrayLen;
    }
}

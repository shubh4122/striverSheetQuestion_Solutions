package striversSheet.medium;

import java.util.*;

//https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/
public class arrays_subarrayWithGivenXOR {
    public static void main(String[] args) {
        int[] arr = {1,2,2,3};//{5, 6, 7, 8, 9};//{4,2,2,6,4};
        int b = 0;//5;//6;
        int xor = 0;
        ArrayList<Integer> a = new ArrayList<>();

        for (int n : arr) {
            a.add(n);
        }
        System.out.println(countSubArrWithGivenXorOPTIMISED(a, b));

    }

    //O(n) TC, O(n) SC - HashMap
    public static int countSubArrWithGivenXorOPTIMISED(ArrayList<Integer> a, int b) {
        int count = 0, currentXor = 0, reqXor = 0;
        HashMap<Integer, Integer> map = new HashMap<>();//key = currXor, val = num of times the currXor value occurs in prefix Xor

        for (int i = 0; i < a.size(); i++) {
            //storing prefix xor in hashset, while we proceed
            currentXor ^= a.get(i);
            reqXor = b ^ currentXor;

            if (reqXor == 0) // currentXOR = b
                count++;
            if (map.containsKey(reqXor))
                count += map.get(reqXor);

            //add currentXor to hashmap at last, after comparision, so that if b = 0
            //It doesnt count itself as reqXor. eg- if b = 0, 0^1 = 1. so if we enter the currentxor at starting, then it will count itself as reqXor
            map.put(currentXor, map.getOrDefault(currentXor, 0)+1);

        }
        return count;
    }

    //O(n^2) -- TLE!
    public int countSubarrWithGivenXor(ArrayList<Integer> a, int b) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            int xor = 0;
            for (int j = i; j < a.size(); j++) {
                xor ^= a.get(j);
                if (xor == b)
                    count++;
            }
        }
        return count;
    }
}

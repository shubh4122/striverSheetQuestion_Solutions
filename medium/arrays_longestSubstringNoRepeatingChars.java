package striversSheet.medium;

import java.util.HashMap;
import java.util.HashSet;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class arrays_longestSubstringNoRepeatingChars {



    //M2: Optimised Sliding Window, HashMAP
    //TC: O(n), SC: O(n)
    public int lengthOfLongestSubstringOptimised(String s) {
        if (s.length() == 0)
            return 0;

        //Map will store the index where an elem was last seen
        HashMap<Character, Integer> map = new HashMap<>();

        int start = 0, end = 0;
        int maxLen = Integer.MIN_VALUE;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c))
                start = Math.max(start, map.get(c) + 1);
            //above is done coz, if the repeating elem lies outside window
            //then we need not change start. i.e. if start > map.get(c) +1
            //then let start be as it is

            //Update the new occurrence of this elem
            map.put(c, end);
            //update len max
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }
        return maxLen;
    }

    //M1: Sliding Window with Set
    //TC: O(2n), SC: O(n)
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> h = new HashSet<>();

        int longest = 0;
        int tempLen = 0;

        int start = 0;
        int end = 0;
        //It is kind of a SLIDING WINDOW question. So what we do here is,
        //the point where we get repeated elem, we stop the end pointer
        //and move the start pointer until we get NON - Repeating elem
        //Once we do, start end pointer again keeping start stationary.

        while (end < s.length()) {
            char c = s.charAt(end);
            if (h.contains(c)) {
                h.remove(s.charAt(start));
                start++;
                tempLen--;
            } else {
                h.add(c);
                tempLen++;
                end++;
                //coz, if all are unique, and whole str is substr,
                //then we never enter if. So update here only
                longest = Math.max(longest, tempLen);
            }
        }
        return longest;
    }
}

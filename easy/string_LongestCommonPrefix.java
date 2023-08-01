package striversSheet.easy;

import java.util.Arrays;
import java.util.HashSet;

//https://leetcode.com/problems/longest-common-prefix/
public class string_LongestCommonPrefix {



    //M2: Sort the arr, Then the strings at [0] and [str.len-1] will be MOST DIFFERENT
    //If these both have anything in common, means all the str between them also
    //have it in common.
    //WORST TC: nLogn + Min(len[0], len[strLen-1])
    public String longestCommonPrefixBETTER(String[] strs){
        Arrays.sort(strs);
        //now the arr contains 2 MOST different strings at the ENDS
        //So just check them

        int minLen = Math.min(strs[0].length(), strs[strs.length-1].length());
        StringBuilder lcp = new StringBuilder();
        for (int i = 0; i < minLen; i++) {
            char c1 = strs[0].charAt(i);
            char c2 = strs[strs.length-1].charAt(i);

            if (c1==c2)
                lcp.append(c1);
            else break;
        }
        return lcp.toString();
    }


    //M1 : Using direct comparison, Worst TC : n*k -> len of arr * len of each STR
    public String longestCommonPrefixBRUTE(String[] strs) {
        //Step 1: take any 2 strings and find their common prefix
        //Step 2: compare each str in list with the COMMON PREFIX
        //        If its common with others too, keep it. Else find their common prefix

        if (strs.length==1)
            return strs[0];
        
        String initialCommonPrefix = findCommonPrefix(strs[0], strs[1]);

        for (int i = 2; i < strs.length; i++) {
            if (initialCommonPrefix.isEmpty())
                break;

            initialCommonPrefix = findCommonPrefix(initialCommonPrefix, strs[i]);
        }
        return initialCommonPrefix;
    }

    public String findCommonPrefix(String s1, String s2) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                s.append(s1.charAt(i));
            else
                break;
        }
        return s.toString();
    }
}

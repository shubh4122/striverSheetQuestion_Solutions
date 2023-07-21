package striversSheet.medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
/*
    https://youtu.be/WBgsABoClE0
    Great video explanation for the BACKTRACKING solution!!
 */
public class recursion_PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        makePartitions(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void makePartitions(String s, int idx, ArrayList<String> list, List<List<String>> ans) {
        if (idx == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            String temp = s.substring(idx, i+1);
            if (isPalindrome(temp)){
                list.add(temp);
                makePartitions(s, i+1, list, ans);
                list.remove(list.size()-1);
            }
        }
    }


    public boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}

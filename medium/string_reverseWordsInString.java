package striversSheet.medium;

import java.util.Stack;

public class string_reverseWordsInString {

    //Striver has another Optimised solution. May see
    //https://takeuforward.org/data-structure/reverse-words-in-a-string/

    public String reverseWordsOptimised(String s) {
        StringBuilder ans = new StringBuilder();
        s = " " + s.trim();
        int wordLen = 0;

        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) == ' ' && wordLen > 0) {
                ans.append(s, i + 1, i + wordLen + 1).append(" ");
                wordLen = 0;
            }
            else if (s.charAt(i) != ' ')   wordLen++;
        }

        return ans.toString().trim();
    }

    public String reverseWordsBruteForce(String s) {
        //O(n) Space,  O(n) Time
        Stack<String> reversedStr = new Stack<>();
        String word = "";
        s += " ";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                word += s.charAt(i);
            else {
                if (!word.isBlank()) {
                    reversedStr.push(word);
                    word = "";
                }
            }
        }

        StringBuilder str = new StringBuilder();
        while (!reversedStr.isEmpty())
            str.append(reversedStr.pop() + " ");

        return str.toString().trim();
    }
}

package striversSheet.OtherGoodQuestions;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/solutions/3855837/easy-intuitive-java-backtracking-solution/
//My post ^
public class backtracking_LetterCombinationsPhoneNum {
    //Interesting Question

    public static void main(String[] args) {
        String digits = "279";
        backtracking_LetterCombinationsPhoneNum ob = new backtracking_LetterCombinationsPhoneNum();
        System.out.println(ob.letterCombinations(digits));

    }


    //Iterative -> BFS -> Using Queue
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/solutions/8090/iterative-c-solution-in-0ms/

    // Recursive soln, DFS
    List<String> ans;
    StringBuilder s;
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        s = new StringBuilder();

        if (digits.isEmpty())
            return ans;

        String[] map = {null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //0 and 1 are null coz they dont map to any string sequence.

        makeCombinations(digits, map);

        return ans;
    }

    private void makeCombinations(String digits, String[] map) {
        if (digits.isEmpty()){
            ans.add(s.toString());
            return;
        }

        String mappedStr = map[digits.charAt(0)-'0'];//Remember, in Notion has a note about how to convert char to dig. this is from there
        for (int i = 0; i < mappedStr.length(); i++) {
            s.append(mappedStr.charAt(i));//choose
            makeCombinations(digits.substring(1), map);//explore
            s.deleteCharAt(s.length()-1);//abandon
        }
    }
}

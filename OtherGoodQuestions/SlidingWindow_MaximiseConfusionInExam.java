package striversSheet.OtherGoodQuestions;

//https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
public class SlidingWindow_MaximiseConfusionInExam {

    /*
    *   General Approach of such SLIDING WINDOW PROBLEM :-
    *   Resource: https://youtu.be/HI9A-pvwIQ0
    *
    *   0. (IMP)If we have Question like this, where we just need the LONGEST consecutive string of same chars,
    *      irrespective of the fact what that char is. (i.e here it can either be the Longest sequence of F or T)
    *      Then, run the Code for both F and T. And Return the MAX of them
    *
    *   1. INITIALIZE WINDOW : Have 2 pointers, `start` and `end` -> indicating start and end of the window
    *   2. INCREASE WINDOW SIZE : Till, our condition is TRUE, [i.e. like here we keep getting our desired character(T when MainChar = T)]
    *      we move end++ -> i.e. keep increasing window size
    *   3. DECREASE WINDOW SIZE : Now at a point when the curr char is not our Desired char. Do start++.
    *       BUT until when?  -> suppose we have `k` as the limit of undesired chars that can happen in our sequence
    *       So, At a Point when counUnDesired > k,
    *           - Start to DECREASE win size by doing start++
    *           - Whenever find unDESIRED elem, do count--;
    *           - Then when count==k, STOP DECREASING size, and store current window size.
    * */
    public static void main(String[] args) {
        SlidingWindow_MaximiseConfusionInExam s = new SlidingWindow_MaximiseConfusionInExam();
        System.out.println(s.maxConsecutiveAnswers("TFFT", 1));
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int a = convert(answerKey, k, 'T');
        int b = convert(answerKey, k, 'F');
        return Math.max(a, b);
    }

    public int convert(String s, int k, char mainChar) {
        int start = 0, end = 0, maxConsecutive = 0, countVIOLATION = 0;
        while (end < s.length()){
            //Step 1: Increase window size
            if (s.charAt(end) == mainChar)
                end++;
            else {
                countVIOLATION++;
                end++;
            }

            //Step 2.1: Check for VIOLATIONS to be <= k, if not, DECREASE WINDOW SIZE
            //Check if the violation is less than equal to k
            //Step 2.2 : Until, VIOLATIONS are again under k(violations <= k)
            while (countVIOLATION > k) {
                if (s.charAt(start)!=mainChar)
                    countVIOLATION--;

                start++;//decreasing window size.
            }

            //Save Max win size only when, violations are under the LIMIT(k)
            maxConsecutive = Math.max(maxConsecutive, end - start);
        }
        return maxConsecutive;
    }

}

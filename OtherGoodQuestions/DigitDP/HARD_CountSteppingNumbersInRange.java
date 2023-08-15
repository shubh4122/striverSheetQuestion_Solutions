package striversSheet.OtherGoodQuestions.DigitDP;

import java.util.Arrays;

//https://leetcode.com/problems/count-stepping-numbers-in-range/description/
public class HARD_CountSteppingNumbersInRange {

    public static void main(String[] args) {
        String n = "90";
        String m = "101";
        HARD_CountSteppingNumbersInRange ob = new HARD_CountSteppingNumbersInRange();
        System.out.println(ob.countSteppingNumbers(n, m));
    }

    long[][][][] dp;
    int mod = (int)(1e9+7);
    public int countSteppingNumbers(String low, String high) {
        /*
            Step1: Initialize DP
            Step2: Find ans for (0 to low-1)[=ans1]
                But doing low-1 is messy as it LOW is a string
                So, instead do: (0 to low)
            Step3: Check if "low" is a STEPPING NUMBER
            Step4: RESET DP, then Find ans for (0 to high)[=ans2]
            Step5: return ans2-ans1+1(if Step3 is TRUE, i.e. low is Stepping)
                   else, return ans2-ans1
         */

//Step1:
        dp = new long[101][2][11][2];//why prevDig = 11 -> coz count -1 too. NOTE all sizes and why
        resetDP(dp);

//Step2
        long ans1 = countSteppingNum(0, 1, -1, 1,  low)%mod;

//Step3
        int isLowSteppingNumber = 1;
        for (int i = 1; i < low.length(); i++) {
            if (Math.abs(low.charAt(i-1)-low.charAt(i))!=1) {
                isLowSteppingNumber = 0;
                break;
            }
        }

//Step4 - Reason note
        resetDP(dp);
        long ans2 = countSteppingNum(0, 1, -1, 1, high)%mod;

//Step5 - VVVV IIMPP! we do a +mod here
        return (int)(ans2-ans1 + mod + isLowSteppingNumber)%mod;
        /*
            res+mod is done, so we dont get a NEGATIVE answer after res%mod.
            This is a technique to get answer in POSITIVE range
         */
    }


    /*
        Steps for recursive function
        Step1: BC
        Step2: Memo
        Step3: Find Range of digits based on Tight. (k)
        Step4: Initialize the count variable to store the count of Stepping numbers
        Step5: For(0 to k) Check for stepping number. Here all combinations are made
            > calc newTight
            > Keep count and update
            > New tight: Refer NOTION for notes on this.
            > isZeroFurther -> see if the currentDigit is 0, then it MIGHT be true.
                            For surity, check isZero. As it might happen we have some non-zero dig before currentDig
                            This info is stored by isZero. so, if this is the case, isZero will be FALSE.
                            hence for all further calls, isZero will always be false.

                            But if we had all 0s before this digit and this dig also = 0, then isZero is TRUE, and hence isZF also TRUE

     */

    private long countSteppingNum(int idx, int tight, int prevDigit, int isLeadingZeroes, String num) {
        //Step1
        if (idx == num.length()) {
            if(isLeadingZeroes==1)
                return 0;
            return 1;//coz if we reached end, means all its digits have been following STEPPING condition
        }

        //Step2 ->NOTE COORDINATE change
        if(dp[idx][tight][prevDigit+1][isLeadingZeroes] != -1)
            return dp[idx][tight][prevDigit+1][isLeadingZeroes];

        //Step3 and Step4
        int k = (tight==1) ? num.charAt(idx)-'0': 9;//k is the LIMIT
        long count = 0;

        //Step5
        for (int currDigit = 0; currDigit <= k; currDigit++) {
            //if (condn) that is, current dig is the BOUNDED dig, so return tight.
            int tightForNext = (num.charAt(idx)-'0'==currDigit) ? tight:0;
            int isLeadingZeroNext = currDigit == 0 ? isLeadingZeroes :0;//Reason above


            if ((isLeadingZeroes == 1)   ||   (Math.abs(prevDigit - currDigit) == 1)) {
                count = (count%mod + countSteppingNum(idx + 1, tightForNext, currDigit, isLeadingZeroNext, num)%mod)%mod;
            }
        }

        return dp[idx][tight][prevDigit+1][isLeadingZeroes] = count;
    }



    private void resetDP(long[][][][] dp) {
        for (long[][][] t1: dp) {
            for (long[][] t2: t1) {
                for (long[] row : t2) {
                    Arrays.fill(row, -1);
                }
            }
        }
    }


}

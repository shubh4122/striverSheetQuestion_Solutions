package striversSheet.medium;

//-------------------------NOT a DP QUES-------------------------
//-------------------------NOT a DP QUES-------------------------
//-------------------------NOT a DP QUES-------------------------
//-------------------------NOT a DP QUES-------------------------

//https://leetcode.com/problems/maximum-product-subarray/description/
public class dp_MaxPdtSubarr {

    //M4: Modified Kadane's Algo -> NOT intuitive and Not recommended in INTERVIEW - Striver

    //M3: Generate all Subarrays and take their product. O(n^3) or O(N^2)

    //M2: M1 with single traversal
    public int maxProduct2(int[] nums) {
        int prefixPdt = 1, suffixPdt = 1, n = nums.length, max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            prefixPdt *= nums[i];
            suffixPdt *= nums[n-i-1];
            max =  Math.max(max,Math.max(prefixPdt, suffixPdt));

            if(prefixPdt == 0)
                prefixPdt = 1;
            if(suffixPdt == 0)
                suffixPdt = 1;
        }
        return max;
    }

    //M1: Kindof prefix and suffix product. Calc them in 2 iterations.
    //TC:O(2n)  SC:O(1)
    public int maxProduct1(int[] nums) {
        //No need to save prefix/suffix PDT. it is useless.
        int pdt = 1, maxFront = Integer.MIN_VALUE, maxBack = Integer.MIN_VALUE;

        for (int n : nums) {
            pdt *= n;
            maxFront = Math.max(maxFront, pdt);

            if (n == 0) {
                //RESET
                pdt = 1;
            }
        }

        pdt = 1;
        for (int i = nums.length-1; i >= 0 ; i--) {
            pdt *= nums[i];
            maxBack = Math.max(maxBack, pdt);

            if (nums[i] == 0)
                pdt = 1;
        }

        return Math.max(maxFront, maxBack);
    }

}

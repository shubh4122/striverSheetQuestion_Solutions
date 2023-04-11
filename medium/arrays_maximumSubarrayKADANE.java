package striversSheet.medium;

//https://leetcode.com/problems/maximum-subarray/description/
public class arrays_maximumSubarrayKADANE {

    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(a));
    }

    //Kadane's algorithm
    // For simply returning max sum. This below code is KADANE algo

    //     public int maxSubArray(int[] arr) {
    //         int maxSum = Integer.MIN_VALUE;
    //         int sum = 0;
    //         int n = arr.length;
    //         for (int i = 0; i < n; i++) {
    //             sum += arr[i];
    //             maxSum = Math.max(maxSum, sum);
    //             if (sum < 0) sum = 0;
    //         }
    //         return maxSum;
    //     }


    //This below algo can also be used to find the subarray which gives max sum
    public static int maxSubArray(int[] nums) {
        int windowStart = 0;
        int windowEnd = 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        while(windowEnd < nums.length){
            if(sum < 0) {// or simply make sum 0 here
//                sum -= nums[windowStart];
//                windowStart++;
                sum = 0;
            }
            else {
                sum += nums[windowEnd];
                maxSum = Math.max(maxSum, sum);
                windowEnd++;
            }
        }

        return maxSum;
    }

}

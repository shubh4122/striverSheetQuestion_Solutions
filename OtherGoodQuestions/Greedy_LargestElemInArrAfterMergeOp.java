package striversSheet.OtherGoodQuestions;

//https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations/
public class Greedy_LargestElemInArrAfterMergeOp {
    /*
        https://youtu.be/c4yhyHtHguA?t=941 - reason
        Why do we approach from right/back

        condn = n[i] <= n[i+1]
        So above condition has to be satisfied.
        But if we move from left/front add keep on adding. I am making the left part[n[i]]
        bigger. which is COUNTER-INTUITIVE as per condition

        We need to make n[i+1] bigger and bigger, so instead start moving from right/back!!

        hence GREEDY
     */
    public long maxArrayValue(int[] nums) {
        long sum = nums[nums.length-1];
        for (int i = nums.length-2; i >= 0 ; i--) {
            if (nums[i] <= sum)
                sum += nums[i];
            else{
                sum = nums[i];
                //This helps in cases like [23,2,5,7,8,3] -> ans is 23. Due to this, we dont need the MAX statement
            }
        }
        return sum;
    }
}
package striversSheet.easy;

public class arrays_maxConsecutive1s {

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, maxCount = Integer.MIN_VALUE;

        for (int n : nums) {
            if (n == 1)
                count++;
            else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}

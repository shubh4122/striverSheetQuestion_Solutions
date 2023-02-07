package striversSheet.medium;

public class binSearch_singleElemInSortedArr {
    public static void main(String[] args) {

    }

    //O(logn) TC - Bin Search --> Intuition: Coz arr is sorted
//                                            and we are supposed to find a val.(thats what called bin search)
//                                Moreover Ques said run in log n time. so it must be obvious
    public static int singleNonDuplicate_BINSEARCH(int[] nums) {
        
    }

    //O(n) TC
    public static int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i+=2) {
            //taking a window of size 2, and elem in that window must be equal
            if (i+1 >= nums.length)
                return nums[i];

            if (nums[i] != nums[i+1]) {
                if (i==0 || (nums[i] != nums[i-1]))
                    return nums[i];
                else if((i+1 == nums.length - 1) || (nums[i+1] != nums[i+2]))
                    return nums[i+1];
            }
        }
        return -1;
    }
}

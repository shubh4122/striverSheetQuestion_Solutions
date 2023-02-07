package striversSheet.medium;

public class binSearch_singleElemInSortedArr {
    public static void main(String[] args) {
        int[] arr = {1,2,2,4,4,5,5};//{3,3,7,7,10,11,11};//{1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate_BINSEARCH(arr));
    }

    //My code. Different from striver.
    //O(logn) TC - Bin Search --> Intuition: Coz arr is sorted
//                                            and we are supposed to find a val.(thats what called bin search)
//                                Moreover Ques said run in log n time. so it must be obvious
    public static int singleNonDuplicate_BINSEARCH(int[] nums) {
        int start = 0, end = nums.length - 1;
        int mid = 0;
        try {
            while (start <= end) {
                mid = start + (end - start) / 2;//optimised way of taking mid. Prevents int overflow

                if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1])
                    return nums[mid];

                if (nums[mid] == nums[mid - 1]) {
                    mid--; // mid should always point to starting elem of pair
                }

                if (mid % 2 == 0) {//we are left part of array, left to single elem
                    start = mid + 2;
                } else {//means we(mid) is in right part of arr, right to single elem
                    end = mid - 1;
                }
            }
        }
        //This executes when single elem lies either at index 0, or at last of arr
        catch (Exception e) {
            return nums[mid];
        }
        //no use, just used coz func needs a return statement at end.
        return -1;
    }


    //O(n) - Bitwise approach
    public static int singleNonDuplicateBitwise(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        return xor;
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

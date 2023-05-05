package striversSheet.easy;

public class arrays_removeDuplicatesFromSortedArr {


    //2 ptr(including k)
    public int removeDuplicates2(int[] nums) {
        int k = 0, ptr2 = 0;

        while (ptr2 < nums.length) {
            nums[k] = nums[ptr2];
            ptr2++;
            k++;

            while (ptr2 < nums.length && nums[ptr2] == nums[ptr2-1]){
                ptr2++;
            }
        }
        return k;
    }

    //2 pointer. + k.
    public int removeDuplicates(int[] nums) {
        int ptr1 = 0, ptr2 = 0;
        int k = 0;

        while (ptr2 < nums.length) {
            nums[ptr1] = nums[ptr2];
            ptr2++;
            ptr1++;
            k++;

            while (ptr2 < nums.length && nums[ptr2] == nums[ptr2-1]){
                ptr2++;
            }
        }
        return k;
    }
}

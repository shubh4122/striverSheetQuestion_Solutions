package striversSheet.medium;

public class array_SORTcolors {

    //O(nlogn), O(n)
    //Actually use Merge Sort.

    //O(2n), O(1)
    //Count all 0, 1,2 and then insert those many count of each in array.

    //O(n), O(1)
    //MOST OPTIMISED : Dutch National Flag Algorithm.
    public void sortColors(int[] nums) {
        /*
            pointers: (indices)
            0----(low-1),(low)----(mid-1),(mid)----(high),(high+1)----(n-1)
            000000000000,1111111111111111,0/1/2/0/1/2/0/1,22222222222222222

            i.e. from 0 to low-1   array is 0
                 from low to mid-1 array is 1
                 from mid to high  array is UNSORTED and randomly filled with 0 1 2
                 from high+1 to n-1 array is all 2
         */
        int low =0, mid = 0, high = nums.length-1;

        while (mid <= high){
            //We focus on the mid-pointer. As the unsorted part starts from "mid"
            //Case 1
            if (nums[mid] == 0){
            /*
                swap it with the low
                move low and mid 1 index ahead
             */
                swap(nums, low, mid);
                low++;
                mid++;
            }

            //Case 2
            else if (nums[mid] == 1)
                mid++;

                //Case 3
            else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int low, int mid) {
        int temp = nums[low];
        nums[low] = nums[mid];
        nums[mid] = temp;
    }
}

package striversSheet.medium;

//https://leetcode.com/problems/find-the-duplicate-number/description/
public class arrays_findDuplicateInArray {

    public static void main(String[] args) {
        int[] n = {1,3,4,2,2};
//        System.out.println(findDuplicate(n));
//        System.out.println(findDuplicateMethod2(n));
//        System.out.println(findDuplicateMethod3(n));
        System.out.println(findDuplicateMethod4(n));
    }

    /*
        A common and great approach used in this question is :
        we use the array elements as Indices too.
        Its common in both M1 and M2, kindof in M3 also

        Editorial : https://leetcode.com/problems/find-the-duplicate-number/editorial/
     */


    //FUCKKKKKKK sooo fuckinnn great APPROACH!!!
    //M1: LinkedList type Floyd's cycle detection Method
    public static int findDuplicate(int[] nums) {
        //NOTE: here slow and fast actually contain the value, and not the indices[tho, they are used as indices too]
        int slow = nums[0], fast = nums[0];

        //Step1 : make slow and fast pointers meet at a point, which proves there is a cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);


        //Step 2: Now do procedure to find start of loop. Which for sure will be the DUPLICATE ELEMENT
        slow = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        //Now both slow and fast are pointing to the duplicate elem
        return slow;
    }

    //M2 : Negative Marking -- takes lesser memory than M1
    public static int findDuplicateMethod2(int[] nums) {
        //Step 1 : use elem as indices and mark elem at those indices as -ve : as a mark/memory that they have been visited
        for (int i = 0; i < nums.length; i++) {
            int ptr = Math.abs(nums[i]);

            if (nums[ptr] < 0) {//already negative, means it has been already visited. hence the ptr is the repeating elem
                //Step 2: when repeated elem found, undo changes made to elem.
                for (int j = 0; j < nums.length; j++)
                    nums[i] = Math.abs(nums[i]);

                return ptr;
            }
            else
                nums[ptr] *= -1;

        }
        return -1;
    }

    //M3 : Array as a HashMap. we map arr values with their corresponding index. i.e. arr[] = 5 is matched with index 5. and hence arr[5] = 5 done
    //V efficient in time and space, BUT IT CHANGES THE ARRAY! Undesirable here.
    public static int findDuplicateMethod3(int[] nums) {
        /*
            Iterative Method below. Can also be done Recursively(tho, that uses O(n) so not recommended)

            Note : index 0 will not be mapped as values here are from 1 to n.
            Hence, index 0 is used as a Temp index, which holds the temp val(val at index i, when val i from index is mapped to it)
            i.e. storing the swapped val, which was initially, before swap at index i
         */
        while(true) {
            int ptr = nums[0];

            if (nums[ptr] == ptr)//check
                return ptr;
            //else keep swapping
            nums[0] = nums[ptr];
            nums[ptr] = ptr;
        }
    }


    /*
        M4: Binary Search - Important observation. as we use BS not directly on this arr but on count arr!!
        So,an arr need not be sorted for applying BS always, we can use BS on a sorted Derivative of that ARR

        M4.1 : we can use an array to store count, and then search it for where i < count[i]
                Its TC : O(n), SC: O(n) - i.e. requires extra space, but lesser time

        M4.2 : No count arr, Do binary search on number range[1, n] and for each num,
                traverse arr and count it.
                if num < arr[num] output it, else continue with BS
                TC: O(nlogn), SC : O(1)

        Here we follow M4.2
     */
    public static int findDuplicateMethod4(int[] nums) {
        int start = 1, end = nums.length - 1;//coz nums are from 1 to n
        int repeatingElem = -1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            int count = findCount(nums, mid);

            if (count > mid) {
                repeatingElem = mid;
                end = mid - 1;
            }
            else {//count <= mid
                start = mid + 1;
            }
        }
        return repeatingElem;
    }

    private static int findCount(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= num)
                count++;
        }
        return count;
    }

    //M4: Sum of set BITS -- ND, may see from editorial[Link above]
    //TC: O(nlogn), SC : O(1)
}

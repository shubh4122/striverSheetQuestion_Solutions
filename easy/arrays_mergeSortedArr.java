package striversSheet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/merge-sorted-array/description/
public class arrays_mergeSortedArr {
    public static void main(String[] args) {
        int[] n1 = {0};//{1};//{1,2,3,0,0,0};
        int[] n2 = {1};//{};//{2,5,6};

        merge(n1, n1.length - n2.length, n2, n2.length);
        System.out.println(Arrays.toString(n1));
    }

    //M2: Gap method. see striver's Method 3. TC - O(m+n), SC - O(1)
    //https://takeuforward.org/data-structure/merge-two-sorted-arrays-without-extra-space/

    //M1: TC - O(m+n), SC - O(1)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //Approach : move from back to front.

        int p1 = m-1, p2 = n-1; //pointers for num1 and num2 respectively.
        for (int i = m+n-1; i >= 0; i--) {
            if (p2 == -1)//means our work done
                break;

            if (p1 == -1){
                nums1[i] = nums2[p2];
                p2--;
                continue;
            }

            if (nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1];
                p1--;
            }
            else {
                nums1[i] = nums2[p2];
                p2--;
            }
        }
    }
}

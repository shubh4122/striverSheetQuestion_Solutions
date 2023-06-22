package striversSheet.easy;

import java.util.HashMap;
import java.util.Stack;

/*
    Must do, similar question
    https://leetcode.com/problems/next-greater-element-ii/
    https://leetcode.com/problems/next-greater-element-iii/
 */


//https://leetcode.com/problems/next-greater-element-i/description/
public class stack_Q_nextGreaterElem {


    //O(n+m) TC, O(n) SC - Hashmap
    public int[] nextGreaterElementBEST(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s = new Stack<>();

        for (int num : nums2) {
            while (!s.isEmpty() && s.peek() < num) {
                map.put(s.peek(), num);
                s.pop();
            }
            s.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i]))
                nums1[i] = map.get(nums1[i]);

            else
                nums1[i] = -1;
        }

        return nums1;
    }


    //O(n*m) TC, O(1) SC
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //num1 all elem in num2.
        //for each n1 elem, see bigger elem just right to it in n2

        for(int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGElem(nums2, nums1[i]);
        }
        return nums1;
    }

    public int nextGElem(int nums[], int num) {
        boolean found = false;
        for(int i = 0; i < nums.length; i++) {
            if(found) {
                if(nums[i] > num)
                    return nums[i];
            }
            if(nums[i] == num)
                found = true;
        }
        return -1;
    }
}

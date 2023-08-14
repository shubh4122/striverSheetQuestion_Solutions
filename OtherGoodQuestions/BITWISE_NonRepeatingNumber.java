package striversSheet.OtherGoodQuestions;


//https://practice.geeksforgeeks.org/problems/finding-the-numbers0215/1
public class BITWISE_NonRepeatingNumber {//v good question.

    public int[] singleNumber(int[] nums){
        /*
            Steps:
            1. Find XOR of all the array elements
            2. Find the RightMost SET BIT in XOR(say, iTH bit)
                Why? - This bit is the first position where the 2 numbers will differ in BITS.
            3. Finding the two numbers - by dividing them in 2 different groups:
                a. 1st Group is of those elems, whose iTH bit is ""UNSET"".
                    > 2nd elem for sure lies here.
                    > Take XOR of this group, and get the FIRST NUMBER.
                b. 2nd group will be the ones which have the iTH bit ""SET"".
                    > One of the elem will for sure lie here, as its iTH bit is set.
                    > Take XOR of all elements lying in Group2. Here we get SECOND NUMBER. All others will be duplicates
            4. store numbers and return
         */

//Step 1
        int xor = 0;
        for (int n : nums)
            xor ^= n;

//Step 2
        int rightMostSetBit = xor & (-xor); //NOTE  ---- it will look something like: 00000001000 -> pos of 1 depends on nums

//Step 3
        int group1Xor = 0, group2Xor = 0;
        for (int n: nums) {
            if ((n & rightMostSetBit) == 0) //Step 3.a
                group1Xor ^= n;

            else //Step 3.b
                group2Xor ^= n;
        }

//Step 4
        int num1 = Math.min(group1Xor, group2Xor);
        int num2 = Math.max(group1Xor, group2Xor);

        return new int[]{num1, num2};
    }
}

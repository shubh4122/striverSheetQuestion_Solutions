package striversSheet.easy;

public class arrays_majorityElem1 {

    //Most Optimised - Moore Voting algo
    //CODE is faster than my prev Moore algo!. so go with this CODE!!
    public int majorityElement(int[] a) {
        int votes = 0;
        int candidate = a[0];

        for (int i = 0; i < a.length; i++) {
            if (candidate == a[i])
                votes++;
            else
                votes--;

            if (votes < 0) {
                candidate = a[i];
                votes = 1;
            }
        }

        return candidate;
    }
}

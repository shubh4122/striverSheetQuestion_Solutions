package striversSheet.easy;

import java.util.ArrayList;
import java.util.List;

public class arrays_PascalTriangle {
    public static void main(String[] args) {
        System.out.println(generate(1));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<Integer>());
        }

        //1 and 1,1 already put
        ans.get(0).add(1);

        if (numRows > 1){
            ans.get(1).add(1);
            ans.get(1).add(1);
        }
        for (int i = 2; i < numRows; i++) {
            ans.get(i).add(1); // coz every row start and end in 1
            for (int j = 1; j < i; j++) {
                ans.get(i).add(ans.get(i-1).get(j-1) + ans.get(i-1).get(j));
            }
            ans.get(i).add(1); // coz every row start and end in 1
        }
        return ans;
    }
}

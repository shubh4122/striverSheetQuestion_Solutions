package striversSheet.medium;

import java.util.ArrayList;
import java.util.Stack;

//https://leetcode.com/problems/online-stock-span/description/
public class stack_Q_OnlineStockSpan {

    //O(1) TC, MONOTONIC Stack
    Stack<int[]> s;
    public stack_Q_OnlineStockSpan() {
        s = new Stack<>();//{price, span}
    }

    //this while loop runs TOTAL of n times, throughout the INPUT.
    //an elem is pushed and popped only once in stack. Hence constant time
    public int next(int price) {
        int span = 1;
        while (!s.isEmpty() && s.peek()[0] <= price) {
            int[] pop = s.pop();
            span += pop[1];
        }

        s.push(new int[]{price, span});
        return span;
    }
}

    //next -> TC O(n) - BRUTE FORCE
//    ArrayList<Integer> prices;
//    public stack_Q_OnlineStockSpan() {
//        prices = new ArrayList<>();
//    }
//
//    public int next(int price) {
//        int span = 1;
//        for(int i = prices.size()-1; i >=0 ; i--) {
//            if(prices.get(i) > price)
//                break;
//
//            span++;
//        }
//        prices.add(price);
//
//        return span;
//    }
}

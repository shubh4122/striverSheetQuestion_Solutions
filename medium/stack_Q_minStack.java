package striversSheet.medium;

import java.util.Stack;

//https://leetcode.com/problems/min-stack/
public class stack_Q_minStack {

    //Space - O(n) - STRIVER CODE. I didn't do it myself.!
    //https://youtu.be/V09NfaGf2ao?t=325 - watch this for more idea/intuition
    Stack < Long > st = new Stack < Long > ();
    Long mini;

    public stack_Q_minStack() {
        mini = Long.MAX_VALUE;
    }

    public void push(int value) {
        Long val = Long.valueOf(value);
        if (st.isEmpty()) {
            mini = val;
            st.push(val);
        } else {
            if (val < mini) {
                st.push(2 * val - mini);
                mini = val;
            } else {
                st.push(val);
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) return;

        Long val = st.pop();
        if (val < mini) {
            mini = 2 * mini - val;
        }
    }

    public int top() {
        Long val = st.peek();
        if (val < mini) {
            return mini.intValue();
        }
        return val.intValue();
    }

    public int getMin() {
        return mini.intValue();
    }

    //Space - O(2n)
//    Stack<int[]> s;
//    public stack_Q_minStack() {
//        s = new Stack<>();//{val, min upto here}
//    }
//
//    public void push(int val) {
//        int min;
//        if (s.isEmpty())
//            min = val;
//        else
//            min = Math.min(s.peek()[1], val);
//
//        s.push(new int[]{val, min});
//    }
//
//    public void pop() {
//        s.pop();
//    }
//
//    public int top() {
//        return s.peek()[0];
//    }
//
//    public int getMin() {
//        return s.peek()[1];
//    }
}

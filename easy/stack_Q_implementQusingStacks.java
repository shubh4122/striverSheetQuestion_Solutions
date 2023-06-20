package striversSheet.easy;

import java.util.Stack;

public class stack_Q_implementQusingStacks {

    //https://youtu.be/3Et9MrMc02A great explaination for below method

    //2Stacks - TC - O(1) AMORTISED!
    Stack<Integer> input, output;

    public stack_Q_implementQusingStacks() {
        input = new Stack<>();//all the input goes only in this stack
        output = new Stack<>();//pops elem in order that queue would pop. [coz it is reverse of inp stack]
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        //O(1) - runs most of the time
        if (!output.isEmpty()) {
            return output.pop();
        }
        //O(n) - but it runs only sometimes, hence overall pop is AMORTISED O(1)
        else {
            //if output is empty, then put inp to output and then pop
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            return output.pop();
        }
    }

    public int peek() {
        //O(1) - runs most of the time
        if (!output.isEmpty()) {
            return output.peek();
        }
        //O(n) - but it runs only sometimes, hence overall pop is AMORTISED O(1)
        else {
            //if output is empty, then put inp to output and then pop
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            return output.peek();
        }
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }


    //2Stacks - TC - O(n)
//    Stack<Integer> s1, s2;
//    public stack_Q_implementQusingStacks() {
//        s1 = new Stack<>();//s1 will always be the ptr pointing to non empty  stack
//        s2 = new Stack<>();
//    }
//
//    public void push(int x) {
//        s1.push(x);
//    }
//
//    // O(2n)
//    public int pop() {
//        while (!s1.isEmpty()) {
//            s2.add(s1.pop());
//        }
//
//        int popped = s2.pop();
//
//        while (!s2.isEmpty()) {
//            s1.add(s2.pop());
//        }
//        return popped;
//
//    }
//
//    public int peek() {
//        while (!s1.isEmpty()) {
//            s2.add(s1.pop());
//        }
//
//        int peek = s2.peek();
//
//        while (!s2.isEmpty()) {
//            s1.add(s2.pop());
//        }
//        return peek;
//    }
//
//    public boolean empty() {
//        return s1.isEmpty();
//    }
}

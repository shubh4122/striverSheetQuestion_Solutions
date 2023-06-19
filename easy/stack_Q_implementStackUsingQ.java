package striversSheet.easy;

import java.util.LinkedList;
import java.util.Queue;

public class stack_Q_implementStackUsingQ {

    //Using 2 Q
//    Queue<Integer> q1;
//    Queue<Integer> q2;
//
//    public stack_Q_implementStackUsingQ() {
//        q1 = new LinkedList<>();
//        q2 = new LinkedList<>();
//    }
//
//    public void push(int x) {
//        if(q1.isEmpty() && !q2.isEmpty())
//            q2.add(x);
//
//        else
//            q1.add(x);
//    }
//
//    public int pop() {
//        int popped = -1;
//
//        if(!q1.isEmpty()) {
//            while(!q1.isEmpty()) {
//                popped = q1.remove();
//                if(!q1.isEmpty()) {
//                    q2.add(popped);
//                }
//            }
//        }
//
//        else {
//            while(!q2.isEmpty()) {
//                popped = q2.remove();
//                if(!q2.isEmpty()) {
//                    q1.add(popped);
//                }
//            }
//        }
//        return popped;
//    }
//
//    public int top() {
//        int popped = -1;
//
//        if(!q1.isEmpty()) {
//            while(!q1.isEmpty()) {
//                popped = q1.remove();
//                q2.add(popped);
//
//            }
//        }
//        else{
//            while(!q2.isEmpty()) {
//                popped = q2.remove();
//                q1.add(popped);
//
//            }
//        }
//        return popped;
//    }
//
//    public boolean empty() {
//        return q1.isEmpty() && q2.isEmpty();
//    }


    //Using 1 Queue
    Queue<Integer> q;
    int top;

    public stack_Q_implementStackUsingQ() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        top = x;
    }

    public int pop() {
        int popped = -1, temp = 0;

        while(temp < q.size()-1) {
            popped = q.remove();
            q.add(popped);
            top = popped;
            temp++;
        }
        popped = q.remove();//remove top
        return popped;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}

package striversSheet.easy;

import java.util.Stack;

public class stack_Q_validParenthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                st.push(c);
            }
            else{
                if(st.isEmpty())
                    return false;

                if(c == ')' && st.peek() == '(')
                    st.pop();

                else if(c == '}' && st.peek() == '{')
                    st.pop();

                else if(c == ']' && st.peek() == '[')
                    st.pop();

                else
                    break;
            }
        }
        return st.isEmpty();
    }
}

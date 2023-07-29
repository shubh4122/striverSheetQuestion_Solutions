package striversSheet.medium;

import java.util.Scanner;

public class string_StringToInteger_ATOI {

    public static void main(String[] args) {
        String s="-01324000";//"20000000000000000000sdfsdf";


        System.out.println(new string_StringToInteger_ATOI().myAtoiCleaner(s));
    }

    //M2:
    public int myAtoiCleaner(String s){
        //We will keep converting a num from char to int as soon as encountered.
        //IMP = to convert char to int -> '4'-'0' = 4

        //Remove Whitespaces
        s=s.trim();

        //sign of expression
        boolean isNeg = false;

        int num =0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i==0 && (c=='-' || c=='+'))
                isNeg = c=='-';

            //removing trailing zeroes
            else if (num==0 && c == '0')
                continue;

            else if (Character.isDigit(c)) {
                int newDigit = c - '0';//0 to 9
                double intMIN = Integer.MIN_VALUE;

                //original check : num*10+newDig > MAX. But this will cause overflow of int.
                //hence, num > (MAX+newDig)/10
                if (!isNeg && num >= ((double)Integer.MAX_VALUE - newDigit)/10)
                    return Integer.MAX_VALUE;

                //IMP NOTE: abs(MIN) causes OVERFLOW of INT. as MIN = 1 more than MAX in terms of abs val.
                //hence we use LONG intMIN var.
                else if (isNeg && num >= (Math.abs(intMIN) - newDigit)/10)
                    return Integer.MIN_VALUE;

                num = num * 10 + newDigit;//converting char/str to int
            }

            else
                break;
        }
        return isNeg? -num : num;
    }





    //M1 : Lengthy
    public int myAtoi(String s) {
        s = s.trim();
        StringBuilder num=new StringBuilder(); char sign = ' ';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if ((c=='-' || c=='+') && num.isEmpty()) {
                if (sign != ' ')
                    break;

                sign = c;
            }

            else if (Character.isDigit(c))
                num.append(c);

            else//is either a letter, white space or a -/+ sign after a number
                break;
        }

        //removing trailing zeroes
        for (int i = 0; i < num.length(); ) {
            if (num.charAt(i)=='0')
                num.deleteCharAt(i);
            else
                break;
        }

        if (num.isEmpty())
            return 0;


        if (sign == '-')
            num.insert(0, sign);

//        System.out.println(num);
        //Cant directly convert num to Integer, it might have a val tooo large for int or even Long to handle, eg:20000000000000000000

        String intMax = String.valueOf(Integer.MAX_VALUE);
        String intMin = String.valueOf(Integer.MIN_VALUE);

        if(num.charAt(0)=='-'){
            if (num.length() < intMin.length())
                return Integer.parseInt(num.toString());
            else if(num.length() > intMin.length())
                return Integer.MIN_VALUE;
            else {//equal len
                for (int i = 1; i < num.length(); i++) {//0 is sign obv
                    char c1 = num.charAt(i);
                    char c2 = intMin.charAt(i);

                    //comparing ASCII val of digits char. as if d1>d2, ascii1>ascii2
                    if (c1 < c2)
                        return Integer.parseInt(num.toString());
                    else if (c1 > c2)
                        return Integer.MIN_VALUE;
                }
                return Integer.MIN_VALUE;//when num=intmin
            }
        }
        else {//+ve num
            if (num.length() < intMax.length())
                return Integer.parseInt(num.toString());
            else if(num.length() > intMax.length())
                return Integer.MAX_VALUE;
            else {//equal len
                for (int i = 0; i < num.length(); i++) {
                    char c1 = num.charAt(i);
                    char c2 = intMax.charAt(i);

                    //comparing ASCII val of digits char. as if d1>d2, ascii1>ascii2
                    if (c1 < c2)
                        return Integer.parseInt(num.toString());
                    else if (c1 > c2)
                        return Integer.MAX_VALUE;
                }
                return Integer.MAX_VALUE;//when num=intmax
            }
        }

    }
}

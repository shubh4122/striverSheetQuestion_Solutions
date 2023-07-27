package striversSheet.easy;

public class string_RomanToInteger {

    //M2: concise code: check when c[i] < c[i+1]. when it is, add the [i+1]-[i]. else add [i]
    //something like this. Not my code. Found on LC solutions
    public int romanToIntCLEAN(String s) {

        int answer = 0, number = 0, prev = 0;

        for (int j = s.length() - 1; j >= 0; j--) {
            switch (s.charAt(j)) {
                case 'M' -> number = 1000;
                case 'D' -> number = 500;
                case 'C' -> number = 100;
                case 'L' -> number = 50;
                case 'X' -> number = 10;
                case 'V' -> number = 5;
                case 'I' -> number = 1;
            }
            if (number < prev) {
                answer -= number;
            }
            else {
                answer += number;
            }
            prev = number;
        }
        return answer;
    }

    //M1
    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case 'I' : ans += 1;
                    if (i+1 != s.length()) {
                        if (s.charAt(i+1) == 'V') {
                            ans += 4 - 1;//-1 coz, i already added 1 earlier.
                            i++;
                        }
                        else if (s.charAt(i+1) == 'X') {
                            ans += 9 - 1;
                            i++;
                        }
                    }
                break;
//--------------------------------------------------------------------------------------------------------
                case 'V' : ans += 5;
                break;
//--------------------------------------------------------------------------------------------------------
                case 'X' : ans += 10;
                    if (i+1 != s.length()) {
                        if (s.charAt(i+1) == 'L') {
                            ans += 40 - 10;
                            i++;
                        }
                        else if (s.charAt(i+1) == 'C') {
                            ans += 90 - 10;
                            i++;
                        }
                    }
                break;
//--------------------------------------------------------------------------------------------------------
                case 'L' : ans += 50;
                break;
//--------------------------------------------------------------------------------------------------------
                case 'C' : ans += 100;
                    if (i+1 != s.length()) {
                        if (s.charAt(i+1) == 'D') {
                            ans += 400 - 100;
                            i++;
                        }
                        else if (s.charAt(i+1) == 'M') {
                            ans += 900 - 100;
                            i++;
                        }
                    }
                    break;
//--------------------------------------------------------------------------------------------------------
                case 'D' : ans += 500;
                    break;
//--------------------------------------------------------------------------------------------------------
                case 'M' : ans += 1000;
                    break;
//--------------------------------------------------------------------------------------------------------
            }
        }
        return ans;
    }
}

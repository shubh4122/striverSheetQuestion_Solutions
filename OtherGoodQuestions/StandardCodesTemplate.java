package striversSheet.OtherGoodQuestions;

public class StandardCodesTemplate {

    public static void main(String[] args) {

    }

    public int lcm(int a, int b){
        //Most efficient - TC : same as gcd
        //IDEA:  a*b = lcm(a,b)*gcd(a,b)
        return a*b/gcd(a,b);
    }

    public int gcd(int a, int b){
        //Euclidean Algo - repeated Division
        //TC: O(log(min(a, b))
        /*
            Why Does the Euclidean Algorithm Work? - IMP to get the intuition behind Euclidean method
            https://www.freecodecamp.org/news/euclidean-algorithm-in-golang/#:~:text=The%20algorithm%20continues%20to%20iterate,divided%20by%20the%20smaller%20number.
        */
        if (b == 0) return a;

        return gcd(b, a%b);//coz by Maths thm, gcd of a and b, is also the divisor of their Remainder. So b can be replaced with rem
    }
}

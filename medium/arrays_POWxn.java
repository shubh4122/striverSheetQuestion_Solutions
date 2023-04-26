package striversSheet.medium;

//https://leetcode.com/problems/powx-n/description/
public class arrays_POWxn {


    //BRUTE FORCE - TLE
    public double myPowPOOR(double x, int n) {
        double ans = 1.000;
        if(n>=0) {
            while(n-->0) {
                ans *= x;
            }
        }
        else{
            while(n++<0) {
                ans /= x;
            }
        }
        return ans;
    }



    //Can also do below Method Recursively!!!


    public double pow(double x, int n) {
        double ans = myPow(x, n);

        if (n < 0)
            return 1/ans;
        else
            return ans;
    }

        //Recursive, and the iteration reduces by a factor of in every call
    public double myPow(double x, int n) {
        if (n == 0)
            return 1.00;

        return (n%2 == 0) ? myPow(x*x, n/2) : (x * myPow(x*x, n/2));
    }
}

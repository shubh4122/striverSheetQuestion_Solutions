package striversSheet.OtherGoodQuestions;

//https://leetcode.com/problems/minimum-speed-to-arrive-on-time/description/
public class BinSearch_MinSpeedToArriveOnTime {

    /*
        Because we want to find the SPEED which we must have
        in order to travel distances -> dist[] before time:HOUR

        We literally have no way to find it out Directly.
        We have to manually try each value from min speed to max speed
        and see what is the min speed that fits.

        so, we can either run a loop from min to max speed. But
        Its less efficient for searching

        HENCE use BINARY search to search the min val.
     */

    public static void main(String[] args) {
        BinSearch_MinSpeedToArriveOnTime o = new BinSearch_MinSpeedToArriveOnTime();

        int[] dist = {1,1,100000};
        double h = 2.01;
        System.out.println(o.minSpeedOnTime(dist, h));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int s = 1, e = (int) 1e7;//min speed can be 1, and max 10^7 as per ques
        int minSpeedRequired = (int) 1e9;

        while (s <= e){
            int speed = s + (e-s)/2;//mid
            double calculatedTime = totalTimeTaken(dist, speed);

            if (calculatedTime <= hour) {
                minSpeedRequired = Math.min(speed, minSpeedRequired);
                e = speed - 1;
            }
            else// if (calculatedTime > hour)
                s = speed + 1;

        }
        return minSpeedRequired==(int) 1e9 ?-1:minSpeedRequired;
    }

    public double totalTimeTaken(int[] dist, int speed){
        double time = 0f;
        for (int i = 0; i < dist.length-1; i++) {
            int d = dist[i];
            time += Math.ceil((double) d / speed);
        }
        //Note: Last d/s should not be ROUNDED!!
        time += (double) dist[dist.length-1]/speed;
        return time;
    }
}

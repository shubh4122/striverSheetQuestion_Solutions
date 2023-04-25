package striversSheet.easy;

public class array_stockBuySell {

    public int maxProfit(int[] prices) {
        int lowestUptoNow = Integer.MAX_VALUE;
        int profit = 0, maxProfit = Integer.MIN_VALUE;

        for (int day = 0; day < prices.length; day++) {
            lowestUptoNow = Math.min(lowestUptoNow, prices[day]);

            //the profit, if we sold stock at todays price
            profit = prices[day] - lowestUptoNow;

            //max Profit scored upto now, if we sold it
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}

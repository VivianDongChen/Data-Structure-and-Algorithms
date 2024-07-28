package LeetCode.StockProblems;

/**
 *  买卖股票的最佳时机
 *  某一天买入股票，未来任意一天卖出，只能卖了再买， 但可以买卖多次，卖出的话只能隔天再买入，求最大利润
 */
public class LeetCode0309BestTimeToBuyAndSellStockWithCooldown {

    /*
     * 动态规划
     * - 使用两个状态数组
     *                                 1     2    3    0    2
     *
     * 买或不买的最优利润                -1    -1   -1     1    1
     * 买                             -1    -2    -3    1    0
     * 不买                            -     -1   -1    -1    1
     *
     * 卖或不卖的最优利润                0      1    2     2    3
     * 卖                             -      1     2    -1    3
     * 不卖                            0     0     1     2    2
     */

    static int maxProfit1(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (i == 1) {
                buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            }else{
                buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            }
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[prices.length - 1];
    }

    /**
     * 降纬优化
     * @param prices
     * @return
     */
    static int maxProfit2(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int buy2 = -prices[0];
        int sell2 = 0;
        int buy1 = Math. max(buy2, -prices[1]);
        int sell1 = Math. max(sell2, buy1 + prices[1]);

        for (int i = 2; i < prices.length; i++) {
            int buy = Math.max(buy1, sell2 - prices[i]);
            int sell = Math.max(sell1, buy1 + prices[i]);
            sell2 = sell1;
            buy1 = buy;
            sell1 = sell;
        }
        return sell1;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{1, 2, 3, 0, 2})); // 3
    }
}

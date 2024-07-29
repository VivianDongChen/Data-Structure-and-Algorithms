package LeetCode.StockProblems;

/**
 * 买卖股票的最佳时机
 * 某一天买入股票，未来任意一天卖出，只能先卖再买，最多买卖两次，求最大利润
 */
public class LeetCode0123BestTimeToBuyAndSellStockIII {

    /*
    动态规划 - 使用四个状态数组

                               3      5      0      3      1      4
       第一次买卖状态

       买或不卖的最优利润        -3     -3      0     0      0       0
                             （买）  （等）  （买） （等）  （等）   （等）
       买                     -3      -5     0     -3     -1      -4
       不买                    -      -3     -3     0      0       0

       卖或不卖的最优利润        0       2      2     3     3       4
                            （等）   （卖）  （等） （卖） （等）   （卖）
       卖                     -        2     -3     3     1       4
       不卖                    0       0      2     2     3       3

       第二次买卖状态

       买或不卖的最优利润        -3     -3     2     2     2       2
                             （买）  （等） （买） （等） （等）    （等）
       买                     -3     -5     2     -1     2       -1
       不买                    -     -3     -3     2     2        2

       卖或不卖的最优利润        0      2     2     5     5        6
                             （等）  （卖）（等）  （卖）（等）    （卖）
       卖                     -      2     -3     5     3        6
       不卖                    0      0     2     2     5        5

       第一次买 不依赖之前状态，以当日价格买入
       第一次卖，依赖于昨天第一次买 + 当日价格

       第二次买，依赖于昨天第一次卖 - 当日价格
       第二次卖，依赖于昨天第二次买 + 当日价格

     */

    /**
     * 数组
     * @param prices
     * @return
     */
    static int maxProfit1(int[] prices){
        int[] buy1 = new int[prices.length];
        int[] sell1 = new int[prices.length];
        int[] buy2 = new int[prices.length];
        int[] sell2 = new int[prices.length];

        buy1[0] = - prices[0];
        sell1[0] = 0;
        buy2[0] = - prices[0];
        buy2[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1[i] = Math.max(buy1[i], - prices[i]);
            sell1[i] = Math.max(sell1[i], buy1[i-1] + prices[i]);
            buy2[i] = Math.max(buy2[i], sell1[i-1] - prices[i]);
            sell2[i] = Math.max(sell2[i], buy2[i-1] + prices[i]);
        }
        return sell2[prices.length - 1];
    }

    /**
     * 优化后的代码
     * @param prices
     * @return
     */
    static int maxProfit2(int[] prices){
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for(int price: prices){
            buy1 = Math.max(buy1, - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit1(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // 6
    }
}

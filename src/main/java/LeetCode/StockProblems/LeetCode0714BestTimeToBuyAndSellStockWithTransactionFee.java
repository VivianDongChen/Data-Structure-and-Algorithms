package LeetCode.StockProblems;
/**
 * 买卖股票的最佳时机
 * 某一天买入股票，未来任意一天卖出，只能卖了再买， 但可以买卖多次，不允许同一天卖出后买入，每笔交易有手续费，求最大利润
 */

public class LeetCode0714BestTimeToBuyAndSellStockWithTransactionFee {
    /*
     * 动态规划 - 使用两个状态数组
     *                                 9     3    12    1    2    3
     *
     * 买或不买的最优利润                -9    -3    -3    7    7    7
     *                               （买） （买） （等）（买）（等）（等）
     * 买                             -9    -3    -12   7    6    5
     * 不买                            -    -9    -3    -3   7    7
     *
     * 卖或不卖的最优利润（扣除手续费1）    0     0     8     8    8    9
     *                               （等） （等） （卖）（等）（等）（卖）
     * 卖                              -    -7     8    -3    8    9
     * 不卖                            0     0     0     8    8    8
     *
     * 对于买 => 1. 延续上次买的利润不变
     *          2. 在上次卖的利润基础上买
     * 对于卖 => 1. 延续上次卖的利润不变
     *          2. 在上次卖的利润基础上卖
     */
    static int maxProfit1(int[] prices, int fee){
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);
        }

        return sell[prices.length - 1];
    }

    /**
     * 代码优化
     * - 用变量代替数组（降维）
     * @param prices
     * @param fee
     * @return
     */

    static int maxProfit2(int[] prices, int fee){
        int _buy = -prices[0];  //上一次buy数据
        int _sell = 0;   //上一次的sell数据
        for (int i = 1; i < prices.length; i++) {
            int buy = Math.max(_buy, _sell - prices[i]);    //这一次的buy数据
            int sell = Math.max(_sell, _buy + prices[i] - fee);    //这一次的sell数据
            _buy = buy;
            _sell = sell;
        }
        return _sell;
    }

    /**
     * 再次优化
     * 将上一次的数据和这次的数据变量合并
     * @param prices
     * @param fee
     * @return
     */

    static int maxProfit3(int[] prices, int fee){
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);    //这里的意义变了，是根据这次的buy来计算这次的sell，但是并不影响结果，原因如下：
            /*
            如果buy =
                buy  那么这次的buy就是上次的buy
                sell - prices[i] , 那么sell =
                Math.max(sell, sell - fee) 结果跟buy没有关系
             */
        }
        return sell;
    }

    /**
     * 再次优化
     * - 让i从0开始，将for循环改为增强for循环
     * @param prices
     * @param fee
     * @return
     */
    static int maxProfit4(int[] prices, int fee){
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for(int price : prices){
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, buy + price - fee);
        }
        return sell;
    }


    public static void main(String[] args) {
        // 两次交易的情况
        System.out.println(maxProfit4(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
        System.out.println(maxProfit4(new int[]{1, 3, 7, 2, 18, 3}, 3)); // 16
        System.out.println(maxProfit4(new int[]{2, 1, 4, 4, 2, 3, 2, 5, 1, 2}, 1)); // 4
        System.out.println(maxProfit4(new int[]{9, 3, 12, 1, 2, 3}, 1)); // 9

        // 一次交易的情况
        System.out.println(maxProfit4(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
        System.out.println(maxProfit4(new int[]{1, 3, 7, 5, 10, 11, 3}, 3)); // 7

    }
}

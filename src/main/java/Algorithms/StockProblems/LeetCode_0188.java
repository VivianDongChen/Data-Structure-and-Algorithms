package Algorithms.StockProblems;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机
 * 某一天买入股票，未来任意一天卖出，只能先卖再买，最多买卖K次，求最大利润
 */
public class LeetCode_0188 {


    static int maxProfit(int k, int[] prices){

       /*
        动态规划

       第一次买 不依赖之前状态，以当日价格买入
       第一次卖，依赖于昨天第一次买 + 当日价格

       第二次买，依赖于昨天第一次卖 - 当日价格
       第二次卖，依赖于昨天第二次买 + 当日价格

       第三次买，依赖于昨天第二次卖 - 当日价格
       第三次卖，依赖于昨天第三次买 + 当日价格

       ...

       第k次买，依赖于昨天第k-1次卖 - 当日价格
       第k次卖，依赖于昨天第k次买 + 当日价格

       */
        if(k > prices.length / 2){      //k大于实际能交易的数，这个时候相当于没有k, 按照下面的贪心算法来计算即可
            return  maxProfit(prices);
        }

        int[] buy = new int[k];   //用数组来存储每天的buy数据
        int[] sell = new int[k];  //用数组来存储每天的sell数据
        Arrays.fill(buy, Integer.MIN_VALUE);  //初始化buy数组

        for(int price: prices){
            buy[0] = Math.max(buy[0], - price);
            sell[0] = Math.max(sell[0], buy[0] + price);
            for (int i = 1; i < k; i++) {
                buy[i] = Math.max(buy[i], sell[i-1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }

        }
        return sell[k-1];
    }

    /**
     * 使用贪心算法（LeetCode 122）
     */
    static int maxProfit(int[] prices){
        int i = 0;
        int j = 1;
        int max = 0;
        while(j < prices.length){
            if(prices[j] - prices[i] > 0) {
                max += prices[j] - prices[i];
            }
            i++;
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); // 7
        System.out.println(maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // 6
        System.out.println(maxProfit(4, new int[]{1, 2, 0, 1, 0, 3, 1, 4, 5})); // 9
    }
}

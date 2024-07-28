package LeetCode.StockProblems;

/**
 * 买卖股票的最佳时机
 * 某一天买入股票，未来任意一天卖出，只能卖了再买， 但可以买卖多次，并允许
 * 同一天卖出后买入，求最大利润
 * 贪心算法：有利润就卖
 */
public class LeetCode0122BestTimeToBuyAndSellStockII {
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
        System.out.println(maxProfit(new int[]{9, 3, 12, 1, 2, 3})); // 11
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 7
    }

}

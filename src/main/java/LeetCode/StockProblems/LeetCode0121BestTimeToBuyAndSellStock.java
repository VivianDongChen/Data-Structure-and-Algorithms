package LeetCode.StockProblems;

/**
 * 买卖股票的最佳时机
 * 某一天买入股票，未来任意一天卖出，只卖一次，求最大利润
 * i 尝试买入，j 尝试卖出
 *  遇到涨 i 不变, j++
 *  遇到跌 i 变 j，j++
 */
public class LeetCode0121BestTimeToBuyAndSellStock {

    static int maxProfit(int[] prices){
        int i = 0;
        int j = 1;
        int max = 0;

        while(j < prices.length){
            if(prices[j] - prices[i] > 0){  //涨了
                max = Math.max(max, prices[j] - prices[i]);
            }else{  //跌了或没有变
                i = j;
            }
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(maxProfit(new int[]{9, 3, 12, 1, 2, 3})); // 9
    }

}

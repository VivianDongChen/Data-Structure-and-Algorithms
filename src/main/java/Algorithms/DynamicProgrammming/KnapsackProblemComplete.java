package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 完全背包问题 - 动态规划
 * 物品个数无限多
 */
public class KnapsackProblemComplete {

    static class Item {
        int index;
        String name;
        int weight;
        int value;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item(" + name + ")";
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "青铜", 2, 3),    // c
                new Item(2, "白银", 3, 4),    // s
                new Item(3, "黄金", 4, 7),    // a
        };
        System.out.println(select2(items, 6));
    }

    /*
               0   1   2   3   4   5   6
           0   0   0   c   c   cc  cc  ccc     青铜 重2
           1   0   0   c   s   cc  sc  ccc     白银 重3    容量是4时，可以放的下白银，4-3剩余容量1什么也放不下，所以放白银的话只能放一个白银，但是两个青铜价值 > 1个白银，所以还是放两个青铜
                                                          容量是5时，可以放的下白银，5-3剩余容量2可以放下1个青铜，白银+青铜 > 两个青铜的价值，所以放白银 + 青铜
                                                          容量是6时，可以放的下白银，6-3剩余容量3可以放下1个白银，2个白银的价值 < 三个青铜的价值，所以放三个青铜
           2   0   0   c   s   a   a   ac      黄金 重4    容量是4时，可以放的下黄金，1个黄金的价值 > 2个青铜，所以放1个黄金
                                                          容量是5时，可以放的下黄金，5-4剩余容量1什么也放不下， 1个黄金的价值 = 白银 + 青铜， 放哪个都行（黄金较轻，这里放一个黄金）
                                                          容量时6时，可以放的下黄金，6-4剩余容量2可以放下1个青铜，黄金 + 青铜 > 三个青铜， 所以放黄金 + 青铜

        if(放得下) {
            dp[i][j] = max(dp[i-1][j], dp[i][j-item.weight] + item.value)
        } else {
            dp[i][j] = dp[i-1][j]
        }
     */

    /**
     * 解法1: 使用二维数组存放dp
     * @param items 物品数组
     * @param total 背包容量
     * @return 可以放下的最大价值
     */
    private static int select1(Item[] items,int total){
        int[][] dp = new int[items.length][total + 1];
        //第一行数据单独处理，因为后面的公式里有i-1
        for (int j = 0; j < total + 1; j++) {
            Item item = items[0];
            if(j >= item.weight){
                dp[0][j] = dp[0][j-item.weight] + item.value;
            }
        }

        for (int i = 1; i < items.length; i++) {
            Item item = items[i];
            for (int j = 0; j < total +1; j++) {
                if(j >= item.weight) {
                    dp[i][j] =Math.max(dp[i-1][j], dp[i][j-item.weight] + item.value);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        print(dp);
        return dp[items.length -1][total];
    }

    /**
     * 打印二维数组
     * @param dp 二维数组
     */
    static void print(int[][] dp) {
        System.out.println("   " + "-".repeat(63));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%5d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%5d ".repeat(d.length)) + "%n", array);
        }
    }

    /**
     * 解法2: 使用一维数组存放dp
     * @param items 物品数组
     * @param total 背包容量
     * @return 可以放下的最大价值
     */
    private static int select2(Item[] items,int total){
        int[] dp = new int[total + 1];
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            for (int j = total; j >= 0; j--) {
                if(j >= item.weight) {
                    dp[j] = Math.max(dp[j], dp[j-item.weight] + item.value);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[total];
    }
}

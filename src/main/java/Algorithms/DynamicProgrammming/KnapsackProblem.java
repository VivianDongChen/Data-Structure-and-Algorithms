package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * 0 - 1 背包问题 - 动态规划
 */
public class KnapsackProblem {

    /*
        1. n个物品都是固体，有重量和价值
        2. 现在你要取走不超过 10克 的物品
        3. 每次可以不拿或全拿，问最高价值是多少

            编号 重量(g)  价值(元)                        简称
            1   4       1600           黄金一块   400     A
            2   8       2400           红宝石一粒 300     R
            3   5       30             白银一块           S
            4   1       1_000_000      钻石一粒           D
        1_001_630 贪心解
        1_002_400 正确解
     */

    /*
              0   1   2   3   4   5   6   7   8   9   10     - 背包的最大容量 j

        0     0   0   0   0   A   A   A   A   A   A   A       黄金

        1     0   0   0   0   A   A   A   A   R   R   R       红宝石  背包容量为8的时候，可以装黄金也可以装红宝石，根据价值，红宝石价值高，将黄金替换为红宝石

        2     0   0   0   0   A   A   A   A   R   R   R       白银    背包容量为9的时候，可以装下黄金和白银，但是它们加起来也没有红宝石的价值高，所以还是装红宝石

        3     0   D   D   D   D   DA  DA  DA  DA  DR  DR      钻石    背包容量为4的时候，虽然可以装黄金，但是钻石价值更高，还是装钻石；容量为9的时候，红宝石 + 钻石 > 黄金 + 钻石， 替换为红宝石和钻石

        |
       物品 i



        if(装不下) {
            dp[i][j] = dp[i-1][j]
        } else { 装得下

            dp[1][8] = max(dp[0][8], R) = R
            dp[3][5] = max(dp[2][5], D + dp[2][4]) = DA

            dp[i][j] = max(dp[i-1][j], item.value + dp[i-1][j-item.weight])
        }
     */

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
                new Item(1, "黄金", 4, 1600),
                new Item(2, "宝石", 8, 2400),
                new Item(3, "白银", 5, 30),
                new Item(4, "钻石", 1, 10_000),
        };
        System.out.println(select2(items, 10));
    }

    /**
     * 解法1 - 使用二维数组存储dp
     * @param items 物品的数组
     * @param total 背包的容量
     * @return  最大价值
     */
    static int select1(Item[] items, int total) {
        //定义一个二维数组，行数：物品数，列数：背包容量 + 1
        int[][] dp = new int[items.length][total+1];
        //对第1行（索引为0）的数据要进行特殊处理，因为带回要进行“i-1”的计算
        Item item0 = items[0];  //第1个物品
        for (int j = 0; j < total + 1; j++) {  //遍历第一行，进行赋值
            if(j >= item0.weight){
                dp[0][j] = item0.value;
            }else{
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < items.length; i++) {
            Item item = items[i];
            for (int j = 0; j < total + 1; j++) {
                if(j < item.weight) {   //装不下
                    dp[i][j] = dp[i-1][j];
                } else {   //装得下
                    dp[i][j] = Math.max(dp[i-1][j], item.value + dp[i-1][j-item.weight]);
                }
            }
        }

        print(dp);
        return dp[items.length - 1][total];

    }

    /**
     * 解法2 - 使用一维数组存储dp
     * @param items 物品的数组
     * @param total 背包的容量
     * @return  最大价值
     */
    static int select2(Item[] items, int total) {

        int[] dp = new int[total+1];
        Item item0 = items[0];  //第1个物品
        for (int j = 0; j < total + 1; j++) {
            if(j >= item0.weight){
                dp[j] = item0.value;
            }else{
                dp[j] = 0;
            }
        }

        for (int i = 1; i < items.length; i++) {
            Item item = items[i];
            for (int j = total; j >= 0; j--){     //逆序遍历以防止计算dp[j-item.weight]找到的是正确的值
                if(j >= item.weight) {   //装得下
                    dp[j] = Math.max(dp[j], item.value + dp[j-item.weight]);
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[total];

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
}

package Algorithms.Greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 零钱兑换
 * 贪心解法：可能得到错误的解
 */
public class LeetCode0322CoinChange2 {
    public int coinChange(int[] coins, int amount){
        /*
           总金额 18
           5  2  1

           3*5 3
           1*2 1
           1*1 1

           贪心的原则：
           1. 每一次都选取当前最优解
           2. 向前看，别回头

           几个有问题的情况：

           1. 总金额 21
           15 10 1

           1*15 1
           6*1  6
           总共用了7个硬币（并不是最优解）

           2. 总金额 20
           15 10

           1*15 5 无解（其实是有解的）


         */
        Arrays.sort(coins);
        int remainder = amount;
        int count = 0;
        for(int i = coins.length -1; i >= 0;i--){
            while(remainder > coins[i]){
                remainder -= coins[i];
                count++;
            }
            if(remainder == coins[i]){
                remainder = 0;
                count++;
                break;
            }
        }
        if(remainder > 0){
            return -1; //没找到
        }else{
            return count;
        }

    }




    public static void main(String[] args) {
        LeetCode0322CoinChange2 test = new LeetCode0322CoinChange2();
//        int count = test.coinChange(new int[]{1,2,5},18);
//        int count = test.coinChange(new int[]{10,25,5,1},41);
//        int count = test.coinChange(new int[]{2}, 3);
        //问题1: 没有回头，导致找到更差的解
//        int count = test.coinChange(new int[]{1,10,15},21);
        //问题2: 没有回头，导致无解
        int count = test.coinChange(new int[]{10,15},20);
        System.out.println(count);

    }


}

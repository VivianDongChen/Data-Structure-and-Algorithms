package Algorithms.Greedy;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 零钱兑换 - 穷举解法（暴力递归）
 * 凑成总金额的凑法中，需要硬币最少个数是几？
 */
public class LeetCode_0322_I {
    static int min = -1; //需要的最少硬币数， -1为没有凑法
    public int coinChange(int[] coins, int amount){

        rec(coins.length-1,coins,amount,new AtomicInteger(-1),new LinkedList<>(),true);
        return min;
    }

    /**
     * 求凑成剩余金额的解的个数
     * @param index 当前硬币索引
     * @param coins 硬币面值数组
     * @param remainder 剩余金额
     * @param count 代表某一组合中钱币的总数
     * @param stack 用来盛放组合的元素
     * @param first 用来判断是否需要将元素放入stack
     */
    public void rec(int index, int[] coins, int remainder, AtomicInteger count, LinkedList<Integer> stack, boolean first){
        if(!first){
            stack.push(coins[index]);

        }
        first = false;
        count.incrementAndGet(); //count++

        if(remainder == 0){
            System.out.println(stack);
            if(min == -1){
                min = count.get();
            }else{
                min = Integer.min(min,count.get());
            }
        }
        else if(remainder > 0){
            for (int i = index; i >= 0; i--) {
                rec(i,coins,remainder - coins[i],count,stack,first);
            }
        }
        count.decrementAndGet(); //count--

        if(!stack.isEmpty()){
            stack.pop();
        }

    }

      /*                           count
     rec(5,5)                     0
        rec(5,0)                  1 -> 0
        rec(2,3)                  1 -> 0
            rec(2,1)              2 -> 1
                rec(2,-1)
                rec(1,0)          3 -> 2
            rec(1,2)
                rec(1,1)
                    rec(1,0) 1
        rec(1,4)
            rec(1,3)
                rec(1,2)
                    rec(1,1)
                        rec(1,0) 1
     */


    public static void main(String[] args) {
        LeetCode_0322_I test = new LeetCode_0322_I();
//        int count = test.coinChange(new int[]{1,2,5},5);
//        int count = test.coinChange(new int[]{1,5,10,25},41);
//        int count = test.coinChange(new int[]{2}, 3);
        int count = test.coinChange(new int[]{1,10,15},21);
        System.out.println(count);

    }


}

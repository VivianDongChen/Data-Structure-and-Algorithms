package Algorithms.Greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 零钱兑换 II - 穷举解法优化 - 元素由大到小进行递归
 * 可以凑成总金额所需的所有组合可能数
 */
public class LeetCode0518CoinChangeII2 {
    public int coinChange(int[] coins, int amount){

        return rec(coins.length-1,coins,amount,new LinkedList<>(),true); //起点为最大的元素
    }

    /**
     * 求凑成剩余金额的解的个数
     * @param index 当前硬币索引
     * @param coins 硬币面值数组
     * @param remainder 剩余金额
     * @param stack 用来盛放组合的元素
     * @param first 用来判断是否需要将元素放入stack
     * @return 解的个数
     */
    public int rec(int index, int[] coins, int remainder, LinkedList<Integer> stack, boolean first){
        if(!first){
            stack.push(coins[index]);
        }
        first = false;

        int count = 0;
        //情况1: 剩余金额 < 0 - 无解
        if(remainder < 0){
            print("无解", stack);

        }
        //情况2: 剩余金额 == 0 - 有解
        else if(remainder == 0){
            print("有解",stack);
            count = 1;
        }
        //情况3: 剩余金额 > 0 - 继续递归(多路）
        else{
            for (int i = index; i >= 0; i--) {   //由大到小进行递归
                count += rec(i,coins,remainder - coins[i],stack,first);
            }
        }
        if(!stack.isEmpty()){
            stack.pop();
        }
        return count;
        /*
          rec(1, 5)
            rec(1, 4)
                rec(1, 3)
                    rec(1, 2)
                        rec(1, 1)
                            rec(1, 0)  1
                            rec(2, -1)  0
                            rec(5, -4)  0
                        rec(2, 0)  1
                        rec(5, -3)  0
                    rec(2, 1)
                        rec(2, -1)  0
                        rec(5, -4)  0
                    rec(5, -2)  0
                rec(2, 2)
                    rec(2, 0)  1
                    rec(5, -3)  0
                rec(5, -1)  0
            rec(2, 3)
                rec(2, 1)
                    rec(2, -1) 0
                    rec(5, -4) 0
                rec(5，-2） 0
            rec(5, 0)  1

         */
    }

    private static void print(String prompt, LinkedList<Integer> stack) {
        ArrayList<Integer> print = new ArrayList<>();
        ListIterator<Integer> iterator = stack.listIterator(stack.size()); //从stack尾部开始的迭代器
        while (iterator.hasPrevious()) {    //往前逐个加入列表print
            print.add(iterator.previous());
        }
        System.out.println(prompt + print);
    }

    public static void main(String[] args) {
        LeetCode0518CoinChangeII2 test = new LeetCode0518CoinChangeII2();
        int count = test.coinChange(new int[]{1,2,5},5);
        System.out.println(count);

    }

    /*
    由（元素）大到小递归
     rec(5,5)
        rec(5,0) 1
        rec(2,3)
            rec(2,1)
                rec(2,-1) 0
                rec(1,0) 1
            rec(1,2)
                rec(1,1)
                    rec(1,0) 1
        rec(1,4)
            rec(1,3)
                rec(1,2)
                    rec(1,1)
                        rec(1,0) 1
     */

}

package Algorithms.Greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 零钱兑换 II - 穷举解法(效率低）
 * 可以凑成总金额所需的所有组合可能数
 */
public class LeetCode0518CoinChangeII1 {
    public int coinChange(int[] coins, int amount){
        return rec(0,coins,amount,new LinkedList<>(),true);
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
        if(!first){  //不是第一次调用才放入栈，第一次还没有进行计算
            stack.push(coins[index]);
        }
        first = false;   //后面的都不是第一次调用了

        int count = 0;
        //情况1: 剩余金额 < 0 - 无解
        if(remainder < 0){
            print("无解", stack);
            //count 保持 0
        }
        //情况2: 剩余金额 == 0 - 有解
        else if(remainder == 0){
            print("有解",stack);
            count = 1;
        }
        //情况3: 剩余金额 > 0 - 继续递归(多路）
        else{
            for (int i = index; i < coins.length; i++) {
                count += rec(i,coins,remainder - coins[i],stack,first);
            }
        }
        if(!stack.isEmpty()){   //删除栈顶元素，好继续下一轮的组合
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
        LeetCode0518CoinChangeII1 test = new LeetCode0518CoinChangeII1();
        int count = test.coinChange(new int[]{1,2,5},5);
        System.out.println(count);

    }

}

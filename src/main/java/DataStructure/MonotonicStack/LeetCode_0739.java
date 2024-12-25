package DataStructure.MonotonicStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * index中的元素对应的temperature是递减的
 */
public class LeetCode_0739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> index = new LinkedList<>();
        for(int i = 0; i < temperatures.length; i++){
            //这个 stack 中存储的是 索引（index），而不是具体的温度值。虽然栈本身的元素（索引）没有要求单调递减，
            //但这些索引所对应的温度值 (temps[index]) 是单调递减的。
            while(! index.isEmpty() && temperatures[i] > temperatures[index.peek()]){
                res[index.peek()] = i - index.pop();
            }
            index.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_0739 test = new LeetCode_0739();
        // expected：[1, 1, 4, 2, 1, 1, 0, 0]
        System.out.println(Arrays. toString(test.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
 }

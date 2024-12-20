package DataStructure.MonotonicStack;

import java.util.LinkedList;

/**
 * 接雨水 - 单调栈
 * 单调栈：一种专门的数据结构，用于在各种算法问题中维护按特定顺序排列的元素序列。它在解决与查找下一个更大或更小的元素、区间查询和滑动窗口最大值相关的问题时特别有用。
 */
public class LeetCode_0042 {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5})); // 9
    }
    static int trap(int[] heights){
        //定义一个单调栈，里面存放Data数据
        LinkedList<Data> stack = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            Data right = new Data(heights[i], i);    //这个柱子如果满足下面循环的条件，就是凹陷处右侧的柱子
            //如果栈顶元素的height比待放入元素的height小，就弹出栈顶元素（相当于实现了一个单调递增栈），然后再放入新元素，这样栈顶始终是栈中最小元素
            while(!stack.isEmpty() && stack.peek().height < right.height){
                //每次弹出的时候说明找到了凹处
                Data pop = stack.pop();   //凹陷处的柱子
                Data left = stack.peek(); //左侧的柱子
                if(left != null){   //计算雨水
                    int width = right.i - left.i - 1;
                    int height = Math. min(left.height, right.height) - pop.height;
                    sum += width * height;
                }
            }
            stack.push(right);
//            System.out.println(stack);
        }

        return sum;


    }

    /**
     * 柱子的类，包含柱子的高度和索引
     */
    static class Data{
        int height; //柱子的高度
        int i; //柱子的索引

        public Data(int height, int i) {
            this.height = height;
            this.i = i;
        }

        @Override
        public String toString() {
            return String.valueOf(height);
        }
    }
}

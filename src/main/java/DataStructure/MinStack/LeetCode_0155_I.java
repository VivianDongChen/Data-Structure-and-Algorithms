package DataStructure.MinStack;

import java.util.LinkedList;

/**
 * 最小栈 - 使用两个栈结构实现
 */
public class LeetCode_0155_I {

    static class MinStack{
        LinkedList<Integer> stack = new LinkedList<>();   //存放所有值的栈
        LinkedList<Integer> min = new LinkedList<>();   //存放最小值的栈

        public MinStack(){
            min.push(Integer.MAX_VALUE);   //min初始值为无穷大
        }

        /**
         * 添加元素到栈顶
         * @param val 待添加元素
         */
        public void push(int val){
            stack.push(val);
            min.push(Math.min(val, min.peek()));   //min每次将stack中的最小值放入栈顶
        }

        /**
         * 弹出栈顶元素
         */
        public void pop(){
            if(stack.isEmpty()){  //避免将MAX_VALUE弹出
                return;
            }
            stack.pop();
            min.pop();
        }

        /**
         * 获取栈顶元素（不弹出）
         * @return 栈顶元素
         */
        public int top(){
            return stack.peek();
        }

        /**
         * 获取最小值（不弹出）
         * @return 最小值
         */
        public int getMin(){
             return min.peek();
        }

    }
}

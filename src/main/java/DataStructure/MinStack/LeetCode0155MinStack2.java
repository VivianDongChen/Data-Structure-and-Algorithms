package DataStructure.MinStack;

import java.util.LinkedList;

/**
 * 最小栈 - 使用一个栈结构实现
 */
public class LeetCode0155MinStack2 {

    static class MinStack{
        /*在Java中，record是一种特殊的类，用于简化数据对象的定义。它自动生成常用的方法，
          如构造函数、equals()、hashCode()和toString()方法。它里面的值都是final，不能更改
         */
        record Data(int val, int min){}
        LinkedList<Data> stack = new LinkedList<>();


        /**
         * 添加元素到栈顶
         * @param val 待添加元素
         */
        public void push(int val){
            if(stack.isEmpty()){    //一开始栈为空的时候
                stack.push(new Data(val,val));
            }else{
                stack.push(new Data(val, Math.min(val, stack.peek().min)));
            }
        }

        /**
         * 弹出栈顶元素
         */
        public void pop(){
            stack.pop();
        }

        /**
         * 获取栈顶元素（不弹出）
         * @return 栈顶元素
         */
        public int top(){
            return stack.peek().val();
        }

        /**
         * 获取最小值（不弹出）
         * @return 最小值
         */
        public int getMin(){
             return stack.peek().min();
        }

    }
}

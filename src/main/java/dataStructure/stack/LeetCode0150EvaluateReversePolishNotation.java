package dataStructure.stack;

import java.util.LinkedList;

/**
 * 逆波兰表达式也成后缀表达式，即运算符写在后面
 * - 从左到右进行计算
 * - 不必考虑运算符优先级，即不用包含括号
 * - 计算机使用比较方便
 *
 *   1 + 2    中缀
 *   1 2 +    后缀
 */
public class LeetCode0150EvaluateReversePolishNotation {
    /**
     * |   |
     * |   |
     * |   |
     * |   |
     * |   |
     * |   |
     * -----
     *  "4", "13", "5","/", "+"
     *
     *  - 遇到数字压入栈
     *  - 遇到运算符，就从栈中弹出两个数字做运算，将结果压入栈
     *  - 遍历结束，栈中剩下的数字就是结果
     *
     */
    public int evalRPN(String[] token){

        LinkedList<Integer> stack = new LinkedList<>();

        for(String t: token){
            switch (t){
                case "+" ->{
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a + b);
                }
                case "-" ->{
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);

                }
                case "*" ->{
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);

                }
                case "/" ->{
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);

                }
                default ->{
                    stack.push(Integer.parseInt(t));
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {

        String[] token = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};

        System.out.println(new LeetCode0150EvaluateReversePolishNotation().evalRPN(token));

        String[] token1 = {"2","1","+","3","*"};

        System.out.println(new LeetCode0150EvaluateReversePolishNotation().evalRPN(token1));


    }
}

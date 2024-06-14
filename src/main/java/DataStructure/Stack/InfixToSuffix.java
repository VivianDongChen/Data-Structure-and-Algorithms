package DataStructure.Stack;

import java.util.LinkedList;

// 中缀表达式转后缀表达式
public class InfixToSuffix {
    /**
     *
     * 思路
     *
     * ｜   ｜
     * ｜   ｜
     * ｜   ｜
     * ｜   ｜
     * ｜   ｜
     * -------
     *
     * a+b           ab+
     * a+b-c         ab+c-
     * a*b+c         ab*c+
     * a+b*c         abc*+
     * a+b*c-d       abc*+d-
     * (a+b)*c       ab+c*
     * (a+b*c-d)*e   abc*+d-e*
     * a*(b+c)       abc+*
     *
     * 1. 遇到非运算符 直接拼串
     * 2. 遇到 + - * /
     *    - 它的优先级比栈顶运算符高，入栈
     *    - 否则把栈里优先级>=它的都出栈，它再入栈
     * 3. 带（ ）
     *    - 左括号直接入栈，左括号优先级设置为0
     *    - 右括号，就把栈中到左括号为止的所有运算符都出栈
     * 4. 遍历完成，栈里剩余运算符依次出栈
     */
    static String infixToSuffix(String exp){
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());

        for(int i = 0; i < exp.length(); i++){
            char e = exp.charAt(i);
            switch (e){
                case '+','-','*','/' ->{
                    if (stack.isEmpty() || priority(e) > priority(stack.peek())){
                        stack.push(e);
                    } else {
                        while(!stack.isEmpty() && priority(e) <= priority(stack.peek())){
                            sb.append(stack.pop());
                        }
                        stack.push(e);
                    }
                }
                case '(' ->{
                    stack.push(e);
                }
                case ')' ->{
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    sb.append(e);
                }
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();

    }

    static int priority(char c){

        return switch (c){
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("不合法的运算符：" + c);
        };

    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix( "a+b"));
        System.out.println(infixToSuffix( "a+b-c"));
        System.out.println(infixToSuffix( "a+b*c"));
        System.out.println(infixToSuffix( "a*b-c"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));
    }
}

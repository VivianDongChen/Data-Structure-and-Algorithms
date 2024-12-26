package DataStructure.MonotonicStack;

import java.util.Stack;

public class LeetCode_0901 {

    Stack<int[]> stack;
    public LeetCode_0901() {
        stack = new Stack<>();
    }

    public int next(int price){
        int span = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        LeetCode_0901 test = new LeetCode_0901();
        System.out.println(test.next(100));
        System.out.println(test.next(80));
        System.out.println(test.next(60));
        System.out.println(test.next(70));
        System.out.println(test.next(60));
        System.out.println(test.next(75));
        System.out.println(test.next(85));
    }
}

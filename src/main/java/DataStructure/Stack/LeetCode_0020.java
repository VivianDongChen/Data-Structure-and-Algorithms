package DataStructure.Stack;

public class LeetCode_0020 {
    /**
     *  ( [
     *
     *  底 - 栈 - 顶
     *  ）]
     *
     *  遇到左括号，把要配对的右括号放入栈顶
     *  遇到右括号，把它与栈顶元素对比
     *      - 若相等，弹出栈顶元素，继续对比下一组
     *      - 若不等，无效括号，直接返回false
     */
    public boolean isValid(String s){
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                stack.push(')');
            }else if(c == '[') {
                stack.push(']');
            }else if(c == '{') {
                stack.push('}');
            }else{
                if(!stack.isEmpty() && c == stack.peek()){  //走到这一步，如果说stack还是空的话，说明是以右括号开头的，// 也是无效情况，所以else返回false
                    stack.pop();
                }else{
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {

        LeetCode_0020 s = new LeetCode_0020();
        System.out.println(s.isValid("()"));
        System.out.println(s.isValid("()[]{}"));
        System.out.println(s.isValid("{()[]}"));
        System.out.println(s.isValid("{([])}"));
        System.out.println("------------------------");

        System.out.println(s.isValid("("));
        System.out.println(s.isValid("[)"));
        System.out.println(s.isValid("([)]"));
        System.out.println(s.isValid("(}]"));

        System.out.println("------------------------");
        System.out.println(s.isValid("]"));
        System.out.println(s.isValid(")("));



    }
}

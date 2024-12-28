package DataStructure.Stack;

import java.util.LinkedList;

/**
 * 224. Basic Calculator
 * 本题目的关键在于理解题目：
 * - 基础计算只有+，-运行，表达式里面只有数字，+， - ，（， ）
 * - 所以不会能出现1+2（3+4），因为没有乘法，所以只有1+2+（3+4)这种情况
 * - 所以“）”前面的数字其余的数字后面都会跟着运算符，而这个运算符就代表了其后面的数字或者括号的正负
 */
public class LeetCode_224 {
    public int calculate(String s){
        LinkedList<Integer> stack = new LinkedList<>(); //stack里面放integer便于计算，所以下面符号也要转换为+1/-1
        int currNumber = 0;
        int sign = 1;
        int res = 0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);   //指针目前指的字符
            if(Character.isDigit(c)){
                currNumber = currNumber * 10 + (int)(c-'0'); //将字符c转化为int，然后极端currNumber
            }else if(c == '-'){    //遇到运算符说明已经确认了前面的currNumber，可以将前面的currNumber加入res
                res += sign * currNumber;
                currNumber = 0;
                sign = -1;
            }else if (c == '+'){
                res += sign * currNumber;
                currNumber = 0;
                sign = 1;
            }else if(c == '('){      //遇到（， 将前面计算的res和sign放入stack，以便在括号结束时引用
                stack.push(res);
                stack.push(sign);
                res = 0;   //重置res和sign来极端括号内的数值
                sign = 1;
            }else if(c == ')'){
                res += sign * currNumber;
                res *= stack.pop();
                res += stack.pop();
                currNumber = 0;
            }
        }
        //循环结束后，可能所有的number都已经加了，也有可能没有（currNumber不为0）
        if(currNumber != 0){
            res += sign * currNumber;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_224 test = new LeetCode_224();
        System.out.println(test.calculate(" 2-1 + 2 "));
        System.out.println(test.calculate(" 1+1 "));
    }

}

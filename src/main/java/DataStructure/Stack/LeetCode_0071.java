package DataStructure.Stack;

import java.util.LinkedList;
/**
 * 71. Simplify Path
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class LeetCode_0071 {
    public String simplifyPath(String path){
        LinkedList<String> stack = new LinkedList<>();
        // 用“/”分割路径字符串，存储为一个字符串数组，分割结果中不包含“/”
        String[] split = path.split("/");
        for (String s: split) {
            if (s .isEmpty() || s.equals(".") ) {  //s.isEmpty()说明遇到了“//”或者"///"的情况
                continue;
            }else if (s. equals("..") ) {
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(s);  // 添加有效路径片段
            }
        }
        //construct the String
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.insert(0,"/" + stack.pop());   //插入到索引0；
        }
        return res.length() > 0 ? res.toString() : "/";
    }

    public static void main(String[] args) {
        LeetCode_0071 test = new LeetCode_0071();
        System.out.println(test.simplifyPath("/home/"));
    }
}

package DataStructure.BinaryTree;

import java.util.LinkedList;

/**
 * 根据后缀表达式构造表达式树
 */
public class ExpressionTree {

    static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }


    }
    /*
           中缀表达式     （2-1）*3
           后缀表达式      21-3*

           栈
           1. 遇到数字入栈
           2. 遇到运算符出栈， 建立节点关系，再入栈

           栈
           ｜   ｜
           ｜   ｜
           ｜   ｜
           ------

           ’-‘ . right = 1
           '-' . left = 2

           '*'. right = 3
           '*'. left = '-'

           表达式树
              *
            /   \
           -     3
         /   \
        2     1

        将表达式树后序遍历就是原后缀表达式

     */


    public TreeNode contructExpressionTree(String[] tokens){

        LinkedList<TreeNode> stack = new LinkedList<>();

        for(String t : tokens ){
            switch (t){
                case "+","-","*","/" ->{   //运算符
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(t);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                default -> {   //数字
                    stack.push(new TreeNode(t));
                }
            }
        }
        return stack.peek();
    }
}


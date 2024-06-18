package DataStructure.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树遍历 - 非递归实现
 */
public class TreeTraversal2 {

    private static void traversal(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode curr = node; //代表当前节点
        TreeNode pop = null;  //最近一次弹栈的元素

        while(curr != null || !stack.isEmpty()){

            if(curr != null){
                stack.push(curr);
                colorPrintln("前序遍历：" + curr.val,31);
                //待处理左子树
                curr = curr.left;
            //左子树处理完了
            }else{
                TreeNode peek = stack.peek();
                //没有右子树
                if(peek.right == null ){
                    colorPrintln("中序遍历：" + peek.val,36);
                    pop = stack.pop();
                    colorPrintln("后序遍历：" + pop.val,48);
                //右子树处理完了
                }else if(peek.right == pop){
                    pop = stack.pop();
                    colorPrintln("后序遍历：" + pop.val,48);
                }else {
                    colorPrintln("中序遍历：" + peek.val,36);
                    //待处理右子树
                    curr = peek.right;
                }
            }
        }
    }

    public static void colorPrintln(String origin, int color){
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode
                (new TreeNode(new TreeNode(4),
                        2,
                        null),
                        1,

                        new TreeNode(new TreeNode(5),
                                3,
                                new TreeNode(6))
                );
        traversal(root);
    }
}

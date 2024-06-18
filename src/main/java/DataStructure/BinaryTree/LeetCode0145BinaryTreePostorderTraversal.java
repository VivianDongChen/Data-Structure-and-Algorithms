package DataStructure.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树后序遍历 - 非递归
 */
public class LeetCode0145BinaryTreePostorderTraversal {

    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        traversal(result,root);
        return result;
    }

    private static void traversal(List<Integer> list, TreeNode node){

        TreeNode curr = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null; //最近一次弹栈的元素

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode peek = stack.peek();
                if(peek.right == null || peek.right == pop){  //右子树为null或处理完了， 将中间节点弹出
                    pop = stack.pop();
                    list.add(pop.val);
                }else{  //处理右子树
                    curr = peek.right;
                }
            }
        }
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
        List<Integer> result = postorderTraversal(root);

        for(int num : result){
            System.out.print(num + "\t");
        }
    }
}

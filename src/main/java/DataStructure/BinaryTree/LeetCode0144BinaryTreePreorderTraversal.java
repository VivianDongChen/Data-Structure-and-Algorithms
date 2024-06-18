package DataStructure.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树前序遍历 - 非递归
 */
public class LeetCode0144BinaryTreePreorderTraversal {
    public static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        traversal(result,root);
        return result;

    }

    public static void traversal(List<Integer> list, TreeNode node){
        TreeNode curr = node; //代表当前节点
        LinkedList<TreeNode> stack = new LinkedList<>();    //使用栈来记录走过的节点

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                list.add(curr.val);
                stack.push(curr);  //压入栈，为了记住回来的路
                curr = curr.left;
            }else{
                TreeNode pop = stack.pop();
                curr = pop.right;
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
        List<Integer> result= preorderTraversal(root);

        for(int num : result){
            System.out.print(num + "\t");
        }
    }


}

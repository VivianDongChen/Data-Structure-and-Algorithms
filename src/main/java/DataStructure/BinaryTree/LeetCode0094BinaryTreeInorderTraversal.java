package DataStructure.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中序遍历 - 非递归
 */
public class LeetCode0094BinaryTreeInorderTraversal {
    public static List<Integer> inOrderTraversal(TreeNode root){

        List<Integer> result = new ArrayList<>();
        traversal(result,root);
        return result;
    }

    private static void traversal(List<Integer> list, TreeNode root) {
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode pop = stack.pop();
                list.add(pop.val);
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
        List<Integer> result = inOrderTraversal(root);

        for(int num : result){
            System.out.print(num + "\t");
        }
    }
}

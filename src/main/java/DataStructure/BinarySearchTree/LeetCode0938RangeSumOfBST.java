package DataStructure.BinarySearchTree;


import com.sun.source.tree.Tree;

import java.util.LinkedList;

/**
 * 求范围和
 */
public class LeetCode0938RangeSumOfBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {};
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 方法1: 中序遍历非递归
     */
    public int rangeSumBST1(TreeNode node,int low,int high) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                //处理值
                if(pop.val > high){
                    break;
                }
                if (low <= pop.val) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;

    }

    /**
     * 方法2: 上下限递归
     */
    public int rangeSumBST2(TreeNode node,int low,int high) {
        if(node == null){
            return 0;
        }
        if(node.val < low){
            return rangeSumBST2(node.right,low,high);
        }
        if(node.val > high){
            return rangeSumBST2(node.left,low,high);
        }
        //在范围内
        return node.val + rangeSumBST2(node.left, low, high) +rangeSumBST2(node.right, low, high);

    }



    public static void main(String[] args) {
        /*
                   10
                 /    \
                5      15
              /   \      \
             3     7     18

         */
        TreeNode n1 = new TreeNode(5, new TreeNode(3),new TreeNode(7));
        TreeNode n2 = new TreeNode(15,null,new TreeNode(18));
        TreeNode root = new TreeNode(10,n1,n2);

        int sum = new LeetCode0938RangeSumOfBST().rangeSumBST2(root, 7,15);
        System.out.println(sum);  //32


    }
}

package DataStructure.BinarySearchTree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST
 * DFS in-order iterative
 * 时间复杂度：O(k+h)
 * 空间复杂度：O(h)
 */
public class LeetCode_0230_III {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(k >= 0){
            TreeNode pop = stack.pop();
            k--;
            if(k == 0){
                return pop.val;
            }
            TreeNode right = pop.right;
            while(right != null){
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LeetCode_0230_III test = new LeetCode_0230_III();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        int k = 1;
        int result = test.kthSmallest(root, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}

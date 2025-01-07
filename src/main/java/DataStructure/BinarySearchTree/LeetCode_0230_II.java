package DataStructure.BinarySearchTree;

import com.sun.source.tree.Tree;

/**
 * Kth Smallest element in a BST
 * DFS in-order recursive
 */
public class LeetCode_0230_II {
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
    private static int count = 0;
    private static int number = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    private void helper(TreeNode node) {
        if (node.left != null) {
            helper(node.left);
        }
        count--;
        if (count == 0) {
            number = node.val;
            return;
        }
        if (node.right != null) {
            helper(node.right);
        }
    }

    public static void main(String[] args) {
        LeetCode_0230_II test = new LeetCode_0230_II();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        int k = 1;
        int result = test.kthSmallest(root, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }

}

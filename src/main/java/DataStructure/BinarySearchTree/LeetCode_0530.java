package DataStructure.BinarySearchTree;

import org.w3c.dom.ls.LSOutput;

/**
 * Minimum Absolute Difference in BST
 * 利用BST的中序遍历的特点：中序遍历的节点值按从小带大排列，所以相邻节点的差值是最小的
 */
public class LeetCode_0530 {

     public class TreeNode {
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

     int min = Integer.MAX_VALUE;
     Integer prev = null;
     public int getMinimumDifference(TreeNode root) {
         if(root == null){
             return min;
         }
         getMinimumDifference(root.left);
         if(prev != null){
             min = Math.min(min, root.val - prev);
         }
         prev = root.val;
         getMinimumDifference(root.right);
         return min;
     }

    public static void main(String[] args) {
        LeetCode_0530 test = new LeetCode_0530();
        TreeNode root = test.new TreeNode(4);
        root.left = test.new TreeNode(2);
        root.right = test.new TreeNode(6);
        root.left.left = test.new TreeNode(1);
        root.left.right = test.new TreeNode(3);
        System.out.println(test.getMinimumDifference(root));
    }
}

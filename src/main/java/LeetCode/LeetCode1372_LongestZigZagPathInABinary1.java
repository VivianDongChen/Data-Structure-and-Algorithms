package LeetCode;

public class LeetCode1372_LongestZigZagPathInABinary1 {

     class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode() {
         }

         TreeNode(int val) {
             this.val = val;
         }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

         int max = 0;

     public int longestZigZag(TreeNode root) {
         if (root == null) {
             return 0;
         }

         dfs(root, 0, 0);
         dfs(root, 1, 0);

         return max;
     }

     private void dfs(TreeNode root, int isLeft, int length) {
         if (root == null) {
             return;
         }

         max = Math.max(max, length);

         if (isLeft == 1) {
             dfs(root.right, 0, length + 1);
             dfs(root.left, 1, 1);
         } else {
             dfs(root.left, 1, length + 1);
             dfs(root.right, 0, 1);
         }
     }

    public static void main(String[] args) {
        LeetCode1372_LongestZigZagPathInABinary1 solution = new LeetCode1372_LongestZigZagPathInABinary1();

        TreeNode root = solution.new TreeNode(1);
        root.right = solution.new TreeNode(1);
        root.right.left = solution.new TreeNode(1);
        root.right.right = solution.new TreeNode(1);
        root.right.left.left = solution.new TreeNode(1);
        root.right.left.right = solution.new TreeNode(1);
        root.right.left.right.right = solution.new TreeNode(1);

        System.out.println(solution.longestZigZag(root)); // Expected output: 3
    }
}
package DataStructure.BinaryTree;

/**
 * 112. Path Sum
 */
public class LeetCode_0112 {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return root.val == targetSum;
        }

        boolean hasPathSumLeft = hasPathSum(root.left, targetSum - root.val);
        boolean hasPathSumRight = hasPathSum(root.right, targetSum - root.val);

        return hasPathSumLeft || hasPathSumRight;
    }

    public static void main(String[] args) {
        LeetCode_0112 solution = new LeetCode_0112();

        // 构造二叉树 [5,4,8,11,null,13,4,7,2,null,null,null,1]
        TreeNode root = solution.new TreeNode(5);
        root.left = solution.new TreeNode(4);
        root.right = solution.new TreeNode(8);

        root.left.left = solution.new TreeNode(11);
        root.left.left.left = solution.new TreeNode(7);
        root.left.left.right = solution.new TreeNode(2);

        root.right.left = solution.new TreeNode(13);
        root.right.right = solution.new TreeNode(4);
        root.right.right.right = solution.new TreeNode(1);

        // 目标和
        int targetSum = 22;
        System.out.println(solution.hasPathSum(root, targetSum));
    }
}

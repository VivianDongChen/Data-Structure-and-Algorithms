package DataStructure.BinaryTree;

/**
 * 124. Binary Tree Maximum Path Sum
 * 贪心思维的递归问题
 * 时间复杂度O(n)：
 * 每个节点只会被访问一次（递归遍历）。
 * 每个递归函数的时间复杂度为O(1)，因此总时间复杂度为O(n)，其中n 是节点总数。
 * 空间复杂度O(h)：
 * 递归的空间复杂度由调用栈决定，最大栈深为树的高度h。
 * 在平衡树中，h=log(n)；在最坏情况下（链状树），h=n。
 *
 * 难点：理解max是左+中+右，left = 左/右 + 中， right = 左/右 + 中
 */
public class LeetCode_0124 {
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

    int max = Integer.MIN_VALUE;   //定义一个全局变量，这个值是 32 位整数的最小值，表示为 -2147483648
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(helper(root.left),0);  //跟0比较取大的，意思就是如果这一支是负数的话，就舍去这一支
        int right = Math.max(helper(root.right),0);
        //每一层递归都会计算max，max其实每次都是取root.val, 然后最后用Math函数找到最大值（全部都是负数的情况也可以使用这个逻辑）
        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);   //每一层返回的值是左或右支 + 本层节点
    }

    public static void main(String[] args) {
        LeetCode_0124 solution = new LeetCode_0124();
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(2);
        root.right = solution.new TreeNode(3);

        System.out.println(solution.maxPathSum(root));
    }
}

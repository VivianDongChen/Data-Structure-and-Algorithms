package DataStructure.BinarySearchTree;

/**
 * 查询节点
 */
public class LeetCode_0700 {

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
     * 递归实现
     */
    public TreeNode searchBST1(TreeNode node, int val) {
        if(node == null || node.val == val){
            return node;
        }
        return node.val < val ? searchBST1(node.right, val): searchBST1(node.left, val);
    }

    /**
     * 非递归实现
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while(root != null){
            if(root.val < val){
                root = root.right;
            }else if(val < root.val){
                root = root.left;
            }else{
                return root;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCode_0700 solution = new LeetCode_0700();

        // 构建如下的 BST:
        //         4
        //       /   \
        //      2     7
        //     / \
        //    1   3

        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7));

        // 测试递归
        TreeNode result1 = solution.searchBST1(root, 2);
        System.out.println("递归查找 2，结果节点值为: " + (result1 != null ? result1.val : "null"));

        // 测试非递归
        TreeNode result2 = solution.searchBST2(root, 3);
        System.out.println("非递归查找 3，结果节点值为: " + (result2 != null ? result2.val : "null"));
    }
}


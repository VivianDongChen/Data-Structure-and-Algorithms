package DataStructure.BinaryTree;

/**
 * 反转二叉树 - 递归
 */
public class LeetCode0226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root){
        fn(root);
        return root;
    }

    public void fn(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode t = node.right;
        node.right = node.left;
        node.left = t;

        fn(node.right);
        fn(node.left);

    }
}

package DataStructure.BinaryTree;


public class LeetCode0101SymmetricTree {

    public boolean isSymmetric(TreeNode root){
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if(left == null && right == null){
            return true;
        }
        // left 和 right不会同时为null
        if(left == null || right == null){
            return false;
        }

        if (left.val != right. val){
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}

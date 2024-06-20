package DataStructure.BinarySearchTree;

/**
 * 查询节点
 */
public class LeetCode0700SearchInaBinarySearchTree {

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
        if(node == null){
            return null;
        }
        if(node.val < val){
            return searchBST1(node.right,val);
        }else if(val < node.val){
            return searchBST1(node.left,val);
        }else{
            return node;
        }
    }

    /**
     * 非递归实现
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        TreeNode p = root;
        while(p != null){
            if(p.val < val){
                p = p.right;
            }else if(val < p.val){
                p = p.left;
            }else{
                return p;
            }
        }
        return null;
    }
}

package DataStructure.BinarySearchTree;

/**
 * 新增节点（题目前提：val一定与树中节点不同）
 */
public class LeetCode0701InsertIntoaBinarySearchTree {

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
     *
     *     val = 7
     *
     *         5
     *       /   \
     *      2     6
     *    /  \     \
     *   1   3      7
     *
     */
    public TreeNode insertIntoBST1(TreeNode node,int val){
        if(node == null){
            return new TreeNode(val);
        }
        if(val < node.val){
            node.left = insertIntoBST1(node.left,val);
        }else if( node.val < val){
            node.right = insertIntoBST1(node.right, val);
        }
        return node;
    }

    /**
     * 非递归实现
     */
    public TreeNode insertIntoBST2(TreeNode root,int val){
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode p = root;
        TreeNode parent = null;
        while(p != null){
            parent = p;
            if(p.val < val){
                p = p.right;
            }else if(val < p.val){
                p = p.left;
            }
        }
        TreeNode newNode = new TreeNode(val);

        if(val < parent.val){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        return root;
    }

}

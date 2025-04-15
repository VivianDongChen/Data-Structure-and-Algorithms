package DataStructure.BinarySearchTree;

/**
 * 新增BST的节点（题目前提：val一定与树中节点不同）
 */
public class LeetCode_0701 {

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

    public static void printInOrder(LeetCode_0701.TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        LeetCode_0701 solution = new LeetCode_0701();

        // Construct the initial BST:
        //         5
        //       /   \
        //      2     6
        //    /  \
        //   1   3
        LeetCode_0701.TreeNode root = new LeetCode_0701.TreeNode(5,
                new LeetCode_0701.TreeNode(2,
                        new LeetCode_0701.TreeNode(1),
                        new LeetCode_0701.TreeNode(3)),
                new LeetCode_0701.TreeNode(6));

        System.out.println("🔁 Recursive Insert 7:");
        LeetCode_0701.TreeNode newRoot1 = solution.insertIntoBST1(root, 7);
        printInOrder(newRoot1);  // Should print: 1 2 3 5 6 7
        System.out.println();

        // Reconstruct the original tree for non-recursive test
        root = new LeetCode_0701.TreeNode(5,
                new LeetCode_0701.TreeNode(2,
                        new LeetCode_0701.TreeNode(1),
                        new LeetCode_0701.TreeNode(3)),
                new LeetCode_0701.TreeNode(6));

        System.out.println("🔁 Non-Recursive Insert 7:");
        LeetCode_0701.TreeNode newRoot2 = solution.insertIntoBST2(root, 7);
        printInOrder(newRoot2);  // Should print: 1 2 3 5 6 7
    }

}

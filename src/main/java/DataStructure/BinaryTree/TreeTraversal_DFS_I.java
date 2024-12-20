package DataStructure.BinaryTree;

/**
 * 二叉树遍历 - DFS - 递归实现
 */
public class TreeTraversal_DFS_I {

    public static void main(String[] args) {
        /*
          1
        /   \
       2     3
      / \    / \
     4   5  6   7

    */

        TreeNode root = new TreeNode
                (new TreeNode(new TreeNode(4),
                        2,new TreeNode(5)),
                        1,

                        new TreeNode(new TreeNode(6),
                                3,
                                new TreeNode(7))
                );

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

    }


    /**
     * 前序遍历（root - left - right) - 递归
     * @param node 节点
     */

    static void preOrder(TreeNode node) {
        if (node == null) {      //base case
            return;
        }
        System.out.print(node.val + "\t"); //值
        preOrder(node.left);   //左
        preOrder(node.right);    //右

    }

    /**
     * 中序遍历(left-root-right) - 递归
     * @param node 节点
     */
    static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);  //左
        System.out.print(node.val + "\t"); //值
        inOrder(node.right);   //右

    }

    /**
     * 后序遍历（left-right-root) - 递归
     * @param node 节点
     */
    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t"); //值

    }
}

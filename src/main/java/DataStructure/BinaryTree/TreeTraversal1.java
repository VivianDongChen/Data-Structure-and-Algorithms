package DataStructure.BinaryTree;

/**
 * 二叉树遍历 - 递归实现
 */
public class TreeTraversal1 {

    public static void main(String[] args) {
        /*
          1
        /   \
       2     3
      /     / \
     4     5   6

    */

        TreeNode root = new TreeNode
                (new TreeNode(new TreeNode(4),
                        2,
                        null),
                        1,

                        new TreeNode(new TreeNode(5),
                                3,
                                new TreeNode(6))
                );

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

    }


    /**
     * 前序遍历 - 递归
     * @param node 节点
     */

    static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.val + "\t"); //值
        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * 中序遍历 - 递归
     * @param node 节点
     */
    static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + "\t"); //值
        inOrder(node.right);

    }

    /**
     * 后序遍历 - 递归
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

package DataStructure.BinaryTree;

/**
 * 114. Flatten Binary Tree to Linked List
 * 利用递归实现的反向前序遍历 (Reverse Pre-order Traversal) 来展平二叉树。
 * 空间复杂度为O(h)，其中h是树的高度。
 * 关键难点：理解递归是先完全处理完一个递归调用（当前分支），然后才会回到上一层，继续处理下一个递归调用。递归的执行是基于调用栈的，
 * 因此它的执行顺序是深度优先、先入后出。
 */
public class LeetCode_0114_III {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode head = null;

    public void flatten(TreeNode root) {
        if(root != null) revPreOrder(root);
    }

    private void revPreOrder(TreeNode node) {
        if(node .right != null){
            revPreOrder(node.right);   //先处理完右子树，出来的时候head = 5；
        }
        if(node.left != null){         //再处理左子树，左子树深入到4，4与head（5）连起来
            revPreOrder(node.left);
        }
        node.left = null;
        node.right = head;
        head = node;
    }

    // 打印展平后的树
    private void printTreeStructure(TreeNode root) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        TreeNode curr = root;
        while (curr != null) {
            result.append(curr.val).append(",");
            if (curr.right != null) {
                result.append("null,"); // 添加 null 表示只有右子节点
            }
            curr = curr.right;
        }
        if (result.length() > 1) {
            result.setLength(result.length() - 1); // 删除最后一个多余的逗号
        }
        result.append("]");
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        LeetCode_0114_III test= new LeetCode_0114_III();

        TreeNode root = test.new TreeNode(1);
        root.left = test.new TreeNode(2);
        root.right = test.new TreeNode(5);
        root.left.left = test.new TreeNode(3);
        root.left.right = test.new TreeNode(4);
        root.right.right = test.new TreeNode(6);

        test.flatten(root);
        test.printTreeStructure(root);  //expected: [1,null,2,null,3,null,4,null,5,null,6]

    }
}

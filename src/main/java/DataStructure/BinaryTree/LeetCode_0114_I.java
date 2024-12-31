package DataStructure.BinaryTree;

/**
 * 114. Flatten Binary Tree to Linked List
 * Morris Traversal
 */
public class LeetCode_0114_I {

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

   public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){   //如果有左子树
                TreeNode runner = curr.left;   //指针指向左子树的头节点
                while(runner.right != null){    //找到左子树最右边的叶子节点
                    runner = runner.right;
                }
                runner.right = curr.right;   //将curr的右子树作为这个叶子节点的右孩子
                curr.right = curr.left;    //curr的左子树作为curr的右孩子
                curr.left = null;    //curr的左子树设为null
            }
            curr = curr.right;
        }
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
        LeetCode_0114_I test= new LeetCode_0114_I();

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

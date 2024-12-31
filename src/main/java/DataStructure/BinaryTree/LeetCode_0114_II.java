package DataStructure.BinaryTree;

/**
 * 114. Flatten Binary Tree to Linked List
 * Morris Traversal
 */
public class LeetCode_0114_II {

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
        TreeNode head = null;  //作为已处理的最下方的“链表”的头
        TreeNode curr = root;  //指针

        while (head != root) {//head逐步上升，直到root
            //先断开左右子树与head的连接
            if(curr.left == head){
                curr.left = null;
            }
            if(curr.right == head){
                curr.right = null;
            }
            //指针去寻找子树中最右侧的叶子节点
            if(curr.right != null){
                curr = curr.right;
            }else if(curr.left != null){
                curr = curr.left;
            }else{   //找到了，将这个叶子节点与head连起来，head定位到这个叶子节点，叶子节点成为最新的head，curr回到root
                curr.right = head;
                head = curr;
                curr = root;
            }
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
        LeetCode_0114_II test= new LeetCode_0114_II();

        LeetCode_0114_II.TreeNode root = test.new TreeNode(1);
        root.left = test.new TreeNode(2);
        root.right = test.new TreeNode(5);
        root.left.left = test.new TreeNode(3);
        root.left.right = test.new TreeNode(4);
        root.right.right = test.new TreeNode(6);

        test.flatten(root);
        test.printTreeStructure(root);  //expected: [1,null,2,null,3,null,4,null,5,null,6]

    }
}

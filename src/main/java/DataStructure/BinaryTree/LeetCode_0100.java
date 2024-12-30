package DataStructure.BinaryTree;

/**
 * 100. Same Tree
 */
public class LeetCode_0100 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }else if(p == null || q == null ){ //一个为null， 一个不为null
            return false;
        }else if(p.val != q.val) { //现在可以确定两个都不为null，不会出现空指针异常了
            return false;
        }else{
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    public static void main(String[] args) {
        LeetCode_0100 test = new LeetCode_0100();

        TreeNode p = test.new TreeNode(1);
        p.left = test.new TreeNode(2);
        p.right = test.new TreeNode(3);

        TreeNode q = test.new TreeNode(1);
        q.left = test.new TreeNode(2);
        q.right = test.new TreeNode(3);

        TreeNode p1 = test.new TreeNode(1);
        p1.left = test.new TreeNode(2);

        TreeNode q1 = test.new TreeNode(1);
        q1.right = test.new TreeNode(2);

        System.out.println(test.isSameTree(p, q));
        System.out.println(test.isSameTree(p1, q1));
    }
}

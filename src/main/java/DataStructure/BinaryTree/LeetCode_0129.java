package DataStructure.BinaryTree;

/**
 * 129 Sum Root to Leaf Numbers
 */
public class LeetCode_0129 {

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

     public int sumNumbers(TreeNode root) {
         return sum(root,0);
     }

     private int sum(TreeNode root, int s) {
         if(root == null){
             return 0;
         }
         if(root.left == null && root.right == null){
             return s*10 + root.val;
         }
         return sum(root.right,s*10 + root.val) + sum(root.left,s*10 + root.val);
     }

    public static void main(String[] args) {
        LeetCode_0129 test = new LeetCode_0129();

        TreeNode root = test.new TreeNode(1);
        root.left = test.new TreeNode(2);
        root.right = test.new TreeNode(3);

        System.out.println(test.sumNumbers(root));  //expected: 25
    }
}

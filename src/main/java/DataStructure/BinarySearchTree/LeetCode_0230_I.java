package DataStructure.BinarySearchTree;

/**
 * Kth Smallest element in a BST
 * Divide and Conquer
 * 时间复杂度：每次调用递归会调用 count 并花费O(m) 时间，其中
 * m 是当前子树的大小。最坏情况下，对于每个递归层都需要完整统计节点数。
 * 因此：对于高度h 的树，递归调用深度为O(h)。
 * 每次递归调用统计子树节点时，时间复杂度为 O(n)。
 * 最终总时间复杂度为O(h⋅n)。
 * 如果树是完全不平衡的（链表形式），高度h=n，时间复杂度为O(h⋅n)=O(n2)。
 * 如果树是完全平衡的，高度h=logn，时间复杂度为O(h⋅n)=O(nlogn)。
 *
 * 空间复杂度：主要由递归栈深度决定： O(h)，其中h 是树的高度。
 * 平衡树：O(logn)。
 * 不平衡树：O(n)。
 */
public class LeetCode_0230_I {
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

    public int kthSmallest(TreeNode root, int k) {
        int count = count(root.left);
        if(k <= count){
            return kthSmallest(root.left, k);
        }else if(k > count + 1){
            return kthSmallest(root.right, k - count - 1);
        }
        return root.val;
    }

    private int count(TreeNode node){
        if(node == null){return 0;}
        return count(node.left) + count(node.right) + 1;
    }

    public static void main(String[] args) {
        LeetCode_0230_I test = new LeetCode_0230_I();
        LeetCode_0230_I.TreeNode root = test.new TreeNode(3);
        root.left = test.new TreeNode(1);
        root.right = test.new TreeNode(4);
        root.left.right = test.new TreeNode(2);

        int k = 1; // Test case: Find the 1st smallest element
        int result = test.kthSmallest(root, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}

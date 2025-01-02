package DataStructure.BinarySearchTree;

import java.util.LinkedList;

/**
 * 173.Binary Search Tree Iterator
 * 根据BST的特性（每个节点的右子树的值都大于该节点的值，每个节点的左子树的值都小于该节点的值）
 * 所以中序遍历（左-中-右）得出的结果是由小到大排列的节点。
 */
public class LeetCode_0173 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class BSTIterator {
        LinkedList<TreeNode> stack = new LinkedList<>();   //空间复杂度为O(h)

        public BSTIterator(TreeNode root) {
           pushAll(root);
        }

        public int next() {    //每个节点最多会被压栈和弹栈一次，因此整体操作的均摊时间复杂度是O(1)
            TreeNode curr = stack.pop();   //取出栈中的最小值节点
            pushAll(curr.right); //找到节点的右子树（大于该节点）的左边缘的节点，这些节点逐渐减小，直到最小值，放入栈
            return curr.val;
        }

        public boolean hasNext() {     //时间复杂度为O(1)
            return !stack.isEmpty();
        }

        public void pushAll(TreeNode node){    //一直往下找左孩子，放入stack，这个时候stack最上面的就是值最小的节点
            while(node != null){
                stack.push(node);
                node = node.left;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_0173 test = new LeetCode_0173();

        TreeNode root = test.new TreeNode(7);
        root.left = test.new TreeNode(3);
        root.right = test.new TreeNode(15);
        root.right.left = test.new TreeNode(9);
        root.right.right = test.new TreeNode(20);

        BSTIterator iterator = test.new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}

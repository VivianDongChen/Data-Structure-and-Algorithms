package DataStructure.Queue;

import java.util.ArrayList;
import java.util.List;

//二叉树层序遍历 - 利用queue的头出尾进来遍历
public class LeetCode0102BinaryTreeLevelOrderTraversal {

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, int val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    /**
     * /*
     *                 1
     *                / \
     *               2   3
     *              / \ / \
     *             4  5 6  7
     *
     *            1 2 3 4 5 6 7
     *
     *            尾部加入1                 头[1]尾
     *
     *            弹出1                    头[]尾
     *            尾部加入其子：2，3         头[2,3]尾
     *
     *            弹出2                    头[3]尾
     *            尾部加入其子：4，5         头[3,4,5]尾
     *
     *            弹出3                    头[4,5]尾
     *            尾部加入其子：6，7         头[4,5,6,7]尾
     *
     *            弹出4                    头[5,6,7]尾
     *
     *            弹出5                    头[6,7]尾
     *
     *            弹出6                    头[7]尾
     *
     *            弹出7                    头[]尾
     *
    */
    public List<List<Integer>>levelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; //当前层的节点数
        while(!queue.isEmpty()){
            int c2 = 0; //下一层的节点数
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
                level.add(n.val);
                if(n.left != null){
                    queue.offer(n.left);
                    c2++;
                }
                if(n.right != null){
                    queue.offer(n.right);
                    c2++;
                }
            }
            result.add(level);
            c1 = c2;
            System.out.println();
        }
        return result;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)
                ),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7)
                )

        );

        System.out.println(new LeetCode0102BinaryTreeLevelOrderTraversal().levelOrder(root));


    }

}

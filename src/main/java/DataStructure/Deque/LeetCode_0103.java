package DataStructure.Deque;

import DataStructure.Queue.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 二叉树Z字层序遍历
public class LeetCode_0103 {

    /*
             1
            / \
           2   3
          /\   /\
         4  5 6  7
        /\
       8  9

       1
       3 2
       4 5 6 7
       9 8

     */
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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;  //当前层的节点数
        boolean odd = true; //奇数层
        while(!queue.isEmpty()){
            int c2 = 0; //下一层的节点数
            LinkedList<Integer> level = new LinkedList<>();  //保存每一层结果
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();

                //如果是奇数层，往尾部添加； 如果是偶数层，往头部添加
                if(odd){
                    level.offerLast(n.val);
                }else{
                    level.offerFirst(n.val);
                }

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
            odd = !odd;
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

        System.out.println(zigzagLevelOrder(root));


    }

}


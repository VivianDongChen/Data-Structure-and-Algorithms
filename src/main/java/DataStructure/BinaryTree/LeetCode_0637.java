package DataStructure.BinaryTree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree
 * BFS - queue
 */
public class LeetCode_0637 {
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

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            double sum = 0.0;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode poll = queue.poll();
                sum += poll.val;
                if(poll.left != null){
                    queue.offer(poll.left);
                }
                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_0637 test = new LeetCode_0637();
        // 构建测试用例：root = [3,9,20,null,null,15,7]
        TreeNode root = test.new TreeNode(3);
        root.left = test.new TreeNode(9);
        root.right = test.new TreeNode(20);
        root.right.left = test.new TreeNode(15);
        root.right.right = test.new TreeNode(7);

        System.out.println(test.averageOfLevels(root)); // 输出: [3.0, 14.5, 11.0]
    }
}

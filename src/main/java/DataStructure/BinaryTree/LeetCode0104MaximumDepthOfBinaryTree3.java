package DataStructure.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度 - 使用层序遍历
 */
public class LeetCode0104MaximumDepthOfBinaryTree3 {
    /*
      思路：使用层序遍历，层数即最大深度
     */

    public int maxDepth(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll.right != null){
                    queue.offer(poll.right);
                }
                if(poll.left != null){
                    queue.offer(poll.left);
                }
            }
            maxDepth++;
        }
        return maxDepth;
    }

}

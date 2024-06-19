package DataStructure.BinaryTree;

import static java.lang.Math.min;

/**
 * 二叉树最小深度 - 后序遍历（递归）
 */
public class LeetCode0111MinimumDepthofBinaryTree1 {

    /*
        深度2
          1
         /
        2

     */

    public int minDepth(TreeNode node){
        if(node == null){
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        if(d1 == 0){   //当右子树为null
            return d2 + 1; //返回左子树深度+1
        }
        if(d2 == 0){    //当左子树为null
            return d1 + 1;  //返回右子树深度+1
        }
        return min(d1,d2) + 1;
    }
}

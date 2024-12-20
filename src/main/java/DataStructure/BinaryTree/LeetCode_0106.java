package DataStructure.BinaryTree;

import java.util.Arrays;

/**
 * 根据前中、后序遍历结果构造二叉树
 */
public class LeetCode_0106 {

    /*
        inOrder = {4,2,1,6,3,7}
        postOrder = {4,2,6,7,3,1}

        根 1     in         post
        左       4，2        4，2
        右       6，3，7     6，7，3

        根 2
        左 4

        根 3
        左 6
        右 7
     */

    public TreeNode buildTree(int[] inOrder,int[] postOrder){
        //递归结束条件
        if(inOrder.length == 0){
            return null;
        }
        //根
        int rootValue = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(rootValue);

        //切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == rootValue){
                int[] inLeft = Arrays.copyOfRange(inOrder,0,i);
                int[] inRight = Arrays.copyOfRange(inOrder,i+1,inOrder.length);
                int[] postLeft = Arrays.copyOfRange(postOrder,0,i);
                int[] postRight = Arrays.copyOfRange(postOrder,i,postOrder.length - 1);

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }

        }
        return root;

    }
}

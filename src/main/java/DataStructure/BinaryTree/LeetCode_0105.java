package DataStructure.BinaryTree;

import java.util.Arrays;

//根据前序、中序遍历结果构造二叉树
public class LeetCode_0105 {

    /*
       preOrder = {1,2,4,3,6，7}
       inOrder = {4,2,1,6,3,7}

       根 1
             pre        in
       左    2，4        4，2
       右    3，6，7     6，3，7

       根 2
       左 4

       根 3
       左 6
       右 7

     */

    public TreeNode buildTree(int[] preOrder, int[] inOrder){
        if(preOrder.length == 0){
            return null;
        }
        //创建根节点
        int rootValue = preOrder[0];
        TreeNode root = new TreeNode(rootValue);
        //区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == rootValue){
                // 0 ～ i-1  左子树
                // i ～ inOrder.length - 1 右子树
                int[] inLeft = Arrays.copyOfRange(inOrder,0,i);       //[4,2]
                int[] inRight = Arrays.copyOfRange(inOrder,i+1,inOrder.length);      //[6,3,7]
                int[] preLeft = Arrays.copyOfRange(preOrder,1,i+1);     //[2,4]
                int[] preRight = Arrays.copyOfRange(preOrder,i+1,preOrder.length);   //[3,6,7]

                root.left = buildTree(preLeft, inLeft);     //2
                root.right = buildTree(preRight, inRight);  //3
                break;
            }

        }
        return root;

    }
}

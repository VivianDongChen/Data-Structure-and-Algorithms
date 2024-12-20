package DataStructure.BinaryTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeetCode0104MaximumDepthOfBinaryTree1Test {

    @Test
    public void test1(){
        TreeNode root = new TreeNode(new TreeNode(2),1,new TreeNode(3));
        assertEquals(2, new LeetCode_0104_I().maxDepth(root));
    }

    @Test
    public void test2(){
        TreeNode root = new TreeNode(new TreeNode(2),1,new TreeNode(null,3,new TreeNode(4)));
        assertEquals(3, new LeetCode_0104_I().maxDepth(root));
    }

    @Test
    public void test3(){
        TreeNode root = new TreeNode(1);
        assertEquals(1, new LeetCode_0104_I().maxDepth(root));
    }


}
package DataStructure.BinaryTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeetCode0101SymmetricTreeTest {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(3),2,new TreeNode(4)),
                1,
                new TreeNode(new TreeNode(4),2,new TreeNode(3))
        );
        assertTrue(new LeetCode_0101().isSymmetric(root));

    }

    @Test
    public void test2(){
        TreeNode root = new TreeNode(
                new TreeNode(null,2,new TreeNode(3)),
                1,
                new TreeNode(null,2,new TreeNode(3))
        );
        assertFalse(new LeetCode_0101().isSymmetric(root));

    }

}
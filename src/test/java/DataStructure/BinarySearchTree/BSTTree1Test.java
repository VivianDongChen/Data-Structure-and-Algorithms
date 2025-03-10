package DataStructure.BinarySearchTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTree1Test {

    public BSTTree_I creatTree(){
        /*
                 4
               /   \
              2     6
            /  \   /  \
           1   3   5   7

         */

        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1,"张无忌");
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3,"宋青书");
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2,"周芷若",n1,n3);
        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5,"说不得");
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7,"殷离");
        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6,"赵敏",n5,n7);
        BSTTree_I.BSTNode root = new BSTTree_I.BSTNode(4,"小昭",n2,n6);

        BSTTree_I tree = new BSTTree_I();
        tree.root = root;
        return tree;
    }

    @Test
    void testGet(){
        BSTTree_I tree = creatTree();
        assertEquals("张无忌",tree.get(1));
        assertEquals("周芷若",tree.get(2));
        assertEquals("宋青书",tree.get(3));
        assertEquals("小昭",tree.get(4));
        assertEquals("说不得",tree.get(5));
        assertEquals("赵敏",tree.get(6));
        assertEquals("殷离",tree.get(7));
        assertNull(tree.get(8));

    }

    @Test
    void testMinMAx(){
        BSTTree_I tree = creatTree();
        assertEquals("张无忌",tree.min());
        assertEquals("殷离",tree.max());
    }

    @Test
    void testPut(){
        BSTTree_I tree1 = creatTree();
        BSTTree_I tree2 = new BSTTree_I();
        tree2.put(4, "小昭");
        tree2.put(2, "周芷若");
        tree2.put(6, "赵敏");
        tree2.put(1, "张无忌");
        tree2.put(3, "宋青书");
        tree2.put(7, "殷离");
        tree2.put(5, "说不得");
        assertTrue(isSameTree(tree1.root, tree2.root));
        tree2.put(1,"教主张无忌");
        assertEquals("教主张无忌",tree2.get(1));

    }

    static boolean isSameTree(BSTTree_I.BSTNode tree1, BSTTree_I.BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }

    @Test
    public void testPredecessor() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5);
        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6, n5, null);
        BSTTree_I.BSTNode n8 = new BSTTree_I.BSTNode(8, 8);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7, n6, n8);
        BSTTree_I.BSTNode root = new BSTTree_I.BSTNode(4, 4, n2, n7);

        BSTTree_I tree = new BSTTree_I();
        tree.root = root;

        assertNull(tree.predecessor(1));
        assertEquals(1, tree.predecessor(2));
        assertEquals(2, tree.predecessor(3));
        assertEquals(3, tree.predecessor(4));
        assertEquals(4, tree.predecessor(5));
        assertEquals(5, tree.predecessor(6));
        assertEquals(6, tree.predecessor(7));
        assertEquals(7, tree.predecessor(8));
    }

    @Test
    public void testSuccessor() {
        /*
                     5
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     \
                      4
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n4 = new BSTTree_I.BSTNode(4, 4);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3, null, n4);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode n8 = new BSTTree_I.BSTNode(8, 8);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7, n6, n8);
        BSTTree_I.BSTNode root = new BSTTree_I.BSTNode(5, 5, n2, n7);

        BSTTree_I tree = new BSTTree_I();
        tree.root = root;

        assertEquals(2, tree.successor(1));
        assertEquals(3, tree.successor(2));
        assertEquals(4, tree.successor(3));
        assertEquals(5, tree.successor(4));
        assertEquals(6, tree.successor(5));
        assertEquals(7, tree.successor(6));
        assertEquals(8, tree.successor(7));
        assertNull(tree.successor(8));
    }

    @Test
    @DisplayName("删除叶子节点")
    public void testDelete1() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5);
        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6, n5, null);
        BSTTree_I.BSTNode n8 = new BSTTree_I.BSTNode(8, 8);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7, n6, n8);
        BSTTree_I.BSTNode root1 = new BSTTree_I.BSTNode(4, 4, n2, n7);

        BSTTree_I tree1 = new BSTTree_I();
        tree1.root = root1;

        assertEquals(1, tree1.delete2(1));
        assertEquals(3, tree1.delete2(3));
        assertEquals(5, tree1.delete2(5));
        assertEquals(8, tree1.delete2(8));


        /*
                     4
                   /   \
                  2     7
                       /
                      6
         */
        BSTTree_I.BSTNode x2 = new BSTTree_I.BSTNode(2, 2);
        BSTTree_I.BSTNode x6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode x7 = new BSTTree_I.BSTNode(7, 7, x6, null);
        BSTTree_I.BSTNode root2 = new BSTTree_I.BSTNode(4, 4, x2, x7);
        BSTTree_I tree2 = new BSTTree_I();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void testDelete2() {
        /*
                     4
                   /   \
                  2     7
                 / \   /
                1   3 6
                     /
                    5
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5);
        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6, n5, null);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7, n6, null);
        BSTTree_I.BSTNode root1 = new BSTTree_I.BSTNode(4, 4, n2, n7);

        BSTTree_I tree1 = new BSTTree_I();
        tree1.root = root1;

        assertEquals(7, tree1.delete2(7));


        /*
                     4
                   /   \
                  2     6
                 / \   /
                1   3 5
         */
        BSTTree_I.BSTNode x1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode x3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode x2 = new BSTTree_I.BSTNode(2, 2, x1, x3);
        BSTTree_I.BSTNode x5 = new BSTTree_I.BSTNode(5, 5);
        BSTTree_I.BSTNode x6 = new BSTTree_I.BSTNode(6, 6, x5, null);
        BSTTree_I.BSTNode root2 = new BSTTree_I.BSTNode(4, 4, x2, x6);
        BSTTree_I tree2 = new BSTTree_I();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继不邻")
    public void testDelete3() {
        /*
                      4
                   /     \
                  2      7
                 / \   /   \
                1   3 5     8
                       \
                        6
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5, null, n6);
        BSTTree_I.BSTNode n8 = new BSTTree_I.BSTNode(8, 8);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7, n5, n8);
        BSTTree_I.BSTNode root1 = new BSTTree_I.BSTNode(4, 4, n2, n7);

        BSTTree_I tree1 = new BSTTree_I();
        tree1.root = root1;

        assertEquals(4, tree1.delete2(4));


        /*
                      5
                   /     \
                  2      7
                 / \   /   \
                1   3 6     8

         */
        BSTTree_I.BSTNode x1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode x3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode x2 = new BSTTree_I.BSTNode(2, 2, x1, x3);

        BSTTree_I.BSTNode x6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode x8 = new BSTTree_I.BSTNode(8, 8);
        BSTTree_I.BSTNode x7 = new BSTTree_I.BSTNode(7, 7, x6, x8);
        BSTTree_I.BSTNode root2 = new BSTTree_I.BSTNode(5, 5, x2, x7);
        BSTTree_I tree2 = new BSTTree_I();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继相邻")
    public void testDelete4() {
        /*
                     4
                   /   \
                  2     5
                 / \     \
                1   3     6

         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5, null, n6);
        BSTTree_I.BSTNode root1 = new BSTTree_I.BSTNode(4, 4, n2, n5);

        BSTTree_I tree1 = new BSTTree_I();
        tree1.root = root1;

        assertEquals(4, tree1.delete2(4));


        /*
                     5
                   /  \
                  2    6
                 / \
                1   3

         */
        BSTTree_I.BSTNode x1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode x3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode x2 = new BSTTree_I.BSTNode(2, 2, x1, x3);

        BSTTree_I.BSTNode x6 = new BSTTree_I.BSTNode(6, 6);
        BSTTree_I.BSTNode root2 = new BSTTree_I.BSTNode(5, 5, x2, x6);
        BSTTree_I tree2 = new BSTTree_I();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }


    @Test
    public void testLessGreaterBetween() {
        /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
         */
        BSTTree_I.BSTNode n1 = new BSTTree_I.BSTNode(1, 1);
        BSTTree_I.BSTNode n3 = new BSTTree_I.BSTNode(3, 3);
        BSTTree_I.BSTNode n2 = new BSTTree_I.BSTNode(2, 2, n1, n3);

        BSTTree_I.BSTNode n5 = new BSTTree_I.BSTNode(5, 5);
        BSTTree_I.BSTNode n7 = new BSTTree_I.BSTNode(7, 7);
        BSTTree_I.BSTNode n6 = new BSTTree_I.BSTNode(6, 6, n5, n7);
        BSTTree_I.BSTNode root = new BSTTree_I.BSTNode(4, 4, n2, n6);

        BSTTree_I tree = new BSTTree_I();
        tree.root = root;

        assertIterableEquals(List.of(1, 2, 3, 4, 5), tree.less(6));
        assertIterableEquals(List.of(7), tree.greater1(6));
        assertIterableEquals(List.of(7), tree.greater2(6));
        assertIterableEquals(List.of(3, 4, 5), tree.between(3, 5));

    }





}
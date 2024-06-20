package DataStructure.BinarySearchTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTree1Test {

    public BSTTree1 creatTree(){
        /*
                 4
               /   \
              2     6
            /  \   /  \
           1   3   5   7

         */

        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1,"张无忌");
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3,"宋青书");
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2,"周芷若",n1,n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5,"说不得");
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7,"殷离");
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6,"赵敏",n5,n7);
        BSTTree1.BSTNode root = new BSTTree1.BSTNode(4,"小昭",n2,n6);

        BSTTree1 tree = new BSTTree1();
        tree.root = root;
        return tree;
    }

    @Test
    void testGet(){
        BSTTree1 tree = creatTree();
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
        BSTTree1 tree = creatTree();
        assertEquals("张无忌",tree.min());
        assertEquals("殷离",tree.max());
    }

    @Test
    void testPut(){
        BSTTree1 tree1 = creatTree();
        BSTTree1 tree2 = new BSTTree1();
        tree2.put(4, "小昭");
        tree2.put(2, "周芷若");
        tree2.put(6, "赵敏");
        tree2.put(1, "张无忌");
        tree2.put(3, "宋青书");
        tree2.put(7, "殷离");
        tree2.put(5, "说不得");
        assertEquals(tree1.root.value,tree2.root.value);
        tree2.put(1,"教主张无忌");
        assertEquals("教主张无忌",tree2.get(1));

    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void delete2() {
        /*
                     4
                   /   \
                  2     7
                 / \   /
                1   3 6
                     /
                    5
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);

        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, null);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);

        BSTTree1 tree1 = new BSTTree1();
        tree1.root = root1;

        assertEquals(7, tree1.delete(7));


        /*
                     4
                   /   \
                  2     6
                 / \   /
                1   3 5
         */
        BSTTree1.BSTNode x1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode x3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode x2 = new BSTTree1.BSTNode(2, 2, x1, x3);
        BSTTree1.BSTNode x5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode x6 = new BSTTree1.BSTNode(6, 6, x5, null);
        BSTTree1.BSTNode root2 = new BSTTree1.BSTNode(4, 4, x2, x6);
        BSTTree1 tree2 = new BSTTree1();
        tree2.root = root2;

        assertEquals(tree1.root.value, tree2.root.value);
    }


}
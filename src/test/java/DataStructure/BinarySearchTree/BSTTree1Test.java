package DataStructure.BinarySearchTree;

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
    void get(){
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

}
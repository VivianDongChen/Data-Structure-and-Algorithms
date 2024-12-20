package DataStructure.BinarySearchTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTree2Test {

    public BSTTree_II<String,String> creatTree(){
        /*
                 4
               /   \
              2     6
            /  \   /  \
           1   3   5   7

         */

        BSTTree_II.BSTNode<String, String> n1 = new BSTTree_II.BSTNode<>("a","张无忌");
        BSTTree_II.BSTNode<String, String> n3 = new BSTTree_II.BSTNode<>("c","宋青书");
        BSTTree_II.BSTNode<String, String> n2 = new BSTTree_II.BSTNode<>("b","周芷若",n1,n3);
        BSTTree_II.BSTNode<String, String> n5 = new BSTTree_II.BSTNode<>("e","说不得");
        BSTTree_II.BSTNode<String, String> n7 = new BSTTree_II.BSTNode<>("g","殷离");
        BSTTree_II.BSTNode<String, String> n6 = new BSTTree_II.BSTNode<>("f","赵敏",n5,n7);
        BSTTree_II.BSTNode<String, String> root = new BSTTree_II.BSTNode<>("d","小昭",n2,n6);

        BSTTree_II<String, String> tree = new BSTTree_II<>();
        tree.root = root;
        return tree;
    }

    @Test
    void get(){
        BSTTree_II<String, String> tree = creatTree();
        assertEquals("张无忌",tree.get("a"));
        assertEquals("周芷若",tree.get("b"));
        assertEquals("宋青书",tree.get("c"));
        assertEquals("小昭",tree.get("d"));
        assertEquals("说不得",tree.get("e"));
        assertEquals("赵敏",tree.get("f"));
        assertEquals("殷离",tree.get("g"));
        assertNull(tree.get("h"));

    }


}
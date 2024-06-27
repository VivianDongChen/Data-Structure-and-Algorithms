package DataStructure.BTree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BTreeTest {

    @Test
    @DisplayName("split(t=2)")
    public void testSplit1(){

        /*
                 5                  2/5
              /     \     ==>     /  |  \
            1/2/3    6           1   3   6

         */
        BTree tree = new BTree();
        BTree.Node root = tree.root;
        root.leaf = false;
        root.keys[0] = 5;
        root.keyNumber = 1;

        root.children[0] = new BTree.Node(new int[]{1,2,3});
        root.children[0].keyNumber = 3;

        root.children[1] = new BTree.Node(new int[]{6});
        root.children[1].keyNumber = 1;

        tree.split(root.children[0], root, 0);
        assertEquals("[2, 5]", root.toString());
        assertEquals("[1]", root.children[0].toString());
        assertEquals("[3]", root.children[1].toString());
        assertEquals("[6]", root.children[2].toString());
    }

    @Test
    @DisplayName("split(t=3)")
    void testSplit2() {
        /*
                  6                 3|6
               /     \     ==>    /  |  \
           1|2|3|4|5  7         1|2 4|5  7

         */
        BTree tree = new BTree(3);
        BTree.Node root = tree.root;
        root.leaf = false;
        root.keys[0] = 6;
        root.keyNumber = 1;
        root.children[0] = new BTree.Node(new int[]{1, 2, 3, 4, 5});
        root.children[0].keyNumber = 5;

        root.children[1] = new BTree.Node(new int[]{7});
        root.children[1].keyNumber = 1;

        tree.split(root.children[0], root, 0);
        assertEquals("[3, 6]", root.toString());
        assertEquals("[1, 2]", root.children[0].toString());
        assertEquals("[4, 5]", root.children[1].toString());
        assertEquals("[7]", root.children[2].toString());
    }

    @Test
    @DisplayName("put(t=2)")
    void testPut1() {
        /*
                                  2
                                 / \
              1|2|3       =>    1   3

                2                2|4
               / \        =>    / | \
              1 3|4|5          1  3  5
                                                    4
                2|4             2|4|6              / \
               / | \      =>   / / \ \     =>     2   6
              1  3 5|6|7      1  3  5 7          / \ / \
                                                1  3 5  7

                4                 4
               / \              /   \
              2   6      =>    2    6|8
             /\  / \          /\   / | \
            1  3 5 7|8|9     1  3 5  7  9

                 4                   4                   4|8
               /   \               /   \                / | \
              2    6|8      =>    2    6|8|10      =>  2  6  10
             /\   / | \          /\   / / \ \         /\  /\  /\
            1  3 5  7 9|10|11   1  3 5 7  9  11      1 3 5 7 9 11
        */
        BTree tree = new BTree();
        tree.put(1);
        tree.put(2);
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.put(7);
        tree.put(8);
        tree.put(9);
        tree.put(10);
        tree.put(11);
        assertEquals("[4, 8]", tree.root.toString());
        assertEquals("[2]", tree.root.children[0].toString());
        assertEquals("[6]", tree.root.children[1].toString());
        assertEquals("[10]", tree.root.children[2].toString());
        assertEquals("[1]", tree.root.children[0].children[0].toString());
        assertEquals("[3]", tree.root.children[0].children[1].toString());
        assertEquals("[5]", tree.root.children[1].children[0].toString());
        assertEquals("[7]", tree.root.children[1].children[1].toString());
        assertEquals("[9]", tree.root.children[2].children[0].toString());
        assertEquals("[11]", tree.root.children[2].children[1].toString());

    }

}
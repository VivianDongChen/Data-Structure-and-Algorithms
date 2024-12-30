package DataStructure.BinaryTree;

/**
 * 117. populating Next RightPointers in Each Node II
 * 这题的关键主要在于理解 next 的含义以及如何利用辅助指针和 dummyHead 来动态建立每层的 next 链接。
 */
public class LeetCode_0117 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        Node dummyHead = new Node(0);    //存储root下一层的起始节点
        Node curr = dummyHead;    //指针
        Node rootInitail = root;  //这里用另一个Node记录root的起始位置，root接下来用作指针

        while (root != null) {
            if (root.left != null) {
                curr.next = root.left;
                curr = curr.next;
            }
            if (root.right != null) {
                curr.next = root.right;
                curr = curr.next;
            }
            root = root.next;
            if (root == null) {
                curr = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
        return rootInitail;
    }

    public void printTreeByLevel(Node root) {
            while (root != null) {
                Node curr = root;
                while (curr != null) {
                    System.out.print(curr.val + " -> ");
                    curr = curr.next;
                }
                System.out.println("null");
                root = root.left != null ? root.left : root.right; // Move to the first node of the next level
            }
        }


    public static void main(String[] args) {
        LeetCode_0117 solution = new LeetCode_0117();

        // Construct the tree [1,2,3,4,5,null,7]
        Node root = solution.new Node(1);
        root.left = solution.new Node(2);
        root.right = solution.new Node(3);
        root.left.left = solution.new Node(4);
        root.left.right = solution.new Node(5);
        root.right.right = solution.new Node(7);

        // Connect the tree
        root = solution.connect(root);

        // Print the tree level by level
        System.out.println("Tree after connecting nodes:");
        solution.printTreeByLevel(root);
    }



}

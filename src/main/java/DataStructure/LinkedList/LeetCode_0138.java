package DataStructure.LinkedList;

import com.sun.jdi.PathSearchingVirtualMachine;

public class LeetCode_0138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node curr = head;   //指针
        Node next;//指针


        // Step 1: 交错插入新节点
        while(curr != null){
            next = curr.next;

            Node newNode = new Node(curr.val);
            newNode.next = next;
            curr.next = newNode;

            curr = next;
        }

        // Step 2: 设置 random 指针
        curr = head;
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: 拆分链表，恢复原链表
        curr = head;
        Node newDummyHead = new Node(0);
        Node newCurr = newDummyHead;    //指针
        while(curr != null){
            next = curr.next.next;

            newCurr.next = curr.next;
            newCurr = newCurr.next;

            curr.next = next;   //恢复原装链
            curr = next;
        }

        Node res = newDummyHead.next;
        newDummyHead.next = null;
        return res;
    }

    // Helper 方法：构建链表
    public Node createTestList(int[][] data) {
        Node[] nodes = new Node[data.length];
        for (int i = 0; i < data.length; i++) {
            nodes[i] = new Node(data[i][0]); // 创建节点
        }
        for (int i = 0; i < data.length; i++) {
            if (i < data.length - 1) {
                nodes[i].next = nodes[i + 1]; // 设置 next 指针
            }
            if (data[i][1] != -1) {
                nodes[i].random = nodes[data[i][1]]; // 设置 random 指针
            }
        }
        return nodes[0]; // 返回链表头节点
    }

    // Helper 方法：打印链表
    public void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            int randomVal = (curr.random != null) ? curr.random.val : -1;
            System.out.println("Node value: " + curr.val + ", Random points to: " + randomVal);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        LeetCode_0138 test = new LeetCode_0138();

       // 测试用例 [[7, null], [13, 0], [11, 4], [10, 2], [1, 0]]
        int[][] testCase = {
                {7, -1},
                {13, 0},
                {11, 4},
                {10, 2},
                {1, 0}
        };

        // 构建测试链表
        Node head = test.createTestList(testCase);

        // 打印原始链表
        System.out.println("Original list:");
        test.printList(head);

        // 复制链表
        Node copiedHead = test.copyRandomList(head);

        // 打印复制的链表
        System.out.println("Copied list:");
        test.printList(copiedHead);

    }
}
package DataStructure.LinkedList;

import java.util.LinkedList;

public class LeetCode_0002 {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);   //哨兵节点用于记录头节点（dummyHead.next即为头节点）
        ListNode cur = dummyHead; //指针
        int carry = 0;

        while(l1 != null || l2 != null || carry != 0){
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;

            int sum = digit1 + digit2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(digit);
            cur.next = newNode;
            cur = cur.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        ListNode res = dummyHead.next;
        dummyHead.next = null;   //断开dummyHead与res的连接，让它指向null
        return res;

    }

    public static void main(String[] args) {
        LeetCode_0002 test = new LeetCode_0002();

        // 构建链表 l1: 2 -> 4 -> 3
        ListNode l1 = test.new ListNode(2);
        l1.next = test.new ListNode(4);
        l1.next.next = test.new ListNode(3);

        // 构建链表 l2: 5 -> 6 -> 4
        ListNode l2 = test.new ListNode(5);
        l2.next = test.new ListNode(6);
        l2.next.next = test.new ListNode(4);

        // 调用 addTwoNumbers 方法
        ListNode result = test.addTwoNumbers(l1, l2);

        // 打印结果链表
        printList(result);
    }


    // 打印链表
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }


}

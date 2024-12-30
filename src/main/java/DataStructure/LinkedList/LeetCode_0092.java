package DataStructure.LinkedList;

/**
 * 92. Reverse Linked List II
 */
public class LeetCode_0092 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseBetween(ListNode head, int left, int right){
       ListNode dummy = new ListNode(0);  //定义一个哨兵节点，用来处理left=1的情况
       dummy.next = head;
       //找到三个指针的初始位置
        ListNode pre = dummy;
        for(int i = 1; i < left; i++){
           pre = pre.next;
       }
       ListNode curr = pre.next;
       ListNode next = curr.next;
       //从left+1 遍历到right，每次把next翻到前面去， 就能实现区间内的反转
       for(int i = 0; i < right - left; i++){
           curr.next = next.next;
           next.next = pre.next;
           pre.next = next;
           next = curr.next;
       }
       return dummy.next;
    }

    public static void main(String[] args) {
        LeetCode_0092 test = new LeetCode_0092();

        ListNode head = test.new ListNode(1);
        head.next = test.new ListNode(2);
        head.next.next = test.new ListNode(3);
        head.next.next.next = test.new ListNode(4);
        head.next.next.next.next = test.new ListNode(5);

        int left = 2;
        int right = 4;

        printList(test.reverseBetween(head, left, right));


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

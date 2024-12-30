package DataStructure.LinkedList;

/**
 * 86. Partition List
 */
public class LeetCode_0086 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode leftTail = leftDummy;
        ListNode rightTail = rightDummy;

        while (head != null) {
            if(head.val < x){
                leftTail.next = new ListNode(head.val);
                leftTail = leftTail.next;
            }else {
                rightTail.next = new ListNode(head.val);
                rightTail = rightTail.next;
            }
            head = head.next;
        }
        leftTail.next = rightDummy.next;
        return leftDummy.next;
    }

    // 打印链表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LeetCode_0086 test = new LeetCode_0086();
        ListNode head = test.new ListNode(1);
        head.next = test.new ListNode(4);
        head.next.next = test.new ListNode(3);
        head.next.next.next = test.new ListNode(2);
        head.next.next.next.next = test.new ListNode(5);
        head.next.next.next.next.next = test.new ListNode(2);

        int x = 3;

        printList(test.partition(head,x)); //expected: [1,2,2,4,3,5]
    }
}

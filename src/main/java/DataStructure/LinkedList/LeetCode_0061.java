package DataStructure.LinkedList;

/**
 /**
 * 61. Rotate List
 * Rather than describing the operation as moving k nodes from the end to the front, it is more precise to view it
 * as moving n nodes from the front to the back. This approach aligns better with the LinkedList structure,
 * which only supports next operations, making pointer manipulation more efficient in this direction.
 * so the key steps is to calculate the length of the linked list and determine the rotation index `n`
 * Time Complexity: O(n), where `n` is the number of nodes in the linked list.<br>
 * Space Complexity: O(1), as no additional data structures are used.
 */
public class LeetCode_0061 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null){
            return head;  // Edge cases: no rotation needed
        }
        ListNode tail = head;
        // Calculate the length of the linked list
        int i;
        for( i = 1; tail.next != null; i++){
            tail = tail.next;
        }
        int n = i - k % i;   // Calculate the number of nodes to skip from the start
        if(n == 0){
            return head;
        }
        for(int j = 0; j < n; j++){
            ListNode temp = head.next;
            tail.next = head;
            head.next = null;
            tail = head;
            head = temp;
        }
        return head;
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
        LeetCode_0061 test = new LeetCode_0061();

        ListNode head = test.new ListNode(1);
        head.next = test.new ListNode(2);
        head.next.next = test.new ListNode(3);
        head.next.next.next = test.new ListNode(4);
        head.next.next.next.next = test.new ListNode(5);

        int k = 2;
        printList(test.rotateRight(head,k));


    }
}

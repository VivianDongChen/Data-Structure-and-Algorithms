package Algorithms.DivideAndConquer;

public class LeetCode_0148 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(prev != null){
            prev.next = null;
        }

        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(left != null && right != null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if(left != null){
            cur.next = left;
        }
        if(right != null){
            cur.next = right;
        }
        return dummy.next;
    }

    // Function to print the linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LeetCode_0148 test = new LeetCode_0148();

        // Create linked list: 4 -> 2 -> 1 -> 3
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));

        System.out.println("Original List:");
        printList(head);

        // Sort the linked list
        ListNode sortedHead = test.sortList(head);

        System.out.println("Sorted List:");
        printList(sortedHead);

    }
}

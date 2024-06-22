package DataStructure.Heap.LinkedList;


//LeetCode很多链表题目用到的节点类
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // Utility method to create a linked list from an array of values
    public static ListNode of(int... values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0], null);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i], null);   //尾部插入
            current = current.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while (p != null) {
            sb.append(p.val);
            if (p.next != null) {
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }

}

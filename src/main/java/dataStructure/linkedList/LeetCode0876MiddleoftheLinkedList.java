package dataStructure.linkedList;

public class LeetCode0876MiddleoftheLinkedList {

    /**
     * 快慢指针
     *
     *           p1
     *                     p2
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     *
     *                p1
     *                                p2
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
     *
     */
    public ListNode middleNode(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;

        while(p2 != null && p2.next != null){    //一定要将p2 != null写在前面，这样p2 == null时，就不会执行后面的运算了（&&具有短路效果），避免空指针问题
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode head1 = ListNode.of(1,2,3,4,5);
        System.out.println(new LeetCode0876MiddleoftheLinkedList() .middleNode(head1));

        ListNode head2 = ListNode.of(1,2,3,4,5,6);
        System.out.println(new LeetCode0876MiddleoftheLinkedList() .middleNode(head2));

    }
}

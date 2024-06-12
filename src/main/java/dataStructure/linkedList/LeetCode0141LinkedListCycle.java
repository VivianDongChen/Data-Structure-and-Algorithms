package dataStructure.linkedList;

public class LeetCode0141LinkedListCycle {

    /**
     * 佛洛依德龟兔赛跑算法（环判算法）
     *  - 龟一次走一步，兔子一次走两步
     *  - 当兔子能走到终点时，不存在环
     *  - 当兔子能追上龟时，可以判定存在环
     */
    public boolean hasCycle(ListNode head){
        ListNode h = head;
        ListNode t = head;

        while(h != null && h.next!= null){
            t = t.next;
            h = h.next.next;
            if( t == h){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode n12 = new ListNode(12, null);
        ListNode n11 = new ListNode(11, n12);
        ListNode n10 = new ListNode(10, n11);
        ListNode n9 = new ListNode(9, n10);
        ListNode n8 = new ListNode(8, n9);
        ListNode n7 = new ListNode(7, n8);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

//        n12.next = n8;

        System.out.println(new LeetCode0141LinkedListCycle().hasCycle(n1));



    }
}

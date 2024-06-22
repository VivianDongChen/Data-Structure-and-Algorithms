package DataStructure.Heap.LinkedList;

public class LeetCode0142LinkedListCycleII {

    /**
     * 佛洛依德龟兔赛跑算法（环判算法）
     *  阶段1
     *  - 龟一次走一步，兔子一次走两步
     *  - 当兔子能走到终点时，不存在环
     *  - 当兔子能追上龟时，可以判定存在环
     *  阶段2
     *  - 从它们第一次相遇开始，龟回到起点，兔子保持原位不变
     *  - 龟和兔子一次都走一步
     *  - 当再次相遇时，地点就是环的入口
     */
    public ListNode detectCycle(ListNode head){
        ListNode h = head;
        ListNode t = head;

        while(h != null && h.next!= null){
            t = t.next;
            h = h.next.next;
            if( t == h){   //第一次相遇
                //进入第二阶段
                t = head;   //龟回到起点
                while(true){
                    if(t == h){     //第二次相遇
                        return t;
                    }
                    t = t.next;
                    h = h.next;
                }
            }
        }
        return null;
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

        n12.next = n1;

        System.out.println(new LeetCode0142LinkedListCycleII().detectCycle(n1).val);



    }
}

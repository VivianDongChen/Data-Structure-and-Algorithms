package dataStructure.linkedList;

//根据值删除节点
public class LeetCode203RemoveLinkedListElements {

    /**
     * 方法1 - 使用哨兵节点和两个指针（如果不加哨兵，则删除第一个节点要特殊处理）
     * @param head 头节点
     * @param val 要删除的值
     * @return 删除后的链表头
     */
    public ListNode removeElements(ListNode head, int val){
        ListNode s = new ListNode(-1,head);  //定义一个哨兵，指向头节点
        ListNode p1 = s;   //指针1
        ListNode p2 = head; //指针2

        while(p2 != null){
            if(p2.val == val){
                //删除节点，p2后移一位
                p1.next = p2.next;
                p2 = p2.next;
            }else{
                //p1, p2都后移一位
                p1 = p1.next;
                p2 = p2.next;
            }
        }

        return s.next;

    }

    /**
     * 方法1 代码优化
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val){
        ListNode s = new ListNode(-1,head);
        ListNode p1 = s;
        ListNode p2;

        while((p2 = p1.next)!= null){
            if(p2.val == val){
                p1.next = p2.next;
            }else {
                p1 = p1.next;
            }
        }

        return s.next;

    }

    /**
     * 方法2 递归
     * @param p
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode p, int val){
        if(p == null){
            return null;
        }
        if(p.val == val){
            return removeElements2(p.next,val);
        }else{
            p.next = removeElements(p.next,val);
            return p;
        }

    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1,2,6,3,6);
        System.out.println(head);
        System.out.println(new LeetCode203RemoveLinkedListElements().removeElements2(head,6));

    }


}

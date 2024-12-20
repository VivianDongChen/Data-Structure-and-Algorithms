package DataStructure.LinkedList;

public class LeetCode_0234 {

    /**
     * 方法1: 分三步
     * 1 2 2 1
     * 1 2
     *
     * 1 2 3 2 1
     * 1 2 3
     * 步骤1: 找中间点
     * 步骤2: 中间点之后的半个链表做反转
     * 步骤3: 反转后链表与原链表逐一比较
     *
     */
    public boolean isPalindrome1(ListNode head){
        ListNode newHead = reverse(middle(head));

        while(newHead != null){     //只比较原链表的前半部分
            if(newHead.val != head.val){
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }

        return true;
    }

    /**
     * 步骤1
     * @param head
     * @return
     */
    private ListNode middle(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;

        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    /**
     * 步骤2 - 这里没有判断链表为空的情况，因为题目中给出了链表节点最少为1的条件
     */
    private ListNode reverse(ListNode o1){
        if(o1.next == null){
            return o1;
        }
        ListNode n1 = null;
        while(o1!= null){
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }

    /**
     * 方法2 - 优化
     * 将步骤1和步骤2的两个循环合并成一个，在找到后半个链表的同时将前半个链表反转
     *           p1         p2
     * 1 -> 2 -> 2 -> 1 -> null
     * 2 -> 1                       如果是偶数节点，比较后半个链表和前半个链表
     *
     *           p1        p2
     * 1 -> 2 -> 3 -> 2 -> 1 -> null
     * 2 -> 1                       如果是奇数节点，比较p1.next后的链表和前半个链表
     *
     * - 步骤1: 找中间点的同时反转前半个链表
     * - 步骤2: 反转后的前半个链表与中间点开始的后半个链表逐一比较
     */
    public boolean isPalindrome2(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode n1 = null;  //前半链表反转后的头节点
        ListNode o1 = head;  //前半链表反转前的头节点
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;

            //反转前半链表
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }
        System.out.println(n1);
        System.out.println(p1);

        if(p2 != null){
            p1 = p1.next;
        }

        while(n1 != null){
            if(n1.val != p1.val){
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode_0234().isPalindrome2(ListNode.of(1,2,2,1)));
        System.out.println(new LeetCode_0234().isPalindrome2(ListNode.of(1,2,3,2,1)));
        System.out.println(new LeetCode_0234().isPalindrome2(ListNode.of(1,2,3,2,4)));
    }
}

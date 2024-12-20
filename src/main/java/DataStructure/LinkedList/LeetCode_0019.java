package DataStructure.LinkedList;

public class LeetCode_0019 {

    /**
     * 方法1: 递归

        recursion（ListNode p=1, int n=2){
            recursion（ListNode p=2, int n=2){
                recursion（ListNode p=3, int n=2){
                    recursion（ListNode p=4, int n=2){
                        recursion（ListNode p=5, int n=2){
                            recursion（ListNode p=null, int n=2){
                                return 0;
                           }
                           return 1;
                       }
                       return 2;
                   }
                   if (上一级的返回值 == n == 2){
                       删除
                   }
                   return 3;
               }
               return 4;
           }
           return 5;
       }

     */
    public ListNode removeNthFromEnd1(ListNode head,int n){
        ListNode s = new ListNode(-1,head);  //定义一个哨兵，让它指向head，这是为了处理第一个数据；
        recursion(s,n);
        return s.next;
    }

    private int recursion(ListNode p,int n){

        if(p == null){
            return 0;
        }

        int nth = recursion(p.next,n);   //下一个节点的倒数位置
        System.out.println(p.val + " " + nth);
        if(nth == n){
            p.next = p.next.next;   //删除代码
        }
        return nth + 1;   //返回本节点的倒数位置

    }

    /**
     * 方法2: 快慢指针

       n = 2
       p1  指针1
       p2  指针2 - 与指针1中间隔n个节点
       s -> 1 -> 2 -> 3 -> 4 -> 5 -> null
            p2
       s -> 1 -> 2 -> 3 -> 4 -> 5 -> null
                 p2
       s -> 1 -> 2 -> 3 -> 4 -> 5 -> null
                      p2
       s -> 1 -> 2 -> 3 -> 4 -> 5 -> null
       ...
                      p1              p2          p2指向null的时候停止，这个时候p1指向倒数第3个节点
       s -> 1 -> 2 -> 3 -> 4 -> 5 -> null

       s -> 1 -> 2 -> 3 -> 5 -> null               p1.next = p1.next.next 即为删除 p1.next
     */
    public ListNode removeNthFromEnd2(ListNode head,int n){
        ListNode s = new ListNode(-1,head);
        ListNode p1 = s;
        ListNode p2 = s;

        for (int i = 0; i < n+1; i++) {
            p2 = p2.next;
        }

        while(p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;

        return s.next;
    }





    public static void main(String[] args) {
        ListNode head = ListNode.of(1,2,3,4,5);
        System.out.println(head);
        System.out.println(new LeetCode_0019().
                removeNthFromEnd2(head, 5));
    }
}

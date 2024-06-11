package dataStructure.linkedList;

public class LeetCode0082RemoveDuplicatesfromSortedListII {

    /**
     * 方法1:递归
     *
     * 递归函数负责返回：从当前节点（我）开始，完成去重的链表
     * - 若我与next重复，一直找到下一个不重复的节点，以它的返回结果为准
     * - 若我与next不重复，返回我，同时更新next
     *
     * deleteDuplicates1（ListNode p=1){
     *   deleteDuplicates1（ListNode p=1){
     *      deleteDuplicates1（ListNode p=1){
     *          deleteDuplicates1（ListNode p=2){
     *              deleteDuplicates1（ListNode p=3){
     *                  //只剩一个节点，返回p 3;
     *              }
     *              返回我，更新next 2 + next
     *          }
     *          返回我，更新next 1 + next
     *      }
     *      向下寻找
     *   }
     *   向下寻找
     * }
     *
     */
    public ListNode deleteDuplicates1(ListNode p){
        if(p == null || p.next == null){
            return p;
        }
        if(p.val == p.next.val){
            ListNode x = p.next.next;
            while(x != null && x.val == p.val){
                x = x.next;        //只要有重复，一直往下找
            }
            return deleteDuplicates1(x); //找到了跟p取值不同的节点
        }else{
            p.next = deleteDuplicates1(p.next);
            return p;
        }
    }


    /**
     * 方法2:两个指针

       P1：哨兵指针，用来删除重复的节点
       P2
       P3

       P1   p2   P3
       s -> 1 -> 1 -> 1 -> 2 -> 3 -> 3 -> null     遇到重复的，移动P3；
       P1   p2             P3
       s -> 1 -> 1 -> 1 -> 2 -> 3 -> 3 -> null     当P3找到跟P2不一样的值时，P1.next = P3
       P1   p2   P3
       s -> 2 -> 3 -> 3 -> null                    如果没有重复的，移动P1、P2、P3，
            P1   p2   P3
       s -> 2 -> 3 -> 3 -> null                    继续之前的操作，直到P3或者p2为null；
            P1   p2         P3
       s -> 2 -> 3 -> 3 -> null
            P1   p2   P3
       s -> 2 -> null
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode s = new ListNode(-1,head);
        ListNode p1 = s;
        ListNode p2,p3;

        while((p2 = p1.next)!= null && (p3 = p2.next) != null){     //
            if(p2.val == p3.val){    //一旦发现重复的，进入循环，寻找不重复，或者p3 == null的情况
                while((p3 = p3.next)!= null && p3.val == p2.val){
                }
                p1.next = p3; //找到了不重复的节点，或者p3 == null
            }else{
                p1 = p1.next;   //没有重复的，p1往前移动，p2和p3的位置在重新进去循环的时候定义
            }
        }
        return s.next;

    }
    public static void main(String[] args) {
        ListNode head = ListNode.of(1,2,3,3,3,4,4,5);
//        ListNode head = ListNode.of(1,1,1,1);
        System.out.println(head);
        System.out.println(new LeetCode0082RemoveDuplicatesfromSortedListII().deleteDuplicates2(head));
    }
}

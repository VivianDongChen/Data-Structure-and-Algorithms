package DataStructure.LinkedList;

//删除重复元素保留1个
public class LeetCode_0083 {

    /**
     * 方法1: 两个指针
     *
     * P1   p2
     * 1 -> 1 -> 2 -> 3 -> 3 -> null       p1 = p2, 删除p2, p1保持不变， p2指向p1.next
     * P1   p2
     * 1 -> 2 -> 3 -> 3 -> null           p1 != P2  两个指针向后移动
     *      P1   p2
     * 1 -> 2 -> 3 -> 3 -> null
     *           P1   p2
     * 1 -> 2 -> 3 -> 3 -> null
     *           P1   p2
     * 1 -> 2 -> 3 -> null               p1 = p2, 删除p2, p1保持不变， p2指向p1.next
     */
    public ListNode deleteDuplicates1(ListNode head){
        if(head == null || head.next == null){     //边缘情况
            return head;
        }

        ListNode p1 = head;
        ListNode p2;

        while((p2 = p1.next)!= null){
            if(p1.val == p2.val){
                p1.next = p1.next.next;
            }else{
                p1 = p1.next;
            }
        }

        return head;

    }

    /**
     * 方法2: 递归
     * 递归函数负责返回：从当前节点（我）开始，完成去重的链表：
     * - 若我与next重复，返回next
     * - 若我与next不重复，返回我，但next应当更新
     *
     deleteDuplicates2（ListNode p=1){
        deleteDuplicates2（ListNode p=1){
            deleteDuplicates2（ListNode p=2){
                deleteDuplicates2（ListNode p=3){
                    deleteDuplicates2（ListNode p=3){
                        //只剩一个节点，返回 3
                    }
                    返回next
                }
                发回我，更新next 2 + next
            }
            返回我，更新next 1 + next
        }
        返回next
     }

     */
    public ListNode deleteDuplicates2(ListNode p) {
        if (p == null || p.next == null) {     //边缘情况
            return p;
        }

        if(p.val == p.next.val){
            return deleteDuplicates2(p.next);
        }else{
            p.next = deleteDuplicates2(p.next);
            return p;
        }
    }


    public static void main(String[] args) {
        ListNode head = ListNode.of(1,1,2,3,3);
        System.out.println(head);
        System.out.println(new LeetCode_0083().deleteDuplicates2(head));
    }
}

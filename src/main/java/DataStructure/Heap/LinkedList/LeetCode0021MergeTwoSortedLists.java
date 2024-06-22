package DataStructure.Heap.LinkedList;

public class LeetCode0021MergeTwoSortedLists {

    /**
     * 方法1 - 指针法
     *  - 谁小，就把谁链接给p,p和小的都向后平移一位
     *  - 当p1,p2又一个为null,退出循环，把不为null的链给p
     *
     * p1
     * 1 -> 3 -> 8 -> 9 -> null
     * p2
     * 2 -> 4 -> null
     * p
     * s -> null
     *
     *      p1
     * 1 -> 3 -> 8 -> 9 -> null
     * p2
     * 2 -> 4 -> null
     *      p
     * s -> 1
     *
     *      p1
     * 1 -> 3 -> 8 -> 9 -> null
     *      p2
     * 2 -> 4 -> null
     *           p
     * s -> 1 -> 2
     *
     *           p1
     * 1 -> 3 -> 8 -> 9 -> null
     *      p2
     * 2 -> 4 -> null
     *                p
     * s -> 1 -> 2 -> 3
     *
     *           p1
     * 1 -> 3 -> 8 -> 9 -> null
     *            p2
     * 2 -> 4 -> null
     *                     p
     * s -> 1 -> 2 -> 3 -> 4
     *
     *           p1
     * 1 -> 3 -> 8 -> 9 -> null
     *            p2
     * 2 -> 4 -> null
     *                     p
     * s -> 1 -> 2 -> 3 -> 4 -> 8 -> 9 -> null
     */
    public ListNode mergeTwoLists1(ListNode p1, ListNode p2){
        ListNode s = new ListNode(-1,null);
        ListNode p = s;

        while(p1 !=null && p2 != null){
            if(p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 =p2.next;
            }
            p = p.next;
        }

        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;

        }

        return s.next;


    }



    /**
     * 方法2 - 递归
     *
     * 递归函数应该返回：
     * - 更小的那个链表节点，并把它剩余节点与另一个链表再次递归
     * - 返回之前，更新此节点的next
     *
     * mergeTwoLists2(p1 = [1,3,8,9], p2 = [2,4]){
     *  mergeTwoLists2(p1 = [3,8,9], p2 = [2,4]){
     *      mergeTwoLists2(p1 = [3,8,9], p2 = [4]){
     *          mergeTwoLists2(p1 = [8,9], p2 = [4]){
     *              mergeTwoLists2(p1 = [8,9], p2 = null){
     *                  return [8,9]
     *              }
     *              return 4
     *          }
     *          return 3
     *      }
     *      return 2
     *  }
     *     return 1
     * }
     *
     * mergeTwoLists2(p1 = [1,3,8,9], p2 = [2,4]){
     *  1.next = mergeTwoLists2(p1 = [3,8,9], p2 = [2,4]){
     *      2.next = mergeTwoLists2(p1 = [3,8,9], p2 = [4]){
     *          3.next = mergeTwoLists2(p1 = [8,9], p2 = [4]){
     *              4.next = mergeTwoLists2(p1 = [8,9], p2 = null){
     *                  return [8,9]
     *              }
     *              return 4
     *          }
     *          return 3
     *      }
     *      return 2
     *  }
     *     return 1
     * }
     */
    public ListNode mergeTwoLists2(ListNode p1, ListNode p2){
        if(p1 == null){
            return p2;
        }
        if(p2 == null){
            return p1;
        }

        if(p1.val < p2.val){
            p1.next = mergeTwoLists2(p1.next,p2);
            return p1;
        }else{
            p2.next = mergeTwoLists2(p1,p2.next);
            return p2;
        }

    }

    public static void main(String[] args) {
        ListNode p1 = ListNode.of(1,3,8,9);
        ListNode p2 = ListNode.of(2,4);

        System.out.println(new LeetCode0021MergeTwoSortedLists().mergeTwoLists2(p1,p2));
    }
}

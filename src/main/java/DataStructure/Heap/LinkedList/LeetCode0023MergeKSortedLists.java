package DataStructure.Heap.LinkedList;

public class LeetCode0023MergeKSortedLists {

    public ListNode mergeTwoLists(ListNode p1, ListNode p2){
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
     * 递归：分而治之
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }

        return  split(lists,0, lists.length-1);

    }

    public ListNode split(ListNode[] lists,int i,int j){

        if(i == j){   //数组内只有一个链表
            return lists[i];
        }

        int m = (i+j) >>> 1;

        ListNode right = split(lists,m+1,j);
        ListNode left = split(lists,i,m);
        return mergeTwoLists(left,right);
    }

    public static void main(String[] args) {
        ListNode[] lists ={
                ListNode.of(1,4,5),
                ListNode.of(1,3,4),
                ListNode.of(2,6)
        };
        ListNode m = new LeetCode0023MergeKSortedLists().mergeKLists(lists);
        System.out.println(m);
    }

}

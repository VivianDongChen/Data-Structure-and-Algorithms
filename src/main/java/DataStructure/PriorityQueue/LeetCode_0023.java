package DataStructure.PriorityQueue;

import DataStructure.LinkedList.ListNode;

public class LeetCode_0023 {

    /*
                    p
    1 -> 4 -> 5 -> null
                    p
    1 -> 3 -> 4 -> null
               p
    2 -> 6 -> null

    小顶堆

    空链表  1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6

     */

    public static ListNode mergeKLists(ListNode[] lists){
        MinHeap heap = new MinHeap(lists.length);  //准备一个MinHeap
        //将lists中的每个非空链表的头放入MinHeap
        for(ListNode h:lists){
            if(h != null){
                heap.offer(h);
            }
        }

        ListNode s = new ListNode(-1, null); //定义一个带哨兵的空链表
        ListNode t = s; //定义一个指针，指向哨兵
        while(!heap.isEmpty()){
            //拿出最小的头节点放入空链表
            ListNode n = heap.poll();
            t.next = n;
            t = n;

            //将头节点的next节点放入minHeap
            if(n.next != null){
                heap.offer(n.next);
            }
        }

        return s.next;
    }

    public static void main(String[] args) {
        ListNode[] lists ={
                ListNode.of(1,4,5),
                ListNode.of(1,3,4),
                ListNode.of(2,6)
        };
        ListNode m = mergeKLists(lists);
        System.out.println(m);
    }

}

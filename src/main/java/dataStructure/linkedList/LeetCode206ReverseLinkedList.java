package dataStructure.linkedList;

//反转链表
public class LeetCode206ReverseLinkedList {


    /**
     * 方法1: 构造一个新链表，从旧链表依次拿到每个节点，创建新节点添加至新链表头部，完成后的新链表即是倒序的
     * 优点：代码比较简单；缺点：每次都要创建新的节点，占用内存较多
     * @param o1
     * @return
     */
    public static ListNode reverseList1(ListNode o1){
        ListNode n1 = null;
        while(o1 != null){
            n1 = new ListNode(o1.val,n1);
            o1 = o1.next;
        }
        return n1;
    }


    /**
     * 方法2: 构造一个新链表，从旧链表头部移除节点，添加到新链表头部，完成后新链表即是倒序的，
     * 特点：面向对象的编程方法； 方法5跟方法2思路相同，但是方法5是面向过程的编程方法，方法2在实际使用更多
     * 区别在于：
     *  - 原题目未提供节点外层的容器类，这里提供一个
     *  - 并不去构造新节点
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head){

        List list1 = new List(head);
        List list2 = new List(null);

        while(true){
            ListNode first = list1.removeFirst();
            if(first == null){
                break;
            }
            list2.addFirst(first);
        }

        return list2.head;


    }

    static class List{

        private ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first){
            first.next = head;
            head = first;
        }

        public ListNode removeFirst(){
            ListNode first = head;
            if(first != null){
                head = first.next;
            }
            return first;
        }
    }

    /**
     * 方法3: 递归
     * @param p
     * @return
     */
    public static ListNode reverseList3(ListNode p){
        if(p == null || p.next == null){
            return p;
        }

        ListNode last = reverseList3(p.next);
        p.next.next = p;
        p.next = null;
        return last;

    }


    /**
     * 方法4: 从链表每次拿到第二个节点，将其从链表断开，插入头部，直至它为null结束, 需要使用三个指针进行定位
     * @param o1
     * @return
     */
    public static ListNode reverseList4(ListNode o1){       //o1:old1，旧链表头指针；

        //边缘情况:空链表或者只有一个元素
        if(o1 == null || o1.next == null){
            return o1;
        }
        ListNode o2 = o1.next;       //o2: old2, 旧链表第二个位置指针
        ListNode n1 = o1;          //n1: 新链表头指针；

        while(o2 != null){
            o1.next = o2.next;    //将旧链表第二个位置元素从链表中抽出：第一指向第三
            o2.next = n1;     //将旧链表第二个位置元素放在新链表头部n1
            n1 = o2;    //n1指针仍指向新链表头部；
            o2 = o1.next; //o2指针回到原位置；
        }

        //重复以上步骤，直到o2为null

        return n1;

    }

    /**
     * 方法5: 把链表分成两部分，思路是不断从链表2的头，往链表1的头搬移
     * @param o1
     * @return
     */
    public static ListNode reverseList5(ListNode o1){

        if(o1 == null || o1.next == null){
            return o1;
        }

        ListNode n1 = null;
        ListNode o2;

        while(o1 != null){
            o2 = o1.next;   //暂存
            o1.next = n1;   //搬移
            n1 = o1;        //n1指针复位
            o1 = o2;        //o1指针复位
        }
        return n1;
    }




    //测试
    public static void main(String[] args) {
        ListNode o5 = new ListNode(5,null);
        ListNode o4 = new ListNode(4,o5);
        ListNode o3 = new ListNode(3,o4);
        ListNode o2 = new ListNode(2,o3);
        ListNode o1 = new ListNode(1,o2);
        System.out.println(o1);
        ListNode n1 = LeetCode206ReverseLinkedList.reverseList5(o1);
        System.out.println(n1);


    }
}

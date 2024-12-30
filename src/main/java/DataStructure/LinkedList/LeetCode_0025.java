package DataStructure.LinkedList;
/**
 * 25. Reverse Nodes in k-Group
 * 本题的难点是使用递归来分段处理链表，并且将处理好的每一段使用返回值（节点头）与上一层遍历连接起来
 * 以及使用指针反转节点的操作。
 */
public class LeetCode_0025 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        // find the k+1 node
        while(curr != null && count < k){
            curr = curr.next;
            count++;
        }
        if (count == k) {//说明前面找到了完整的一段k个节点
            curr = reverseKGroup(curr, k); //使用递归继续处理后续的节点，使用curr将链表连接起来。
            for(int i = 0; i < k; i++){
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        return head;
    }

    // 打印链表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LeetCode_0025 test = new LeetCode_0025();
        ListNode head = test.new ListNode(1);
        head.next = test.new ListNode(2);
        head.next.next = test.new ListNode(3);
        head.next.next.next = test.new ListNode(4);
        head.next.next.next.next = test.new ListNode(5);

        int k = 2;

        printList(test.reverseKGroup(head, k));


    }


}

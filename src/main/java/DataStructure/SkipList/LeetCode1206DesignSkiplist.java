package DataStructure.SkipList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 跳表
 */
public class LeetCode1206DesignSkiplist {
    public static void main(String[] args) {
        Skiplist list = new Skiplist();
        Skiplist.Node head = list.head;
        Skiplist.Node n3 = new Skiplist.Node(3);
        Skiplist.Node n7 = new Skiplist.Node(7);
        Skiplist.Node n11 = new Skiplist.Node(11);
        Skiplist.Node n12 = new Skiplist.Node(12);
        Skiplist.Node n16 = new Skiplist.Node(16);
        Skiplist.Node n19 = new Skiplist.Node(19);
        Skiplist.Node n22 = new Skiplist.Node(22);
        Skiplist.Node n23 = new Skiplist.Node(23);
        Skiplist.Node n26 = new Skiplist.Node(26);
        Skiplist.Node n30 = new Skiplist.Node(30);
        Skiplist.Node n37 = new Skiplist.Node(37);
        head.next[0] = head.next[1] = head.next[2] = n3;
        head.next[3] = head.next[4] = head.next[5] = head.next[6] = head.next[7] = n19;
        n3.next[0] = n3.next[1] = n3.next[2] = n7;
        n7.next[0] = n11;
        n7.next[1] = n12;
        n7.next[2] = n16;
        n11.next[0] = n12;
        n12.next[0] = n12.next[1] = n16;
        n16.next[0] = n16.next[1] = n16.next[2] = n19;
        n19.next[0] = n19.next[1] = n19.next[2] = n22;
        n19.next[3] = n26;
        n22.next[0] = n23;
        n22.next[1] = n22.next[2] = n26;
        n23.next[0] = n26;
        n26.next[0] = n30;
        n26.next[1] = n37;
        n30.next[0] = n37;

        System.out.println(Arrays.toString(list.find(30)));
    }

    private static void testRandomLevel() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int level = randomLevel(5);
            map.compute(level, (k, v) -> v == null ? 1 : v + 1);
        }
        System.out.println(map.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> String.format("%d%%", e.getValue() * 100 / 1000)
                )
        ));
    }

    /*
    设计一个方法，方法会随机返回 1~max 的数字
    从 1 开始，数字的几率逐级减半，例如 max = 4，让大概
        - 50% 的几率返回 1
        - 25% 的几率返回 2
        - 12.5% 的几率返回 3
        - 12.5% 的几率返回 4
     */
    static Random r = new Random();
    static int randomLevel(int max){
        //nextBoolean() 生成一个随机的布尔值，它有50%概率返回true，50%返回false
//        return r.nextBoolean() ? 1: r.nextBoolean()? 2 : r.nextBoolean() ?  3 : 4;
        int x = 1;
        while( x < max){
            if(r.nextBoolean()){  //每一次调用，为true的概率减半
                return x;
            }
            x++;
        }
        return x;  //最后剩下的x（max）的返回概率也是最后的一半
    }

    static class Skiplist{
        private static final int MAX = 10;   // MAX的具体取值根据需求决定（redis数据库中上线是32个 Java是62个）
        private final Node head = new Node(-1); //头节点

        static class Node{
            int val; //值
            Node[] next = new Node[MAX]; //next指针数组的上限是MAX

            public Node(int val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return "Node(" + val + ")";
            }
        }

        /**
         * 查找元素经过的路径 - 实现下楼梯规则
         * - 若next指针为null， 或者next指向的节点值 >= 目标值， 向下找
         * - 若next指针不为null， 且next指向的节点值 < 目标值， 向右找
         * @param val 待查找元素值
         */
        public Node[] find(int val){
            Node[] path = new Node[MAX];     //记录经过的路径
            Node curr = head;
            for(int level = MAX - 1; level >= 0; level--) { //从上向下找
                while (curr.next[level] != null && curr.next[level].val < val) {   //向右找
                    curr = curr.next[level];
                }
                path[level] = curr;
            }
//            System.out.println(curr);  //此时curr指向目标节点前的那个节点（比目标节点小的最大值）
            return path;
        }

        /**
         * 查找元素
         * @param val 待查找元素的值
         * @return 查找到了返回true，没有查找到返回false
         */
        public boolean search(int val){
            Node[] path = find(val);
            Node node = path[0].next[0];
            return node != null && node.val == val;
        }

        /**
         * 添加节点
         *  - 跳表各元素的高度要高低不平才能提高查找效率
         *  - 设计原则：让添加节点的高度随机生成，矮的几率大一些，高的几率小一些。
         * @param val 待添加节点的值
         */
        public void add(int val){
            //1.确定添加节点的位置（把val当作目标查询，经历的路径就可以确定添加位置）
            Node[] path = find(val);

            //2.确定节点的高度（随机）
            Node node = new Node(val);
            int level = randomLevel(MAX);  //产生随机的高度
            //3.修改路径节点next指针，以及新节点next指针
            for (int i = 0; i < level; i++) {
                node.next[i] = path[i].next[i];
                path[i].next[i] = node;
            }
        }

        /**
         * 删除节点
         * @param val 待删除节点的值
         * @return 删除失败（没有找到节点）返回false，删除成功，返回true
         */
        public boolean erase(int val){
            //1.确定删除节点的位置
            Node[] path = find(val);
            Node node = path[0].next[0];
            if(node == null || node.val != val){
                return false;
            }
            //2.修改路径节点next指针
            for (int i = 0; i < MAX; i++) {
                if(path[i].next[i] != node){   //高度到了
                    break;
                }
                path[i].next[i] = node.next[i];
            }
            return true;
        }
    }
}

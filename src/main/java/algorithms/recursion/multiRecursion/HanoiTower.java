package algorithms.recursion.multiRecursion;

import org.springframework.util.StopWatch;

import java.util.LinkedList;

//汉诺塔的递归实现
public class HanoiTower {
    static LinkedList<Integer> a = new LinkedList<>();  //柱子用链表表示，链表中的元素表示圆盘
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    // 3 2 1 顶

    /**
     * 数据的初始化，以此将圆盘添加到a柱子上
     * @param n 最底层盘子的大小
     */
    static void init(int n){
        for (int i = n; i >= 1 ; i--) {
            a.addLast(i);
        }
    }

    /**
     * 模拟移动圆盘
     * @param n  圆盘的个数
     * @param a  源头柱子
     * @param b  中间柱子
     * @param c  目标柱子
     */
    static void move(int n, LinkedList a,LinkedList b,LinkedList c){

        if( n == 0){
            return;
        }

        move(n-1,a,c,b); //将n-1个盘子，由a,借助c,移动到b
        c.addLast(a.removeLast());  //将最后一个盘子由a移动到c
//        print();
        move(n-1,b,a,c);  //再将n-1个盘子，由b,借助a,移动到c

    }

    //估算时间复杂度：T(n) = 2T(n-1) + c, O(2^n). 当 n = 64时，2^64 = 好多好多年

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        int n = 1;
        init(n);
        print();
        sw.start("n=1");
        move(n,a,b,c);
        sw.stop();
        print();
        System.out.println(sw.prettyPrint());
    }

    private static void print() {
        System.out.println("-------------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}

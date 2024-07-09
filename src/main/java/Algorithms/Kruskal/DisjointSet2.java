package Algorithms.Kruskal;

import java.util.Arrays;

/**
 * 不相交集合（并查集合）- 优化
 */
public class DisjointSet2 {
    int[] s;

    public DisjointSet2(int size){
        s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
    }

    /**
     * 找到集合的老大 - 优化：路径压缩
     *
     * find（2） 0
     *   find（1） 0
     *     find（0） 0
     *
     * @param x 待寻找老大的集合中的一个元素
     * @return 元素老大
     */
    public int find(int x){  //x = 2
        if(x == s[x]){
            return x;
        }
        return  s[x] = find (s[x]); //0  s[2] = 0  让s[x]等于返回的结果，即s[x]直接指向老大，压缩了路径
    }

    /**
     * 让两个集合“相交”，即选出新老大，x、y是原老大索引
     * @param x 一个集合的老大的索引
     * @param y 另一个集合的老大的索引
     */
    public void union(int x, int y){
        s[y] = x;    //x,y相交，x是y的老大

    }

    @Override
    public String toString(){
        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        DisjointSet2 set = new DisjointSet2(7);
        System.out.println(set);
        System.out.println("---------------------");

//        //1. 演示Kruskal算法执行流程，下面是按边的权重排序的顺序：
//
//        int x = set.find(0);
//        int y = set.find(3);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(5);
//        y = set.find(6);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(0);
//        y = set.find(1);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(2);
//        y = set.find(3);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(1);
//        y = set.find(3);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(0);
//        y = set.find(2);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(3);
//        y = set.find(6);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }

//        x = set.find(2);
//        y = set.find(5);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(4);
//        y = set.find(6);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(3);
//        y = set.find(4);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(3);
//        y = set.find(5);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }
//
//        x = set.find(1);
//        y = set.find(4);
//        System.out.println("老大分别是：" + x + " " + y);
//        if( x != y){
//            set.union(x,y);
//            System.out.println(set);
//        }

        //2. 演示最糟情况
        set.union(0,1);
        set.union(1,2);
        set.union(2,3);
        set.union(3,4);
        set.union(4,5);
        set.union(5,6);
        System.out.println(set);   //此时顶点6距离老大0的路径很远， 要通过其它所有顶点才能找到老大0
        set.find(6);   //所有的顶点都指向了老大， 路径进行了压缩
        System.out.println(set);

    }
}

package Algorithms.Kruskal;

import java.util.Arrays;

/**
 * 不相交集合（并查集合）
 */
public class DisjointSet {
    int[] s;
    //数组的索引对应顶点
    //数组的元素是用来表示与顶点有关系的顶点（顶点默认只与自己有关系， 所以s[i] = i)

    public DisjointSet(int size){
        s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
    }

    /**
     * 找到老大
     * @param x 待寻找老大的集合中的一个元素
     * @return 元素老大
     */
    public int find(int x){
        if(x == s[x]){    //x跟自己有关系。说明它就是老大
            return x;
        }
        return find(s[x]); //如果不等于自己，说明不是老大，继续递归调用，寻找老大

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
        DisjointSet set = new DisjointSet(7);
        System.out.println(set);
        System.out.println("---------------------");

        int x = set.find(0);
        int y = set.find(3);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(5);
        y = set.find(6);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(0);
        y = set.find(1);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(2);
        y = set.find(3);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(1);
        y = set.find(3);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(0);
        y = set.find(2);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(3);
        y = set.find(6);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(2);
        y = set.find(5);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(4);
        y = set.find(6);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(3);
        y = set.find(4);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(3);
        y = set.find(5);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }

        x = set.find(1);
        y = set.find(4);
        System.out.println("老大分别是：" + x + " " + y);
        if( x != y){
            set.union(x,y);
            System.out.println(set);
        }




    }
}

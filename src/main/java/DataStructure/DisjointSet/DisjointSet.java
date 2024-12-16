package DataStructure.DisjointSet;

import java.util.Arrays;

/**
   Disjoint Set（并查集） 经常用于解决图论中的动态连通性问题，但它的应用范围不仅限于图的概念。
   实际上，它是一种通用的数据结构，可以解决各种需要动态分组、集合管理或连通性检测的问题。
 */
public class DisjointSet {
    int[] s;

    public DisjointSet(int size){
        s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;   //索引对应顶点，元素用来表示与之有关系的顶点， 一开始索引 = 元素，表示每个顶点只跟自己有关系
        }
    }

    //查找集合的老大
    public int find(int x){
        if(x == s[x]){
            return x;
        }
        return find(s[x]);
    }

    //优化：路径压缩
    public int findPathCompression(int x){
        if(x == s[x]){
            return x;
        }
        return s[x] = findPathCompression(s[x]);
    }

    //让两个集合“相交”，选出新老大
    public void union(int a, int b){
        s[b] = a;
    }


    //用于打印数组的toString函数
    @Override
    public String toString() {
        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(7);
        System.out.println(set);

        set.union(0,1);
        set.union(1,2);
        set.union(2,3);
        set.union(3,4);
        set.union(4,5);
        set.union(5,6);
        System.out.println(set);

        set.find(2);
        System.out.println(set);

        set.findPathCompression(2);
        System.out.println(set);

        set.findPathCompression(6);
        System.out.println(set);


    }

}

package DataStructure.DisjointSet;

import java.util.Arrays;

/**
 优化：Union by size 使相交后的集合中顶点到老大的路径整体最短
 */
public class DisjointSet_UnionBySize {
    int[] s;
    int[] size; //新数组用来记录每个集合中元素的个数

    public DisjointSet_UnionBySize(int size){
        s = new int[size];
        this. size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;   //索引对应顶点，元素用来表示与之有关系的顶点， 一开始索引 = 元素，表示每个顶点只跟自己有关系
            this.size[i] = 1; //刚开始，每个集合中都只有一个元素
        }
    }

    public int find(int x){
        if(x == s[x]){
            return x;
        }
        return find(s[x]);
    }

    public int findPathCompression(int x){
        if(x == s[x]){
            return x;
        }
        return s[x] = findPathCompression(s[x]);
    }

    public void union(int a, int b){
        s[b] = a;
        size[a] += size[b];
    }

    public void unionBySize(int a, int b){
        if(size[a] < size[b]){
            s[a] = b;
            size[b] += size[a]; //更新老大的元素个数
        }else{
            s[b] = a;
            size[a] += size[b]; //更新老大的元素个数
        }
    }

    //用于打印数组的toString函数
    @Override
    public String toString() {
        return "内容: " + Arrays.toString(s) + "\n" + "大小: " + Arrays.toString(size);
    }

    public static void main(String[] args) {
        DisjointSet_UnionBySize set1 = new DisjointSet_UnionBySize(5);
        System.out.println(set1);
        System.out.println();

        set1.union(1,2);
        set1.union(3,4);
        set1.union(1,3);
        System.out.println(set1);
        System.out.println();

        set1.unionBySize(1,0);
        System.out.println(set1);
        System.out.println("--------------------------------");

        DisjointSet_UnionBySize set2 = new DisjointSet_UnionBySize(5);
        System.out.println(set2);
        System.out.println();

        set2.union(1,2);
        set2.union(3,4);
        set2.union(1,3);
        System.out.println(set2);
        System.out.println();

        set2.unionBySize(0,1);
        System.out.println(set2);


    }

}
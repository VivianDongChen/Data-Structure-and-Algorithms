package Algorithms.Kruskal;

import java.util.Arrays;

/**
 * 不相交集合（并查集合）- 优化2：union by size
 */
public class DisjointSet_III_UnionBySize {
    int[] s;
    int[] size; //加入一个新的数组参数, 代表集合里元素的个数

    public DisjointSet_III_UnionBySize(int size){
        s = new int[size];
        this.size = new int[size];  //初始化数组的大小
        for (int i = 0; i < size; i++) {
            s[i] = i;   //初始化s数组，每个index的元素是它自己
            this.size[i] = 1;   //初始化size数组，每个index的元素都是1（每个集合的初始元素数都是1）
        }
    }

    /**
     * 找到集合的老大
     * @param x 待寻找老大的集合中的一个元素
     * @return 元素老大
     */
    public int find(int x){
        if(x == s[x]){
            return x;
        }
        return s[x] = find(s[x]);
    }

    /**
     * 让两个集合“相交”，即选出新老大，x、y是原老大索引
     * @param x 一个集合的元素的索引
     * @param y 另一个集合的元素的索引
     */
    public void union(int x, int y){

        int rootX = find(x);
        int rootY = find(y);
        //x集合的元素个数小于y集合的元素个数， x并入y
//        if(size[rootX] < size[rootY]){
//            s[rootX] = rootY;          //rootY成为新老大，rootX成为新小弟
//            size[rootY] += size[rootX];  //更新老大元素个数
//        }else{
//            s[rootY] = rootX;         //rootX成为新老大，rootY成为新小弟
//            size[rootX] += size[y];  //更新老大元素个数
//        }
        if(size[rootX] < size[rootY]){
            int t = rootX;
            rootX = rootY;
            rootY = t;
        }
        s[rootY] = rootX;
        size[rootX] += size[rootY];
    }

    @Override
    public String toString(){
        return "内容：" + Arrays.toString(s) + "\n大小：" + Arrays.toString(size);
    }

    public static void main(String[] args) {
        DisjointSet_III_UnionBySize set = new DisjointSet_III_UnionBySize(5);

        /* set.union(1,2);
           set.union(1,3);
           set.union(3,4);
           System.out.println(set);   //现在1，2，3，4都在一个集合里（老大是1），0不在

           set.union(0,1);   //将0连起来，老大是0（顶点多的集合连接到顶点少的集合）
           set.union(1,0);   //将0连起来，老大是1（顶点少的集合连接到顶点多的集合）， 这种方法要优于上面的方法，因为各顶点达到老大的距离较短
        */
        set.union(1,2);
        set.union(3,4);
        set.union(1,3);
        System.out.println(set);  //现在1，2，3，4都在一个集合里（老大是1），0不在

        set.union(0,1);   //会把0并入1集合
        System.out.println(set);
    }


}

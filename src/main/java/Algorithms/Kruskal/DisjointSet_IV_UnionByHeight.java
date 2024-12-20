package Algorithms.Kruskal;

import java.util.Arrays;

/**
 * 不相交集合（并查集合）- 优化3：union by Height
 */

public class DisjointSet_IV_UnionByHeight {
    int[] s;
    int[] height; //加入一个新的数组参数, 代表集合树的高度

    public DisjointSet_IV_UnionByHeight(int size){
        s = new int[size];
        this.height = new int[size];   //初始化数组大小
        for (int i = 0; i < size; i++) {
            s[i] = i;   //初始化s数组，每个index的元素是它自己
            this.height[i] = 0;    //初始化height数组，每个index的高度都是0（每个集合的初始高度都是0）
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

        if(height[rootX] < height[rootY]){      //rootX树的高度小于rootY树的高度， rootX并入rootY
            s[rootX] = y;          //rootY成为新老大，rootX成为新小弟
        }else if(height[rootX] > height[rootY]){     //rootX树的高度大于rootY树的高度， rootY并入rootX
            s[rootY] = rootX;         //rootX成为新老大，rootY成为新小弟
        }else{     //两树高度相同，选择一个为新老大，并且新老大的高度+1
            s[rootY] = rootX;    //rootX成为新老大
            height[rootX]++;   //更新老大的高度
        }

    }

    @Override
    public String toString(){
        return "内容：" + Arrays.toString(s) + "\n高度：" + Arrays.toString(height);
    }

    public static void main(String[] args) {
        DisjointSet_III_UnionBySize set = new DisjointSet_III_UnionBySize(5);

        set.union(1,2);
        set.union(3,4);
        set.union(1,3);
        System.out.println(set);  //现在1，2，3，4都在一个集合里（老大是1），0不在

        set.union(0,1);   //会把0并入1集合
        System.out.println(set);
    }


}
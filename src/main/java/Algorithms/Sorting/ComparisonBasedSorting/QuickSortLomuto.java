package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;

/**
 * 快速排序
 * 最好：O（nlogn)  最坏： O (n^2)   平均：O (nlogn)   空间：O（logn）
 * 核心思想：每轮找到一个基准点元素，把比它小的放到它左边，比它大的放到它右边，这称为分区
 *
 * - 单边循环快排（lomuto 洛穆托分区方案)
 * 1. 选择最右元素作为基准点元素
 * 2. j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
 *  - 交换时机：j 找到小的，且与 i 不相等
 *  - i 找到 >= 基准点元素后，不应自增
 *  即：j从left遍历到right-1找比基准点小的且与i不相等，一旦找到，与i进行交换, 交换完之后i自增。
 * 3. 这样遍历完，i左边的全部比基准点小，右边全部比基准点大（除了基准点），i本身比基准点大
 * 4. 最后基准点与i交换，i即为基准点最终索引
 */
public class QuickSortLomuto {

    public static void sort(int[] a){
        quick(a,0,a.length-1);
    }

    private static void quick(int[] a, int left, int right){
        if(left >= right){
            return;
        }
        int p = partition(a, left, right);  //p代表基准点索引
        quick(a, left, p-1);
        quick(a, p+1, right);

    }

    private static int partition(int[] a, int left, int right){
        int pv = a[right];  //选择最右边元素作为基准点
        int i = left;
        for (int j = left; j < right; j++) {
            if(a[j] < pv){  //j找到比基准点小的了
                if(j != i){
                    swap(a,i,j);
                }
                i++; //j找到小的，i跟j一起向前移动，j找到大的，i停止
            }
        }
        swap(a, i,right);
        return i;
    }



    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,7,6,4,8,5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}

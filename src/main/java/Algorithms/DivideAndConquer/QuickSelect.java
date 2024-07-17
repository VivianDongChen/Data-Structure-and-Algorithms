package Algorithms.DivideAndConquer;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 快速选择算法 - 分而治之 O(n)
 */
public class QuickSelect {
    /*
       求排在第i名的元素， i从0开始，由小到大排
       6  5  1  2  4
     */

    static int quick(int[] array, int left, int right, int i){
//        Arrays.sort(array);  //n *log(n)
//        return array[i];
        /*
            6   5   1   2   [4]    基准点

                    2
            1   2   4   6   5

            1   2   4   6   [5]

                        3
            1   2   4   5   6
         */

        int p = partition(array, left, right);
        //将得到的锚点与i进行比较，确定下一步是否需要继续排序
        if(p == i) {
            return array[p];
        }else if(p > i){    //到左边找
            return quick(array, left, p-1,i);
        }else {    //到右边找
            return quick(array, p+1, right, i);
        }

    }

    /**
     * 快速排序分区 - 随机
     * @param a 待排序的数组
     * @param left 本次分区的起点
     * @param right 本次分区的终点
     * @return 本次排序分区得到的锚点索引
     */
    private static int partition(int[] a, int left, int right){
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, idx, left);

        int pv = a[left];
        int i = left;
        int j = right;
        while(i < j){
            while(i < j && a[j] > pv){
                j--;
            }
            while(i < j && a[i] <= pv){
                i++;
            }
            swap(a, i, j);
        }
        swap(a, i,left);
        return i;
    }


    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 1, 2, 4};
        System.out.println(quick(array, 0, array.length - 1, 0)); // 1
        System.out.println(quick(array, 0, array.length - 1, 1)); // 2
        System.out.println(quick(array, 0, array.length - 1, 2)); // 4
        System.out.println(quick(array, 0, array.length - 1, 3)); // 5
        System.out.println(quick(array, 0, array.length - 1, 4)); // 6
    }
}

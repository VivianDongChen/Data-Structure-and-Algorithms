package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机快速排序
 * 因为以最左边或最右边元素作为基准点，都有可能降低快速排序的性能，所以采用随机基准点
 */
public class QuickSortRandomized {
    public static void sort(int[] a){
        quick(a,0,a.length-1);
    }

    private static void quick(int[] a, int left, int right){
        if(left >= right){
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p-1);
        quick(a, p+1, right);

    }

    private static int partition(int[] a, int left, int right){
        //生成一个随机的index，这个index的范围是 left ～ right（包括right）
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, idx, left); //将随机生成的idx对应的值放到最左边，下面就用QuickSortHoare来实现

        int pv = a[left];
        int i = left;
        int j = right;
        while(i < j){
            //1. j从右向左找小的
            while(i < j && a[j] > pv){
                j--;
            }
            //2. i从左向右找大的
            while(i < j && a[i] <= pv){
                i++;
            }
            //3. 交换数据
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
        int[] a = {2,3,1,7,6,4,8,5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}

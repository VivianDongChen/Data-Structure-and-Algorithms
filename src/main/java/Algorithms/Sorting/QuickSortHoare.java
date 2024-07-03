package Algorithms.Sorting;

import java.util.Arrays;

/**
 *  双边循环快排
 *  1. 选择最左元素作为基准点元素
 *  2. j 指针负责从右向左找比基准点小或等的元素，i 指针负责从左向右找比基准点大的元素，一旦找到二者交换，直至 i，j 相交
 *  3. 最后基准点与 i（此时 i 与 j 相等）交换，i 即为分区位置
 *
 */
public class QuickSortHoare {
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
        int pv = a[left];  //选择最左元素作为基准点
        int i = left;
        int j = right;
        while(i < j){   //i和j相遇的位置， 这个位置取决于之前循环里是i先走还是j先走，所以循环里的1.2的顺序不能换
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
        swap(a, i,left); //这里与i交换，为确保i指向的是位置正确，循环里的1.2顺序不能换
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

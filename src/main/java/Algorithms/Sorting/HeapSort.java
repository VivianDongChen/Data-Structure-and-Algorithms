package Algorithms.Sorting;

import java.util.Arrays;

/**
 * 堆排序
 * 最好：O（nlogn)  最坏： O (nlogn)   平均：O (nlogn)   空间：O（1）
 */
public class HeapSort {
    public static void sort(int[] a){
        heapify(a, a.length);  //将数组建堆，这时数组的第一个是最大值
        //每次将数组的第一个交换到最后， 交换后将前面未排序数组进行下潜
        for (int right = a.length - 1; right > 0; right--) {
            swap(a, 0, right);
            down1(a, 0, right);
        }
    }

    /**
     * 建堆：弗洛伊德法建堆算法，从非叶子节点开始向堆顶节点，每个节点进行下潜
     * 时间复杂度：O（n)
     */
    private static void heapify(int[] a, int size){
        for (int i = size / 2 - 1; i >= 0; i--) {
            down1(a,i,size);
        }
    }

    /**
     * 下潜(递归实现）
     * @param a
     * @param parent  下潜节点的索引
     * @param size
     */
    private static void down1(int[] a, int parent, int size){
        int max = parent;
        int left = parent * 2 + 1;
        int right = left + 1;

        if(left < size && a[left] > a[max]){
            max = left;
        }
        if(right < size && a[right] > a[max]){
            max = right;
        }

        if(max != parent){  //找到了更大的孩子
            swap(a, max, parent);
            down1(a, max, size);
        }

    }

    /**
     * 下潜(非递归实现）
     * LeetCode上数组排序题目用堆排序解决，非递归实现比递归实现大约快6ms
     * @param a
     * @param parent  下潜节点的索引
     * @param size
     */
    private static void down2(int[] a, int parent, int size){
        while(true){
            int max = parent;
            int left = parent * 2 + 1;
            int right = left + 1;

            if(left < size && a[left] > a[max]){
                max = left;
            }
            if(right < size && a[right] > a[max]){
                max = right;
            }

            if(max == parent){  //没有找到更大的孩子
                break;
            }

            //走到这里，就是找到了更大的孩子
            swap(a, max, parent);
            parent = max;
        }

    }

    //交换
    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,7,6,4,5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

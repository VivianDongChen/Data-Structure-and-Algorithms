package DataStructure.Heap;

import java.util.Arrays;

/**
 * 堆排序
 * 1、Heapify 建立大顶堆
 * 2、将堆顶与堆底交换（最大元素被交换到堆底），缩小并下潜调整堆
 * 3、重复第二步直至堆里剩一个元素
 */
public class HeapSort {

    public static int[] sort(int[] array){
        MaxHeap maxHeap = new MaxHeap(array);
;        while (maxHeap.size > 1) {
            maxHeap.swap(0, maxHeap.size-1);
            maxHeap.size--;
            maxHeap.down(0);
        }
        return maxHeap.array;    //size变量用于控制堆的逻辑大小，而不是数组的物理大小
    }

    public static void main(String[] args) {
        int[] array = {4,7,6,2,3,5,1};
        System.out.println(Arrays.toString(sort(array)));
    }


}

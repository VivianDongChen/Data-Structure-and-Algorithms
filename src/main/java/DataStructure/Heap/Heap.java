package DataStructure.Heap;

import java.util.Arrays;

/*
可以扩容的heap，max用于指定是大顶堆还是小顶堆
 */
public class Heap {
    int[] array;
    int size;
    boolean max;

    public int size(){
        return size;
    }

    public Heap(int capacity, boolean max){
        this.array = new int[capacity];
        this.max = max;
    }

    /**
     * 获取堆顶元素
     * @return 堆顶元素
     */
    public int peek(){
        if(size == 0){
            throw new IllegalArgumentException("堆为空");
        }
        return array[0];
    }
    /**
     * 删除堆顶元素
     * @return 堆顶元素
     */
    public int poll(){
        if(size == 0){
            throw new IllegalArgumentException("堆为空");
        }
        int top = array[0];
        swap (0, size - 1);
        size--;
        down(0);
        return top;

    }

    /**
     * 堆的尾部添加元素
     * @param offered 新元素
     */
    public void offer(int offered){
        if(size == array.length){
            //扩容
            grow();
        }
        up(offered);
        size++;

    }

    /**
     * 扩容
     */
    private void grow(){
        int capacity = size + (size >> 1);  //扩容到size的1.5倍
        int[] newArray = new int[capacity];    //定义新数组
        System.arraycopy(array,0,newArray,0,size);  //将原数组数据拷贝到新数组
        array = newArray;
    }

    /**
     * 将offered元素上浮
     * @param offered 元素
     */
    private void up (int offered){
        int child = size;

        while(child > 0){
            int parent = (child -1)/2;
            boolean cmp = max ? offered > array[parent] : offered < array[parent];
            if(cmp){
                array[child] = array[parent];
            }else{
                break;
            }
            child = parent;
        }
        array[child] = offered;

    }
    public Heap(int[] array, boolean max){   //接收一个普通数组的构造方法
        this.array = array;
        this.size = array.length;
        this.max = max;
        heapify();
    }

    /**
     * 建堆
     */
    private void heapify() {
        //如何找到最后一个非叶子节点？ 公式：size/2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 将parent索引处的元素下潜
     * @param parent 索引
     */
    public void down(int parent){
        int left = parent * 2 + 1;
        int right = left + 1;
        int maxOrMin = parent;
        if(left < size && (max ? array[left] > array[maxOrMin]: array[left] < array[maxOrMin])){
            maxOrMin = left;
        }
        if(right < size && (max ? array[right] > array[maxOrMin]: array[right] < array[maxOrMin])){
            maxOrMin = right;
        }
        if(maxOrMin != parent){
            swap(maxOrMin, parent);
            down(maxOrMin);
        }
    }



    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Heap maxHeap = new Heap(5,true);
        for (int i = 1; i <= 10; i++) {
            maxHeap.offer(i);
            System.out.println(Arrays.toString(maxHeap.array));
        }
    }

}


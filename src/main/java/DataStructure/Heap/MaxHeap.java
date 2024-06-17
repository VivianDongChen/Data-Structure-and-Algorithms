package DataStructure.Heap;

import java.util.Arrays;

/**
 * 大顶堆
 */
public class MaxHeap {
    int[] array;
    int size;  //size变量用于控制堆的逻辑大小，而不是数组的物理大小

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array){   //接收一个普通数组的构造方法
        this.array = array;
        this.size = array.length;
        heapify();
    }

    //建堆
    private void heapify(){
        //如何找到最后一个非叶子节点？ 公式：size/2 - 1
        for (int i = size/2 - 1 ; i >= 0; i--) {
            down(i);
        }


    }

    /**
     * 将parent索引处的元素下潜，与两个孩子较大者交换，直至没有孩子或孩子没它大
     * @param parent 索引
     */
    public void down(int parent){
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if(left < size && array[left] > array[max]){
            max = left;
        }
        if(right < size && array[right] > array[max]){
            max = right;
        }
        if(max != parent){
            swap(max, parent);
            down(max);

        }
    }

    /**
     * 互换两个索引的值
     * @param i
     * @param j
     */
    public void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
        swap(0,size-1);
        size --;
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     * @param index 索引
     * @return 被删除的元素
     */
     public int poll(int index){
         if(size == 0){
             throw new IllegalArgumentException("堆为空");
         }
         int deleted = array[index];
         swap(index,size-1);
         size--;
         down(index);
         return deleted;
     }

    /**
     * 替换堆顶元素
     * @param replaced -新元素
     */
     public void replace(int replaced){
         array[0] = replaced;
         down(0);
     }

    /**
     * 堆的尾部添加元素
     * @param offered 新元素
     * @return 是否添加成功
     */
     public boolean offer(int offered){
         if(size == array.length){
             return false;
         }
         up(offered);
         size++;
         return true;

     }

    /**
     * 将offered元素上浮，直至offered小于父元素或到堆顶
     * @param offered 元素
     */
     private void up(int offered){
         int child = size;

         while(child > 0){
             int parent = (child -1)/2;
             if(offered > array[parent]){
                 array[child] = array[parent];
             }else{
              break;
             }
             child = parent;
         }
         array[child] = offered;

     }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        MaxHeap maxHeap = new MaxHeap(array);
        System.out.println(Arrays.toString(maxHeap.array));
    }
}

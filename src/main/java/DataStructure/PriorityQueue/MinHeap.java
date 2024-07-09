package DataStructure.PriorityQueue;

import DataStructure.LinkedList.ListNode;

public class MinHeap {
    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        array = new ListNode[capacity];
    }

    public boolean offer(ListNode offered) {
        if(isFull()){
            return false;
        }
        int child = size++;
        int parent = (child-1)/2;
        //上浮
        while(child > 0 && offered.val < array[parent].val){
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child] = offered;
        return true;
    }



    public ListNode poll() {
        if(isEmpty()){
            return null;
        }
        //交换元素
        swap(0,size-1);

        //出队
        size--;
        ListNode e = array[size];
        array[size] = null;  //help GC

        //下潜
        down(0);

        return e;
    }
    private void swap(int i, int j){
        ListNode t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void down(int parent){
        int left = parent*2 + 1;
        int right = left + 1;
        int min = parent;
        if(left < size && array[left].val < array[min].val){
            min = left;
        }
        if(right < size && array[right].val < array[min].val){
            min = right;
        }
        if(min != parent){
            swap(min,parent);
            down(min);
        }

    }

    public ListNode peek() {
        if(isEmpty()){
            return null;
        }

        return array[0];
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean isFull() {
        return size == array.length;
    }


}



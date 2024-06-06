package com.itheima.algorithms.dataStructure.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer>{

    private int size = 0; //逻辑大小，控制数组内有效元素的个数
    private int capacity = 8; //容量
    private int[] array = {};   //一开始先给一个空数组，避免浪费，在第一次添加的时候再给一个容量为capacity的数组

    /**
     * 向最后位置[size]添加元素
     *››
     * @param element -待添加元素
     */
    public void addLast(int element) {
        add(size, element);
    }

    /**
     * 向[0...size]位置添加元素
     *
     * @param index   - 索引位置
     * @param element - 待添加元素
     */
    public void add(int index, int element) {
        checkAndGrow();

        //添加逻辑
        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size ++;
    }

    /**
     * 检查并扩容
     */
    private void checkAndGrow() {
        //容量检查
        if(size == 0){        //第一次添加的时候
            array = new int[capacity];
        }else if(size == capacity){
            //进行扩容1.5倍，1.618倍，2倍  PS： Java使用1.5倍
            capacity += capacity >>> 1;   //无符号右移运算符
            int[] newArray = new int[capacity];    //定义新数组
            System.arraycopy(array,0,newArray,0,size);  //将原数组数据拷贝到新数组
            array = newArray;
        }
    }

    /**
     * 查询元素
     * @param index - 索引位置，在[0,size)区间内
     * @return 该索引位置的元素
     */
    public int get(int index){  //[0,size)
        return array[index];
    }

    /**
     * 删除元素
     * @param index - 要删除的元素索引
     * @return 删除的元素4
     */
    public int remove(int index) {   // [0,size)

        int removed = array[index];
        if(index < size -1){
            System.arraycopy(array,index+1,array,index,size-index-1);
        }
        size --;
        return removed;
    }

    /**
     * 遍历方式1
     * @param consumer - 遍历要执行的操作，入参：每个元素
     */
    public void foreach(Consumer<Integer> consumer){    //需要一个函数式接口：（1）提供array[i]（2）返回void 满足以上条件的接口： Consumer
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);

        }

    }

    /**
     * 遍历方式2 - 迭代器循环
     * @return - 迭代器
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;
            @Override
            public boolean hasNext() { //有没有下一个元素
                return i < size;
            }

            @Override
            public Integer next() {   //返回当前元素，并移动到下一个元素
                return array[i++];   //后++，先返回array[i], 在i++
            }
        };
    }

    /**
     * 遍历方式3 - stream流遍历
     * @return
     */
    public IntStream stream(){
//        return IntStream.of(array);
        return IntStream.of(Arrays.copyOfRange(array,0,size));
    }


}

package DataStructure.Heap;

import java.util.Arrays;

//数据流的中位数
public class LeetCode_0295_I {

    /*
       1    2    3      7    8    9

       s    s    3      7    g    g

       （大顶堆）堆顶     堆顶（小堆顶）

       为了保证两边数据量的平衡
       - 两边个数一样时，左边个数加1
       - 两边个数不一样时，右边个数加1

       但是，随便一个数能直接加入吗？
       - 左边个数加1时，应该挑右边最小的加入（先将数据加入右边，再把右边堆顶的取出来加入左边）
       - 右边个数加1时，应该挑左边最大的加入（先将数据加入左边，再把左边堆顶的取出来加入右边）
     */

    private Heap right = new Heap(10,false); //小顶堆
    private Heap left = new Heap(10,true);   //大顶堆


    public void addNum(int num){
        if(right.size() == left.size()){
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian(){
        if(right.size() == left.size()){
            return (right.peek() + left.peek()) / 2.0;
        }else{
            return left.peek();
        }
    }

    @Override
    public String toString() {
        return  Arrays.toString(Arrays.copyOf(left.array, left.size)) + " <-> " +
                Arrays.toString(Arrays.copyOf(right.array, right.size));
    }

    public static void main(String[] args) {
        LeetCode_0295_I test = new LeetCode_0295_I();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(7);
        test.addNum(8);
        test.addNum(9);
        System.out.println(test);
        System.out.println(test.findMedian());
        test.addNum(10);
        System.out.println(test);
        System.out.println(test.findMedian());
        test.addNum(4);
        System.out.println(test);
        System.out.println(test.findMedian());

    }


}
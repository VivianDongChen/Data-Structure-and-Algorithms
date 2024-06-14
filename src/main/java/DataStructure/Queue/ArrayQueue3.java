package DataStructure.Queue;

import java.util.Iterator;

/**
 * 使用环形数组来实现队列 - 方法3
 * 方法3: 只把head和tail看成不断累加的整数，需要用它们来定位数值的时候，再进行计算，这样head和tail就不存在重合的问题了
 *  - 带来的问题：因为head和tail是不断累加的整数，所以有可能会超出整数的边界变成负数。
 *    - 解决方案1:将head或tail的数值转化为Long (使用Integer. toUnsignedLong),
 *               此时不再为负数，求模之后再转换为int(使用（int))。 PS：数组的索引必须是int。
 *    - 解决方案2: 求模运算 -> 按位于计算（性能高，不用考虑符号位）
 *
 *    求模运算二进制规律：
 *    - 如果除数是2的n次方
 *    - 那么被除数的二进制后n位即为余数（模）
 *    - 求被除数的后n位方法：与2^n -1 按位于
 */
public class ArrayQueue3<E> implements Queue<E>,Iterable<E>{

    private E[] array;
    int head = 0;
    int tail = 0;


    @SuppressWarnings("all")
    public ArrayQueue3(int c) {
        //如何确保数组长度是2^n
        //1. 抛异常
//        if((capacity & (capacity -1)) != 0){
//            throw new IllegalArgumentException("capacity 必须是2的幂");
//        }
        //2. 改成2^n    13 -> 16    22 -> 32  (方法在main中）
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        array = (E[]) new Object[c];
    }
    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
//        array[(int)(Integer.toUnsignedLong(tail) % array.length)] = value;
        array[tail & (array.length -1)] = value;    //确保数组的长度是2^n
        tail ++;

        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
//        E value = array[(int)(Integer.toUnsignedLong(head) % array.length)];
        E value = array[head & (array.length -1)];
        head ++;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
//        return array[(int)(Integer.toUnsignedLong(head) % array.length)];
        return array[head & (array.length-1)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head  == array.length;
    }     //这个运算不会超过正整数的最大值

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
//                E value = array[(int)(Integer.toUnsignedLong(p) % array.length)];
                E value = array[p & (array.length-1)];
                p ++;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        //求离c最近，比c大的2^n？（方法1）
           int c = 32;

       /*
              2^4 = 16
              2^4.? = 30
              2^5 = 32

              int(log2(30))+1

              log2(x) = log10(x)/log10(2)
        */


//        int n = (int) (Math.log10(c-1)/Math.log10(2)) + 1;
//        System.out.println(n);
//        System.out.println(1 << n);  //2^n

        /*
            1
            1 << 1  10     2^1
            1 << 2  100    2^2
            1 << 3  1000   2^3
            1 << n  1....  2^n
         */

        //求离c最近，比c大的2^n？（方法2）
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        System.out.println(c);

    }
}

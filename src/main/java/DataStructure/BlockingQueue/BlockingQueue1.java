package DataStructure.BlockingQueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁实现
 * @param <E> - 元素类型
 */
@SuppressWarnings("all")
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();      //锁对象
    private Condition headWaits = lock.newCondition();     //条件变量对象   用来处理poll()中的线程阻塞
    private Condition tailWaits = lock.newCondition();     //条件变量对象   用来处理offer()中的线程阻塞

    @Override
    public void offer(E e) throws InterruptedException {   // poll() 等待非空

        lock.lockInterruptibly();
        try{
            while(isFull()){
                tailWaits.await();  //等多久
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();

        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {  //单位：毫秒 5s
        lock.lockInterruptibly();
        try{
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);   //毫秒换算成纳秒
            while(isFull()){
                if(t <= 0){          //剩余时间没有了，还是full的状态
                    return false;
                }
                t = tailWaits.awaitNanos(t);  //最多等待多少纳秒 1s 4s  返回值代表剩余时间
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;    //添加成功了
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(isEmpty()){
                headWaits.wait();
            }
            E e = array[head];
            array[head] = null;  //help GC
            if(++head == array.length){
                head = 0;
            }
            size--;
            tailWaits.signal();
        }finally {
            lock.unlock();
        }
        return null;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    private boolean isFull(){
        return size == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array) ;
    }
}

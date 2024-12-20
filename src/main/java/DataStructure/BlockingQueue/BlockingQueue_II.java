package DataStructure.BlockingQueue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现
 * @param <E> 元素类型
 */
@SuppressWarnings("all")
public class BlockingQueue_II<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
  //private int size;
    private AtomicInteger size = new AtomicInteger();  //为了size运算的线程安全，改为AtomicInteger

    public BlockingQueue_II(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock headLock = new ReentrantLock();      //锁对象 用来处理头部操作poll()
    private Condition headWaits = headLock.newCondition();     //条件变量对象 用来处理poll()中的线程阻塞
    private ReentrantLock tailLock = new ReentrantLock();      //锁对象 用来处理尾部操作offer()
    private Condition tailWaits = tailLock.newCondition();    //条件变量对象 用来处理offer()中的线程阻塞

    @Override
    public void offer(E e) throws InterruptedException {
        int c; //添加前的元素个数
        tailLock.lockInterruptibly();
        try{
            //1. 满则等待
            while(isFull()){
                tailWaits.await();
            }
            //2. 不满则入队
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            //3. 修改size

                /*
                size++;   因为两把锁可以同时进行，size++和size--存在线程不安全的问题
                 1. 读取成员变量size的值
                 2. 自增
                 3. 结果写回成员变量size
                */

            c = size.getAndIncrement();  //c = size, 然后size ++

            //4. 如果自增前+1还不满（即自增后还是不满 0->1, 1->2)，就唤醒等待不满的offer线程
            if(c+1 < array.length){
                tailWaits.signal();
            }

        }finally {
            tailLock.unlock();
        }

        //5. 如果元素由0个变成非空时(0->1)，唤醒等待非空的poll线程  注意，唤醒代码必须要被相应的锁包裹

        if(c == 0){
            headLock.lock();
            try{
                headWaits.signal();
            }finally {
                headLock.unlock();
            }
        }


    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        int c; //取走前的元素个数
        headLock.lockInterruptibly();
        try{
            //1. 空则等待
            while(isEmpty()){
                headWaits.await();
            }
            //2. 非空则出队
            e = array[head];
            array[head] = null; //help GC
            if(++head == array.length){
                head = 0;
            }
            //3. 修改size

              /*
               size--;   因为两把锁可以同时进行，size++和size--存在线程不安全的问题
               1. 读取成员变量size的值
               2. 自减
               3. 结果写回成员变量size
              */

            c = size.getAndDecrement();   //c = size 然后size--

            //4. 如果减少前的元素大于1（那么减少后也不会是空的, 3->2, 2->1），唤醒等待非空的poll线程
            if(c > 1){
                headWaits.signal();
            }

        }finally {
            headLock.unlock();
        }

        //5. 如果减少前的元素是满的（减少变成不满， 3->2）， 唤醒等待不满的offer线程  注意：唤醒代码必须要被相应的锁包裹
        if( c == array.length){
            tailLock.lock();
            try{
                tailWaits.signal();
            }finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    private boolean isEmpty(){
        return size.get() == 0;
    }

    private boolean isFull(){
        return size.get() == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array) ;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue_II<String> queue = new BlockingQueue_II<>(3);
        queue.offer("元素1");
        queue.offer("元素2");

        new Thread(() ->{
            try{
                queue.offer("元素3");
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        },"offer").start();

        new Thread(() ->{
            try{
                queue.poll();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        },"poll").start();

    }
}

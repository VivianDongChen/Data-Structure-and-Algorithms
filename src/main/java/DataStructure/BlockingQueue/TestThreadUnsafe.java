package DataStructure.BlockingQueue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

     /*
         问题：指令交错 - 结果有多种可能
                       tail
          [e2    null    null   null   null   null   null   null   null   null]
             0     1      2      3      4      5      6      7      8      9

             t1                             t2
             array[0] = e1
                                            array[0] = e2
                                            tail++
             tail++;


        解决方法：
        1. synchronized 关键字， 功能少
        2. ReentrantLock 工具类-可重入锁， 功能多
    */
    public class TestThreadUnsafe {
    private final String[] array = new String[10];
    private int tail = 0;
    private int size = 0;
    ReentrantLock lock = new ReentrantLock();  //锁对象
    Condition tailWaits = lock.newCondition();   //条件变量对象 集合

    public void offer(String e) throws InterruptedException {

//        lock.lock();  //加锁（不可以打断）
        lock.lockInterruptibly();  //加锁（可以在阻塞状态随时打断）
        try{
            while (isFull()){
                //满了该做的事， offer线程阻塞
                tailWaits.await();   //当前线程加入集合tailWaits，并且让此线程阻塞。   唤醒：tailWaits.signal()
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
        }finally {
            lock.unlock();  //解锁
        }

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadUnsafe queue = new TestThreadUnsafe();

        for (int i = 0; i < 10; i++) {
            queue.offer("e"+ i);
        }

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "添加元素之前");
                queue.offer("e10");
                System.out.println(Thread.currentThread().getName() + "添加元素成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1").start();

        new Thread(() ->{
            System.out.println("开始唤醒");
            try{
                queue.lock.lockInterruptibly();
                queue.tailWaits.signal();        //调用signal唤醒，之前也要加锁，之后也要解锁
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                queue.lock.unlock();
            }
        },"t2").start();
    }
}

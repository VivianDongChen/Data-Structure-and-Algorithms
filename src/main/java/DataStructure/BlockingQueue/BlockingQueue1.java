package DataStructure.BlockingQueue;

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
    public void offer(E e) throws InterruptedException {

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        return null;
    }
}
